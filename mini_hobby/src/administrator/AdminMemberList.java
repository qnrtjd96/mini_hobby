package administrator;
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

public class AdminMemberList extends JPanel{
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
	
	//제목
	String stuTitle[]	= {"아이디", "이름", "등급"};
	Object stuData[][]= {
			{"user1234","홍길동","프로취미러"},
			{"user2345","이순신","상급취미러"},
			{"user3456","세종대왕","하급취미러"},
			{"user4567","장영실","중급취미러"},
			{"user5678","유승룡","취미가뭐지"},	
	};
	JScrollPane stuSp;
	
	//제목
	String teaTitle[]	= {"아이디", "이름", "클래스"};
	Object teaData[][]= {
			{"class1","위인1","요리"},
			{"class2","위인2","미술"},
			{"class3","위인3","스포츠"},
			{"class4","위인4","수영"},
			{"class5","위인5","음악"},	
	};
	
	JScrollPane teaSp;
	public AdminMemberList() {
		setLayout(new BorderLayout());
        
		//배경 변경
		topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		//chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,30,30));
				
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
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
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
			teaTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		teaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
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
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new AdminMemberList();

	}
}