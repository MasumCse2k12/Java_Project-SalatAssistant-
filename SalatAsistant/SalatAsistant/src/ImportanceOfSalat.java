
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.orbutil.graph.Graph;


public class ImportanceOfSalat extends JPanel
{

	JDialog mainFrame;
	Font banglaFont = new Font("Siyam Rupali", Font.PLAIN, 15);
	public ImportanceOfSalat() {
			
		
		mainFrame = new JDialog();
		mainFrame.setFont(banglaFont);		
		mainFrame.setTitle("নামাযের গুরুত্ব  ");
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
			 		"\t\tনামাযের গুরুত্ব \n" + 
			       "\n\t আয়াত সমুহ :\n"+ 
					"\t আল্লাহ তায়ালার এরশাদ ' নিশ্চয়ই নামায  নির্লজ্জ \n ও অশোভনীয় কাজ বিরত রাখে । (সূরা আন কাবুত -৪৫),\n\n\n" +
					"\t হাদিস সমূহ :\n" +
					"\t হযরত আব্দুল্লাহ ইবনে ক্কুরত (রা:) হতে বর্ণিত  \n "
					+ " আছে যে ,রাসুলুল্লাহ সাল্লাল্লাহু আ'লাইহি ওয়া সাল্লাম\n "
					+ " এরশাদ করেছেন ,'কিয়ামতের দিন সর্বপ্রথম নামাযের\n"
					+ " হিসাব নেয়া হবে ।যদি নামায ঠিক থাকে তবে বাকি\n"
					+ " আমলও  ঠিক হবে ।আর নামায যদি খারাপ হইয়া \n"
			        +" থাকে তবে বাকি আমলও খারাপ হবে ।'\n\n\n";
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
		new ImportanceOfSalat();

	}

}
