import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudenCateList {
	JPanel mainPane = new JPanel();
		JTextField searchTf = new JTextField(20);
		JButton searchBtn = new JButton("검색");
		// 테이블 필드명
		String lblStr[] = {"No", "클래스명", "지역", "비용", "강사"};
		String testStr [] = {"1","배워바요 쿠킹","경기도","40000","이영준(강사)"};
		
		JTable table;
        	JScrollPane sp;
        	DefaultTableModel model;
	public StudenCateList() {
		mainPane.setLayout(null);
		// JTable
		model = new DefaultTableModel(lblStr,0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		mainPane.add(sp);
		
		
		
		
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		StudenTopMenu ts = new StudenTopMenu();
		ts.add(BorderLayout.CENTER, mainPane);
	}
	
}
