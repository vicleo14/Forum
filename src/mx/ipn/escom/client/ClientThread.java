/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */


/*
 * TODO:
 * Implementar el codigo necesario para que la lista lateral izquierda de la GUI
 * principal sea actualizada al recibir una modificacion.
 * */
package mx.ipn.escom.client;

import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
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
		for(int i=0;i<forumsList.getSize();i++)
		{
			System.out.println("topic:"+forumsList.getForumAt(i).getTitle());
	    	//System.out.println("value:"+i);
			//client.getModelList().addForumSummary(forumsList.getForumAt(i));
		}
	}
}
