import java.awt.Font;
import java.awt.TextField;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class SalatMasail extends JPanel{
	private JDialog masailFrame;
	private  JLabel masailLabel;
	
	private Font banglaFont;
	private JTextArea textField;
	public SalatMasail() {
	
		textField = new JTextArea();
		textField.setSize(400, 600);
		add(textField);
		banglaFont = new Font("Siyam Rupali",Font.BOLD, 18);
	masailLabel = new JLabel();
	masailLabel.setFont(banglaFont);
	
	add(masailLabel);
	masailLabel.setText("msdkjlkd ffkjghlkd kkglkdf \n sdfjdfdsk \n fd");
	
	masailFrame  = new JDialog();
	masailFrame.setTitle("Masail of Salat");
	masailFrame.setSize(400, 600);
	masailFrame.setDefaultCloseOperation(masailFrame.DISPOSE_ON_CLOSE);
	masailFrame.setLocationRelativeTo(null);
	masailFrame.add(this);
	masailFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    new SalatMasail();
	}

}
