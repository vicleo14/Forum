package mx.ipn.escom.client;
import mx.ipn.escom.sockets.MulticastS;
import mx.ipn.escom.entity.User;
public class Client {
	
	public static void main(String[] args) {
		System.out.println("Cliente en ejecución");
		MulticastS msc=new MulticastS("228.1.1.1",9999,true);
		try
		{
			for(;;)
			{
				Object obj=msc.receiveObject();
				if(obj instanceof User)
				{
					User user=(User)obj;
					System.out.println(user.toString());
				}
				else
					System.out.println(obj.toString());
			}
		}catch(Exception ex) 
		{}
	}
}
