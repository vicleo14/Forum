/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

package mx.ipn.escom.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mx.ipn.escom.client.Client;
import mx.ipn.escom.entity.User;

public class JLogIn extends JFrame  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JTextField jtfUser;
	private JPasswordField jpfPswd;
	private final int width=300;
	private final int height=130;
	private Client root;
	
	public JLogIn(Client root)
	{
		super("Log In");
		this.root=root;	
		init();
	}
	public void init()
	{
		lblUser=new JLabel("User:");
		lblPassword=new JLabel("Password:");
		btnAceptar=new JButton("Aceptar");
		btnCancelar=new JButton("Cancelar");
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		jtfUser=new JTextField();
		jpfPswd=new JPasswordField();
		
		this.setLayout(new GridLayout(3, 2));
		this.add(lblUser);
		this.add(jtfUser);
		this.add(lblPassword);
		this.add(jpfPswd);
		this.add(btnAceptar);
		this.add(btnCancelar);
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e)
	{
	      if(e.getSource().equals(btnCancelar))
	    	  System.exit(0);
	      else if (e.getSource().equals(btnAceptar))
	      {
	    	  String nickName=jtfUser.getText();
	    	  String password=jpfPswd.getText();
	    	  User user=new User(nickName,password);  
	    	  Boolean exists=root.authenticateUser(user);
	    	  if(exists.equals(true))
	    	  {
	    		  this.setVisible(false);
	    		  root.view();
	    	  }
	    	  else
	    	  {
	    		  JOptionPane.showMessageDialog(root, "El usuario ingresado no existe","Error fatal",JOptionPane.ERROR_MESSAGE);
	    		  System.exit(-1);
	    	  }
	    	  
	    	  
	      }
	      
	}
	

}
