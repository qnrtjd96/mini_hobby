package administrator;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.ConsDAO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;

public class AdminPaymentDialog extends JFrame implements ActionListener,  MouseListener{
	JPanel mainPane = new JPanel();
		JLabel topLbl;
		JTable diaTable;
			JScrollPane sp;
			DefaultTableModel model;
		
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			
		JButton cancleBtn = new JButton("결제취소");
	// 테이블 필드명
	Object headList[] = {"선택","강사아이디","이름","금액","결제일자"};
	// 테이블 레코드 테스트 값
	//Object recoList1[] = {"○","adfg1234","김강사","30000","2021.02.07"};
	//Object recoList2[] = {"○","ggdg123","박강사","20000","2021.01.15"};
	
	Color col6 = new Color(204,222,233);
	Font fntPlain13 = new Font("맑은 고딕", Font.PLAIN, 13);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	
	String id;
	public AdminPaymentDialog() {};
	public AdminPaymentDialog(String id, String name) {
		this.id = id;
		mainPane.setLayout(null);
		//기본셋팅
		topLbl = new JLabel(id+"("+name+")"+"학생님의 결제내역 입니다.", JLabel.CENTER);
		
		//JTable
		// 내용 수정 불가
		model = new DefaultTableModel(headList, 0) {
			 public boolean isCellEditable(int i, int c){ return false; }
		};
		diaTable = new JTable(model);
		sp = new JScrollPane(diaTable);
		
		// 컬럼 너비 조절
		diaTable.getColumn("선택").setPreferredWidth(40);
		diaTable.getColumn("강사아이디").setPreferredWidth(130);
		diaTable.getColumn("이름").setPreferredWidth(80);
		diaTable.getColumn("금액").setPreferredWidth(80);
		diaTable.getColumn("결제일자").setPreferredWidth(100);
		diaTable.setAlignmentX(JLabel.CENTER);
		
		// 테이블 필드명 높이 조절
		diaTable.setTableHeader(new JTableHeader(diaTable.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 35;
		    return d;
			}
		});
		
		// 셀 이동 불가
		diaTable.getTableHeader().setReorderingAllowed(false);
		
		// 테이블 레코드 높이 조절
		diaTable.setRowHeight(30);
		
		// 테이블 배경색
		diaTable.getTableHeader().setBackground(Color.lightGray);
		diaTable.getParent().setBackground(Color.white);
	
		// 테이블 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = diaTable.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
		
		
		// 테이블 데이터
		getBoardList();
		//model.addRow(recoList1);
		//model.addRow(recoList2);
		
		// 위치값
		topLbl.setBounds(0,0,485,30);
		sp.setBounds(0,30,485,380);
		cancleBtn.setBounds(370,420,100,30);
		
		// 폰트설정
		topLbl.setFont(fntPlain13);
		diaTable.getTableHeader().setFont(fntBold15);
		diaTable.setFont(fntPlain13);
		cancleBtn.setFont(fntPlain13);
		
		// 배경색 셋팅
		mainPane.setBackground(Color.white);
		cancleBtn.setBackground(col6);
		
		mainPane.add(topLbl);
		mainPane.add(sp);
		mainPane.add(cancleBtn);
		
		add("Center",mainPane);
		setTitle("결제횟수");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		diaTable.addMouseListener(this);
		cancleBtn.addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = diaTable.getSelectedRow();
			int col = diaTable.getSelectedColumn();
			Object value = diaTable.getValueAt(row, col);
			if(value.equals("○")) {
				diaTable.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				diaTable.setValueAt("○", row, col);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//결제 상세내역 선택
	public void getBoardList() {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.PaymentSelect(id);
		
		for(int i=0; i<lst.size();i++) {
			BoardVO vo = lst.get(i);
			Object[] data = {"○", vo.getId(), vo.getName(), vo.getCost(), vo.getPay_date()};
			model.addRow(data);
		}
	}
	// 결제취소
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == cancleBtn) {
			int result=0;
			// 데이터 삭제 구현
			for(int i=0; i<diaTable.getRowCount(); i++) {
				if(diaTable.getValueAt(i, 0).equals("●")) {
					int num = (int)diaTable.getValueAt(i, 1);
					BoardDAO dao = new BoardDAO();
					result = dao.paymentDel(num);
				}
			}
			if(result>0) {
				JOptionPane.showMessageDialog(this, "선택한 결제내역이 삭제되었습니다.");
				model.setRowCount(0);
				getBoardList();
			}else {
				JOptionPane.showMessageDialog(this, "선택된 결제내역이 없습니다.");
			}
		}
	}
}
