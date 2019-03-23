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

public class JMainWindow extends JFrame  implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField txtSearch;
	protected JButton btnSearch;
	protected JButton btnNewForum;
	protected JButton btnAddComment;
	protected JButton btnLoadImage;
	protected JPanel jpSouth;
	protected JPanel jpSouth1;
	protected JPanel jpSouth2;
	protected JPanel jpSouth3;
	protected JEditorPane jepForum;
	protected JEditorPane jepComment;
	protected JList jlstForums;
	protected DefaultListModel model;
	protected JScrollPane jspForums;
	protected JScrollPane jspSelectedForum;
	protected JScrollPane jspComment;

	protected final int width=900;
	protected final int height=600;
	public JMainWindow()
	{
		super("Forum");

	}

	public JMainWindow(String userName)
	{
		super("Forum("+userName+")");
		init();

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

		

		jlstForums = new JList();
		jlstForums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

		//instanciamos el modelo
		model = new DefaultListModel();

		//instanciamos el Scroll que tendra la lista
		jspForums = new JScrollPane();
		jspForums.setBounds(20, 120,220, 80);
		jspForums.setViewportView(jlstForums);
		
		jspSelectedForum=new JScrollPane();
		jspSelectedForum.setViewportView(jepForum);
		jspComment=new JScrollPane();
		jspComment.setViewportView(jepComment);

		jpSouth1=new JPanel(new GridLayout(3,1));
		jpSouth1.add(txtSearch);
		jpSouth1.add(btnSearch);
		jpSouth1.add(btnNewForum);


		jpSouth2=new JPanel(new GridLayout(1,2));
		jpSouth2.add(btnLoadImage);
		jpSouth2.add(btnAddComment);

		jpSouth3=new JPanel(new BorderLayout());
		jpSouth3.add(jspComment,BorderLayout.CENTER);
		jpSouth3.add(jpSouth2,BorderLayout.SOUTH);

		jpSouth=new JPanel(new GridLayout());
		jpSouth.add(jpSouth1,BorderLayout.CENTER);
		jpSouth.add(jpSouth3,BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());

		this.add(jpSouth,BorderLayout.SOUTH);
		this.add(jspSelectedForum,BorderLayout.CENTER);
		this.add(jspForums,BorderLayout.WEST);
		this.setSize(width, height);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void view()
	{
		this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{

	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(JTextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnNewForum() {
		return btnNewForum;
	}

	public void setBtnNewForum(JButton btnNewForum) {
		this.btnNewForum = btnNewForum;
	}

	public JButton getBtnAddComment() {
		return btnAddComment;
	}

	public void setBtnAddComment(JButton btnAddComment) {
		this.btnAddComment = btnAddComment;
	}

	public JButton getBtnLoadImage() {
		return btnLoadImage;
	}

	public void setBtnLoadImage(JButton btnLoadImage) {
		this.btnLoadImage = btnLoadImage;
	}

	public JPanel getJpSouth() {
		return jpSouth;
	}

	public void setJpSouth(JPanel jpSouth) {
		this.jpSouth = jpSouth;
	}

	public JPanel getJpSouth1() {
		return jpSouth1;
	}

	public void setJpSouth1(JPanel jpSouth1) {
		this.jpSouth1 = jpSouth1;
	}

	public JPanel getJpSouth2() {
		return jpSouth2;
	}

	public void setJpSouth2(JPanel jpSouth2) {
		this.jpSouth2 = jpSouth2;
	}

	public JPanel getJpSouth3() {
		return jpSouth3;
	}

	public void setJpSouth3(JPanel jpSouth3) {
		this.jpSouth3 = jpSouth3;
	}

	public JEditorPane getJepForum() {
		return jepForum;
	}

	public void setJepForum(JEditorPane jepForum) {
		this.jepForum = jepForum;
	}

	public JEditorPane getJepComment() {
		return jepComment;
	}

	public void setJepComment(JEditorPane jepComment) {
		this.jepComment = jepComment;
	}

	public JList getJlstForums() {
		return jlstForums;
	}

	public void setJlstForums(JList jlstForums) {
		this.jlstForums = jlstForums;
	}

	public DefaultListModel getModel() {
		return model;
	}

	public void setModel(DefaultListModel model) {
		this.model = model;
	}

	public JScrollPane getJspForums() {
		return jspForums;
	}

	public void setJspForums(JScrollPane jspForums) {
		this.jspForums = jspForums;
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
