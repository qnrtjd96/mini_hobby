<<<<<<< HEAD
package administrator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dbConnection.MemberDAO;
import dbConnection.MemberVO;

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
		JLabel connList = new JLabel("회원 목록", JLabel.CENTER);
	
	JPanel centerPane = new JPanel(new GridLayout(0,2,30,20));
		JPanel stu = new JPanel(new BorderLayout());
			JLabel student = new JLabel("학 생", JLabel.CENTER);

		JPanel tea = new JPanel(new BorderLayout());
			JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);	
	
	JTable stuTable;
	DefaultTableModel stuT;
	//제목
	String stuTitle[]	= {"아이디", "이름", "생년월일"};
	Object stuData[][]= {};

	JScrollPane stuSp;
	
	JTable teaTable;
	DefaultTableModel teaT;
	//제목
	String teaTitle[]	= {"아이디", "이름", "클래스"};
	Object teaData[][]= {};

	
	JScrollPane teaSp;
	
	public AdminMemberList() {
		setLayout(new BorderLayout());
        
		//배경 변경
		topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,30,30));
				
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		// 내용 수정 불가 시작 // 학생
        stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
		stuSp = new JScrollPane(stuTable);
		stuTable.getTableHeader().setFont(fntBold15);
		
		//내용 수정 불가 시작// 선생님
	    teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		teaTable = new JTable(teaT);
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
		
		stu.add(student,BorderLayout.NORTH); stu.add(stuSp);
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		
		centerPane.add(stu);
		centerPane.add(tea);
		
		getMemberAll();
		getTeacherAll();
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		
	}
	//학생 레코드넣기
	public void setNewTableList(List<MemberVO> lst) {
		stuT.setRowCount(0); //JTable의 레코드 지우기
		
		
		for(int i=0; i<lst.size(); i++) {
			MemberVO vo = lst.get(i);
			Object[] stuData = {vo.getId(), vo.getName(), vo.getCate()};
			stuT.addRow(stuData);
		}
	}
	public void setNewTeacherTableList(List<MemberVO> lst2) {
		teaT.setRowCount(0); //JTable의 레코드 지우기
		
		for(int i=0; i<lst2.size(); i++) {
			MemberVO vo2 = lst2.get(i);
			Object[] teaData = {vo2.getId(), vo2.getName(), vo2.getCate()};
			
			teaT.addRow(teaData);
		}
	}
	//회원선택
	public void getMemberAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst = dao.memberSelect();
		
		setNewTableList(lst);
	}
	//선생님선택
	public void getTeacherAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		MemberDAO dao2 = new MemberDAO();
		List<MemberVO> lst2 = dao2.TeacherAllSelect();
		
		setNewTeacherTableList(lst2);
	}

	public static void main(String[] args) {
		new AdminMemberList();

	}
=======
package administrator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dbConnection.MemberDAO;
import dbConnection.MemberVO;

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
		JLabel connList = new JLabel("회원 목록", JLabel.CENTER);
	
	JPanel centerPane = new JPanel(new GridLayout(0,2,30,20));
		JPanel stu = new JPanel(new BorderLayout());
			JLabel student = new JLabel("학 생", JLabel.CENTER);

		JPanel tea = new JPanel(new BorderLayout());
			JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);	
	
	JTable stuTable;
	DefaultTableModel stuT;
	//제목
	String stuTitle[]	= {"아이디", "이름", "생년월일"};
	Object stuData[][]= {};

	JScrollPane stuSp;
	
	JTable teaTable;
	DefaultTableModel teaT;
	//제목
	String teaTitle[]	= {"아이디", "이름", "클래스"};
	Object teaData[][]= {};

	
	JScrollPane teaSp;
	
	public AdminMemberList() {
		setLayout(new BorderLayout());
        
		//배경 변경
		topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		
		//폰트변경
		connList.setFont(fntBold30); student.setFont(fntBold20); teacher.setFont(fntBold20);
		
		//여백주기
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,30,30,30));
				
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		// 내용 수정 불가 시작 // 학생
        stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
		stuSp = new JScrollPane(stuTable);
		stuTable.getTableHeader().setFont(fntBold15);
		
		//내용 수정 불가 시작// 선생님
	    teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		teaTable = new JTable(teaT);
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
		
		stu.add(student,BorderLayout.NORTH); stu.add(stuSp);
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		
		centerPane.add(stu);
		centerPane.add(tea);
		
		getMemberAll();
		getTeacherAll();
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		
	}
	//학생 레코드넣기
	public void setNewTableList(List<MemberVO> lst) {
		stuT.setRowCount(0); //JTable의 레코드 지우기
		
		
		for(int i=0; i<lst.size(); i++) {
			MemberVO vo = lst.get(i);
			Object[] stuData = {vo.getId(), vo.getName(), vo.getCate()};
			stuT.addRow(stuData);
		}
	}
	public void setNewTeacherTableList(List<MemberVO> lst2) {
		teaT.setRowCount(0); //JTable의 레코드 지우기
		
		for(int i=0; i<lst2.size(); i++) {
			MemberVO vo2 = lst2.get(i);
			Object[] teaData = {vo2.getId(), vo2.getName(), vo2.getCate()};
			
			teaT.addRow(teaData);
		}
	}
	//회원선택
	public void getMemberAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst = dao.memberSelect();
		
		setNewTableList(lst);
	}
	//선생님선택
	public void getTeacherAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		MemberDAO dao2 = new MemberDAO();
		List<MemberVO> lst2 = dao2.TeacherAllSelect();
		
		setNewTeacherTableList(lst2);
	}

	public static void main(String[] args) {
		new AdminMemberList();

	}
>>>>>>> refs/remotes/origin/master
}