package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class StudenCharge extends JPanel implements ActionListener {
	Font fn = new Font("맑은 고딕",Font.PLAIN, 25);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 28);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	Color col6 = new Color(204,222,233);
	
	JPanel big = new JPanel(new BorderLayout());
		JPanel pane = new Studen4MyMenu().paneStu;
			JPanel start = new JPanel(new BorderLayout());
				JLabel title = new JLabel("충전하기");
				JPanel charge = new JPanel();
					JLabel nowLbl = new JLabel("현재잔액");
					JTextField nowTa = new JTextField("16,800");
					JLabel wonLbl = new JLabel("원");
					JLabel chargeLbl = new JLabel("충전하기");
					JTextField chargeTf = new JTextField("50,000");
					JLabel wonLbl2 = new JLabel("원");
					JButton btn = new JButton("충전하기");
				JLabel title2 = new JLabel("전체 충전 내역");
				
				String col[] = {"번호", "충전금액","일시"};
				String data[][];
				DefaultTableModel model = new DefaultTableModel(data, col);
				JTable table = new JTable(model);
				JScrollPane sp = new JScrollPane(table);
	

	public StudenCharge() {
		add(big); big.setBackground(Color.white);
		big.add("Center", pane); pane.setBackground(Color.white);
		pane.add(start); start.setBorder(new LineBorder(Color.black)); start.setBackground(Color.white);
		start.setLayout(null);
		start.add(title); start.add(charge); start.add(title2); start.add(sp);
		title.setBounds(10,10,200,40); title.setFont(fnt);
		charge.setBounds(10,60,550,340); charge.setBackground(Color.white); charge.setBorder(new LineBorder(Color.black));
			charge.setLayout(null);
			charge.add(nowLbl); charge.add(nowTa); charge.add(chargeLbl); charge.add(chargeTf);
			charge.add(wonLbl); charge.add(wonLbl2); charge.add(btn);
			nowLbl.setBounds(100,10,200,50); nowLbl.setFont(fnt);
			nowTa.setBounds(200,70,150,50); nowTa.setFont(fn);
			wonLbl.setBounds(351,70,50,50); wonLbl.setFont(fnt);
			chargeLbl.setBounds(100,130,200,50); chargeLbl.setFont(fnt);
			chargeTf.setBounds(200,190,150,50); chargeTf.setFont(fn);
			wonLbl2.setBounds(351,190,50,50); wonLbl2.setFont(fnt);
			nowTa.setHorizontalAlignment(JTextField.RIGHT); chargeTf.setHorizontalAlignment(JTextField.RIGHT);
			btn.setBounds(200,270,120,50); btn.setFont(fn2); btn.setBackground(col6);
		title2.setBounds(10,420,300,40); title2.setFont(fnt);
		sp.setBounds(10,470,550,310); sp.setBackground(Color.white);
		
		table.getParent().setBackground(Color.white);
		table.getParent().setFont(fn);
		table.getTableHeader().setBackground(col6);
		table.getTableHeader().setFont(fnt3);
		
		btn.addActionListener(this);
		
		setSize(800,1000);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==btn) {
			JOptionPane.showMessageDialog(this, "충전이 완료되었습니다.");
		}
		
	}

	public static void main(String[] args) {
		new StudenCharge();

	}

	

}
