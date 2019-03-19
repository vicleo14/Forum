package mx.ipn.escom.frames;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JLogIn extends JFrame{
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
	
	
	public JLogIn()
	{
		super("Log In");
		init();
	}
	public void init()
	{
		lblUser=new JLabel("User:");
		lblPassword=new JLabel("Password:");
		btnAceptar=new JButton("Aceptar");
		btnCancelar=new JButton("Cancelar");
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

}
