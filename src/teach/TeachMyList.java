package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;

public class TeachMyList extends JPanel implements MouseListener, ActionListener{
	JPanel mainPane = new JPanel();
	JTextField searchTf = new JTextField(20);
	JButton searchBtn = new JButton("검색");
	// 테이블 필드명
	String lblStr[] = {"No", "클래스명", "지역", "클래스일자", "클래스시간"};
	String data[][] = new String[0][lblStr.length];
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
	
	String id;
	public TeachMyList() {}

	public TeachMyList(String id) {
		this.id=id;
		
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		
		// JTable - 745
		model = new DefaultTableModel(data,lblStr);
		table = new JTable(model);
		sp = new JScrollPane(table);
		mainPane.add(sp);
		// 테이블 컬럼 높이 조절
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 50;
		    return d;
			}
		});
		// 컬럼 너비 조절
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("No").setPreferredWidth(10); table.getColumn("No").setCellRenderer(dtcr);
		table.getColumn("클래스명").setPreferredWidth(280);
		table.getColumn("지역").setPreferredWidth(100); table.getColumn("지역").setCellRenderer(dtcr);
		table.getColumn("클래스일자").setPreferredWidth(100); table.getColumn("클래스일자").setCellRenderer(dtcr);
		table.getColumn("클래스시간").setPreferredWidth(100); table.getColumn("클래스시간").setCellRenderer(dtcr);
		
		
		table.getParent().setBackground(Color.white);
		table.setFont(fntPlain15);
		table.setRowHeight(30); 
		table.getTableHeader().setBackground(col6);
		table.getTableHeader().setFont(fntBold20);
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.teachMyList(id);
		for (int i=0; i<lst.size(); i++) {
			BoardVO vob = lst.get(i);
			Object obj[] = {vob.getClass_num(), vob.getClassname(), vob.getArea(), vob.getClassdate(),
					vob.getClasstime()
			};
			model.addRow(obj);
		}
		
		// 검색 부분
		searchTf.setText(" 검색할 카테고리명을 입력하세요."); 
		mainPane.add(searchTf); mainPane.add(searchBtn);
		mainPane.add(newListBtn);
		
		// 폰트 설정
		searchTf.setFont(fntBold15);
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
		searchTf.addMouseListener(this);
		table.addMouseListener(this);
		newListBtn.addActionListener(this);
	}
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if (obj==searchTf) {
			searchTf.setText("");
		} else if (obj==table) {
			int col = table.getSelectedColumn();
			int row = table.getSelectedRow();
			String classname = (String)table.getValueAt(row, col);
			int class_num = (int) table.getValueAt(row, 0);
			String classdate = (String)table.getValueAt(row, 3);
			if (col==1) {
				new TeachReservationDetail(id, classname, class_num, classdate);
			}
		}	
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(id);
			System.exit(0);
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj= ae.getSource();
		if(obj==newListBtn) {
			new TeachTextCreate(id);
		}
		
	}

}