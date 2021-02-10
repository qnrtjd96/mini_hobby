<<<<<<< HEAD
<<<<<<< HEAD
package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class TeachLiveChat extends JPanel{
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JPanel mainPane = new JPanel(new BorderLayout());
		JPanel topPane = new JPanel(new BorderLayout());
			JLabel connList = new JLabel("접속목록", JLabel.CENTER);
		
			//int rows, int cols, int hgap, int vgap)
		JPanel centerPane = new JPanel(new GridLayout(0,2,30,20));
			JPanel stu = new JPanel(new BorderLayout());
				JLabel student = new JLabel("학 생", JLabel.CENTER);
				//임시데이터 stuTable, stutitle, studata
			JPanel tea = new JPanel(new BorderLayout());
				JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);
				//임시데이터 teaTable, teatitle, teadata	
			
		JPanel bottomPane = new JPanel(new BorderLayout());
			JButton chatt = new JButton("채팅하기");
			JButton admin = new JButton("관리자와 채팅");
		
	//제목
	String stuTitle[]	= {"접속여부", "이름"};
	Object stuData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	JScrollPane stuSp;
	
	//제목
	String teaTitle[]	= {"접속여부", "아이디"};
	Object teaData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	
	JScrollPane teaSp;
		
	public TeachLiveChat() {
		setLayout(new BorderLayout());
        
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		bottomPane.setBackground(Color.WHITE); //chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		chatt.setFont(fntBold20); admin.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		// 내용 수정 불가 시작 // 학생
        DefaultTableModel stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuSp = new JScrollPane(stuTable);
		stuTable.getTableHeader().setFont(fntBold15);
		
		//내용 수정 불가 시작// 선생님
	    DefaultTableModel teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable teaTable = new JTable(teaT);
			teaTable.setRowHeight(30);
			teaTable.setFont(fntPlain15);
			teaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			teaTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		teaSp = new JScrollPane(teaTable);
		teaTable.getTableHeader().setFont(fntBold15);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = stuTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(center);
		}
		
		DefaultTableCellRenderer center1 = new DefaultTableCellRenderer();
		center1.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule1 = teaTable.getColumnModel();
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
				tcmSchedule1.getColumn(i).setCellRenderer(center1);
		}
		
//		listTbl.getParent().setFont(fn15);
//		listTbl.getTableHeader().setBackground(col6);
//		listTbl.getTableHeader().setFont(fnt20);
//		table.getColumn("○").setPreferredWidth(50);
		
		stu.add(student,BorderLayout.NORTH); stu.add(stuSp);
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		
		centerPane.add(stu);
		centerPane.add(tea);
		
		//하단
		bottomPane.add(chatt,BorderLayout.EAST);
		bottomPane.add(admin,BorderLayout.WEST);
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		new TeachLiveChat();
	}
}
=======
package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class TeachLiveChat extends JPanel{
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JPanel mainPane = new JPanel(new BorderLayout());
		JPanel topPane = new JPanel(new BorderLayout());
			JLabel connList = new JLabel("접속목록", JLabel.CENTER);
		
			//int rows, int cols, int hgap, int vgap)
		JPanel centerPane = new JPanel(new GridLayout(0,2,30,20));
			JPanel stu = new JPanel(new BorderLayout());
				JLabel student = new JLabel("학 생", JLabel.CENTER);
				//임시데이터 stuTable, stutitle, studata
			JPanel tea = new JPanel(new BorderLayout());
				JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);
				//임시데이터 teaTable, teatitle, teadata	
			
		JPanel bottomPane = new JPanel(new BorderLayout());
			JButton chatt = new JButton("채팅하기");
			JButton admin = new JButton("관리자와 채팅");
		
	//제목
	String stuTitle[]	= {"접속여부", "이름"};
	Object stuData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	JScrollPane stuSp;
	
	//제목
	String teaTitle[]	= {"접속여부", "아이디"};
	Object teaData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	
	JScrollPane teaSp;
		
	public TeachLiveChat() {
		setLayout(new BorderLayout());
        
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		bottomPane.setBackground(Color.WHITE); //chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		chatt.setFont(fntBold20); admin.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		// 내용 수정 불가 시작 // 학생
        DefaultTableModel stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuSp = new JScrollPane(stuTable);
		stuTable.getTableHeader().setFont(fntBold15);
		
		//내용 수정 불가 시작// 선생님
	    DefaultTableModel teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable teaTable = new JTable(teaT);
			teaTable.setRowHeight(30);
			teaTable.setFont(fntPlain15);
			teaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			teaTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		teaSp = new JScrollPane(teaTable);
		teaTable.getTableHeader().setFont(fntBold15);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = stuTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(center);
		}
		
		DefaultTableCellRenderer center1 = new DefaultTableCellRenderer();
		center1.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule1 = teaTable.getColumnModel();
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
				tcmSchedule1.getColumn(i).setCellRenderer(center1);
		}
		
//		listTbl.getParent().setFont(fn15);
//		listTbl.getTableHeader().setBackground(col6);
//		listTbl.getTableHeader().setFont(fnt20);
//		table.getColumn("○").setPreferredWidth(50);
		
		stu.add(student,BorderLayout.NORTH); stu.add(stuSp);
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		
		centerPane.add(stu);
		centerPane.add(tea);
		
		//하단
		bottomPane.add(chatt,BorderLayout.EAST);
		bottomPane.add(admin,BorderLayout.WEST);
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		new TeachLiveChat();
	}
}
>>>>>>> refs/remotes/origin/master
=======
package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class TeachLiveChat extends JPanel{
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JPanel mainPane = new JPanel(new BorderLayout());
		JPanel topPane = new JPanel(new BorderLayout());
			JLabel connList = new JLabel("접속목록", JLabel.CENTER);
		
			//int rows, int cols, int hgap, int vgap)
		JPanel centerPane = new JPanel(new GridLayout(0,2,30,20));
			JPanel stu = new JPanel(new BorderLayout());
				JLabel student = new JLabel("학 생", JLabel.CENTER);
				//임시데이터 stuTable, stutitle, studata
			JPanel tea = new JPanel(new BorderLayout());
				JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);
				//임시데이터 teaTable, teatitle, teadata	
			
		JPanel bottomPane = new JPanel(new BorderLayout());
			JButton chatt = new JButton("채팅하기");
			JButton admin = new JButton("관리자와 채팅");
		
	//제목
	String stuTitle[]	= {"접속여부", "이름"};
	Object stuData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	JScrollPane stuSp;
	
	//제목
	String teaTitle[]	= {"접속여부", "아이디"};
	Object teaData[][]= {
			{"ㅇ","홍길동"},
			{"ㅇ","이순신"},
			{"ㅇ","세종대왕"},
			{"ㅇ","장영실"},
			{"ㅇ","유승룡"},	
	};
	
	JScrollPane teaSp;
		
	public TeachLiveChat() {
		setLayout(new BorderLayout());
        
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		bottomPane.setBackground(Color.WHITE); //chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		chatt.setFont(fntBold20); admin.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		// 내용 수정 불가 시작 // 학생
        DefaultTableModel stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuSp = new JScrollPane(stuTable);
		stuTable.getTableHeader().setFont(fntBold15);
		
		//내용 수정 불가 시작// 선생님
	    DefaultTableModel teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		JTable teaTable = new JTable(teaT);
			teaTable.setRowHeight(30);
			teaTable.setFont(fntPlain15);
			teaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			teaTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		teaSp = new JScrollPane(teaTable);
		teaTable.getTableHeader().setFont(fntBold15);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = stuTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(center);
		}
		
		DefaultTableCellRenderer center1 = new DefaultTableCellRenderer();
		center1.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule1 = teaTable.getColumnModel();
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
				tcmSchedule1.getColumn(i).setCellRenderer(center1);
		}
		
//		listTbl.getParent().setFont(fn15);
//		listTbl.getTableHeader().setBackground(col6);
//		listTbl.getTableHeader().setFont(fnt20);
//		table.getColumn("○").setPreferredWidth(50);
		
		stu.add(student,BorderLayout.NORTH); stu.add(stuSp);
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		
		centerPane.add(stu);
		centerPane.add(tea);
		
		//하단
		bottomPane.add(chatt,BorderLayout.EAST);
		bottomPane.add(admin,BorderLayout.WEST);
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		new TeachLiveChat();
	}
}
>>>>>>> refs/remotes/origin/master
