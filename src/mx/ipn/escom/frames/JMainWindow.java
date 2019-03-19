package mx.ipn.escom.frames;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.border.BevelBorder;


import javax.swing.JEditorPane;

public class JMainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnNewForum;
	private JButton btnAddComment;
	private JButton btnLoadImage;
	private JPanel jpSouth;
	private JPanel jpSouth1;
	private JPanel jpSouth2;
	private JPanel jpSouth3;
	private JEditorPane jepForum;
	private JEditorPane jepComment;
	private JList jlstForums;
	private DefaultListModel model;
	private JScrollPane jspForums;	
	
	private final int width=700;
	private final int height=300;
	public JMainWindow()
	{
		super("Forum");
		init();
		String s="<h1>Hola</h1><br /> <h3>Cool</h3>";	
		jepForum.setText(s);
	
	}
	
	public JMainWindow(String userName)
	{
		super("Forum("+userName+")");
		init();
		String s="<h1>Hola</h1><br /> <h3>Cool</h3>";	
		jepForum.setText(s);
	
	}
	public void init()
	{
		btnSearch=new JButton("Search");
		btnNewForum=new JButton("New Forum");
		btnAddComment=new JButton("Add comment");
		btnLoadImage=new JButton("Search image");
		
		jepForum=new JEditorPane();
		jepComment=new JEditorPane();
		jepForum.setContentType("text/html");
		jepComment.setContentType("text/html");
		jepForum.setBorder(new BevelBorder(BevelBorder.LOWERED));
		jepComment.setBorder(new BevelBorder(BevelBorder.LOWERED));
		txtSearch=new JTextField();
		
		jpSouth1=new JPanel(new GridLayout(3,1));
		jpSouth1.add(txtSearch);
		jpSouth1.add(btnSearch);
		jpSouth1.add(btnNewForum);
		
		
		jpSouth2=new JPanel(new GridLayout(1,2));
		jpSouth2.add(btnLoadImage);
		jpSouth2.add(btnAddComment);
		
		jpSouth3=new JPanel(new BorderLayout());
		jpSouth3.add(jepComment,BorderLayout.CENTER);
		jpSouth3.add(jpSouth2,BorderLayout.SOUTH);
		
		jpSouth=new JPanel(new GridLayout());
		jpSouth.add(jpSouth1,BorderLayout.CENTER);
		jpSouth.add(jpSouth3,BorderLayout.EAST);
		
		
		
		jlstForums = new JList();
		jlstForums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		   
		//instanciamos el modelo
		model = new DefaultListModel();
		      
		//instanciamos el Scroll que tendra la lista
		jspForums = new JScrollPane();
		jspForums.setBounds(20, 120,220, 80);
		jspForums.setViewportView(jlstForums);
		
		
		this.setLayout(new BorderLayout());
		
		this.add(jpSouth,BorderLayout.SOUTH);
		this.add(jepForum,BorderLayout.CENTER);
		this.add(jspForums,BorderLayout.WEST);
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}
	

}