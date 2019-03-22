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

public class Comment extends Publication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer forumId;
	public Comment()
	{
		
	}
	public Comment(Integer forumId,Integer idComment,String user,String text,String image)
	{
		super(idComment,user,text,image);
		this.forumId=forumId;
		
	}
	public Comment(Integer forumId,Integer idComment,String user,String text)
	{
		super(idComment,user,text,null);
		this.forumId=forumId;
		
	}
	
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
