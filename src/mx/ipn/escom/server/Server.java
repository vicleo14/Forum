package mx.ipn.escom.server;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.sockets.TcpClientSocket;
import mx.ipn.escom.sockets.TcpServerSocket;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mx.ipn.escom.entity.Comment;

import mx.ipn.escom.constants.TcpRequestName;
public class Server {
	private TcpServerSocket tcpss;
	
	public Server()
	{
		tcpss=new  TcpServerSocket();
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
		System.out.println("Forum title:"+forum.getTitle());
		System.out.println("Forum info:"+forum.getText());
		System.out.println("Forum user:"+forum.getUser());
		/*Insertar foro en esta linea de codigo con BD*/
	}
	
	public void newComment(Comment comment)
	{	
		System.out.println("Invoca al método newComment que conecta con Server.");
		System.out.println("Forum :"+comment.getForumId());
		System.out.println("User:"+comment.getUser());
		System.out.println("Text:"+comment.getText());
		/*Insertar comentario a BD*/
	}
	
	public Forum getForum(Integer idForum)
	{			
		Forum forum=null;
		/*Crear objeto Forum con todos los comentarios que hay en la BD*/
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
		/*Verificar usuario en esta linea de codigo con BD*/
		return bool;
	}
	public static void main(String[] args) {
		System.out.println("Servidor en ejecución");
		Server server=new Server();
		
		/*MulticastS mss=new MulticastS("228.1.1.1",9999,true, 128);
		try
		{
			for(;;)
			{
				User user =new User("vicleo14","prueba");
				mss.sendObject(user);
				Thread.sleep(3000);
				
			}
		}catch(Exception ex) 
		{
			System.out.println("Error:"+ex.toString());
		}*/
	}

}
