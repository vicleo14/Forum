package mx.ipn.escom.server;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.entity.User;
public class Server {

	public static void main(String[] args) {
		System.out.println("Servidor en ejecución");
		MulticastS mss=new MulticastS("228.1.1.1",9999,true, 128);
		try
		{
			for(;;)
			{
				User user =new User("vicleo14","prueba");
				mss.sendObject(user);
				Thread.sleep(3000);
				
			}
		}catch(Exception ex) 
		{
			System.out.println("Error:"+ex.toString());
		}
	}

}
