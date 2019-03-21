package mx.ipn.escom.server;

import mx.ipn.escom.entity.User;
import mx.ipn.escom.sockets.MulticastS;

public class ServerThread implements Runnable {
	private MulticastS mtcs;
	
	public ServerThread()
	{
		mtcs=new MulticastS();
	}
	public ServerThread(MulticastS mtcs)
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
				System.out.println("Comienza envío de usuario");
				User user =new User("vicleo13","prueba");
				mtcs.sendObject(user);
				Thread.sleep(3000);
			}
		}
		catch(Exception ex) 
		{
			System.out.println("MulticastThread in Client error:"+ex.toString());
			
		}

	}
	
}
