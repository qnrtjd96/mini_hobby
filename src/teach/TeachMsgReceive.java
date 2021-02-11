package teach;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;
import studen.StudenReceiveMsgDialog;

public class TeachMsgReceive extends JPanel implements MouseListener, ActionListener{

	JPanel mainPane = new JPanel();
	JTable table;
		JScrollPane sp;
		DefaultTableModel model;
		
	// 테이블 필드명
	Object headList[] = {"선택","글번호","메세지내용","보낸사람","보낸시간"};
	
	DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
	
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	
	JButton delLbl = new JButton("삭제하기");
	String idStr;
	public TeachMsgReceive(String id) {
		this.idStr = id;
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
	
		//JTable
		// 내용 수정 불가
		model = new DefaultTableModel(headList, 0) {
			 public boolean isCellEditable(int i, int c){ return false; }
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		// 컬럼 너비 조절
		table.getColumn("선택").setPreferredWidth(30);
		table.getColumn("글번호").setPreferredWidth(30);
		table.getColumn("메세지내용").setPreferredWidth(300);
		table.getColumn("보낸사람").setPreferredWidth(100);
		table.getColumn("보낸시간").setPreferredWidth(100);
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
		
		// 폰트 설정
		table.getTableHeader().setFont(fntBold15);
		table.setFont(fntPlain15);
		delLbl.setFont(fntBold15);
		
		// 배경색 설정
		table.getTableHeader().setBackground(Color.lightGray);
		table.getParent().setBackground(Color.white);
		
		// 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
		
		//mainPane에 add
		mainPane.add(sp); mainPane.add(delLbl);
		
		msgList(id);
		
		// setBounds
		sp.setBounds(0,0,745,770); delLbl.setBounds(620,790,100,30);
		table.addMouseListener(this);
		delLbl.addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		if(clickBtn==1) {
			//선택한 컬럼의 데이터 가져오기
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			Object value = table.getValueAt(row, col);
			if(value.equals("○")) {
				table.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				table.setValueAt("○", row, col);
			}else if(col==2) {
				//msg_num 구하기
				int msgNum = (int)table.getValueAt(row, 1);
				//받은메세지 다이어로그 호출
				new TeachReceiveMsgDialog(idStr, msgNum);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	//삭제하기
	public void actionPerformed(ActionEvent ae) {
		Object delStr = ae.getSource();
		if(delStr == delLbl) {
			int result=0;
			// 데이터 삭제 구현
			for(int i=0; i<table.getRowCount(); i++) {
				if(table.getValueAt(i, 0).equals("●")) {
					int msgNum = (int)table.getValueAt(i, 1);
					ConsDAO dao = new ConsDAO();
					result = dao.msgDelete(msgNum);
				}
			}
			if(result>0) {
				JOptionPane.showMessageDialog(this, "선택한 메세지가 삭제되었습니다.");
				model.setRowCount(0);
				msgList(idStr);
			}else {
				JOptionPane.showMessageDialog(this, "선택된 메세지가 없습니다....");
			}
		}
	}
	
	//데이터 핸들링
	public void msgList(String id) {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.studenMsgRec(id);
		
		for(int i=0; i<lst.size(); i++) {
			ConsVO vo = lst.get(i);
			Object[] data = {"○", vo.getMsg_num(),"<HTML> <U>"+vo.getMsg_title()+"</U></HTML>",vo.getSend(),vo.getSend_time()};
			model.addRow(data);
		}
	}
}
