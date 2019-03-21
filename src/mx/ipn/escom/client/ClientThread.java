package mx.ipn.escom.client;

import mx.ipn.escom.entity.User;
import mx.ipn.escom.sockets.MulticastS;

public class ClientThread implements Runnable {
	private MulticastS mtcs;

	public ClientThread()
	{
		mtcs=new MulticastS();
	}
	public ClientThread(MulticastS mtcs)
	{
		this.mtcs=mtcs;
	}
	@Override
	public void run()
	{
		try
		{
			
			for(;;)
			{
				Object obj=mtcs.receiveObject();
				if(obj instanceof User)
				{
					User user=(User)obj;
					System.out.println(user.toString());
				}
				else
					System.out.println(obj.toString());
			}

		}
		catch(Exception ex) 
		{
			System.out.println("MulticastThread in Client error:"+ex.toString());
			
		}
	}
}
