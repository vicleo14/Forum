/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

package mx.ipn.escom.server;

import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.entity.User;
import mx.ipn.escom.sockets.MulticastS;

public class ServerThread implements Runnable {
	private MulticastS mtcs;
	private ForumsList forumsList;
	public ServerThread()
	{
		mtcs=new MulticastS();
	}
	public ServerThread(MulticastS mtcs)
	{
		this.mtcs=mtcs;
	}
	public ServerThread(MulticastS mtcs,ForumsList forumsList)
	{
		this.mtcs=mtcs;
		this.forumsList=forumsList;
	}
	@Override
	public void run()
	{
		try
		{
			for(;;)
			{
				mtcs.sendObject(forumsList);
				Thread.sleep(500);
			}
		}
		catch(Exception ex) 
		{
			System.out.println("MulticastThread in Client error:"+ex.toString());
			
		}

	}
	
}
