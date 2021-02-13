package administrator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dbConnection.BanDAO;
import dbConnection.BanVO;
import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class AdminReceiveMsg extends JPanel implements MouseListener, ActionListener, ItemListener {
	JPanel mainPane = new JPanel();
	
	JLabel sortLbl = new JLabel("정렬하기");
	//콤보박스
	JComboBox<String> sortBox;
	Vector<String> v = new Vector<String>();
	DefaultComboBoxModel<String> mcbm = new DefaultComboBoxModel<String>();
	
	JTable table;
		JScrollPane sp;
		DefaultTableModel model;
		
	// 테이블 필드명
	Object headList[] = {"선택","글번호","메세지내용","보낸사람","분류","보낸시간"};
	
	DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
	
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	
	JButton delLbl = new JButton("삭제하기");
	
	//제약어 추가
	JLabel consLbl = new JLabel("제약어 추가", JLabel.CENTER);
	JTextField consTf = new JTextField(); JButton consBtn = new JButton("추가");
	
	//설정된 제약어 리스트
	JLabel consListLbl = new JLabel("설정된 제약어 리스트", JLabel.CENTER);
	LineBorder lineBorder = new LineBorder(Color.black);
	JPanel consListPane = new JPanel(new GridLayout(0,4));
		//체크박스 데이터
	JButton clearBtn = new JButton("해제");
	
	//체크된 체크박스 저장
	List<String> lst = new ArrayList<String>();
	int overlap=0;
	public AdminReceiveMsg() {
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		
		//JComboBox
		v.add("글 번호");
		v.add("보낸사람");
		v.add("분류");
		v.add("수신시간");
		
		//정렬박스
		sortBox = new JComboBox<String>(v);
		
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
		table.getColumn("메세지내용").setPreferredWidth(270);
		table.getColumn("보낸사람").setPreferredWidth(120);
		table.getColumn("분류").setPreferredWidth(60);
		table.getColumn("보낸시간").setPreferredWidth(120);
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
		consLbl.setFont(fntBold20); 
		consListLbl.setFont(fntBold20);
		consBtn.setFont(fntBold15); clearBtn.setFont(fntBold15);
		
		// 배경색 설정
		table.getTableHeader().setBackground(Color.lightGray);
		table.getParent().setBackground(Color.white);
		delLbl.setBackground(col6);
		consBtn.setBackground(col6); clearBtn.setBackground(col6);
		consListPane.setBackground(Color.white);
		
		// 가운데 정렬
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
		tcmSchedule.getColumn(5).setCellRenderer(tScheduleCellRenderer);
		
		//JCheck패널 설정
		consListPane.setBorder(lineBorder);
		
		//mainPane에 add
		mainPane.add(sortLbl);mainPane.add(sortBox);
		mainPane.add(sp); mainPane.add(delLbl);
		mainPane.add(consLbl); 
		mainPane.add(consTf); mainPane.add(consBtn);
		mainPane.add(consListLbl); 
		mainPane.add(consListPane);
		mainPane.add(clearBtn);
		
		//데이터 호출
		adminMsgList();
		getBanListDef();
		
		// setBounds
		sortLbl.setBounds(580,0,100,30); sortBox.setBounds(639,0,100,30);
		sp.setBounds(0,30,745,350); delLbl.setBounds(630,390,100,30);
		consLbl.setBounds(280,410,200,50);
		consTf.setBounds(30,460,560,30); consBtn.setBounds(610,460,100,30);
		consListLbl.setBounds(252,510,250,30); 
		consListPane.setBounds(30,540,680,200);
		clearBtn.setBounds(610,755,100,30);
		
		table.addMouseListener(this);
		consTf.addMouseListener(this);
		delLbl.addActionListener(this);
		consBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		sortBox.addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		Object obj = e.getSource();
		if(clickBtn==1 && !(obj == consTf)) {
			//선택한 컬럼의 데이터 가져오기
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			int colCount = table.getColumnCount();
			Object value = table.getValueAt(row, col);
			if(value.equals("○")) {
				table.setValueAt("●", row, col);
			}else if(value.equals("●")) {
				table.setValueAt("○", row, col);
			}else if(col==2) {
				int msgNum = (int)table.getValueAt(row, 1);
				new AdminReceiveMsgDialog("master", msgNum);
			}
		}else if(clickBtn==1 && obj == consTf) {
			consTf.setText("");
		}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	//데이터 핸들링
	public void adminMsgList() {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.adminMsgRec();
		
		for(int i=0; i<lst.size(); i++) {
			ConsVO vo = lst.get(i);
			String sort="";
			if(vo.getSort()==1) {
				sort = "학생";
			}else if(vo.getSort()==2) {
				sort = "선생님";
			}
			Object[] data = {"○", vo.getMsg_num(),"<HTML> <U>"+vo.getMsg_title()+"</U></HTML>",vo.getSend(), sort ,vo.getSend_time()};

			model.addRow(data);
		}
	}
	//삭제하기 및 제약어추가
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
				adminMsgList();
			}else {
				JOptionPane.showMessageDialog(this, "선택한 메세지가 없습니다.");
			}
		}else if(delStr == consBtn) { //체크박스 추가 이벤트
			consListPane.removeAll();
			consListPane.setVisible(false);
			String data = consTf.getText();
			overlapBan(data);
			consListPane.setVisible(true);
			mainPane.add(consListPane);
		}else if(delStr == clearBtn) { //체크박스 해제 이벤트
			consListPane.removeAll();
			consListPane.setVisible(false);
			deleteItem();
			consListPane.setVisible(true);
			mainPane.add(consListPane);
		}else if(delStr == sortBox) {
			String sortStr = sortBox.getSelectedItem().toString();
			model.setRowCount(0);
			msgSort(sortStr);
		}
	}
	//정렬 메소드
	public void msgSort(String sortStr) {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.adminMsgSort(sortStr);
		if(lst.size() == 0) {
			JOptionPane.showMessageDialog(this, "받은 메세지가 없습니다.");
		}else {
			for(int i=0; i<lst.size(); i++) {
				ConsVO vo = lst.get(i);
				String sort="";
				if(vo.getSort()==1) {
					sort = "학생";
				}else if(vo.getSort()==2) {
					sort = "선생님";
				}
				Object[] data = {"○", vo.getMsg_num(),"<HTML> <U>"+vo.getMsg_title()+"</U></HTML>",vo.getSend(), sort ,vo.getSend_time()};
				
				model.addRow(data);
			}
		}
	}
	//제약어 중복확인
	public void overlapBan(String searchBan) {
		BanDAO dao = new BanDAO();
		List<BanVO> searchList = dao.overlapCheck(searchBan); 
		if(searchList.size() == 0) {
			overlap=2;
			if(searchBan.equals("")) {
				JOptionPane.showMessageDialog(this, "입력 후 버튼을 눌러주세요.");
			}else {
				getBanList(searchBan); //insert
				JOptionPane.showMessageDialog(this, "등록되었습니다.");
				consTf.setText("");
			}
			getBanListDef();
		}else {
			overlap=1;
			JOptionPane.showMessageDialog(this, "이미 등록된 제약어입니다. 다시 입력해주세요.");
			getBanListDef();
		}
	}
	//제약어 추가
	public void getBanList(String tfStr) {
		BanVO vo = new BanVO(tfStr);
		BanDAO dao = new BanDAO();
		int result = dao.insertBan(vo);
	}
	//제약어 리스트
	public void getBanListDef() {
		BanDAO dao = new BanDAO();
		List<BanVO> lst = dao.banList();
		
		for(int i=0; i<lst.size(); i++) {
			BanVO vo = lst.get(i);
			JCheckBox consListchk = new JCheckBox(vo.getDont());
			consListchk.addItemListener(this);
			consListchk.setFont(fntPlain15);
			consListchk.setBackground(Color.white);
			consListPane.add(consListchk);
		}
	}
	//제약어 선택/해제 데이터 핸들링
	public void itemStateChanged(ItemEvent ie) {
		JCheckBox obj = (JCheckBox)ie.getItem();
		if(ie.getStateChange() == ItemEvent.SELECTED) {
			lst.add(obj.getText());
		}else if(ie.getStateChange() == ItemEvent.DESELECTED){
			lst.remove(obj.getText());
		}
	}
	//제약어 삭제
	public void deleteItem() {
		int result = 0;
		if(lst.size()>0) {
			BanDAO dao = new BanDAO();
			for(int i=0; i<lst.size(); i++) {
				result = dao.banDelete(lst.get(i));
			}
			if(result>0) {
				JOptionPane.showMessageDialog(this, "선택된 제약어가 삭제되었습니다.");
			}
		}else {
			JOptionPane.showMessageDialog(this, "선택된 제약어가 없습니다.");
		}
		getBanListDef();
	}
}
