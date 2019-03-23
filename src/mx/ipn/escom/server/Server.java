/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */
/*TODO: Escribir imagen en una carpeta basado en la fecha en la que se subio con un nombre unico
 * 
 * Escribir la ruta de la imagen con el nuevo nombre al objeto Forum que recibe por newForum(...)
 * 
 * Realizar la peticion a la BD de los comentarios cuando solicitan un foro.
 * 
 * */


package mx.ipn.escom.server;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.sockets.TcpClientSocket;
import mx.ipn.escom.sockets.TcpServerSocket;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumSummary;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import mx.ipn.escom.mysql.Connector;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.ipn.escom.entity.Comment;

import mx.ipn.escom.constants.TcpRequestName;
public class Server {
	private TcpServerSocket tcpss;
	private MulticastS mss;
	private Runnable st;
	private final String path="server_images/";
	private Connector connector = new Connector();
	private Connection connectionD;
	
	private ForumsList forumsList=new ForumsList();
	
	
	public Server()
	{
		
		tcpss=new  TcpServerSocket();
		mss=new MulticastS("228.1.1.1",9999,true, 128);
		forumsList=new ForumsList();
		this.loadForumsFromDB();
		st=new ServerThread(mss,forumsList);
		new Thread(st).start();
		
		createDirectory(path);
		beginListening();
	}
	public void createDirectory(String dirName)
	{
		File dir=new File(dirName);
		if(!dir.exists())
			dir.mkdir();
	}
	public void loadForumsFromDB()
	{
		try 
		{
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL publications()}");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) 
			{	
				ForumSummary fs=new ForumSummary(rs.getInt(1),rs.getString(2),rs.getDate(3));	
				forumsList.addForumSummary(fs);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			String timestamp=sdf.format(new Date());
			forumsList.setTimestamp(timestamp);
			
			System.out.println("Info loaded from database.");
		}
		catch (SQLException ex) {
			System.out.println("ERROR LOADING INFO:"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	public void beginListening()
	{
		try
		{
			for(;;)
			{
				tcpss.getPetition();
				System.out.println("Ejecuta hasta aqui");
				Object receivedObject=tcpss.readObject();
				if(receivedObject instanceof Integer)
				{
					Integer opc=(Integer)receivedObject;
					if (opc.equals(TcpRequestName.AUTHENTICATE_USER)) 
					{
						User user=(User)tcpss.readObject();
						Boolean bool=authenticateUser(user);
						tcpss.sendObject(bool);
					}
					else if(opc.equals(TcpRequestName.NEW_FORUM))
					{
						String filename;
						Forum forum=(Forum)tcpss.readObject();
						Boolean hasImage=(Boolean)tcpss.readObject();
						
						System.out.println("Has image?"+hasImage);
						if(hasImage)
						{
							SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
							SimpleDateFormat sdf2=new SimpleDateFormat("yyMMddHHmmss");
							String folder=path+"imgs"+sdf.format(new Date());
							createDirectory(folder);
							String imageName=forum.getUser()+sdf2.format(new Date());
							filename=tcpss.readFile(folder,imageName);
							forum.setImage(filename);
							System.out.println("File name:"+filename);
						}
						
						newForum(forum);
					}
					else if(opc.equals(TcpRequestName.NEW_COMMENT))
					{
						String filename;
						Comment comment=(Comment)tcpss.readObject();
						Boolean hasImage=(Boolean)tcpss.readObject();
						
						System.out.println("Has image?"+hasImage);
						if(hasImage)
						{
							SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
							SimpleDateFormat sdf2=new SimpleDateFormat("yyMMddHHmmss");
							String folder=path+"imgs"+sdf.format(new Date());
							createDirectory(folder);
							String imageName=comment.getUser()+sdf2.format(new Date());
							filename=tcpss.readFile(folder,imageName);
							comment.setImage(filename);
							System.out.println("File name:"+filename);
						}
						
						
						
						newComment(comment);
						
					}
					else if(opc.equals(TcpRequestName.GET_FORUM))
					{
						
						Integer id=(Integer)tcpss.readObject();
						System.out.println("Ingresa a get_forum:"+id);
						Forum f=getForum(id);
						tcpss.sendObject(f);
						
					}
					else if(opc.equals(TcpRequestName.LOOK_FOR_FORUM))
					{
						
					}
					else
					{
						System.out.println("Valor obtenido:"+opc);
					}
				}
				else
				{
					System.out.println("No es integer");
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error en Server:"+ex.toString());
			ex.printStackTrace();
		}
	}
	public void newForum(Forum forum)
	{	
		System.out.println("Invoca al método newForum que conecta con Server.");
		try {
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL insertPub(?,?,?,?,?)}");
			statement.setString(1, forum.getTitle());
			statement.setString(2, forum.getText());
			statement.setString(3, forum.getImage());
			statement.setDate(4, new java.sql.Date(forum.getDate().getTime()));//Revisar cambios
			statement.setString(5, forum.getUser());
			ResultSet rs = statement.executeQuery();
			System.out.println("The forum has been saved.");
			forumsList.getForumsSummary().clear();
			this.loadForumsFromDB();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
	}
	
	public void newComment(Comment comment)
	{	
		System.out.println("Invoca al método newComment que conecta con Server.");
		System.out.println("Forum :"+comment.getForumId());
		System.out.println("User:"+comment.getUser());
		System.out.println("Text:"+comment.getText());
		try {
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL insertCom(?,?,?,?)}");
			statement.setString(1, comment.getText());
			statement.setString(2, comment.getImage());
			statement.setString(3, comment.getUser());
			statement.setInt(4, comment.getForumId());
			ResultSet rs = statement.executeQuery();
			System.out.println("The comment has been saved.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public Forum getForum(Integer idForum)
	{			
		Forum forum=null;
		
		try 
		{
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL infoPub(?)}");
			statement.setInt(1, idForum);
			ResultSet rs = statement.executeQuery();
			if(rs.next()!=false)
				forum=new Forum(rs.getInt("idPublic"), rs.getString("nombre"), rs.getString("nickName"), rs.getString("info"), rs.getString("imagen"), rs.getDate("fecha"));
			
			CallableStatement statement2 = connectionD.prepareCall("{CALL commentsPub(?)}");
			statement2.setInt(1, idForum);
			ResultSet rs2 = statement2.executeQuery();
			while(rs2.next()) 
			{
				System.out.println("Comentario nuevo");
				Comment comment = new Comment(rs2.getInt("idPublic"),rs2.getInt("idComent"),rs2.getString("nickName"),rs2.getString("info"),rs2.getString("imagen"));
				System.out.println("C:"+comment.getText());
				forum.getComments().add(comment);
			}			
			System.out.println("Forum information has been extracted from de database.");
		}
		catch (SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		return forum;
	}
	public ForumsList lookForForum(String input)
	{	
		ForumsList forumsList=null;
		
		
		
		return forumsList;
	}
	
	public Boolean authenticateUser(User user)
	{	
		//System.out.println("Invoca authenticateUser en Client. Descomentar para conectar con socket");
		System.out.println("User:"+user.getNickName());
		System.out.println("Password:"+user.getPassword());
		Boolean bool=true; 
		try 
		{
			connector.connect();
			connectionD = connector.getConnectionD();
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL userValid(?,?)}");
			statement.setString(1, user.getNickName());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				System.out.println("The user exists.");
				bool = true;
			}
			else
			{
				System.out.println("The user doesn't exists.");
				bool = false;
			}			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return bool;
	}
	
	public static void main(String[] args) {
		System.out.println("Servidor en ejecución");
		Server server=new Server();
		
		/*MulticastS mss=new MulticastS("228.1.1.1",9999,true, 128);
		*/
	}

}
