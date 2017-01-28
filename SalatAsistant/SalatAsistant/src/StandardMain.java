import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


import com.aboutme.golap.InfoFrame;

public class StandardMain extends JPanel {

	String[] month =  {"Jan", "Feb","Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};	
	public StandardMain() {

		banglaFont = new Font("Siyam Rupali", Font.PLAIN, 20);
		headBanglaFont = new Font("Siyam Rupali", Font.TRUETYPE_FONT, 30);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		String[] day = new String[31];
		
		String[] year = new String[150];
		String[] dist = { "Dhaka", "Khulna", "Narail", "Jessore", "Rajshahi",
				"Barishal" };
		//System.out.println(dist[0]);
		for (int i = 0; i < 31; i++)
			day[i] = "" + (i + 1);
//		for (int i = 0; i < 12; i++)
//			month[i] = "" + (i + 1);
		for (int i = 0; i < 150; i++)
			year[i] = "" + (i + 1950);
		int x = 10, y = 50;

		distComboBox = new JComboBox(dist);
		add(distComboBox);
		
		changeAlarm = new JButton("Change alarm");
		changeAlarm.addActionListener(buttonHandler);
		
		add(changeAlarm);
		
		URL urlClick = StandardMain.class.getResource("azan1.wav");
		click = Applet.newAudioClip(urlClick);
		showButton = new JButton("Show");
		showButton.addActionListener(buttonHandler);
		showButton.setToolTipText("Show Selected Date Schedule");
		add(showButton);

		currentDayComboBox = new JComboBox(day);
		currentDayComboBox.setSelectedItem("" + calendar.get(Calendar.DATE));
		currentDayComboBox.addActionListener(buttonHandler);
		add(currentDayComboBox);
		//

		currentMonthComboBox = new JComboBox(month);
		currentMonthComboBox.setSelectedItem(""+ month[(calendar.get(Calendar.MONTH))]);
		//System.out.println(""+calendar.get(Calendar.MONTH));
		
		currentMonthComboBox.addActionListener(buttonHandler);
		add(currentMonthComboBox);

		currentYearComboBox = new JComboBox(year);
		currentYearComboBox.setSelectedItem("" + calendar.get(Calendar.YEAR));
		currentYearComboBox.addActionListener(buttonHandler);
		add(currentYearComboBox);

		
		FileHandelling fileHandelling = new FileHandelling();
		this.arTime = fileHandelling.arTime;
        
		// new AzanClass();
		backgroundLabelDeclaring();

		setCurrentSalatTime();
		//chooser.showOpenDialog(null);
		soundFile =chooser.getSelectedFile();
		//System.out.println(soundFile);
		azanClass = new AzanClass(this);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu set = new JMenu("Setting");
		JMenu about = new JMenu("More");
		JMenu newAbout = new JMenu("About");
		//JMenu source = new JMenu("Source");
		JMenu help = new JMenu("Help");

		menuBar.add(set);
		menuBar.add(about);
		menuBar.add(newAbout);
		//menuBar.add(source);
		menuBar.add(help);
		JMenuItem masail = new JMenuItem("Masail of Salat");
		JMenuItem imp = new JMenuItem("Importance of Salat");
		
		JMenuItem aboutme = new JMenuItem("About Me");
		JMenuItem AlertOnOf = new JMenuItem("Alert On/Off");
		JMenuItem AlertBase = new JMenuItem("Alert Based on");
		
		newAbout.add(aboutme);
		about.add(masail);
		about.add(imp);
		
		set.add(AlertOnOf);
		set.add(AlertBase);
		
		masail.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getActionCommand().equals("Masail of Salat"))
				{
				  //System.out.println("visible the masail of salat class");
					new MasailofSalat();
				 	
				}
				
			}
		});
		aboutme.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new InfoFrame();
			}
		});
		imp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getActionCommand().equals("Importance of Salat"))
				{
					new ImportanceOfSalat();
				}
				
			}
		});
		mainFrame = new JFrame();
		mainFrame.add(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("SALAT ASISTANT APP by MD. MASUM & MEHADI CSE 2K12(KUET)");
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon(
				StandardMain.class.getResource("Mosquelogo.jpg"));
		mainFrame.setIconImage(icon.getImage());
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setVisible(true);

	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


		LabelLocation();

		

		distComboBox.setLocation(360, 140);
		currentDayComboBox.setLocation(200, 210);
		currentMonthComboBox.setLocation(245, 210);
		currentYearComboBox.setLocation(300, 210);
		showButton.setLocation(355, 208);
		changeAlarm.setLocation(420, 208);
		backgroundLabel.setLocation(0, 0);
		// System.out.println("executed -> " + (++x));
	}

	
	GregorianCalendar calendar = new GregorianCalendar();
	int mnth = calendar.get(Calendar.MONTH) + 1;

	int Day = calendar.get(Calendar.DATE);
	int year = calendar.get(Calendar.YEAR);
	int arTime[][] = new int[370][14];

	public void setCurrentSalatTime() {

		
		
		int j, c = 0, count=0;
		String din[] = {  "Sunday","Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday" };
		for(j=1951; j<=year;j++)
		{
			 if((j%4==0 && j%100 !=0)|| j%400==0)
					 {
					
					 c=1;
					 count++;
					
					 }
					 else
					 {
					 if(c==1) count+=2;
					 else count++;
					 c=0;
					 }
		}
		String Strmnth = month[calendar.get(Calendar.MONTH)];
		
		if (Strmnth.equals(month[0])) {
			count = (count + (Day-1) ) % 7;
		}

		else if (Strmnth.equals(month[1]) ) {
			count =(count +  (31 + Day-1)) % 7;
		} 
		
		else if (((year ) % 4 == 0 && (year ) % 100 != 0)|| (year ) % 400 == 0) 
		{
			if(Strmnth.equals(month[2]))	count = (count +(31 + 29 + Day-1)) % 7;
			   else if (Strmnth.equals(month[3]))     count =(count + (31 + 29 + 31 + Day-1)) % 7;
			   else if (Strmnth.equals(month[4]))  count = (count+(31 + 29 + 31 + 30 +  Day-1)) % 7;
			   else if (Strmnth.equals(month[5]))  count = (count + (31 + 29 + 31 + 30 + 31 +  Day-1)) % 7;
			   else if (Strmnth.equals(month[6])) count =(count + (31 + 29 + 31 + 30 + 31 + 30 + Day-1)) % 7;
			   else if(Strmnth.equals(month[7]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 +31+ Day-1)) % 7;
			   else if (Strmnth.equals(month[8]))  count =(count+ (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + Day-1)) % 7;
			   else if (Strmnth.equals(month[9]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+ Day-1) )% 7;
		       else if (Strmnth.equals(month[10]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+ Day-1)) % 7;
			   else if (Strmnth.equals(month[11]))  count = (count+(31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+30+ Day-1)) % 7;
		}
		
		else 
		{
			   if(Strmnth.equals(month[2]))	count = (count +(31 + 28 + Day-1)) % 7;
			   else if (Strmnth.equals(month[3]))     count =(count + (31 + 28 + 31 + Day-1)) % 7;
			   else if (Strmnth.equals(month[4]))  count = (count+(31 + 28 + 31 + 30 +  Day-1)) % 7;
			   else if (Strmnth.equals(month[5]))  count = (count + (31 + 28 + 31 + 30 + 31 +  Day-1)) % 7;
			   else if (Strmnth.equals(month[6])) count =(count + (31 + 28 + 31 + 30 + 31 + 30 + Day-1)) % 7;
			   else if(Strmnth.equals(month[7]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 +31+ Day-1)) % 7;
			   else if (Strmnth.equals(month[8]))   count =(count+ (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + Day-1)) % 7;
			   else if (Strmnth.equals(month[9]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+ Day-1) )% 7;
		       else if (Strmnth.equals(month[10]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+ Day-1)) % 7;
			   else if (Strmnth.equals(month[11]))  count = (count+(31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+30+ Day-1)) % 7;
		}	
	try
	{
		 barlabel.setText(din[count]);
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "Failed to calculate day when count: "+count);
	}

		
		String fday = Day < 10 ? "0" + Day : "" + Day;

		selectDayLabel.setText("" + fday + "");
		selectMonthLabel.setText("" + Strmnth + "");
		selectYearLabel.setText(currentYearComboBox.getSelectedItem() + "");
       int i;
		if(mnth <= 2){
        	
        	 i = (((mnth - 1) * 31) + Day ) - 1;
        }
		else  i = (((mnth - 1) * 31) + Day - 2) - 1;
		
		

		String fmin = arTime[i][3] < 10 ? "0" + arTime[i][3] : ""
				+ arTime[i][3];
		String smin = arTime[i][5] < 10 ? "0" + arTime[i][5] : ""
				+ arTime[i][5];
		String jmin = arTime[i][7] < 10 ? "0" + arTime[i][7] : ""
				+ arTime[i][7];
		String amin = arTime[i][9] < 10 ? "0" + arTime[i][9] : ""
				+ arTime[i][9];
		String magib_min = arTime[i][11] < 10 ? "0" + arTime[i][11] : ""
				+ arTime[i][11];
		String isahamin = arTime[i][13] < 10 ? "0" + arTime[i][13] : ""
				+ arTime[i][13];

		String fajorJmin = ((arTime[i][3] + 40) % 60) < 10 ? "0"
				+ ((arTime[i][3] + 40) % 60) : "" + ((arTime[i][3] + 40) % 60);
		int fajorJhour = (arTime[i][2] + ((arTime[i][3] + 40) / 60));

		String asorJmin = ((arTime[i][9] + 30) % 60) < 10 ? "0"
				+ ((arTime[i][9] + 30) % 60) : "" + ((arTime[i][9] + 30) % 60);
		int asorJhour = (arTime[i][8] + ((arTime[i][9] + 30) / 60));

		String magJmin = ((arTime[i][11] + 5) % 60) < 10 ? "0"
				+ ((arTime[i][11] + 5) % 60) : "" + ((arTime[i][11] + 5) % 60);
		int magJhour = (arTime[i][10] + ((arTime[i][11] + 5) / 60));

		String eshaJmin = ((arTime[i][13] + 30) % 60) < 10 ? "0"
				+ ((arTime[i][13] + 30) % 60) : ""
				+ ((arTime[i][13] + 30) % 60);
		int eshaJhour = (arTime[i][12] + ((arTime[i][13] + 30) / 60));

		sahriEndTimeLabel.setText("0" + (arTime[i][2]) + ":" + fmin + " AM");
		fajorTimeLabel.setText("0" + arTime[i][2] + ":" + fmin + " AM");
		sunriseTimeLabel.setText("" + arTime[i][4] + ":" + smin + " AM");
		jaharTimeLabel.setText("" + (arTime[i][6]) + ":" + jmin + " PM");
		asorTimeLabel.setText("0" + (arTime[i][8]) + ":" + amin + " PM");
		magribTimeLabel
				.setText("0" + (arTime[i][10]) + ":" + magib_min + " PM");
		sunSetTimeLabel.setText("" + (arTime[i][10]) + ":" + magib_min + " PM");
		iftarTimeLabel.setText("0" + (arTime[i][10]) + ":" + magib_min + " PM");
		eshaTimeLabel.setText("0" + (arTime[i][12]) + ":" + isahamin + " PM");

		fajorJamatLabel.setText("0" + fajorJhour + ":" + fajorJmin + " AM");
		jaharJamatLabel.setText("01" + ":" + "30" + " PM");
		asorJamatLabel.setText("0" + asorJhour + ":" + asorJmin + " PM");
		magribJamatLabel.setText("0" + magJhour + ":" + magJmin + " PM");
		eshaJamatLabel.setText("0" + eshaJhour + ":" + eshaJmin + " PM");
		
		fozorTime = "0" + arTime[i][2] + ":" + fmin + ":00 AM";
		johorTime = "" + (arTime[i][6]) + ":" + jmin + ":00 PM";
		asorTime = "0" + (arTime[i][8]) + ":" + amin + ":00 PM";
		magribTime = "0" + (arTime[i][10]) + ":" + magib_min + ":00 PM";
		eshaTime = "0" + (arTime[i][12]) + ":" + isahamin + ":00 PM";

	}
   int i;
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
           
           
			String Strmnth = (currentMonthComboBox.getSelectedItem().toString());
			
			Day = Integer.parseInt(currentDayComboBox.getSelectedItem().toString());
	//		System.out.println("day = " + Day);
			year = Integer.parseInt(currentYearComboBox.getSelectedItem().toString());
			//String fmonth = mnth < 10 ? "0" + mnth : "" + mnth;
			String fday = Day < 10 ? "0" + Day : "" + Day;

			
			String din[] = {   "Sunday", "Monday", "Tuesday" ,"Wednesday", "Thursday",
					"Friday", "Saturday" };


	       
			
			if (event.getSource() == showButton) {
				int j, c = 0,count=0;
				for( j=1951;  j<=year ; j++)
				{
					 if((j%4==0 && j%100 !=0)|| j%400==0)
							 {
							
							 c=1;
							 count++;
							
							 }
							 else
							 {
							 if(c==1) count+=2;
							 else count++;
							 c=0;
							 }
				}
				try
				{
					if (Strmnth.equals(month[0])) {
						count = (count + (Day-1) ) % 7;
					}

				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "problem in january");
				}
				
				try
				{
					if (Strmnth.equals(month[1])) {
						count = (count + (31+Day-1) ) % 7;
					}

				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "problem in February");
				}
			
//			else if (Strmnth.equals(month[1]) ) {
//				count =(count +  (31 + Day-1)) % 7;
			//} 
			
			 if (((year ) % 4 == 0 && (year ) % 100 != 0)|| (year ) % 400 == 0) 
			{
				if(Strmnth.equals(month[2]))	count = (count +(31 + 29 + Day-1)) % 7;
				   else if (Strmnth.equals(month[3]))     count =(count + (31 + 29 + 31 + Day-1)) % 7;
				   else if (Strmnth.equals(month[4]))  count = (count+(31 + 29 + 31 + 30 +  Day-1)) % 7;
				   else if (Strmnth.equals(month[5]))  count = (count + (31 + 29 + 31 + 30 + 31 +  Day-1)) % 7;
				   else if (Strmnth.equals(month[6])) count =(count + (31 + 29 + 31 + 30 + 31 + 30 + Day-1)) % 7;
				   else if(Strmnth.equals(month[7]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 +31+ Day-1)) % 7;
				   else if (Strmnth.equals(month[8]))  count =(count+ (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + Day-1)) % 7;
				   else if (Strmnth.equals(month[9]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+ Day-1) )% 7;
			       else if (Strmnth.equals(month[10]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+ Day-1)) % 7;
				   else if (Strmnth.equals(month[11]))  count = (count + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+30+ Day-1)) % 7;
			}
			
			else 
			{
				   if(Strmnth.equals(month[2]))	count = (count +(31 + 28 + Day-1)) % 7;
				   else if (Strmnth.equals(month[3]))     count =(count + (31 + 28 + 31 + Day-1)) % 7;
				   else if (Strmnth.equals(month[4]))  count = (count+(31 + 28 + 31 + 30 +  Day-1)) % 7;
				   else if (Strmnth.equals(month[5]))  count = (count + (31 + 28 + 31 + 30 + 31 +  Day-1)) % 7;
				   else if (Strmnth.equals(month[6])) count =(count + (31 + 28 + 31 + 30 + 31 + 30 + Day-1)) % 7;
				   else if(Strmnth.equals(month[7]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 +31+ Day-1)) % 7;
				   else if (Strmnth.equals(month[8]))  count =(count+ (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + Day-1)) % 7;
				   else if (Strmnth.equals(month[9]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+ Day-1) )% 7;
			       else if (Strmnth.equals(month[10]))  count = (count + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+ Day-1)) % 7;
				   else if (Strmnth.equals(month[11]))  count = (count+(31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 +30+31+30+ Day-1)) % 7;
			}
			 try
			 {
				 selectDayLabel.setText("" + fday + "");
					selectMonthLabel.setText("" + Strmnth + "");
					selectYearLabel.setText(currentYearComboBox.getSelectedItem()
							+ "");
			 }catch(Exception e )
			 {
				 JOptionPane.showMessageDialog(null, "Failed to date correctly.");
			 }
			
				int i=0;
                if(Strmnth.equals(month[0])){mnth=1; i = (((mnth - 1) * 31) + Day ) - 1;}
                else  if(Strmnth.equals(month[1])){mnth=2; i = (((mnth - 1) * 31) + Day ) - 1;}
                else if(Strmnth.equals(month[2])){mnth=3; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else  if(Strmnth.equals(month[3])){mnth=4; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else if(Strmnth.equals(month[4])){mnth=5; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else  if(Strmnth.equals(month[5])){mnth=6; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else if(Strmnth.equals(month[6])){mnth=7; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else  if(Strmnth.equals(month[7])){mnth=8; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else if(Strmnth.equals(month[8])){mnth=9; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else  if(Strmnth.equals(month[9])){mnth=10; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else if(Strmnth.equals(month[10])){mnth=11; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                else  if(Strmnth.equals(month[11])){mnth=12; i = (((mnth - 1) * 31) + Day - 2) - 1;}
                
				 

				//System.out.println("The number of current day is:  " + i);

				String fmin = arTime[i][3] < 10 ? "0" + arTime[i][3] : ""
						+ arTime[i][3];
				String smin = arTime[i][5] < 10 ? "0" + arTime[i][5] : ""
						+ arTime[i][5];
				String jmin = arTime[i][7] < 10 ? "0" + arTime[i][7] : ""
						+ arTime[i][7];
				String amin = arTime[i][9] < 10 ? "0" + arTime[i][9] : ""
						+ arTime[i][9];
				String magib_min = arTime[i][11] < 10 ? "0" + arTime[i][11]
						: "" + arTime[i][11];
				String isahamin = arTime[i][13] < 10 ? "0" + arTime[i][13] : ""
						+ arTime[i][13];

				int fajorJhour = (arTime[i][2] + ((arTime[i][3] + 40) / 60));

				String fajorJmin = ((arTime[i][3] + 40) % 60) < 10 ? "0"
						+ ((arTime[i][3] + 40) % 60) : ""
						+ ((arTime[i][3] + 40) % 60);
			
				String asorJmin = ((arTime[i][9] + 30) % 60) < 10 ? "0"
						+ ((arTime[i][9] + 30) % 60) : ""
						+ ((arTime[i][9] + 30) % 60);
				int asorJhour = (arTime[i][8] + ((arTime[i][9] + 30) / 60));

				String magJmin = ((arTime[i][11] + 5) % 60) < 10 ? "0"
						+ ((arTime[i][11] + 5) % 60) : ""
						+ ((arTime[i][11] + 5) % 60);
				int magJhour = (arTime[i][10] + ((arTime[i][11] + 5) / 60));

				String eshaJmin = ((arTime[i][13] + 30) % 60) < 10 ? "0"
						+ ((arTime[i][13] + 30) % 60) : ""
						+ ((arTime[i][13] + 30) % 60);
				int eshaJhour = (arTime[i][12] + ((arTime[i][13] + 30) / 60));
				try
				{
					barlabel.setText(din[count]);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "(count 0-6)Failed calculating day when count: "+count);
				}
				
				 
				 try{
				sahriEndTimeLabel.setText("0" + (arTime[i][2]) + ":" + fmin
						+ " AM");
				fajorTimeLabel.setText("0" + arTime[i][2] + ":" + fmin + " AM");
				sunriseTimeLabel
						.setText("" + arTime[i][4] + ":" + smin + " AM");
				jaharTimeLabel
						.setText("" + (arTime[i][6]) + ":" + jmin + " PM");
				asorTimeLabel
						.setText("0" + (arTime[i][8]) + ":" + amin + " PM");
				magribTimeLabel.setText("0" + (arTime[i][10]) + ":" + magib_min
						+ " PM");
				sunSetTimeLabel.setText("" + (arTime[i][10]) + ":" + magib_min
						+ " PM");
				iftarTimeLabel.setText("0" + (arTime[i][10]) + ":" + magib_min
						+ " PM");
				eshaTimeLabel.setText("0" + (arTime[i][12]) + ":" + isahamin
						+ " PM");

				fajorJamatLabel.setText("0" + fajorJhour + ":" + fajorJmin
						+ " AM");
				jaharJamatLabel.setText("01" + ":" + "30" + " PM");
				asorJamatLabel
						.setText("0" + asorJhour + ":" + asorJmin + " PM");
				magribJamatLabel
						.setText("0" + magJhour + ":" + magJmin + " PM");
				eshaJamatLabel
						.setText("0" + eshaJhour + ":" + eshaJmin + " PM");
			
				 }catch(Exception e)
				 {
					 JOptionPane.showMessageDialog(null, "Error in setting time");
				 }
				 
				 fozorTime = "0" + arTime[i][2] + ":" + fmin + ":00 AM";
					johorTime = "" + (arTime[i][6]) + ":" + jmin + ":00 PM";
					asorTime = "0" + (arTime[i][8]) + ":" + amin + ":00 PM";
					magribTime = "0" + (arTime[i][10]) + ":" + magib_min + ":00 PM";
					eshaTime = "0" + (arTime[i][12]) + ":" + isahamin + ":00 PM";
				 }
			if (event.getSource() == changeAlarm) {
				//click.play();
				//azanClass.azanFrame.setVisible(true);
				
				JFileChooser chooser = new JFileChooser(); 
				chooser.showOpenDialog(null);
				soundFile =chooser.getSelectedFile();
				JOptionPane.showMessageDialog(null,"Your default azan is select as  " +soundFile.getName());
				
			}
		}

	}

	public static void main(String[] args) {

		StandardMain r = new StandardMain();
	
	}
	
	public void backgroundLabelDeclaring() {

		

		headLabel = new JLabel("নামায  সহায়িকা");
		headLabel.setFont(headBanglaFont);
		headLabel.setForeground(Color.WHITE);
		add(headLabel);

		jelaLabel = new JLabel("জেলা :");
		jelaLabel.setFont(banglaFont);
		jelaLabel.setForeground(Color.white);
		add(jelaLabel);

		dayLabel = new JLabel("আজ :");
		dayLabel.setFont(banglaFont);
		dayLabel.setForeground(Color.WHITE);
		add(dayLabel);
		barlabel = new JLabel();
		barlabel.setFont(new Font("", Font.PLAIN, 20));
		barlabel.setForeground(Color.white);
		add(barlabel);

		jamatLabel = new JLabel("নামায ");
		jamatLabel.setFont(banglaFont);
		add(jamatLabel);

		fajrLabel = new JLabel("ফজর :");
		fajrLabel.setFont(banglaFont);
		add(fajrLabel);

		JaharLabel = new JLabel("যোহর :");
		JaharLabel.setFont(banglaFont);
		add(JaharLabel);

		asorLabel = new JLabel("আছর :");
		asorLabel.setFont(banglaFont);
		add(asorLabel);

		magribLabel = new JLabel("মাগরিব :");
		magribLabel.setFont(banglaFont);
		add(magribLabel);

		eshaLabel = new JLabel("এশা :");
		eshaLabel.setFont(banglaFont);
		add(eshaLabel);

		owaktoStartTimeLabel = new JLabel("ওয়াক্ত শুরু");
		owaktoStartTimeLabel.setFont(banglaFont);
		add(owaktoStartTimeLabel);

		owaktoJamatLabel = new JLabel("জামাত");
		owaktoJamatLabel.setFont(banglaFont);
		add(owaktoJamatLabel);

		fajorJamatLabel = new JLabel();
		fajorJamatLabel.setFont(banglaFont);
		add(fajorJamatLabel);

		jaharJamatLabel = new JLabel();
		jaharJamatLabel.setFont(banglaFont);
		add(jaharJamatLabel);

		asorJamatLabel = new JLabel();
		asorJamatLabel.setFont(banglaFont);
		add(asorJamatLabel);

		magribJamatLabel = new JLabel();
		magribJamatLabel.setFont(banglaFont);
		add(magribJamatLabel);

		eshaJamatLabel = new JLabel();
		eshaJamatLabel.setFont(banglaFont);
		add(eshaJamatLabel);

		sahriEndTimeLabel = new JLabel();
		sahriEndTimeLabel.setFont(banglaFont);
		sahriEndTimeLabel.setOpaque(true);
		add(sahriEndTimeLabel);

		sunriseLabel = new JLabel("সূর্যদয় :");
		sunriseLabel.setFont(banglaFont);
		sunriseLabel.setForeground(Color.WHITE);
		add(sunriseLabel);

		sunriseTimeLabel = new JLabel();
		sunriseTimeLabel.setFont(banglaFont);
		//sunriseTimeLabel.setForeground(Color.WHITE);
		sunriseTimeLabel.setOpaque(true);
		add(sunriseTimeLabel);

		sunSetTimeLabel = new JLabel();
		sunSetTimeLabel.setFont(banglaFont);
		//sunSetTimeLabel.setForeground(Color.WHITE);
		sunSetTimeLabel.setOpaque(true);
		add(sunSetTimeLabel);

		sunSetLabel = new JLabel("সূর্যাস্ত:");
		sunSetLabel.setFont(banglaFont);
		add(sunSetLabel);

		fajorTimeLabel = new JLabel();
		fajorTimeLabel.setFont(banglaFont);
		add(fajorTimeLabel);

		jaharTimeLabel = new JLabel();
		jaharTimeLabel.setFont(banglaFont);
		add(jaharTimeLabel);

		asorTimeLabel = new JLabel();
		asorTimeLabel.setFont(banglaFont);
		add(asorTimeLabel);

		magribTimeLabel = new JLabel();
		magribTimeLabel.setFont(banglaFont);
		add(magribTimeLabel);

		eshaTimeLabel = new JLabel();
		eshaTimeLabel.setFont(banglaFont);
		add(eshaTimeLabel);

		sahriLabel = new JLabel("সেহরির  শেষ সময়");
		sahriLabel.setFont(banglaFont);
		sahriLabel.setOpaque(true);
		sahriLabel.setForeground(Color.red);
		add(sahriLabel);

		iftarLabel = new JLabel("ইফতার শুরু ");
		iftarLabel.setFont(banglaFont);
		iftarLabel.setForeground(Color.RED);
		iftarLabel.setOpaque(true);
		add(iftarLabel);

		iftarTimeLabel = new JLabel();
		iftarTimeLabel.setFont(banglaFont);
		iftarTimeLabel.setOpaque(true);
		add(iftarTimeLabel);

		selectDayLabel = new JLabel();
		selectDayLabel.setFont(new Font("",Font.PLAIN,25));
		add(selectDayLabel);

		selectMonthLabel = new JLabel();
		selectMonthLabel.setFont(new Font("",Font.PLAIN,25));
		add(selectMonthLabel);

		selectYearLabel = new JLabel();
		selectYearLabel.setFont(new Font("",Font.PLAIN,25));
		add(selectYearLabel);
		backgroundLabel = new JLabel(new ImageIcon(getClass().getResource(
				"AIRPOE~1 - Copy.JPG")));
		add(backgroundLabel);

	}

	public void LabelLocation() {

		int x = 170, y1 = 315, xdif = 135, x1 = x + xdif, x2 = x1 + 155, Ydif = 35, y2 = y1
				+ Ydif, y3 = y2 + Ydif, y4 = y3 + Ydif, y5 = y4 + Ydif;

		headLabel.setLocation(270, 20);
		jelaLabel.setLocation(295, 135);

		dayLabel.setLocation(295, 	95);
		barlabel.setLocation(355, 97);

		selectDayLabel.setLocation(305, 170);
		selectMonthLabel.setLocation(345, 170);
		selectYearLabel.setLocation(400, 170);

		
		jamatLabel.setLocation(x, 280);
		fajrLabel.setLocation(x, y1);
		JaharLabel.setLocation(x, y2);
		asorLabel.setLocation(x, y3);
		magribLabel.setLocation(x, y4);
		eshaLabel.setLocation(x, y5);
		
		owaktoStartTimeLabel.setLocation(305, 280);
		owaktoJamatLabel.setLocation(460, 280);
		sahriLabel.setLocation(170, 490);
		sahriEndTimeLabel.setLocation(195, 515);
		iftarLabel.setLocation(455, 490);
		iftarTimeLabel.setLocation(465, 515);
		sunriseLabel.setLocation(200, 240);
		sunriseTimeLabel.setLocation(272, 240);

		sunSetLabel.setLocation(385, 240);
		sunSetLabel.setForeground(Color.WHITE);
		sunSetTimeLabel.setLocation(445, 240);

		fajorTimeLabel.setLocation(x1, y1);
		jaharTimeLabel.setLocation(x1, y2);
		asorTimeLabel.setLocation(x1, y3);
		magribTimeLabel.setLocation(x1, y4);
		eshaTimeLabel.setLocation(x1, y5);

		fajorJamatLabel.setLocation(x2, y1);
		jaharJamatLabel.setLocation(x2, y2);
		asorJamatLabel.setLocation(x2, y3);
		magribJamatLabel.setLocation(x2, y4);
		eshaJamatLabel.setLocation(x2, y5);
	}
	
	private JFrame mainFrame;
	private JLabel headLabel;
	private JLabel jelaLabel;
	private JLabel dayLabel;
	private JLabel jamatLabel;
	private JLabel fajrLabel;
	private JLabel JaharLabel;
	private JLabel asorLabel;
	private JLabel magribLabel;
	private JLabel eshaLabel;
	private JLabel sunSetLabel;
	private JLabel sunriseLabel;
	private JLabel labelCalendar;
	private JLabel backgroundLabel;
	private JLabel owaktoStartTimeLabel;
	private JLabel owaktoJamatLabel;
	private JLabel sahriLabel;
	private JLabel sahriTimeLabel;
	private JLabel sahriEndTimeLabel;
	private JLabel iftarLabel;
	private JLabel iftarTimeLabel;
	private int count = 0;

	private JLabel daylabel;
	private JLabel barlabel;
	private JLabel monthlabel;
	private JLabel yearlabel;

	private JLabel selectDayLabel;
	private JLabel selectMonthLabel;
	private JLabel selectYearLabel;
	JLabel sunSetTimeLabel;
	private JLabel sunriseTimeLabel;
	JLabel fajorTimeLabel;
	JLabel jaharTimeLabel;
	JLabel asorTimeLabel;
	JLabel magribTimeLabel;
	JLabel eshaTimeLabel;
	private JLabel fajorJamatLabel;
	private JLabel jaharJamatLabel;
	private JLabel asorJamatLabel;
	private JLabel magribJamatLabel;
	private JLabel eshaJamatLabel;
	private AudioClip click;
	private JButton changeAlarm;
	private JButton stop;
	private Font banglaFont;
	private Font headBanglaFont;
	JFileChooser chooser = new JFileChooser();
	File soundFile;
	
	private JButton showButton;
	private JComboBox currentDayComboBox, currentMonthComboBox,
			currentYearComboBox, distComboBox;
	protected String fozorTime, johorTime, asorTime, magribTime, eshaTime;
	protected AzanClass azanClass;
	private ButtonHandler buttonHandler = new ButtonHandler();

}
