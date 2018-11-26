package MovieGUI;


import javax.swing.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;



import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;

public class ShowTextFrame extends JFrame {

	

	public ShowTextFrame() {
	//public static void main(String[] args) throws IOException{
		
		
		JTextField tf = new JTextField(18); ;//创建文本框
	
		JButton bt = new JButton("搜索");	//创建搜索按钮
		
		JTextArea ta  = new JTextArea(50,60);	//创建文本域显示文本信息
		
		JButton bt2 = new JButton("查看评论");
		
		JButton bt3 = new JButton("添加评论");
		
		JTextField tf2 = new JTextField(10);
		
		JTextArea ta2  = new JTextArea(10,40);
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JLabel jl = new JLabel();
		JPanel jp3 = new JPanel();
		
		JScrollPane sp2 = new JScrollPane(ta2);	//文本域添加到滚动面板
		sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 	//垂直滚动条总是显示
		sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //水平滚动条总是显示
		
		
		JFrame f = new JFrame();
		
		jp1.add(tf);
		jp1.add(bt);
		f.add(jp1, BorderLayout.NORTH);
		
		
		jp2.add(ta);
		f.add(jp2, BorderLayout.CENTER);
		
		
		jp3.add(bt2);
		jp3.add(bt3);
		jp3.add(tf2);
		
		jp3.add(sp2);
		jp3.add(ta2);
		f.add(jp3, BorderLayout.SOUTH);
		
		
		

		
		
		JScrollPane sp = new JScrollPane(ta);	//文本域添加到滚动面板
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 	//垂直滚动条总是显示
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //水平滚动条总是显示
		jp2.add(sp);
		
		
		f.setTitle("豆瓣电影TOP250");	//窗口标题
		f.setSize(1000, 1000);			//窗口大小
		//f.pack();
		f.setLocationRelativeTo(null);	//窗口居中
		//f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("D:\\douban\\doubanmovie.txt"));
			String line;
			
		while ((line = reader.readLine()) != null)
			//按行读取文本，显示在TEXTAREA中
			
			ta.append(line + "\r\n");
			reader.close();
	
	} catch (IOException e) {
		System.out.println(e);
	}


		//设置窗体关闭
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				}
			});
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//获取文本框的值
				String tf_str = tf.getText().trim();
				
				
				JFrame f1 = new JFrame("搜索结果如下");
				
				f1.setSize(700, 800);
				f1.setLocationRelativeTo(null);	//窗口居中
				//f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				JTextArea ta1  = new JTextArea(50,60);
				
				JPanel jp1 = new JPanel();
				
				
				JScrollPane sp1 = new JScrollPane(ta1);	//文本域添加到滚动面板
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 	//垂直滚动条总是显示
				sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //水平滚动条总是显示
				f1.add(sp1);
				
				
				Scanner scan1;
				try {
					scan1 = new Scanner(new File("D:\\douban\\doubanmovie.txt"));
					int k1=0;
					while(true) {
						if(scan1.hasNext()==false) 
							break;
						String s1 = scan1.nextLine();
						k1=k1+1;
						if(s1.contains(tf_str)) {
							ta1.append(s1+"\r\n");
						}
					}
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				//获取光标
				tf.requestFocus();
				
				//设置窗体可见
				f1.setVisible(true);
				
			}
					});
		

		
		
		
		
		
		if(LandingMyActionListener.land==true)	{
			bt3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					
					
					String filename = "D:\\douban\\pinglun.txt";
			
					try {
					File file = new File(filename);
					FileOutputStream fos = null;
					if(!file.exists()) {
						file.createNewFile();//如果文件不存在，就创建该文件
						fos = new FileOutputStream(file);//首次写入获取
					}else {
						//如果文件已存在，就在文件末尾追加写入
						fos = new FileOutputStream(file,true);
						OutputStreamWriter os = new OutputStreamWriter(fos);
						//获取文本框的值
						String tf2_str = tf2.getText().trim();				
						//清空数据
						tf2.setText("");
						//文本框内容追加
						ta2.append(tf2_str+"\r\n");
						
						os.write(tf2_str+"\r\n");		
						os.close();
						
						
					}
					}catch(IOException e1) {
						e1.printStackTrace();
					}
					
								

					
				}		
			});
			
		}	
			
		if(LandingMyActionListener.land==true) {
			bt2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String filename = "D:\\douban\\pinglun.txt";
					//读取文件
					try {
						InputStream is = new FileInputStream(filename);
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						String line = null;
						StringBuffer buffer = new StringBuffer();
						while((line = br.readLine())!=null) {
							buffer.append(line+"\r\n");	
						}
						ta2.setText(buffer.toString());
						br.close();
						is.close();
						
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					}	
					
				
			});
				
		}
			
			ta.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					try {
						//获取链接信息
						URL url = new URL(ta.getText().split("\n").toString().substring(0, 42));
						//在系统默认浏览器中打开链接
						Desktop.getDesktop().browse(url.toURI());
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
					
				}
			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
				f.setVisible(true);
				
	}
/**	
	public static void main(String[] args) {
		new ShowTextFrame();
	}
	**/
}



	
//}
		

		