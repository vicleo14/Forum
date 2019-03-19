package mx.ipn.escom.client;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.sockets.TcpClientSocket;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import mx.ipn.escom.frames.JLogIn;
import mx.ipn.escom.frames.JMainWindow;
import mx.ipn.escom.frames.JNewForum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.xml.stream.events.Comment;
import mx.ipn.escom.constants.TcpRequestName;

public class Client extends JMainWindow implements ActionListener{
	
	private User user;
	private JNewForum jnf;
	
	private JLogIn jlog;
	private Forum forum;
	public Client()
	{
		super();
		init();
		setListeners();
		forum=new Forum();
		forum.setDate(new Date());
		forum.setIdPub(-1);
		forum.setText("No se ha seleccionado ningún foro");
		
		jlog=new JLogIn(this);
		System.out.println("Termina init");
	}	
	public void newForum(Forum forum)
	{	
		
		System.out.println("Invoc al método que conecta con socket. Descomentar código");
		System.out.println("Forum title:"+forum.getTitle());
		System.out.println("Forum info:"+forum.getText());
		System.out.println("Forum user:"+forum.getUser());
		jnf=null;
		TcpClientSocket tcpcs=new TcpClientSocket("127.0.0.1",1234);
		try
		{
			tcpcs.sendObjec(TcpRequestName.NEW_FORUM);
			tcpcs.sendObjec(forum);
			tcpcs.closeConection();
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
	/*Revisar viabilidad de método*/
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
		System.out.println("Invoca authenticateUser en Client. Descomentar para conectar con socket");
		this.setUser(user);
		view();
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
			System.out.println("Error al enviar comentario:"+ex.toString());
		}
		return bool;
	}
	public void setListeners()
	{
		btnSearch.addActionListener(this);
		btnNewForum.addActionListener(this);
		btnAddComment.addActionListener(this);
		btnLoadImage.addActionListener(this);
	}
	public void actionPerformed (ActionEvent e)
	{
	      if(e.getSource().equals(btnSearch))
	      {
	    	  System.out.println("Boton search");
	      }
	      
	      if(e.getSource().equals(btnNewForum))
	      {
	    	  jnf=new JNewForum(this);
	      }
	      
	      if(e.getSource().equals(btnAddComment))
	      {
	    	  Comment comment;
	    	  System.out.println("Boton add comment");
	      }
	      
	      if(e.getSource().equals(btnLoadImage))
	      {
	    	  System.out.println("Boton load image");
	      }
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		this.setTitle("Forum("+user.getNickName()+")");
		System.out.println("User:"+user.getNickName());
	}
	public JLogIn getJlog() {
		return jlog;
	}
	public void setJlog(JLogIn jlog) {
		this.jlog = jlog;
	}
	public static void main(String[] args) {
		System.out.println("Cliente en ejecución");
		//MulticastS msc=new MulticastS("228.1.1.1",9999,true);
		Client c=new Client();
		
		/**try
		{
			
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
			}
			
		
			
		}catch(Exception ex) 
		{}*///Implementacion del multicast
	}
	
	
	
}
