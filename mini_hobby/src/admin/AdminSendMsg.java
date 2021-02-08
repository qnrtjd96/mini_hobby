package admin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

public class AdminSendMsg extends JPanel implements MouseListener {
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
	Object headList[] = {"선택","글번호","메세지내용","받을사람","분류","보낸시간"};
	// 테이블 레코드 테스트 값
	Object recoList1[] = {"○","1","<HTML><U> 답변드려요</U></HTML>","adfg1234(홍길동)","학생","2021.02.04 14:55"};
	Object recoList2[] = {"○","2","<HTML><U> 이거이제댐</U></HTML>","ggdg123(감길동)","선생님","2021.02.04 17:11"};
	
	DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
	
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	
	JLabel delLbl = new JLabel("삭제하기", JLabel.CENTER);
	
	//제약어 추가
	JLabel consLbl = new JLabel("제약어 추가", JLabel.CENTER);
	JTextField consTf = new JTextField(); JButton consBtn = new JButton("추가");
	
	//설정된 제약어 리스트
	JLabel consListLbl = new JLabel("설정된 제약어 리스트", JLabel.CENTER);
	LineBorder lineBorder = new LineBorder(Color.black);
	JPanel consListPane = new JPanel();
		JCheckBox consListChk, consListChk2;
		//데이터 테스트
		String consListStr = ""; 
		List<String> consList = new ArrayList<String>();
	JButton clearBtn = new JButton("해제");
	public AdminSendMsg() {
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		
		//JComboBox
		v.add("글 번호");
		v.add("받을사람");
		v.add("분류");
		v.add("수신시간");
		
		sortBox = new JComboBox<String>(v);
		

		//CheckBox
		//체크박스 데이터 생성 테스트 arrayList, String
		consList.add("시베리안");
		consListStr = "파친";
		
		//체크박스 데이터 넣기
		consListChk = new JCheckBox(String.valueOf(consList));
		consListChk2 = new JCheckBox(consListStr);
		
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
		table.getColumn("받을사람").setPreferredWidth(120);
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
		delLbl.setFont(fntPlain15);
		consLbl.setFont(fntBold20); 
		consListLbl.setFont(fntBold20);
		consBtn.setFont(fntBold15); clearBtn.setFont(fntBold15);
		consListChk.setFont(fntPlain15);
		consListChk2.setFont(fntPlain15);
		
		// 배경색 설정
		table.getTableHeader().setBackground(Color.lightGray);
		table.getParent().setBackground(Color.white);
		consBtn.setBackground(col6); clearBtn.setBackground(col6);
		consListPane.setBackground(Color.white);
		consListChk.setBackground(Color.white);
		consListChk2.setBackground(Color.white);
		
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
		consListPane.add(consListChk); consListPane.add(consListChk2);
		
		//mainPane에 add
		mainPane.add(sortLbl);mainPane.add(sortBox);
		mainPane.add(sp); mainPane.add(delLbl);
		mainPane.add(consLbl); 
		mainPane.add(consTf); mainPane.add(consBtn);
		mainPane.add(consListLbl); 
		mainPane.add(consListPane);
		mainPane.add(clearBtn);
		
		model.addRow(recoList1);
		model.addRow(recoList2);
		
		// setBounds
		sortLbl.setBounds(580,0,100,30); sortBox.setBounds(639,0,100,30);
		sp.setBounds(0,30,745,350); delLbl.setBounds(640,370,100,50);
		consLbl.setBounds(280,390,200,50);
		consTf.setBounds(30,440,560,30); consBtn.setBounds(610,440,100,30);
		consListLbl.setBounds(252,500,250,30); 
		consListPane.setBounds(30,540,680,200);
		clearBtn.setBounds(610,755,100,30);
		
		table.addMouseListener(this);
		delLbl.addMouseListener(this);
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
				this.setVisible(false);
				removeAll();
				new AdminMsgWrite();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		/* 삭제 구현 못함 .... 
		  
		Object delStr = e.getSource();
		if(delStr == delLbl) {
			// 데이터 삭제 구현
			
		}
		
		*/
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
