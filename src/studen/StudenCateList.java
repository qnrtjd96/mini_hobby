package studen;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import administrator.AdminReceiveMsgDialog;
import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.Mem_teacherDAO;
import dbConnection.Mem_teacherVO;

public class StudenCateList extends JPanel implements ActionListener, MouseListener{
	public JPanel mainPane = new JPanel();
		JTextField searchTf = new JTextField(20);
		JButton searchBtn = new JButton("검색");
		// 테이블 필드명
		String lblStr[] = {"No", "클래스명", "지역", "비용", "강사"};
		
		JTable table;
        	JScrollPane sp;
        	DefaultTableModel model;
        	
        	DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
        	
        Color col6 = new Color(204,222,233);
    	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
    	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
    	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
    	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
    	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
    	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
    	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
    	
    String idStr;
    String cate;
    
    public StudenCateList() {}
	public StudenCateList(String cate, String idStr) {
		this.idStr=idStr;
		this.cate=cate;
		System.out.println("cate > > > "+cate);

		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		
		//JTable
		// 내용 수정 불가
		model = new DefaultTableModel(lblStr, 0) {
			 public boolean isCellEditable(int i, int c){ return false; }
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		// 컬럼 너비 조절
		table.getColumn("No").setPreferredWidth(10);
		table.getColumn("클래스명").setPreferredWidth(280);
		table.getColumn("지역").setPreferredWidth(100);
		table.getColumn("비용").setPreferredWidth(100);
		table.getColumn("강사").setPreferredWidth(100);
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
		
		// 테이블 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
		
		//테이블 데이터
		getCateList(cate);
		
		getSearchResult(cate); //검색결과
		
		// 검색 부분
		searchTf.setText(" 검색할 클래스명/지역/강사명을 입력하세요."); 
		mainPane.add(searchTf); mainPane.add(searchBtn);
		
		// 폰트 설정
		searchTf.setFont(fntBold15);
		table.getTableHeader().setFont(fntBold15);
		table.setFont(fntPlain15);
		searchBtn.setFont(fntBold15);
		
		// 배경색 설정
		searchBtn.setBackground(col6);
		table.getTableHeader().setBackground(col6);
		table.getParent().setBackground(Color.white);
		
		// 위치값 설정
		searchTf.setBounds(20,20,620,40); searchBtn.setBounds(660,20,100,40);
		sp.setBounds(20,80,745,775);
		
		// 클릭 시 초기화
		searchTf.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent me) {
					searchTf.setText("");
				}
			}
		);
		table.addMouseListener(this);
		searchBtn.addActionListener(this);
		
		mainPane.add(sp);
		
	}
	//id 받아오기
	public void getId(String id) {
		this.idStr = id;
	}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == searchBtn) {
			String searchTxt = searchTf.getText();
			if(searchTxt.equals("요리") || searchTxt.equals("스포츠") 
					 || searchTxt.equals("미술")  || searchTxt.equals("음악") ) {
				model.setRowCount(0); // model 초기화
				getCateList(searchTxt);
			}else {
				JOptionPane.showMessageDialog(this, "음악, 스포츠, 미술, 요리 중 검색하세요.");
			}
		}
	}
	
	public void getCateList(String cate) {
		Mem_teacherDAO dao = new Mem_teacherDAO();
		List<Mem_teacherVO> lst = dao.cateList(cate);
		for(int i=0; i<lst.size(); i++) {
			Mem_teacherVO vo = lst.get(i);
			Object[] data = {vo.getClass_num(),"<HTML><U>"+vo.getClassName()+"</U></HTML>",vo.getCity(),vo.getCost(),vo.gettName()};
			model.addRow(data);
		}
	}
	
	public void getSearchResult(String searchWord ) {
		Mem_teacherDAO dao = new Mem_teacherDAO();
		List<Mem_teacherVO> searchList = dao.getSearch(searchWord);
		for(int idx=0; idx<searchList.size(); idx++ ) {
			Mem_teacherVO vo = searchList.get(idx);
			Object[] data = {vo.getClass_num(),"<HTML><U>"+vo.getClassName()+"</U></HTML>",vo.getCity(),vo.getCost(),vo.gettName()};
			model.addRow(data);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		String title;
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			if(col==1) {
				title = (String)model.getValueAt(row, 1); // 클래스명 가져오기 (혹시몰라서)
				int class_num = (int)model.getValueAt(row, 0);
				new StudenReservationDetail(idStr, class_num, title);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(idStr);
			System.exit(0);
		}
	}
	
}