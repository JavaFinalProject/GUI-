package DoubanBook;
/**
 * author xiajiyu
 */
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Event;
import java.awt.Desktop;

public class Content extends JFrame {
	private static String object[][];
	private static String[] columsname= {"排名","书名","书籍信息","评分","链接"};
	private  JTextField jtxf;
	private  JPanel jp1=new JPanel();	
	public Content() {
		SetContent();
		setFrame();
	    this.setTitle("豆瓣图书top250");
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.pack();
	}
	
	@SuppressWarnings("resource")
	public static void SetContent() {
		object=new String[250][5];
		try {
			FileReader fr = new FileReader("D:\\doubanBooks.txt");//字符读入流
			BufferedReader br = new BufferedReader(fr);
			int k=0;
			for(int i=0;i<1000;i=i+4) {
				int cow=0;
				for(int j=k;j<k+4;j++) {
					cow++;
					object[k][0]="No."+(k+1);
					if(cow>=1&&br.read()>0) {
					object[k][cow]=br.readLine();}
					
					
				}
				k++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setFrame() {
		JPanel jp2=new comment();
		setSelectFeiled();
		JTable table=new JTable( object,columsname);
		TableColumn column=null;
		int colunms = table.getColumnCount();
		for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为200*/  
            column.setPreferredWidth(200);  
        }  
        /* 
         * 设置JTable自动调整列表的状态，此处设置为关闭 
         */  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
        table.addMouseListener(	new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				 //得到选中的行列的索引值
	               int r= table.getSelectedRow();
	               int c= table.getSelectedColumn();
	               if(c==4) {
	               table.getCellEditor(r,c).stopCellEditing();
	               //得到选中的单元格的值，表格中都是字符串
	               String value=table.getValueAt(r, c).toString();
	               Desktop desktop = Desktop.getDesktop();   
				   try {
					desktop.browse(new URI(value));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //使用默认浏览器打开超链接
				   catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
				
			}
		});
        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  
        JScrollPane scroll1 = new JScrollPane(table);
        scroll1.setSize(400, 300);
        add(jp1,BorderLayout.NORTH);
        add(scroll1,BorderLayout.CENTER);
        add(jp2,BorderLayout.SOUTH);
       
	}
	public void setSelectFeiled() {
		JButton button=new JButton("搜索");
         jtxf=new JTextField("请输入内容");
         jp1.add(button);
         jp1.add(jtxf);
         button.addActionListener(new actionListener());
		
	}
	public String Judge(String a)	{
		String b="";
		a=a.replace(" ","");
		if(a==null)
			return null;
		for(int i=0;i<object.length;i++) {
			for(int j=0;j<object[i].length;j++) {
				if(object[i][j].contains(a)) {
				    b=b+Arrays.asList(object[i]).toString()+"\n";
					break;
				}
			}
			
		}
		if(b=="")
		return null;
		return b;
	}
	
	private class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	    JTextArea Jtextarea;
		JFrame selectFrame=new JFrame("结果如下");
		JPanel pl1=new JPanel();
		
		if(Judge(jtxf.getText())!=null) {
			Jtextarea=new JTextArea(Judge(jtxf.getText()));
		}
		else Jtextarea=new JTextArea("很抱歉，榜单中没有您要的书籍");
		pl1.add(Jtextarea,BorderLayout.CENTER);
		JScrollPane scroll=new JScrollPane(pl1);
		selectFrame.setLocation(100,50);
		selectFrame.setSize(600,600);
		selectFrame.setVisible(true);
		selectFrame.add(scroll);
	    selectFrame.pack();
			}
			
		}
	/*public void openURI() {
		table.addMouseListener(	new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				 //得到选中的行列的索引值

	               int r= table.getSelectedRow();

	               int c= table.getSelectedColumn();

	               //得到选中的单元格的值，表格中都是字符串

	               String value= (String)table.getValueAt(r, c);
	               System.out.println(value);
	               Desktop desktop = Desktop.getDesktop();   
				   try {
					desktop.browse(new URI(value));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //使用默认浏览器打开超链接
 catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	          
		});
	
	}
	*/
	
	
	public static void main(String[] args) {
		new Content();
	}
}



