package administrator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dbConnection.Acess_memDAO;
import dbConnection.Acess_memVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;


public class AdminLiveChat extends JPanel implements MouseListener, ActionListener, Runnable{
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
	Object stuData[][]= {};
	JScrollPane stuSp;
	JTable stuTable;
	DefaultTableModel stuT;
	
	//제목
	String teaTitle[]	= {"접속여부", "아이디"};
	Object teaData[][]= {};
	
	JScrollPane teaSp;
	JTable teaTable;
	DefaultTableModel teaT;
	String id = "master";
	public AdminLiveChat() {
		
		setLayout(new BorderLayout());
        
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		stu.setBackground(Color.WHITE); student.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		bottomPane.setBackground(Color.WHITE); 
		
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
        stuT = new DefaultTableModel(stuData, stuTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		stuTable = new JTable(stuT);
			stuTable.setRowHeight(30);
			stuTable.setFont(fntPlain15);
			stuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			stuTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
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
		
		//테이블넣기
		getMemberAll();
		getTeacherAll();
		
		//테이블 계속돌리기(실시간채팅이기떄문에)
		Thread t = new Thread(this);
		t.start();
		
		//이벤트 주입
		stuTable.addMouseListener(this);
		teaTable.addMouseListener(this);
		chatt.addActionListener(this);
		admin.addActionListener(this);
		
	}
	//학생 레코드넣기
	public void setNewTableList(List<Acess_memVO> lst) {
		stuT.setRowCount(0); //JTable의 레코드 지우기
		
		for(int i=0; i<lst.size(); i++) {
			Acess_memVO vo = lst.get(i);
			Object[] stuData = {"○", vo.getId(), vo.getId()};
			stuT.addRow(stuData);
		}
	}
	public void setNewTeacherTableList(List<Acess_memVO> lst2) {
		teaT.setRowCount(0); //JTable의 레코드 지우기
		
		for(int i=0; i<lst2.size(); i++) {
			Acess_memVO vo2 = lst2.get(i);
			Object[] teaData = {"○", vo2.getId(), vo2.getName()};
			
			teaT.addRow(teaData);
		}
	}
	//회원선택
	public void getMemberAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		Acess_memDAO dao = new Acess_memDAO();
		List<Acess_memVO> lst = dao.LiveChattStu();
		
		setNewTableList(lst);
	}
	//선생님선택
	public void getTeacherAll() {
		//데이터베이스의 모든 회원을 선택해서 JTable에 표시한다
		Acess_memDAO dao2 = new Acess_memDAO();
		List<Acess_memVO> lst2 = dao2.LiveChattpeople();
		
		setNewTeacherTableList(lst2);
	}
	public static void main(String[] args) {
		new AdminLiveChat();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("채팅하기")) { //채팅하기 선택다중으로됐을때, 채팅불가능하게바꾸기
			for(int i=0; i<teaTable.getRowCount(); i++) {
				if(teaTable.getValueAt(i, 0).equals("●")) {
					JOptionPane.showMessageDialog(this, "선택한 선생님과 채팅이 연결됩니다.");
					new main.Main4ChatClient(id);
				}
			}
			for(int i=0; i<stuTable.getRowCount(); i++) {
				if(stuTable.getValueAt(i, 0).equals("●")) {
					JOptionPane.showMessageDialog(this, "선택한 학생과 채팅이 연결됩니다.");
					new main.Main4ChatClient(id);
				}
			}
		}else if(eventBtn.equals("관리자와 채팅")){ //미구현
			new main.Main4ChatClient(id);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = stuTable.getSelectedRow();
			int col = stuTable.getSelectedColumn();
			Object value = stuTable.getValueAt(row, col);
			if(value.equals("○")) {
				stuTable.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				stuTable.setValueAt("○", row, col);
			}
		}else if(clickBtn ==3) {
			//선택한 컬럼의 데이터 가져오기
			int row = teaTable.getSelectedRow();
			int col = teaTable.getSelectedColumn();
			Object value = teaTable.getValueAt(row, col);
			if(value.equals("○")) {
				teaTable.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				teaTable.setValueAt("○", row, col);
			}
		}
	}
	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(3000);}catch(Exception e) {}
			getTeacherAll();
			getMemberAll();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
