

package mx.ipn.escom.frames.tools;
import mx.ipn.escom.entity.ForumSummary;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ForumsListModel extends AbstractListModel{
	private ArrayList<ForumSummary> localForumsList = new ArrayList<ForumSummary>();
	
	@Override
    public int getSize() {
        return localForumsList.size();
    }
 
    @Override
    public Object getElementAt(int index) {
        ForumSummary fs=localForumsList.get(index);
        return fs;
    }
    	
    public void addForumSummary(ForumSummary fs){
      localForumsList.add(fs);
      this.fireIntervalAdded(this, getSize(), getSize()+1);
     }
}

