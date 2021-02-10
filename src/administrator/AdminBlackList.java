package administrator;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AdminBlackList extends JPanel{
	Color col6 = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 20);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	
	JPanel upper = new JPanel();
		JLabel title = new JLabel("블랙리스트");
		
			String col[] = {"○", "번호", "아이디", "분류", "사유"};
			Object data[][] = new Object[0][col.length];
			DefaultTableModel model = new DefaultTableModel(data, col);
			JTable table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
			
		JButton release = new JButton("해제");
		
	JPanel bottom = new JPanel();
		JLabel id = new JLabel("아이디");
		JTextField idTf = new JTextField();
		JLabel reason = new JLabel("사유");
		JTextField reaTf = new JTextField();
		JButton add = new JButton("추가");

	public AdminBlackList() {
		setBackground(col6);
		setLayout(null);
		
		add(upper); upper.setLayout(null); upper.setBackground(col6); upper.setBounds(0,0,730,500);
			upper.add(title); title.setFont(fnt3); 
			upper.add(sp); 
			upper.add(release); release.setFont(fnt); release.setBackground(Color.white);
			title.setBounds(300,10,200,50);
			sp.setBounds(10,70,720,350);
			table.getParent().setBackground(Color.white);
			table.getParent().setFont(fnt2);
			table.getTableHeader().setBackground(Color.white);
			table.getTableHeader().setFont(fnt);
			table.getColumn("○").setPreferredWidth(50);
			table.getColumn("번호").setPreferredWidth(100);
			table.getColumn("아이디").setPreferredWidth(250);
			table.getColumn("분류").setPreferredWidth(120);
			table.getColumn("사유").setPreferredWidth(250);
			release.setBounds(550,440,150,50);
			
		add(bottom); bottom.setLayout(null); bottom.setBackground(col6); bottom.setBounds(10,500,720,290);
		bottom.setBorder(new TitledBorder(new LineBorder(Color.gray), "블랙리스트 추가", TitledBorder.CENTER, TitledBorder.CENTER, fnt3));
			bottom.add(id); bottom.add(idTf); bottom.add(reason); bottom.add(reaTf); bottom.add(add);
			id.setBounds(20,70,100,50); id.setFont(fnt);
			reason.setBounds(20,140,100,50); reason.setFont(fnt);
			idTf.setBounds(130,80,500,30); idTf.setFont(fn);
			reaTf.setBounds(130,150,500,30); reaTf.setFont(fn);
			add.setBounds(550,220,150,50); add.setFont(fnt); add.setBackground(Color.white);
		
		
		
	}
}
