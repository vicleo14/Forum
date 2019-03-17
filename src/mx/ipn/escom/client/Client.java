package mx.ipn.escom.client;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.sockets.TcpClientSocket;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;

import javax.xml.stream.events.Comment;

import mx.ipn.escom.constants.TcpRequestName;
public class Client {
	
	public static void main(String[] args) {
		System.out.println("Cliente en ejecución");
		MulticastS msc=new MulticastS("228.1.1.1",9999,true);
		
		
		try
		{
			/**
			  for(;;)
			 
			{
				Object obj=msc.receiveObject();
				if(obj instanceof User)
				{
					User user=(User)obj;
					System.out.println(user.toString());
				}
				else
					System.out.println(obj.toString());
			}*///Implementacion del multicast
			
		
			
		}catch(Exception ex) 
		{}
	}
	
	public void newForum(Forum forum)
	{	TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
		try
		{
			tcpcs.sendObjec(TcpRequestName.NEW_FORUM);
			tcpcs.sendObjec(forum);
		}
		catch(Exception ex)
		{
			System.out.println("Error al eniar foro:"+ex.toString());
		}
	}
	
	public void newComment(Comment comment)
	{	
		try
		{
			TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
			tcpcs.sendObjec(TcpRequestName.NEW_COMMENT);
			tcpcs.sendObjec(comment);
			tcpcs.closeConection();
		}
		catch(Exception ex)
		{
			System.out.println("Error al eniar comentario:"+ex.toString());
		}
	}
	
	public Forum getForum(Integer idForum)
	{			
		Forum forum=null;
		try
		{
			TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
			tcpcs.sendObjec(TcpRequestName.GET_FORUM);
			tcpcs.sendObjec(idForum);
			forum =(Forum)tcpcs.readObject();
			tcpcs.closeConection();
		}
		catch(Exception ex)
		{
			System.out.println("Error al eniar comentario:"+ex.toString());
		}
		return forum;
	}
	public ForumsList lookForForum(String input)
	{	
		ForumsList forumsList=null;
		try
		{
			TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
			tcpcs.sendObjec(TcpRequestName.LOOK_FOR_FORUM);
			tcpcs.sendObjec(input);
			forumsList =(ForumsList)tcpcs.readObject();
			tcpcs.closeConection();
		}
		catch(Exception ex)
		{
			System.out.println("Error al eniar comentario:"+ex.toString());
		}
		return forumsList;
	}
	
	public Boolean authenticateUser(User user)
	{	
		Boolean bool=false; 
		try
		{
			TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
			tcpcs.sendObjec(TcpRequestName.AUTHENTICATE_USER);
			tcpcs.sendObjec(user);
			bool =(Boolean)tcpcs.readObject();
			tcpcs.closeConection();
		}
		catch(Exception ex)
		{
			System.out.println("Error al eniar comentario:"+ex.toString());
		}
		return bool;
	}
	
	
}
