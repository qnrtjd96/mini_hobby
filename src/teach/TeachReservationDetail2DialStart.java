package teach;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;

public class TeachReservationDetail2DialStart extends JDialog implements ActionListener, ItemListener {
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	JCheckBox box;
	TreeSet<String> select = new TreeSet<String>();
	String selStr;
	String selectStr;
	
	JDialog dial;
	String id; String classname; String time; String SelectStr;
	int class_num;
	BoardDAO dao = new BoardDAO();
	List<BoardVO> lst = dao.detailBoard(class_num);
	public void dialStart() {}
	
	public TeachReservationDetail2DialStart(String id, String classname, String time, int class_num) {
		this.id=id;
		this.classname=classname;
		this.time=time;
		this.class_num=class_num;
		
		dial = new JDialog();
		dial.setSize(340,400);
		dial.setLocation(200, 200);
		dial.setVisible(true);
		JPanel dialPane = new JPanel();
			JLabel lbl1 = new JLabel("선택한 클래스 : "+classname);
			JLabel lbl2 = new JLabel("선택한 일자 : "+time);
			JPanel select = new JPanel(new GridLayout(0,2));
				String timeArr[] = {"09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~"
						+ "16:00", "16:00~17:00","17:00~18:00", "18:00~19:00", "19:00~20:00","20:00~21:00","21:00~22:00"};
				JCheckBox check[] = new JCheckBox[timeArr.length];
			JButton btn = new JButton("수정완료");
			
		dial.add(dialPane); dialPane.setLayout(null);
		dialPane.add(lbl1); dialPane.add(lbl2); dialPane.add(select); dialPane.add(btn);	
		lbl1.setFont(fn2); lbl1.setBounds(10,20,300,30);
		lbl2.setFont(fn2); lbl2.setBounds(40,50,300,30);
		btn.setFont(fn2); btn.setBounds(110,300,120,40); btn.setBackground(Color.LIGHT_GRAY);
		select.setFont(fnt); select.setBounds(50,90,220,200);
		for (int t=0; t<timeArr.length; t++) {
			check[t] = new JCheckBox(timeArr[t]);
			check[t].setFont(fn); check[t].setHorizontalAlignment(JCheckBox.CENTER);
			check[t].addItemListener(this);
			select.add(check[t]);
		}
		btn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String str = ae.getActionCommand();
		if(str.equals("수정완료")) {
			setVisible(false);
			dial.setVisible(false);
			selStr = select.toString();
			selectStr = selStr.substring(1, selStr.length()-1);
			new TeachReservationDetail(selectStr, id, classname, time);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		JCheckBox che = (JCheckBox)ie.getItem();
		String str = che.getText();
		if (che.isSelected()) {
			select.add(str);
		} else {
			Iterator<String> st = select.iterator();
			while(st.hasNext()) {
				String str2 = st.next();
				if (str2.equals(str)) {
					select.remove(str2); break;
				}
			}
		}
		
	}
		
	
}
