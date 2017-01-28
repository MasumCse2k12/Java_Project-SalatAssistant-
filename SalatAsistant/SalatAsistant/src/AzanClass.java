import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AzanClass extends JPanel implements Runnable
{
	private static final StandardMain StandardMain = null;
	JDialog azanFrame;
	protected java.lang.String fozorTime, johorTime, asorTime, magribTime, eshaTime;
	protected JComboBox azanList;
	protected JButton stopAzanButton, listenOrStop, setDefaultAzan;
	private File soundFile;
	private JLabel azanLabel;
	private Font banglaFont;
	
//	public AzanClass()
//	{
//		JFileChooser chooser = new JFileChooser(); 
//		chooser.showOpenDialog(null);
//		soundFile =chooser.getSelectedFile();
//		JOptionPane.showMessageDialog(null,"Your default azan is select as  " +soundFile.getName());
//		System.out.println(soundFile.getName());
//	}
	public AzanClass(StandardMain standardMain) {
		
		this.fozorTime = standardMain.fozorTime;
		this.johorTime = standardMain.johorTime;
		this.asorTime = standardMain.asorTime;
		this.magribTime = standardMain.magribTime;
		this.eshaTime = standardMain.eshaTime;
		
		this.soundFile = standardMain.soundFile;
		//System.out.println(soundFile);
		java.lang.String azan[] = 
		{
			"Azan fazr~1.wav",
			"azan1.wav"
				
		};
		
		banglaFont = new Font("Siyam Rupali", Font.BOLD, 45);
		
		azanList = new JComboBox(azan);
		azanList.setSelectedItem("azan1.wav");
		
		//add(azanList);
		
		
		listenOrStop = new JButton("Listen");
		listenOrStop.addActionListener(new ActionListener() {
			
			boolean listenFlag = true;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listenFlag)
				{
					try
					{
						URL urlClick = StandardMain.class.getResource(azanList.getSelectedItem() + "");
						System.out.println(urlClick);
						click = Applet.newAudioClip(urlClick);
						click.play();
					}catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "File is not same directory");
						azanFrame.dispose();
					}
					try
					{
						click.play();
					}catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "For audio .wav file missing");
					}
					
					
					azanList.setEnabled(false);
					stopAzanButton.setEnabled(false);
					listenOrStop.setText("Stop");
					listenFlag = false;
				}
				else
				{
					listenOrStop.setText("Listen");
					click.stop();
					azanList.setEnabled(true);
					listenFlag = true;							
				}
			}
		});
		
		//add(listenOrStop);
		
		setDefaultAzan = new JButton("Set As Azan");
		setDefaultAzan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				URL urlClick = StandardMain.class.getResource(azanList.getSelectedItem() + "");
				click = Applet.newAudioClip(urlClick);
				JOptionPane.showMessageDialog(null, "Your default azan is select as " + azanList.getSelectedItem());
			}
		});
		//add(setDefaultAzan);
		
		azanLabel = new JLabel();
		//azanLabel.setLocation(50, 160);
		azanLabel.setFont(banglaFont);
		this.add(azanLabel);		
		
		stopAzanButton = new JButton("Stop Azan");
		stopAzanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				click.stop();
				azanList.setEnabled(true);
    	    	listenOrStop.setEnabled(true);
    	    	setDefaultAzan.setEnabled(true);
    	    	stopAzanButton.setEnabled(true);
				azanFrame.dispose();
				
			}
		});
		
		add(stopAzanButton);
		
		URL urlClick = StandardMain.class.getResource(azanList.getSelectedItem() + "");
		click = Applet.newAudioClip(urlClick);
		
		stopAzanButton.setEnabled(false);
		
		thread = new Thread(this);
		thread.start();
		
		azanFrame = new JDialog();
		azanFrame.setTitle("Azan Running");
		azanFrame.setSize(400, 400);
		azanFrame.setLocationRelativeTo(null);
		azanFrame.setDefaultCloseOperation(azanFrame.DISPOSE_ON_CLOSE);
		azanFrame.add(this);
		this.setBackground(Color.RED);
		azanFrame.setVisible(false);
		
	}
	int x=0;
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	 
		azanList.setLocation(50, 15);
		listenOrStop.setLocation(250, 15);
		setDefaultAzan.setLocation(140,50);
		azanLabel.setLocation(20, 150); 
		stopAzanButton.setLocation(150, 250);
		
	
 }
	private AudioClip click;
	private JButton start;
	private JButton stop;
	
	Thread thread;
	@Override
	public void run() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss aa");
		//int length = click.
		URL urlClick = StandardMain.class.getResource(azanList.getSelectedItem() + "");
		click = Applet.newAudioClip(urlClick);
		
		int i=0;
	    while(true)
	    {
	    	try
	    	{
	    		Date now = new Date();
	    		//String strTime = sdfTime.format(now);
	    		
	    		java.lang.String s = sdfTime.format(new Date());
	    	    //System.out.println(s);
	    	    if(s.equals(fozorTime) || s.equals(johorTime) || s.equals(asorTime) || s.equals(magribTime) || s.equals(eshaTime))
	    	    {
	    	    	if(s.equals(fozorTime)){
	    	    		//System.out.println(fozorTime);
	    	    		azanLabel.setText("ফজরের  আযান");
	    	    	    urlClick = StandardMain.class.getResource( "Azan fazr~1.wav");
	    	    		click = Applet.newAudioClip(urlClick);
	    	    		//click.play();
	    	    		
	    	    	}
	    	    	else if(s.equals(johorTime)) azanLabel.setText("যোহরের  আযান");
	    	    	else if(s.equals(asorTime)) azanLabel.setText("আছরের  আযান");
	    	    	else if(s.equals(magribTime)) azanLabel.setText("মাগরিবের  আযান");
	    	    	else if(s.equals(eshaTime)) azanLabel.setText("এশার  আযান");
	    	    	
	    	    	click.play();
	    	    	azanList.setEnabled(false);
	    	    	listenOrStop.setEnabled(false);
	    	    	setDefaultAzan.setEnabled(false);
	    	    	stopAzanButton.setEnabled(true);
	    	    	azanFrame.setVisible(true);
	    	    	System.out.println("Time matched");
	    	    }
	    	    else 
	    	    {
	    	    	//System.out.println( "comparing,  cur:" + s + " fozor: " +  fozorTime);
	    	    }
	    	    thread.sleep(1000);
	    	    
	    		
	    	}catch(Exception e)
	    	{
	    		System.out.println("Error occured in timer thread in AzanClass");
	    	}
	    }
	}

}
