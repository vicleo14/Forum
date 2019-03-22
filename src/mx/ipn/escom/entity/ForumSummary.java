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
import java.util.Date;
public class ForumSummary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Date date;
	public ForumSummary()
	{
		this.id=-1;
		this.title="";
		this.date=new Date();
	}
	public ForumSummary(Integer id,String title,Date date)
	{
		this.id=id;
		this.title=title;
		this.date=date;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
