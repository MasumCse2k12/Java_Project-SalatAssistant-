import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.orbutil.graph.Graph;


public class MasailofSalat extends JPanel
{

	JDialog mainFrame;
	Font banglaFont = new Font("Siyam Rupali", Font.PLAIN, 15);
	public MasailofSalat() {
		
		
		mainFrame = new JDialog();
		mainFrame.setFont(banglaFont);		
		mainFrame.setTitle("নামাযের মাসায়েল");
		setContent();
		mainFrame.add(this);
		mainFrame.setSize(400, 420);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(mainFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(getWidth(), getHeight());
		
		//textArea.setLocation(0, 0);
		//textArea.setSize(400, 400);
	}
	JTextArea textArea;
	JScrollPane scrollPane;
	
	public void setContent()
	{
		textArea = new JTextArea(30, 30);
		
		textArea.setFont(banglaFont);
		
		
		try
		{
			Scanner fileScanner = new Scanner((getClass().getResourceAsStream("masailofSalat.txt")));
			String s="";
			
			s = 
					"\t\tনামাযের ফরয ১৩ টি\n" + 
					"\t১. শরীর পাক,\n" +
					"\t২. কাপড় পাক,\n" +
					"\t৩. জায়গা পাক,\n" +
					"\t৪. সতর ঢাকা,\n" +
					"\t৫. কেবলা মুখি হওয়া,\n" +
					"\t৬. ওয়াক্তমত নামায পড়া,\n" +
					"\t৭. নামাযের নিয়ত করা,\n" +
					"\t৮. তাকবীরে তাহরিমা বলা,\n" +
					"\t৯. দাড়ায়ে নামায পড়া,\n" +
					"\t১০. কেরাত পড়া,\n" +
					"\t১১. রুকু করা,\n" +
					"\t১২. দুই সিজদা করা,\n" +
					"\t১৩. আখেরি বৈঠক করা।\n";
			
			/*while(fileScanner.hasNext())
			{ 
				s += fileScanner.nextLine() + "\n";				
			}*/
			textArea.setText(s);
			textArea.setEditable(false);
			
			textArea.setBackground(Color.LIGHT_GRAY);
			textArea.setForeground(Color.RED);
		}catch(Exception e)
		{}
		
		scrollPane = new JScrollPane(textArea);
		//scrollPane.setSize(400, 400);
		
		add(scrollPane);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MasailofSalat();

	}

}
