package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dbConnection.Acess_memDAO;
import dbConnection.ReviewDAO;
import dbConnection.ReviewVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class TeachClassList extends JPanel {
	
		JPanel start = new JPanel();
			JLabel list = new JLabel("내 클래스 내역");
			
			String listcol[] = {"클래스명","수강비","예약일","수강생"};
			String listdata[][];
			DefaultTableModel listModel = new DefaultTableModel(listdata, listcol);
			JTable listTbl = new JTable(listModel);
			JScrollPane listsp = new JScrollPane(listTbl);
			
			JLabel myReview = new JLabel("나의 강의 평가");
				JPanel pane = new JPanel(new GridLayout(0,1));
					
			JScrollPane sp = new JScrollPane(pane);
					
	Color col6 = new Color(204,222,233);
	Font fn15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fn20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fn25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fn30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fnt15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fnt20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fnt25 = new Font("맑은 고딕", Font.BOLD, 25);
	
	Dimension lblsize = new Dimension(70, 30);
	String idStr;
	public TeachClassList() {};
	public TeachClassList(String id) {
		idStr = id;
		setBackground(Color.white);
		add(start); start.setBackground(Color.white);
		start.setBorder(new LineBorder(Color.black)); start.setLayout(null);
		start.add(list); start.add(listsp); start.add(myReview); start.add(sp);
		list.setBounds(20,10,200,50); list.setFont(fnt25);
		listsp.setBounds(20,70,520,330); //table 520
		listTbl.setTableHeader(new JTableHeader(listTbl.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 40;
		    return d;
			}
		});
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		listTbl.getParent().setBackground(Color.white);
		listTbl.setFont(fn15);
		listTbl.setRowHeight(30);
		listTbl.getTableHeader().setBackground(col6);
		listTbl.getTableHeader().setFont(fnt20);
		
		listTbl.getColumn("클래스명").setPreferredWidth(200);
		listTbl.getColumn("수강비").setPreferredWidth(100);
		listTbl.getColumn("예약일").setPreferredWidth(110);
		listTbl.getColumn("수강생").setPreferredWidth(100);
		listTbl.getColumn("수강비").setCellRenderer(dtcr);
		listTbl.getColumn("예약일").setCellRenderer(dtcr);
		listTbl.getColumn("수강생").setCellRenderer(dtcr);
		
		myReview.setBounds(20,410,200,50); myReview.setFont(fnt25);
		sp.setBounds(20,470,520,300); pane.setBackground(Color.white);
		
		tableSetting1();
		tableSetting2();
		
		setVisible(true);
	}
	//list 뽑아오기
	public void tableSetting1() {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.teachList(idStr);
		if(lst.size()>0) {
			for (int i=0; i<lst.size(); i++) {
				Stu_ClassVO vo = lst.get(i);
				Object obj[] = {vo.getPay_class(), vo.getPay(), vo.getClassdate(), vo.getId()};
				listModel.addRow(obj);
			}
		}
	}
	public void tableSetting2() {
		ReviewDAO dao = new ReviewDAO();
		List<ReviewVO> lst = dao.teachReviewList(idStr);
		if(lst.size()>0) {
			for(int i=0; i<lst.size(); i++) {
				ReviewVO vo = lst.get(i);
				String st="★";
				if(vo.getScore()==2) st="★★";
				else if(vo.getScore()==3) st="★★★";
				else if(vo.getScore()==4) st="★★★★";
				else if(vo.getScore()==5) st="★★★★★";
				JPanel star = new JPanel(new BorderLayout());
				JLabel starLbl = new JLabel("별점");
				JLabel starLbl2 = new JLabel(st);
				star.add("West", starLbl); star.add(starLbl2);
				starLbl.setFont(fn15); starLbl2.setFont(fn15);
				starLbl.setPreferredSize(lblsize);
				pane.add(star); star.setBackground(Color.white);
				JPanel content = new JPanel(new BorderLayout());
				JLabel contentLbl = new JLabel("구매후기");
				JLabel contentLbl2 = new JLabel(vo.getReview_detail());
				pane.add(content); content.setBackground(Color.white);
				content.add("West", contentLbl); content.add(contentLbl2);
				contentLbl.setFont(fn15); contentLbl2.setFont(fn15);
				contentLbl.setPreferredSize(lblsize);
				content.setBorder(new LineBorder(Color.LIGHT_GRAY));
			}
		}
	}
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
