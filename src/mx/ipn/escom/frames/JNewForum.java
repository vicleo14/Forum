package mx.ipn.escom.frames;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import javax.swing.JEditorPane;
public class JNewForum extends JFrame  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitle;
	private JEditorPane jepInfo;
	private JLabel lblTitle;
	private JButton btnPost;
	private JButton btnImage;
	private JPanel jpSouth;
	private JPanel jpNorth;
	
	private final int width=400;
	private final int height=500;
	
	public JNewForum()
	{
		super("New forum");
		init();
	}
	public void init()
	{
		txtTitle=new JTextField();
		btnPost=new JButton("Post");
		btnImage=new JButton("Search image");
		btnPost.addActionListener(this);
		btnImage.addActionListener(this);
		lblTitle=new JLabel("Title");
		jepInfo=new JEditorPane();
		jpNorth=new JPanel(new GridLayout(1,2));
		jpNorth.add(lblTitle);
		jpNorth.add(txtTitle);
		
		jpSouth=new JPanel(new GridLayout(2,1));
		jpSouth.add(btnImage);
		jpSouth.add(btnPost);
		
		this.setLayout(new BorderLayout());
		this.add(jpSouth,BorderLayout.SOUTH);
		this.add(jpNorth,BorderLayout.NORTH);
		this.add(jepInfo,BorderLayout.CENTER);
		
		this.setSize(width, height);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void actionPerformed (ActionEvent e)
	{
	      if(e.getSource().equals(btnPost))
	      {
	    	  System.out.println("Se generó evento con POST");
	      }
	}

}
