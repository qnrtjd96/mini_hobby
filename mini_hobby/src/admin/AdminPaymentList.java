package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class AdminPaymentList extends JPanel implements MouseListener{
	JPanel mainPane = new JPanel();
		JLabel pmtLbl = new JLabel("학생 결제내역 리스트", JLabel.CENTER);
		
		JTable table;
			JScrollPane sp;
			DefaultTableModel model;
			
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			
	// 테이블 필드명
	Object headList[] = {"아이디","이름","결제횟수"};
	// 테이블 레코드 테스트 값
	Object recoList1[] = {"adfg1234","홍길동","<HTML><U>3</U><HTML>"};
	Object recoList2[] = {"ggdg123","강길동","<HTML><U>2</U><HTML>"};
	
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	public AdminPaymentList() {
		mainPane.setLayout(null);
		mainPane.setBackground(col6);
		
		//JTable
		// 내용 수정 불가
		model = new DefaultTableModel(headList, 0) {
			 public boolean isCellEditable(int i, int c){ return false; }
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		// 컬럼 너비 조절
		//table.getColumn("아이디").setPreferredWidth(30);
		//table.getColumn("이름").setPreferredWidth(30);
		//table.getColumn("결제횟수").setPreferredWidth(270);
		table.setAlignmentX(JLabel.CENTER);
		
		// 테이블 필드명 높이 조절
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 40;
		    return d;
			}
		});
		
		// 셀 이동 불가
		table.getTableHeader().setReorderingAllowed(false);
		
		// 테이블 레코드 높이 조절
		table.setRowHeight(30);
		
		// 테이블 배경색
		table.getTableHeader().setBackground(Color.lightGray);
		table.getParent().setBackground(Color.white);
	
		// 테이블 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
		
		// 테이블 데이터
		model.addRow(recoList1);
		model.addRow(recoList2);
		
		// 테이블 위치조정
		pmtLbl.setBounds(275,20,200,30);
		sp.setBounds(20,80,700,715);
		
		// 폰트 설정
		pmtLbl.setFont(fntBold20);
		table.getTableHeader().setFont(fntBold15);
		table.setFont(fntPlain15);
		
		mainPane.add(pmtLbl);
		mainPane.add(sp);
		
		//이벤트 등록
		table.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		String idStr, nameStr;
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			int colCnt = table.getColumnCount();
			if(col == 2) {
				idStr = ((String)model.getValueAt(row, 0));
				nameStr = ((String)model.getValueAt(row, 1));
				new AdminPaymentDialog(idStr, nameStr);
			}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
