import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TeachCateList {
	JPanel mainPane = new JPanel();
	JTextField searchTf = new JTextField(20);
	JButton searchBtn = new JButton("검색");
	// 테이블 필드명
	String lblStr[] = {"No", "클래스명", "지역", "강사", "경력"};
	
	JTable table;
    	JScrollPane sp;
    	DefaultTableModel model;
    
    JButton newListBtn = new JButton("새글쓰기");	
    	
    Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);

	public TeachCateList() {
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		
		// JTable
		model = new DefaultTableModel(lblStr,0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		mainPane.add(sp);
		// 컬럼 너비 조절
		table.getColumn("No").setPreferredWidth(10);
		table.getColumn("클래스명").setPreferredWidth(280);
		table.getColumn("지역").setPreferredWidth(100);
		table.getColumn("강사").setPreferredWidth(100);
		table.getColumn("경력").setPreferredWidth(100);
		// 테이블 필드명 높이 조절
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 40;
		    return d;
			}
		});
		// 테이블 레코드 높이 조절
		table.setRowHeight(30);
		
		// 검색 부분
		searchTf.setText(" 검색할 클래스명/지역/강사명을 입력하세요."); 
		mainPane.add(searchTf); mainPane.add(searchBtn);
		mainPane.add(newListBtn);
		
		// 폰트 설정
		searchTf.setFont(fntBold15);
		table.getTableHeader().setFont(fntBold15);
		table.setFont(fntPlain15);
		searchBtn.setFont(fntBold15);
		newListBtn.setFont(fntBold15);
		
		// 배경색 설정
		searchBtn.setBackground(col6);
		table.getTableHeader().setBackground(col6);
		table.getParent().setBackground(Color.white);
		newListBtn.setBackground(col6);
		
		// 위치값 설정
		searchTf.setBounds(20,20,620,40); searchBtn.setBounds(660,20,100,40);
		sp.setBounds(20,80,745,715);
		newListBtn.setBounds(663,815,100,40);
		
		// 클릭 시 초기화
		searchTf.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent me) {
					searchTf.setText("");
				}
			}
		);
		
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		StudenTopMenu ts = new StudenTopMenu();
		ts.add(BorderLayout.CENTER, mainPane);
	}

}
