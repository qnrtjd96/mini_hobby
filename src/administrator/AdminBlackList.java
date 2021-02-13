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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dbConnection.BlackListDAO;
import dbConnection.BlackListVO;
import dbConnection.MemberDAO;

public class AdminBlackList extends JPanel implements MouseListener, ActionListener{
	Color col6 = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 20);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	
	JPanel upper = new JPanel();
		JLabel title = new JLabel("블랙리스트");
		
			String col[] = {"○", "번호", "아이디", "분류", "사유"};
			Object data[][];
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
		
		tableSetting();
		
		add(upper); upper.setLayout(null); upper.setBackground(col6); upper.setBounds(0,0,730,500);
			upper.add(title); title.setFont(fnt3); 
			upper.add(sp); 
			upper.add(release); release.setFont(fnt); release.setBackground(Color.white);
			title.setBounds(300,10,200,50);
			sp.setBounds(10,70,720,350);
			
			table.setTableHeader(new JTableHeader(table.getColumnModel()) {
				public Dimension getPreferredSize() {
			    Dimension d = super.getPreferredSize();
			    d.height = 40;
			    return d;
				}
			});
			
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			table.getParent().setBackground(Color.white);
			table.setFont(fnt2);
			table.setRowHeight(30);
			table.getTableHeader().setBackground(Color.white);
			table.getTableHeader().setFont(fnt);
			table.getColumn("○").setPreferredWidth(50); 
			table.getColumn("번호").setPreferredWidth(100);
			table.getColumn("아이디").setPreferredWidth(250);
			table.getColumn("분류").setPreferredWidth(120);
			table.getColumn("사유").setPreferredWidth(250);
			table.getColumn("○").setCellRenderer(dtcr);
			table.getColumn("번호").setCellRenderer(dtcr);
			table.getColumn("아이디").setCellRenderer(dtcr);
			table.getColumn("분류").setCellRenderer(dtcr);
			table.getColumn("사유").setCellRenderer(dtcr);
			release.setBounds(550,440,150,50);
			
		add(bottom); bottom.setLayout(null); bottom.setBackground(col6); bottom.setBounds(10,500,720,290);
		bottom.setBorder(new TitledBorder(new LineBorder(Color.gray), "블랙리스트 추가", TitledBorder.CENTER, TitledBorder.CENTER, fnt3));
			bottom.add(id); bottom.add(idTf); bottom.add(reason); bottom.add(reaTf); bottom.add(add);
			id.setBounds(20,70,100,50); id.setFont(fnt);
			reason.setBounds(20,140,100,50); reason.setFont(fnt);
			idTf.setBounds(130,80,500,30); idTf.setFont(fn);
			reaTf.setBounds(130,150,500,30); reaTf.setFont(fn);
			add.setBounds(550,220,150,50); add.setFont(fnt); add.setBackground(Color.white);
			
		table.addMouseListener(this);
		release.addActionListener(this);
		add.addActionListener(this);
	}
	public void tableSetting() {
		BlackListDAO dao = new BlackListDAO();
		List<BlackListVO> lst = dao.selectList();
		if(lst.size()>0) {
			for(int i=0; i<lst.size(); i++) {
				BlackListVO vo = lst.get(i);
				Object black[] = {"○", i+1, vo.getId(), vo.getSort(), vo.getWhy()};
				model.addRow(black);
			}
		}
	}
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if(obj==table) {
			int col = table.getSelectedColumn();
			int row = table.getSelectedRow();
			String str = (String)table.getValueAt(row,0);
			if (col==0 && str.equals("○")) {
				table.setValueAt("●", row, col);
			} else if (col==0 && str.equals("●")) {
				table.setValueAt("○", row, col);
			}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==release) {
			for(int i=0; i<=table.getRowCount(); i++) {
				String str = (String)table.getValueAt(i, 0);
				String id = (String)table.getValueAt(i, 2);
				if(str.equals("●")) {
					BlackListDAO dao = new BlackListDAO();
					MemberDAO dao2 = new MemberDAO();
					int check = dao2.blackSearch(id);
					int result = dao.deleteList(id);
					int result2 = dao2.blackDelete(id);
					if (check==1) {
						JOptionPane.showMessageDialog(this, id+"님은 블랙리스트 목록에 없는 유저입니다.");
					} else {
						if(result>0 && result2>0) {
							JOptionPane.showMessageDialog(this, "블랙리스트 해제가 완료되었습니다.");
							model.setRowCount(0);
							tableSetting();
						}
					}
				}
			}
		} else if(obj==add) { //idTf, reaTf
			String id = idTf.getText();
			String reason = reaTf.getText();
			BlackListDAO dao = new BlackListDAO();
			MemberDAO dao2 = new MemberDAO();
			int check = dao2.blackSearch(id);
			int result = dao.insertList(id, reason);
			int result2 = dao2.blackUpdate(id);
			if(check==2) {
				JOptionPane.showMessageDialog(this, "이미 등록된 블랙리스트 유저입니다.");
			} else {
				if(result>0 && result2>0) {
					JOptionPane.showMessageDialog(this, id+"님을 블랙리스트로 등록하였습니다.");
					model.setRowCount(0);
					tableSetting();
				}
			}
		}
	}
}
