import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class FileHandelling 
{
	private Scanner s;
	protected int arTime[][] = new int[370][14];
	public FileHandelling() 
	{
		try {
			s = new Scanner((getClass().getResourceAsStream("masum.txt")));
			
			int i = 0, j=0, s2;
			System.out.println("keno1");
			while (s.hasNext()) {
				for (j = 0; j < 14; j++) {
					s2 = s.nextInt();
					arTime[i][j] = s2;
				
				}
				System.out.println();
				s2 = s.nextInt();
				i++;
			}
			
			s.close();
		}

		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Input file is missing!");
			System.exit(1);
		}
		
	}

	
}
