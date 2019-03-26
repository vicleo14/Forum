package mx.ipn.escom.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mx.ipn.escom.client.Client;
import mx.ipn.escom.entity.Forum;
import mx.ipn.escom.entity.ForumSummary;
import mx.ipn.escom.entity.ForumsList;
import mx.ipn.escom.frames.tools.ForumsListModel;

public class JSearch implements ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jpSList, jpOptions;
	private JScrollPane jspSForums;
	private JFrame jfSearch;
	private JList jlstSForums;
	private JButton btnReturn;
	private JLabel lblInfo;
	private ForumsList searchForums;
	private DefaultListModel model;
	private final int widthS = 300, heightS = 200;
	private DefaultListModel<ForumSummary> modelList;
	private Forum forum;
	private Client client = null;
	
	
	public JSearch() {
		jfSearch = new JFrame("Search");
	}
	
	public JSearch(String input, ForumsList searchForums, Client client)
	{
		jfSearch = new JFrame("Search: "+input);
		setSearchForums(searchForums);
		this.client = client;
		init();
		System.out.println("this.searchForums.getSize() = "+this.searchForums.getSize()+".");
		modelList=new DefaultListModel<ForumSummary>();
		ForumsListModel newflm=new ForumsListModel();
		for(int i=0;i<this.searchForums.getSize();i++)
		{
			newflm.addForumSummary(this.searchForums.getForumAt(i));
			System.out.println("Si entra: "+i+".");
		}
		jlstSForums.setModel(newflm);
		jlstSForums.addListSelectionListener(this);
	}
	
	public void init() {
		btnReturn = new JButton("Return");
		
		jlstSForums = new JList();
		jlstSForums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		
		//instanciamos el modelo
		setModel(new DefaultListModel());

		//instanciamos el Scroll que tendra la lista
		jspSForums = new JScrollPane();
		jspSForums.setBounds(0, 0,300, 200);
		jspSForums.setViewportView(jlstSForums);
		
		jfSearch.add(jspSForums,BorderLayout.CENTER);
		jfSearch.setLayout(new BorderLayout());
		jfSearch.setSize(widthS, heightS);
		jfSearch.setResizable(false);
		jfSearch.setLocationRelativeTo(null);
		jfSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jfSearch.setVisible(true);
	}

	public void valueChanged(ListSelectionEvent e) {
		int index=jlstSForums.getSelectedIndex();
		System.out.println("Index: "+index);
		ForumsListModel flm=(ForumsListModel)jlstSForums.getModel();
		ForumSummary fs=flm.getForumSummaryByIndex(index);
		System.out.println("Id: "+fs.getId());
		forum = client.getForum(fs.getId());
		client.loadForum(forum);
		client.getJlstForums().setSelectedIndex(fs.getId()-1);
		jfSearch.setVisible(false);
		
	}
	
	public JPanel getJpSList() {
		return jpSList;
	}
	public void setJpSList(JPanel jpSList) {
		this.jpSList = jpSList;
	}
	public JScrollPane getJspSForums() {
		return jspSForums;
	}
	public void setJspSForums(JScrollPane jspSForums) {
		this.jspSForums = jspSForums;
	}
	public JPanel getJpOptions() {
		return jpOptions;
	}
	public void setJpOptions(JPanel jpOptions) {
		this.jpOptions = jpOptions;
	}


	public JButton getbtnReturn() {
		return btnReturn;
	}


	public void setbtnReturn(JButton btnRegresar) {
		this.btnReturn = btnRegresar;
	}


	public JLabel getLblInfo() {
		return lblInfo;
	}


	public void setLblInfo(JLabel lblInfo) {
		this.lblInfo = lblInfo;
	}

	public ForumsList getSearchForums() {
		return searchForums;
	}

	public void setSearchForums(ForumsList searchForums) {
		this.searchForums = searchForums;
	}

	public DefaultListModel getModel() {
		return model;
	}

	public void setModel(DefaultListModel model) {
		this.model = model;
	}
}
