

package mx.ipn.escom.frames.tools;
import mx.ipn.escom.entity.ForumSummary;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ForumsListModel extends AbstractListModel
{
	private ArrayList<ForumSummary> localForumsList = new ArrayList<ForumSummary>();
	
	@Override
    public int getSize() {
        return localForumsList.size();
    }
 
    @Override
    public Object getElementAt(int index) {
        ForumSummary fs=localForumsList.get(index);
        String title=fs.getTitle()+"("+fs.getDate().toString()+")";
        return title;
    }
    	
    public void addForumSummary(ForumSummary fs){
      localForumsList.add(fs);
      //this.fireIntervalAdded(this, getSize(), getSize()+1);
     }
    public void deleteForumSummary(int index)
    {
        localForumsList.remove(index);
        this.fireIntervalRemoved(index, getSize(), getSize()+1);
     }
    public ForumSummary getForumSummaryByIndex(int index)
    {
    	return localForumsList.get(index);
    }

	public ArrayList<ForumSummary> getLocalForumsList() {
		return localForumsList;
	}

	public void setLocalForumsList(ArrayList<ForumSummary> localForumsList) {
		this.localForumsList = localForumsList;
	}
    
   
}

