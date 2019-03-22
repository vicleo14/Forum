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
public class ForumsList implements Serializable{
	private ArrayList<ForumSummary> forumsSummary;
	private String timestamp;
	private static final long serialVersionUID = 1L;
	
	public ForumsList() 
	{
		this.forumsSummary=new ArrayList<ForumSummary>();
	};
	
	public void addForumSummary(ForumSummary fs)
	{
		forumsSummary.add(fs);
	}
	public int getSize()
	{
		return forumsSummary.size();
	}
	public ForumSummary getForumAt(int index)
	{
		return forumsSummary.get(index);
	}
	public void removeForumAt(int index)
	{
		forumsSummary.remove(index);
	}

	public ArrayList<ForumSummary> getForumsSummary() {
		return forumsSummary;
	}

	public void setForumsSummary(ArrayList<ForumSummary> forumsSummary) {
		this.forumsSummary = forumsSummary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString()
	{
		String s="[";
		for(int i=0;i<forumsSummary.size();i++)
		{
			ForumSummary fs=forumsSummary.get(i);
			s+="("+fs.getId()+","+fs.getTitle()+")\n";
		}
		s+="]";
		return s;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
