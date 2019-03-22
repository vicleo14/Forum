/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

package mx.ipn.escom.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import com.mysql.jdbc.Driver;
	
public class Connector {
	private String url = "jdbc:mysql://localhost:3306/";
    private String base_datos = "FOROREDES";
    private String usuario = "vicleo14";
    private String driver = "com.mysql.jdbc.Driver";
    private String pass = "Vicleo_14";
    private Connection connectionD;
    private String noAccessToProcedure="?noAccessToProcedureBodies=true";
    
    public  Connector(){
    	
    }

    public void connect() throws SQLException {
        try
        {
            Class.forName(driver);
            connectionD=DriverManager.getConnection(url+base_datos+noAccessToProcedure,usuario,pass);
            
            System.out.println("Se ha conectado a la base de datos '"+base_datos+"' exitosamente.");
        } catch(Exception ex) {	
	            System.out.println("ERROR EN LA CONEXION:"+ex.toString());
	            ex.printStackTrace();
	            connectionD.close();
        }
        
    }
    
    public Connection getConnectionD() {
    	return connectionD;
    }

    public void disconnect() {
        try {
        	connectionD.close();
            
            System.out.println("Se ha desconectado de la base de datos '"+base_datos+"' exitosamente");
        } catch(Exception ex) {	
            System.out.println("ERROR AL CERRAR LA CONEXION\n"+ex);
        }
    }
    
   
}
