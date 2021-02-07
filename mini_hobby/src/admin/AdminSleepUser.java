package admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class AdminSleepUser extends JPanel implements MouseListener{
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
		JLabel connList = new JLabel("휴먼계정 리스트", JLabel.CENTER);
		
	JPanel centerPane = new JPanel(new BorderLayout());
		JPanel tea = new JPanel(new BorderLayout());
	
	JPanel bottomPane = new JPanel(new BorderLayout());
		JButton chatt = new JButton("계정삭제");
		JLabel admin = new JLabel("※접속 30일이 경과한 유저 목록입니다.");
	
	//제목
		String teaTitle[]	= {"선택", "번호", "아이디", "분류", "마지막 접속일"};
		Object teaData[][]= {
				{"○","1","User1234(홍길동)","학생","1995.05.16"},
				{"○","2","User2222(김길동)","선생님","1993.02.13"},
				{"○","3","User3333(박길동)","학생","1996.01.23"},
				{"○","4","User4444(이길동)","선생님","1990.09.02"},
				{"○","5","User5555(강길동)","선생님","2000.03.30"},	
		};
		JScrollPane teaSp;
	
		JTable teaTable;
	public AdminSleepUser() {
		setLayout(new BorderLayout());
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); 
		bottomPane.setBackground(Color.WHITE); //chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); chatt.setFont(fntBold20); admin.setFont(fntBold20);
		
		//여백주기
		//int top, int left, int bottom, int right)
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
		DefaultTableModel teaT = new DefaultTableModel(teaData, teaTitle) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		teaTable = new JTable(teaT);
			teaTable.setRowHeight(30);
			teaTable.setFont(fntPlain15);
			teaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //여러개 클릭못하게막기
			teaTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
			teaTable.getColumn("선택").setPreferredWidth(30);
			teaTable.getColumn("번호").setPreferredWidth(30);
			teaTable.getColumn("아이디").setPreferredWidth(160);
			teaTable.getColumn("분류").setPreferredWidth(60);
			teaTable.getColumn("마지막 접속일").setPreferredWidth(160); 
			
		teaSp = new JScrollPane(teaTable);
		teaTable.getTableHeader().setFont(fntBold15);
		
		DefaultTableCellRenderer center1 = new DefaultTableCellRenderer();
		center1.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule1 = teaTable.getColumnModel();
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
				tcmSchedule1.getColumn(i).setCellRenderer(center1);
		}
		
		tea.add(teaSp);
		centerPane.add(BorderLayout.CENTER,tea);
		
		//하단
		bottomPane.add(chatt,BorderLayout.EAST);
		bottomPane.add(admin,BorderLayout.WEST);
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		
		//기능추가
		teaTable.addMouseListener(this);
		chatt.addMouseListener(this);
	}

	public static void main(String[] args) {
		new AdminSleepUser();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		System.out.println("click btn = "+ clickBtn);
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = teaTable.getSelectedRow();
			int col = teaTable.getSelectedColumn();
			System.out.println("row, col = " + row + " , " + col);
			Object value = teaTable.getValueAt(row, col);
			System.out.println("value = "+ value);
			if(value.equals("○")) {
				teaTable.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				teaTable.setValueAt("○", row, col);
			}else if(col==-1 && row == -1) {
				//삭제하기는 데이터끌어와서 구현할것
			}
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