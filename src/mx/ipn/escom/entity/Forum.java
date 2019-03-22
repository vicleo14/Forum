/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

package mx.ipn.escom.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Forum extends Publication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;
	private List<Comment> comments;
	public Forum() 
	{
		comments=new ArrayList<Comment>();
	}

	public Forum(Integer idForum,String title,String user,String text,String image,Date date) 
	{
		super(idForum,user,text,image);
		this.date=date;
		this.title=title;
		comments=new ArrayList<Comment>();
		
	}
	public Forum(Integer idForum,String title,String user) 
	{
		super(idForum,user,null,null);//La imagen es nula
		this.date=new Date();;
		this.title=title;
		comments=new ArrayList<Comment>();
		
	}
	
	public Forum(String title,String info) 
	{
		super(null,null,info,null);//La imagen es nula
		this.date=new Date();;
		this.title=title;
		comments=new ArrayList<Comment>();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
