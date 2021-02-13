package administrator;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbConnection.Acess_memDAO;
import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class AdminMsgReplyDialog extends JDialog implements ActionListener{
	Color col = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 28);
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
	
	String getter; String idStr; String msgTitle;
	public AdminMsgReplyDialog() {}
	public AdminMsgReplyDialog(String get, String sender, String msgTitle) {
		this.idStr = "master";
		this.getter = get;
		this.msgTitle = msgTitle;
		
		setBackground(Color.white);
		setLayout(null);
		
		add(title); add(titleTf); add(receiver); add(receiTf); add(sp); add(send);
		title.setBounds(0,0,150,50); title.setFont(fn2); title.setBorder(new LineBorder(Color.LIGHT_GRAY));
		title.setHorizontalAlignment(JLabel.CENTER);
		receiver.setBounds(0,51,150,50); receiver.setFont(fn2); receiver.setBorder(new LineBorder(Color.LIGHT_GRAY));
		receiver.setHorizontalAlignment(JLabel.CENTER);
		titleTf.setBounds(150,0,330,50); titleTf.setFont(fn);
		receiTf.setBounds(150,51,330,50); receiTf.setFont(fn);
		sp.setBounds(0,101,480,330); ta.setFont(fn);
		send.setBounds(440,450,100,40); send.setBackground(col); send.setFont(fn2);
		
		titleTf.setText("[RE]:"+msgTitle);
		receiTf.setText(getter);
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(190, 100);
		
		send.addActionListener(this);
		
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
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==send) {
			ConsVO vo = new ConsVO(getter,idStr,msgTitle,ta.getText());
			ConsDAO dao = new ConsDAO();
			int result = dao.insertReply(vo);
			if (result>0) {
				JOptionPane.showMessageDialog(this, "메시지 전송이 완료되었습니다.");
				this.setVisible(false);
			}
		}
		
	}

}
