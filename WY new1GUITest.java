package dooubanTest;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import teamWork.LandingMyActionListener;

public class GUITest {
	private JFrame myFrame;
	private JTable myTable;
	private Panel myPanel;
	private Panel panel1;
	private Panel panel2;
	private TextArea t1;
	private TextArea t2;
	private Label label;
	private Button b;
	private Button myButton;
	private TextField myTextField;
	 Vector v = new Vector(250);
	 Tool tool=new Tool();
	
	DefaultTableModel model = new DefaultTableModel(); // 新建一个默认数据模型
	
	
	String[]name= {"排名","网站","歌手以及专辑名称","详细信息","评分","评论人数"};
	public  GUITest(){
		myFrame=new JFrame();
		myFrame.setTitle("Douban music Top 250");
		myFrame.setResizable(true);
		myFrame.setSize(500,400);
		
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
			
		});
		
		myButton=new Button("搜索");
		myTextField=new TextField(20);
		//myFrame.setLayout(new GridLayout(4,1));
		myPanel=new Panel();
		myPanel.add(myTextField);
		myFrame.add(myPanel,BorderLayout.NORTH);
		myPanel.add(myButton);
		MyActionListener myActionListener = new MyActionListener(myTextField);
		
		myButton.addActionListener(myActionListener);
		myTable = new JTable(model); // 用数据模型创建JTable
		//创建单元格渲染器暨鼠标事件监听器
        LinkCellRenderer renderer = new LinkCellRenderer();
        //注入渲染器
        myTable.setDefaultRenderer(Object.class, renderer);
        //注入监听器
        myTable.addMouseListener(renderer);
        myTable.addMouseMotionListener(renderer);


		myFrame.add(myTable,BorderLayout.CENTER);
		myFrame.add(new JScrollPane(myTable));
		myFrame.setVisible(true);
		panel1=new Panel(new BorderLayout());
		label=new Label("-----------------------------------------------------"
				+ "----------------------- 评 论 区 -------------------------------"
				+ "------------------------------------");
		JScrollPane jsp=new JScrollPane(t1);
		panel1.add(jsp);
		t1=new TextArea();
		t2=new TextArea();
		//JScrollPane jsp1=new JScrollPane(t2);
		//myFrame.getContentPane().add(jsp1);

		b=new Button("确定评论");
		aActionListener actionListener=new aActionListener(t1,t2);
		if(LandingMyActionListener.land==true)
			b.addActionListener(actionListener);
		 
		panel1.add(label,BorderLayout.NORTH);
		panel1.add(t1,BorderLayout.CENTER);
		panel1.add(b,BorderLayout.EAST);
		panel1.add(t2,BorderLayout.SOUTH);
		
		try {File ctoFile = new File("D:\\mydoc\\评论.txt");
		  
		InputStreamReader rdCto = new InputStreamReader(new FileInputStream(ctoFile));
		BufferedReader bfReader = new BufferedReader(rdCto);
	
      	String txtline = null;
		
		
			while ((txtline = bfReader.readLine()) != null) {
				
				t2.append(txtline+"\r\n");
			}
			bfReader.close();
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		myFrame.add(panel1,BorderLayout.SOUTH);
		
		model.setColumnIdentifiers(name);
		 myFrame.setLocationRelativeTo(null);     
		try {for(int i=0;i<250;i++)
			model.addRow(tool.toArray(i));
		} catch (IOException e) {
			
			e.printStackTrace();
		}}
	
	
		 
	/*	public static void main(String[] args) throws IOException {
			GUITest frame=new GUITest();
	
	
	}*/
	
	}


	class MyActionListener implements ActionListener{
			Tool tool1=new Tool();
			private TextField textfield;
			public MyActionListener(TextField textfield) {
				this.textfield=textfield;
			}
	public void actionPerformed(ActionEvent e) {
		String strr=new String();
		strr=textfield.getText();
		
		JFrame f=new JFrame();
		f.setLocation(100,50);
	    //窗体大小
		f.setSize(800,500);
		JLabel jlabel=new JLabel("抱歉，您要找的歌手或者音乐不在“豆瓣音乐TOP 250里面");
		JLabel label=new JLabel("搜索结果");
		f.add(label,BorderLayout.NORTH);
		JTable table=new JTable();
		String[]name1= {"排名","网站","歌手以及专辑名称","详细信息","评分","评论人数"};
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(name1);
		table = new JTable(model); // 用数据模型创建JTable
		
		f.add(new JScrollPane(table));
		//创建单元格渲染器暨鼠标事件监听器
        LinkCellRenderer renderer = new LinkCellRenderer();
        //注入渲染器
        table.setDefaultRenderer(Object.class, renderer);
        //注入监听器
        table.addMouseListener(renderer);
        table.addMouseMotionListener(renderer);


		f.add(table,BorderLayout.CENTER);
		
		for(int i=0;i<250;i++)
		{try {
			if(tool1.infoShow()[i].contains(strr)) {
					model.addRow(tool1.toArray(i));//i++;
			}	
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		/*int i=0;
		while(i!=250)
		{try {
			if(tool1.infoShow()[i].contains(strr)==false) {
				i++;
			}
			while(i==250)
			{
				f.add(jlabel);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}*/
		f.setVisible(true);
	}
	}
	
	class aActionListener implements ActionListener{
		private TextArea textarea1;
		private TextArea textarea2;
	
		public aActionListener(TextArea textarea1,TextArea textarea2) {
			this.textarea1=textarea1;
			this.textarea2=textarea2;
			
		}
		public void actionPerformed(ActionEvent a) {
			String str=new String();
			str=textarea1.getText();
			 File file=new File("D:\\mydoc");
			  if(!file.exists()){
			 file.mkdir();
			 }
			 File file2 = new File("D:\\mydoc","评论.txt");
			  if(!file2.exists()){
			 try{
				 file2.createNewFile();
				 }catch(IOException e){
			e.printStackTrace();
	}
				}
				 FileWriter fileWriter;
				 try{ /*File ctoFile = new File("D:\\mydoc\\评论.txt");
				  
					InputStreamReader rdCto = new InputStreamReader(new FileInputStream(ctoFile));
					BufferedReader bfReader = new BufferedReader(rdCto);
				
                  	String txtline = null;
					
					while ((txtline = bfReader.readLine()) != null) {
						
						textarea2.append(txtline+"\r\n");
					}
					bfReader.close();*/
					
				  fileWriter = new FileWriter(file2,true);
				  
				  BufferedWriter out=null;
				  out=new BufferedWriter(new OutputStreamWriter(new 
						  FileOutputStream(file2,true)));
				  out.write("@"+LandingMyActionListener.userName+":"+str.toString()+"\r\n");
				 out.close();
				  
				 textarea2.append("@"+LandingMyActionListener.userName+":"+str.toString()+"\r\n");
				 
		    
				 }catch(IOException e){
			         e.printStackTrace();
			        }
				 
			 }
			
		}
	


class Tool{
	public String[] infoShow() throws IOException {
		File ctoFile = new File("D:\\\\douban2.txt");
		
		InputStreamReader rdCto = new InputStreamReader(new FileInputStream(ctoFile));

		BufferedReader bfReader = new BufferedReader(rdCto);

		String txtline = null;
		String a[]=new String[250];
		int i=0;int count=0;

		while ((txtline = bfReader.readLine()) != null) {
		a[i]=txtline;
			i++;
			//return txtline;
			count++;
				}
	    bfReader.close();
		return a;
	}
	
	
	public String toStr6(String Str) {
		String str=Str;
		return str.substring(str.indexOf("评价(")+3, str.lastIndexOf("人")+1);
	}
	public String toStr1(String Str) {
		String str=Str;
		return str.substring(str.indexOf("h"), str.lastIndexOf("%"));
	}
	public String toStr2(String Str) {
		String str=Str;
		return str.substring(str.indexOf("%")+1, str.lastIndexOf("详细"));
	}
	/*public String toStr3(String Str) {
		String str1=Str;
		return str1.substring(str1.indexOf("-"), str1.lastIndexOf("详"));
	}*/
	
	public String toStr4(String Str) {
		String str=Str;
		return str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
	}
	public String toStr5(String Str) {
		String str=Str;
		return str.substring(str.indexOf("评分")+2, str.lastIndexOf("\t"));
	}
	public Vector toArray(int i) throws IOException {
		Tool tool=new Tool();
		Vector v=new Vector();
		
		v.add("No."+(i+1));
		v.add(tool.toStr1(tool.infoShow()[i]));
		v.add(tool.toStr2(tool.infoShow()[i]));
		v.add(tool.toStr4(tool.infoShow()[i]));
		v.add(tool.toStr5(tool.infoShow()[i]));
	    v.add(tool.toStr6(tool.infoShow()[i]));
	    return v;}
}


class LinkCellRenderer extends DefaultTableCellRenderer implements MouseInputListener {

    //鼠标事件所在的行
    private int row = -1;
    //鼠标事件所在的列
    private int col = -1;
    //当前监听的Table
    private JTable table = null;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //恢复默认状态
        this.table = table;
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setForeground(Color.black);
        table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        this.setText(value.toString());
        //如果当前需要渲染器的单元格就是鼠标事件所在的单元格
        if (row == this.row && column == this.col) {
            //如果是第二列(第二列是显示超链接的列)
            if (column == 1) {
                //改变前景色(文字颜色)
                this.setForeground(Color.blue);
                //改变鼠标形状
                table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                //显示超链接样式
                this.setText("<html><u>" + value.toString() + "</u></html>");
            }
            setBackground(table.getSelectionBackground());
        } else if (isSelected) {
            //如果单元格被选中,则改变前景色和背景色
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            //其他情况下恢复默认背景色
            setBackground(Color.white);
        }
        return this;
    }

    /**
     * 鼠标移出事件
     * @param e 
     */
    public void mouseExited(MouseEvent e) {
        if (table != null) {
            int oldRow = row;
            int oldCol = col;
            //鼠标移出目标表格后,恢复行列数据到默认值
            row = -1;
            col = -1;
            //当之前的行列数据有效时重画相关区域
            if (oldRow != -1 && oldCol != -1) {
                Rectangle rect = table.getCellRect(oldRow, oldCol, false);
                table.repaint(rect);
            }
        }
    }

    /**
     * 鼠标拖动事件
     * @param e 
     */
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    /**
     * 鼠标移动事件
     * @param e 
     */
    public void mouseMoved(MouseEvent e) {
        if (table != null) {
            Point p = e.getPoint();
            int oldRow = row;
            int oldCol = col;
            row = table.rowAtPoint(p);
            col = table.columnAtPoint(p);
            //重画原来的区域
            if (oldRow != -1 && oldCol != -1) {
                Rectangle rect = table.getCellRect(oldRow, oldCol, false);
                table.repaint(rect);
            }
            //重画新的区域
            if (row != -1 && col != -1) {
                Rectangle rect = table.getCellRect(row, col, false);
                table.repaint(rect);
            }
        }
    }

    /**
     * 鼠标单击事件
     * @param e 
     */
    public void mouseClicked(MouseEvent e) {
        //获取事件所在的行列坐标信息
        Point p = e.getPoint();
        int c = table.columnAtPoint(p);
        if(c != 1){
            return;
        }
        int r = table.rowAtPoint(p);
        try {
            //取得目标单元格的值,即链接信息
            URL url = new URL(table.getValueAt(r, c).toString());
            //在系统默认浏览器中打开链接
            Desktop.getDesktop().browse(url.toURI());
        } catch (Exception ex) {
            Logger.getLogger(LinkCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}}



	
