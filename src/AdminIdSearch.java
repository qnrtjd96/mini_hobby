
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AdminIdSearch extends JFrame implements ActionListener, KeyListener{	
	ImageIcon img = new ImageIcon("img/Biglogo.png");
	JLabel logo = new JLabel(img);
	JPanel pane_id = new JPanel();
		JLabel lbl_idtitle = new JLabel("아이디 찾기");
		JLabel lbl_id = new JLabel("이메일 주소");
		JTextField tf_id = new JTextField();		
		JLabel lbl_idprint = new JLabel("아이디 조회 결과 출력란");
	JPanel pane_pwd = new JPanel();
		JLabel lbl_pwdtitle = new JLabel("비밀번호 찾기");
		JLabel lbl_pwd = new JLabel("아이디");
		JTextField tf_pwd = new JTextField();
		JLabel lbl_pwd2 = new JLabel("이메일 주소");
		JTextField tf_pwd2 = new JTextField(50);
		JLabel lbl_pwdprint = new JLabel("비밀번호 조회 결과 출력란");
	JButton btn = new JButton("로그인 하기");
	
	Font fnt_title = new Font("맑은 고딕", Font.BOLD, 30);	
	Font fnt = new Font("맑은 고딕", Font.BOLD, 20);
	Dimension lblSize = new Dimension(130, 30);
	Color col6 = new Color(204,222,233);

	public AdminIdSearch() {
		setBackground(Color.white);
		setLayout(null);
		
		add(logo); 
		logo.setBounds(300, 30, 190, 100);
			
		add(pane_id); pane_id.setBackground(Color.white);
		pane_id.setLayout(null);
		pane_id.setBounds(10, 170, 760, 300); 
		pane_id.setBorder(new LineBorder(Color.black, 1));
			pane_id.add(lbl_idtitle); pane_id.add(lbl_id); pane_id.add(tf_id);
			pane_id.add(lbl_idprint);
			lbl_idtitle.setBounds(230, 10, 300, 50);
			lbl_idtitle.setHorizontalAlignment(JLabel.CENTER);
			lbl_idtitle.setFont(fnt_title);
			lbl_id.setBounds(30,70,130,30); tf_id.setBounds(160,70,570,30); 
			lbl_id.setFont(fnt);
			lbl_idprint.setBounds(30, 120, 700, 150);
			lbl_idprint.setFont(fnt); lbl_idprint.setHorizontalAlignment(JLabel.CENTER);
			lbl_idprint.setBorder(new LineBorder(Color.black, 1));
		
		add(pane_pwd);
		pane_pwd.setLayout(null); pane_pwd.setBackground(Color.white);
		pane_pwd.setBounds(10, 500, 760, 350);
		pane_pwd.setBorder(new LineBorder(Color.black, 1));
			pane_pwd.add(lbl_pwdtitle); pane_pwd.add(lbl_pwdprint);
			pane_pwd.add(lbl_pwd); pane_pwd.add(tf_pwd); 
			pane_pwd.add(lbl_pwd2); pane_pwd.add(tf_pwd2);
			lbl_pwdtitle.setHorizontalAlignment(JLabel.CENTER);
			lbl_pwdtitle.setFont(fnt_title);
			lbl_pwdtitle.setBounds(230,10,300,50);
			lbl_pwd.setFont(fnt); lbl_pwd.setBounds(30,80,130,30); tf_pwd.setBounds(160,80,570,30);
			lbl_pwd2.setFont(fnt); lbl_pwd2.setBounds(30,120,130,30); tf_pwd2.setBounds(160,120,570,30);
			lbl_pwdprint.setBounds(30, 170, 700, 150);
			lbl_pwdprint.setFont(fnt); lbl_pwdprint.setHorizontalAlignment(JLabel.CENTER);
			lbl_pwdprint.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		
		
		add(btn);
		btn.setBounds(250, 870, 300, 70);
		btn.setFont(fnt); btn.setBackground(col6);
		
		setVisible(true);
		setSize(800,1000);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btn.addActionListener(this);
		tf_id.addKeyListener(this); tf_pwd.addKeyListener(this); tf_pwd2.addKeyListener(this);
	}
	// 엔터키 override
	public void keyReleased(KeyEvent ke) {
		int i = ke.getKeyCode();
		Object obj = ke.getSource();
		String idStr, pwdStr, pwd2Str;
		if(obj==tf_id && i==KeyEvent.VK_ENTER) {
			idStr = tf_id.getText();
			tf_id.setText("");
			lbl_idprint.setText("입력한 메일주소는 "+idStr+"입니다.");
		} else if (obj==tf_pwd && i==KeyEvent.VK_ENTER) {
			pwdStr = tf_pwd.getText();
			tf_id.setText("");
		} else if (obj==tf_pwd2 && i==KeyEvent.VK_ENTER) {
			pwd2Str = tf_pwd2.getText();
			tf_id.setText("");
		}
		
	}

	// 버튼 Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==btn) {
			this.setVisible(false);
			new Studen1Login();
		}
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

}
