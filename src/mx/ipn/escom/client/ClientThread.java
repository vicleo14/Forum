/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */


package mx.ipn.escom.client;

import java.util.ArrayList;

import mx.ipn.escom.entity.ForumSummary;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import mx.ipn.escom.frames.tools.ForumsListModel;
import mx.ipn.escom.sockets.MulticastS;

public class ClientThread implements Runnable {
	private MulticastS mtcs;
	private ForumsList forumsList;
	private Client client;

	public ClientThread()
	{
		mtcs=new MulticastS();
	}
	public ClientThread(MulticastS mtcs)
	{
		this.mtcs=mtcs;
	}
	public ClientThread(MulticastS mtcs,ForumsList forumsList,Client client)
	{
		this.mtcs=mtcs;
		this.forumsList=forumsList;
		this.client=client;
		
	}
	@Override
	public void run()
	{
		try
		{
			
			for(;;)
			{
				Object obj=mtcs.receiveObject();
				if(obj instanceof ForumsList)
				{
					ForumsList fl=(ForumsList)obj;
					if(forumsList==null)
					{
						forumsList=fl;
						updateList();
					}
					else if(!forumsList.getTimestamp().equals(fl.getTimestamp()))
					{
						forumsList=fl;
						updateList();
					}
					
				}
			}

		}
		catch(Exception ex) 
		{
			System.out.println("MulticastThread in Client error:"+ex.toString());
			ex.printStackTrace();
			
		}
	}
	
	public void updateList()
	{
		System.out.println("Foros actualizados en lista.");
		ForumsListModel newflm=new ForumsListModel();
		for(int i=0;i<forumsList.getSize();i++)
		{
			newflm.addForumSummary(forumsList.getForumAt(i));
		}
		client.getJlstForums().setModel(newflm);
	}
}
