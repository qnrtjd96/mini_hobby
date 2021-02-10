package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class StudenLiveChat extends JPanel{
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
		JPanel centerPane = new JPanel(new BorderLayout());
			JPanel tea = new JPanel(new BorderLayout());
				JLabel teacher = new JLabel("선 생 님", JLabel.CENTER);
				//임시데이터 teaTable, teatitle, teadata	
			
		JPanel bottomPane = new JPanel(new BorderLayout());
			JButton chatt = new JButton("채팅하기");
			JButton admin = new JButton("관리자와 채팅");
	//선생님테이블 임시데이터
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
		
	public StudenLiveChat() {
		setLayout(new BorderLayout());
		//배경 변경
		mainPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		connList.setBackground(Color.WHITE); centerPane.setBackground(Color.WHITE);
		tea.setBackground(Color.WHITE); teacher.setBackground(Color.WHITE);
		bottomPane.setBackground(Color.WHITE); //chatt.setBackground(Color.WHITE);
//		stuTable.setBackground(Color.WHITE); teaTable.setBackground(Color.WHITE);
//		stuSp.setBackground(Color.WHITE);	teaSp.setBackground(Color.WHITE);
//		stuSp.getViewport().setBackground(Color.WHITE);
//		teaSp.getViewport().setBackground(Color.WHITE);
		//폰트변경
		connList.setFont(fntBold30); teacher.setFont(fntBold20);
		chatt.setFont(fntBold20); admin.setFont(fntBold20);
		
		//여백주기
		//int top, int left, int bottom, int right)
		centerPane.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//상단
		topPane.add(connList,BorderLayout.CENTER);
		
		//중단
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
		
		DefaultTableCellRenderer center1 = new DefaultTableCellRenderer();
		center1.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule1 = teaTable.getColumnModel();
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
				tcmSchedule1.getColumn(i).setCellRenderer(center1);
		}
		
		tea.add(teacher,BorderLayout.NORTH); tea.add(teaSp);
		centerPane.add(BorderLayout.CENTER,tea);
		
		//하단
		bottomPane.add(chatt,BorderLayout.EAST);
		bottomPane.add(admin,BorderLayout.WEST);
		
		//전체패널에 추가
		add(topPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		new StudenLiveChat();
	}
}