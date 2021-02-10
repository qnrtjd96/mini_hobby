package studen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class StudenReceiveMsgDial extends JDialog implements ActionListener{
	
	Color col = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 18);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	
	JLabel title = new JLabel("제목");
	JTextField titleTf = new JTextField();
	JLabel receiver = new JLabel("받을사람");
	JTextField receiTf = new JTextField();
	JTextArea ta = new JTextArea();
	JScrollPane sp = new JScrollPane(ta);
	JButton send = new JButton("보내기");
	String id; int msgNum;
	public StudenReceiveMsgDial() {}
	public StudenReceiveMsgDial(String id, int msgNum) {
		this.id=id;
		this.msgNum=msgNum;
		
		setBackground(Color.white);
		setLayout(null);
		
		add(title); add(titleTf); add(receiver); add(receiTf); add(sp); add(send);
		title.setBounds(0,0,150,50); title.setFont(fn2); title.setBorder(new LineBorder(Color.LIGHT_GRAY));
		title.setHorizontalAlignment(JLabel.CENTER);
		receiver.setBounds(0,51,150,50); receiver.setFont(fn2); receiver.setBorder(new LineBorder(Color.LIGHT_GRAY));
		receiver.setHorizontalAlignment(JLabel.CENTER);
		titleTf.setBounds(150,0,330,50); titleTf.setFont(fn);
		receiTf.setBounds(150,51,330,50); receiTf.setFont(fn);
		sp.setBounds(0,101,480,390); ta.setFont(fn);
		send.setBounds(310,510,150,40); send.setBackground(col); send.setFont(fn2);
		
		setSize(500,600);
		setLocation(190,100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		send.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==send) {
			ConsVO vo = new ConsVO(receiTf.getText(), id, title.getText(), ta.getText());
			ConsDAO dao = new ConsDAO();
			int result = dao.sendMsg(vo);
			if (result>0) {
				JOptionPane.showMessageDialog(this, "메시지 전송이 완료되었습니다.");
			}
		}
		
	}

}