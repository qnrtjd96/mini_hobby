package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
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

import dbConnection.Acess_memDAO;
import dbConnection.MoneyDAO;
import dbConnection.MoneyVO;

public class StudenCharge extends JPanel implements ActionListener {
	Font fn = new Font("맑은 고딕",Font.PLAIN, 20);
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
					JTextField nowTa = new JTextField();
					JLabel wonLbl = new JLabel("원");
					JLabel chargeLbl = new JLabel("충전금액");
					JTextField chargeTf = new JTextField();
					JLabel wonLbl2 = new JLabel("원");
					JButton btn = new JButton("충전하기");
				JLabel title2 = new JLabel("전체 충전 내역");
				
				String col[] = {"No", "충전금액","일시"};
				String data[][];
				DefaultTableModel model = new DefaultTableModel(data, col);
				JTable table = new JTable(model);
				JScrollPane sp = new JScrollPane(table);
	
	String idStr;
	int money_char; int rest=0;
	public StudenCharge() {}
	public StudenCharge(String id) {
		idStr = id;
		MoneyDAO dao = new MoneyDAO();
		List<MoneyVO> lst = dao.getMoneyInfo(id);
		if(lst.size()>0) {
			MoneyVO vo = lst.get(0);
			this.rest = vo.getBalance();
			for (int i=0; i<lst.size(); i++) {
				MoneyVO vom = lst.get(i);
				Object dataVO[] = {i+1, vom.getMoney_char()+"원", vom.getChar_date()};
				model.addRow(dataVO);
			}
		}
		nowTa.setText(rest+"");
		
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
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getParent().setBackground(Color.white);
		table.setFont(fn);
		table.setRowHeight(40);
		table.getTableHeader().setBackground(col6);
		table.getTableHeader().setFont(fnt3);
		table.getColumn("No").setPreferredWidth(60); table.getColumn("No").setCellRenderer(dtcr);
		table.getColumn("충전금액").setPreferredWidth(100); table.getColumn("충전금액").setCellRenderer(dtcr);
		table.getColumn("일시").setPreferredWidth(130); table.getColumn("일시").setCellRenderer(dtcr);
		
		btn.addActionListener(this);
		
		setSize(800,1000);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==btn) {
			MoneyDAO dao = new MoneyDAO();
			int moneyChar = Integer.parseInt(chargeTf.getText());
			rest = rest + moneyChar;
			int result = dao.insertMoney(idStr, moneyChar, rest);
			if (result>0) {
				String text = "충전이 완료되었습니다.\n충전금액: "+moneyChar+"\n현재잔액: "+rest;
				JOptionPane.showMessageDialog(this, text);
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
