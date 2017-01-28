package com.aboutme.golap;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class InFormationPanel extends JPanel
{
	/*
	 * constructor of the class
	 */
	public InFormationPanel() 
	{
		imagePanelDeclaring();
		setTextToInfoLabel();
		backgroundLabelDeclaring();
	}
	
	/*
	 * painting part of the class
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.DARK_GRAY);
		
		set_Label_Location();
		backgroundLabelLocation();
		imagePanelLocation();
	}
	
	/*
	 * background label declaring
	 */
	public void backgroundLabelDeclaring()
	{
		backgroundLabel = new JLabel( new ImageIcon(getClass().getResource("InforFreameBackground.jpg")) );
		add(backgroundLabel);
	}
	
	/*
	 * image panels declaring
	 */
	public void imagePanelDeclaring()
	{
		imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("AbdulAwal Sir.jpg")));
		imageLabel2 = new JLabel(new ImageIcon(getClass().getResource("mehadicse12.jpg")));
		imageLabel3 = new JLabel(new ImageIcon(getClass().getResource("MasumBillah.JPG")));
		
		imagePanel1 = new JPanel();
		imagePanel1.add(imageLabel1);
		imagePanel1.setVisible(true);
		add(imagePanel1);
		
		imagePanel2 = new JPanel();
		imagePanel2.add(imageLabel2);
		imagePanel2.setVisible(true);
		add(imagePanel2);
		
		imagePanel3 = new JPanel();
		imagePanel3.add(imageLabel3);
		imagePanel3.setVisible(true);
		add(imagePanel3);
	}
	
	/*
	 * image panels location
	 */
	public void imagePanelLocation()
	{
		imageLabel1.setLocation(0, 0);
		imageLabel1.setToolTipText("Mr. Saifuddin Mahmud.");
		imagePanel1.setSize(280, 250);
		imagePanel1.setLocation(320, 0);
		imagePanel1.setBackground(Color.GREEN);
		
		imageLabel2.setLocation(0, 0);
		imageLabel2.setToolTipText("Md. Milon Islam.");
		imagePanel2.setSize(280, 250);
		imagePanel2.setLocation(5, 130);
		imagePanel2.setBackground(Color.GREEN);
		
		imageLabel3.setLocation(0, 0);
		imageLabel3.setToolTipText("Md. Kamrul Hasan Golap.");
		imagePanel3.setSize(280, 250);
		imagePanel3.setLocation(640, 130);
		imagePanel3.setBackground(Color.GREEN);
	}

	/*
	 * set text to information labels
	 */
	public void setTextToInfoLabel()
	{	
		Font font = new Font("Arial Rounded MT Bold", Font.PLAIN , 12);
		
		s_button = new JButton("Honourable Sir");
		s_button.setEnabled(false);
		add(s_button);
		
		s_Name = new JLabel("Mr. Abdul Awal");
		s_Name.setFont(font);
		s_info = new JLabel("Lecturer, Dept. of CSE.");
		s_kuet = new JLabel("Khulna University of Engineering & Technology(KUET).");
		s_email = new JLabel("www.kuet.ac.bd/AbdulAwal.");
		add(s_info);
		add(s_Name);
		add(s_kuet);
		add(s_email);
		
		
		m_button = new JButton("Developer:");
		m_button.setEnabled(false);
		add(m_button);
		m_Name = new JLabel("Md. Mehadi Hasan");
		m_Name.setFont(font);
		m_info = new JLabel("Student of B.Sc.Engg.(CSE), 2K12 Batch.");
		m_kuet = new JLabel("Khulna University of Engineering & Technology(KUET).");
		//m_kuet = new JLabel("Student of B.Sc.Engg.(CSE), KUET.");
		m_email = new JLabel("mehadikuetcse@yahoo.com");
		add(m_info);
		add(m_Name);
		add(m_kuet);
		add(m_email);
		
		
		g_button = new JButton("Developer:");
		g_button.setEnabled(false);
		add(g_button);
		g_Name = new JLabel("Md. Masum Billah"); 
		g_Name.setFont(font);
		g_info = new JLabel("Student of B.Sc.Engg.(CSE), 2K12 Batch.");
		g_kuet = new JLabel("Khulna University of Engineering & Technology(KUET).");
		g_email = new JLabel("www.mdmasumBillah@yahoo.com");
		add(g_info);
		add(g_Name);
		add(g_kuet);
		add(g_email);
	}
	
	/*
	 * set labels locations
	 */
	public void set_Label_Location()
	{
		
		s_button.setSize(280, 20);
		s_button.setLocation(320, 260);
		s_Name.setLocation(320, 280);
		s_info.setLocation(320, 300);
		s_kuet.setLocation(320, 320);
		s_email.setLocation(320, 340);
		
		
		m_button.setSize(280, 20);
		m_button.setLocation(5, 390);
		m_Name.setLocation(5, 410);
		m_info.setLocation(5, 430);
		m_kuet.setLocation(5, 450);
		m_email.setLocation(5, 470);
		
		
		
		g_button.setSize(280, 20);
		g_button.setLocation(640, 390);
		g_Name.setLocation(640, 410);
		g_info.setLocation(640, 430);
		g_kuet.setLocation(640, 450);
		g_email.setLocation(640, 470);
		
	}
	
	/*
	 * location for background label
	 */
	public void backgroundLabelLocation()
	{
		backgroundLabel.setLocation(0, 0);
	}
	
	/*
	 * declaring part of the class
	 */
	protected JPanel imagePanel1, imagePanel2, imagePanel3;
	protected JTextArea textArea;
	protected JScrollPane scrollPane;
	
	protected JButton s_button, m_button, g_button;
	protected JLabel imageLabel1, imageLabel2, imageLabel3;
	protected JLabel infoLabel[] = new JLabel[5];
	protected JLabel s_info, s_Name, s_kuet, s_email;
	protected JLabel m_info, m_Name, m_kuet, m_email;
	protected JLabel g_info, g_Name, g_kuet, g_email;
	
	
	private JLabel backgroundLabel;
	
}
