import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TeachClassList extends JPanel{
	
		JPanel start = new JPanel();
			JLabel list = new JLabel("내 클래스 내역");
			
			DefaultComboBoxModel<Integer> year = new DefaultComboBoxModel<Integer>();
			JComboBox<Integer> combo = new JComboBox<Integer>();
			
			String listcol[] = {"클래스명","수강비","예약일","수강생"};
			String listdata[][] = new String [0][listcol.length];
			DefaultTableModel listModel = new DefaultTableModel(listdata, listcol);
			JTable listTbl = new JTable(listModel);
			JScrollPane listsp = new JScrollPane(listTbl);
			
			JLabel myReview = new JLabel("나의 강의 평가");
				JPanel pane = new JPanel(new BorderLayout());
					JPanel star = new JPanel(new BorderLayout());
						JLabel starLbl = new JLabel("별점");
						JLabel starLbl2 = new JLabel("★★★★");
					JPanel content = new JPanel(new BorderLayout());
						JLabel contentLbl = new JLabel("구매후기");
						JLabel contentLbl2 = new JLabel("친절하시고 좋았어요~!");
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

	public TeachClassList() {
		setBackground(Color.white);
		add(start); start.setBackground(Color.white);
		start.setBorder(new LineBorder(Color.black)); start.setLayout(null);
		start.add(list); start.add(combo); start.add(listsp); start.add(myReview); start.add(sp);
		list.setBounds(20,10,200,50); list.setFont(fnt25);
		combo.setBounds(430,20,80,30);
		listsp.setBounds(20,70,520,330);
		listTbl.getParent().setBackground(Color.white);
		listTbl.getParent().setFont(fn15);
		listTbl.getTableHeader().setBackground(col6);
		listTbl.getTableHeader().setFont(fnt20);
		
		myReview.setBounds(20,410,200,50); myReview.setFont(fnt25);
		sp.setBounds(20,470,520,330); pane.setBackground(Color.white);
		pane.add("North",star); star.setBackground(Color.white); 
		star.add("West", starLbl); star.add(starLbl2); starLbl.setFont(fn15); starLbl2.setFont(fn15);
		starLbl.setPreferredSize(lblsize);
		pane.add(content); content.setBackground(Color.white);
		content.add("West", contentLbl); content.add(contentLbl2);
		contentLbl.setFont(fn15); contentLbl2.setFont(fn15);
		contentLbl.setPreferredSize(lblsize);

		
		setVisible(true);
	}

	public static void main(String[] args) {
		new TeachClassList();
	}

}
