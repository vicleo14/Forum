/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

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
import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.xml.stream.events.Comment;
import mx.ipn.escom.constants.TcpRequestName;

public class Client extends JMainWindow implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runnable ct;
	private MulticastS msc;
	
	private User user;
	private JNewForum jnf;
	
	private JLogIn jlog;
	private Forum forum;
	private ForumsList forumsList;
	
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
		msc=new MulticastS("228.1.1.1",9999,true);
		ct=new ClientThread(msc,forumsList);
		new Thread(ct).start();
	}	
	public void newForum(Forum forum)
	{	
		
		System.out.println("Invoc al método que conecta con socket. Descomentar código");
		System.out.println("Forum title:"+forum.getTitle());
		System.out.println("Forum info:"+forum.getText());
		System.out.println("Forum user:"+forum.getUser());
		setJnf(null);
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
		System.out.println("Invoca authenticateUser en Client.");
		this.setUser(user);
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
	    	  setJnf(new JNewForum(this));
	      }
	      
	      if(e.getSource().equals(btnAddComment))
	      {
	    	  Comment comment;
	    	  System.out.println("Boton add comment");
	      }
	      
	      if(e.getSource().equals(btnLoadImage))
	      {
	    	  System.out.println("Boton load image");
	    	  JFileChooser jfc=new JFileChooser();
	    	  int r=jfc.showOpenDialog(null);
	    	  if(r==JFileChooser.APPROVE_OPTION)
	    	  {
					File f=jfc.getSelectedFile();
					System.out.println("Leyo archivo");
	    	  }
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
	
	public JNewForum getJnf() {
		return jnf;
	}
	public void setJnf(JNewForum jnf) {
		this.jnf = jnf;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Cliente en ejecución");
		Client c;
		c = new Client();	
	}
	
}
