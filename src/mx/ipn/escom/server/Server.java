package mx.ipn.escom.server;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.sockets.TcpClientSocket;
import mx.ipn.escom.sockets.TcpServerSocket;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import mx.ipn.escom.mysql.Connector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;


import mx.ipn.escom.entity.Comment;

import mx.ipn.escom.constants.TcpRequestName;
public class Server {
	private TcpServerSocket tcpss;
	private MulticastS mss;
	private Runnable st;
	
	private Connector connector = new Connector();
	private Connection connectionD;
	
	
	
	
	public Server()
	{
		tcpss=new  TcpServerSocket();
		
		mss=new MulticastS("228.1.1.1",9999,true, 128);
		st=new ServerThread(mss);
		new Thread(st).start();
		beginListening();
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
						tcpss.sendObjec(bool);
					}
					else if(opc.equals(TcpRequestName.NEW_FORUM))
					{
						Forum forum=(Forum)tcpss.readObject();
						newForum(forum);
					}
					else if(opc.equals(TcpRequestName.NEW_COMMENT))
					{
						Comment comment=(Comment)tcpss.readObject();
						newComment(comment);
					}
					else if(opc.equals(TcpRequestName.GET_FORUM))
					{
						
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
		}
	}
	public void newForum(Forum forum)
	{	
		System.out.println("Invoca al método newForum que conecta con Server.");
		System.out.println("Forum id: "+forum.getIdPub());
		System.out.println("Forum title: "+forum.getTitle());
		System.out.println("Forum info: "+forum.getText());
		System.out.println("Forum user: "+forum.getUser());
		System.out.println("Forum image: "+forum.getImage());
		System.out.println("Forum date: "+forum.getDate());
		try {
			connector.connect();
			connectionD = connector.getConnectionD();
			CallableStatement statement = connectionD.prepareCall("{CALL insertPub(?,?,?,?,?,?)}");
			statement.setInt(1, forum.getIdPub());
			statement.setString(2, forum.getTitle());
			statement.setString(3, forum.getText());
			statement.setString(4, forum.getImage());
			statement.setDate(5, new Date(forum.getDate().getTime()));
			statement.setString(6, forum.getUser());
			ResultSet rs = statement.executeQuery();
			System.out.println("The forum has been saved.");
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
	}
	
	public void newComment(Comment comment)
	{	
		System.out.println("Invoca al mÃ©todo newComment que conecta con Server.");
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
			CallableStatement statement = connectionD.prepareCall("{CALL publication(?,?,?,?,?,?,?,?,?,?)}");
			statement.setInt(1, idForum);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) 
			{
				Comment comment = new Comment(rs.getInt(1), rs.getInt(6), rs.getString(10), rs.getString(8), rs.getString(9));				
			}
			rs.previous();
			forum = new Forum(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(3), rs.getString(4), rs.getDate(5));
			
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
		System.out.println("Invoca authenticateUser en Client. Descomentar para conectar con socket");
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
				bool = true;
			else
				bool = false;
			System.out.println("The user exists.");
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
