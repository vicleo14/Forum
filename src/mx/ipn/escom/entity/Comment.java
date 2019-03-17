/*
 * 
 */

package mx.ipn.escom.entity;

import java.io.Serializable;

public class Comment extends Publication implements Serializable{
	public Comment()
	{
		
	}
	public Comment(Integer idComment,String user,String text,String image)
	{
		super(idComment,user,text,image);
		
	}
	public Comment(Integer idComment,String user,String text)
	{
		super(idComment,user,text,null);
		
	}

}
