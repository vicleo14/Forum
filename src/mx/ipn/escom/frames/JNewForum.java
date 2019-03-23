/*
 * Author: Morales Flores Victor Leonel
 * Author: Ortiz Rivas Julio Cesar
 * ESCOM-IPN(MX)
 * Date:
 * Description:
 * 
 */

package mx.ipn.escom.frames;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mx.ipn.escom.client.Client;
import mx.ipn.escom.entity.Forum;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
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
	private Forum forum;
	
	private Client root;
	private final int width=400;
	private final int height=500;
	private File imageFile;
	
	public JNewForum(Client root)
	{
		super("New forum");
		this.root=root;
		forum=new Forum();
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
		jepInfo.setContentType("text/html");
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
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void actionPerformed (ActionEvent e)
	{
	      if(e.getSource().equals(btnPost))
	      {
	    	  String title=txtTitle.getText();
	    	  String info=jepInfo.getText();
	    	  int indexIni=info.indexOf("<body>")+7;
	    	  int indexEnd=info.indexOf("</body>");
	    	  info=info.substring(indexIni, indexEnd);
	    	  forum=new Forum(null,title,root.getUser().getNickName(),info,"",new Date());
	    	  this.setVisible(false);
	    	  root.newForum(forum,imageFile);
	    	  
	      }
	      if(e.getSource().equals(btnImage))
	      {
	    	  System.out.println("Boton load image");
	    	  JFileChooser jfc=new JFileChooser();
	    	  int r=jfc.showOpenDialog(this);
	    	  if(r==JFileChooser.APPROVE_OPTION)
	    	  {
					imageFile=jfc.getSelectedFile();
	    	  }
	      }
	      
	}
	public JTextField getTxtTitle() {
		return txtTitle;
	}
	public void setTxtTitle(JTextField txtTitle) {
		this.txtTitle = txtTitle;
	}
	public JEditorPane getJepInfo() {
		return jepInfo;
	}
	public void setJepInfo(JEditorPane jepInfo) {
		this.jepInfo = jepInfo;
	}
	public JLabel getLblTitle() {
		return lblTitle;
	}
	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}
	public JButton getBtnPost() {
		return btnPost;
	}
	public void setBtnPost(JButton btnPost) {
		this.btnPost = btnPost;
	}
	public JButton getBtnImage() {
		return btnImage;
	}
	public void setBtnImage(JButton btnImage) {
		this.btnImage = btnImage;
	}
	public JPanel getJpSouth() {
		return jpSouth;
	}
	public void setJpSouth(JPanel jpSouth) {
		this.jpSouth = jpSouth;
	}
	public JPanel getJpNorth() {
		return jpNorth;
	}
	public void setJpNorth(JPanel jpNorth) {
		this.jpNorth = jpNorth;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
}
