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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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

import dbConnection.Mem_teacherDAO;
import dbConnection.Mem_teacherVO;
import studen.StudenReservationDetail;

public class TeachCateList extends JPanel implements ActionListener, MouseListener{
	JPanel mainPane = new JPanel();
	JTextField searchTf = new JTextField(20);
	JButton searchBtn = new JButton("검색");
	// 테이블 필드명
	String lblStr[] = {"No", "클래스명", "지역", "강사", "경력"};
	
	JTable table;
    	JScrollPane sp;
    	DefaultTableModel model;
    	
    	DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
    	
    JButton newListBtn = new JButton("새글쓰기");	
    	
    Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);

	public TeachCateList(String cate) {
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
		
		// 테이블 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
		
		//테이블 데이터
		getCateList(cate);
		
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
		
		mainPane.add(sp);
		
		table.addMouseListener(this);
		searchBtn.addActionListener(this);
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
		List<Mem_teacherVO> lst = dao.teaCateList(cate);
		
		for(int i=0; i<lst.size(); i++) {
			Mem_teacherVO vo = lst.get(i);
			Object[] data = {i+1,"<HTML><U>"+vo.getClassName()+"</U></HTML>",vo.getCity(),vo.gettName(),vo.getCareer()};
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
			int colCount = table.getColumnCount();
			Object value = table.getValueAt(row, col);
			if(col==1) {
				title = (String)model.getValueAt(row, 2); // 클래스명 가져오기 (혹시몰라서)
				TeachReservationDetail trvd = new TeachReservationDetail();
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	

}