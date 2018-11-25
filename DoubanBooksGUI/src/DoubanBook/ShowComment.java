package DoubanBook;
/**
 *author xiajiayu
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ShowComment extends JPanel{
	private JTextArea JxA2=new JTextArea(30,50);
	public ShowComment() {
		JxA2.setText(readComment());
		this.add(JxA2);
		
	}
	public  String readComment() {
		StringBuilder comment=new StringBuilder();
		try {
			FileReader fr = new FileReader("D:\\Comment.txt");//×Ö·û¶ÁÈëÁ÷
			BufferedReader br = new BufferedReader(fr);
			while(br.read() > 0){
				comment.append(br.readLine()+"\n");
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return comment.toString();
	}
}
