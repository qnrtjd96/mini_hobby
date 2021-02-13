package administrator;

//구현완료
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import administrator.Admin1Main;
import main.Main0Login;
import main.Main3ChatServer;

public class Admin0Login extends JFrame implements ActionListener, MouseListener{
	JPanel login = new JPanel();
	ImageIcon img = new ImageIcon("img/Biglogo.png");
	JButton logo = new JButton(img);
	
	JLabel lblId = new JLabel("아이디");  JLabel lblpwd = new JLabel("비밀번호");
	JTextField idTf = new JTextField();  JPasswordField pwdTf = new JPasswordField();
	
	JButton loginBtn = new JButton("사장님만을 위한 로그인");
	
	JLabel askCompany = new JLabel("문의하기 02-1234-1234 / miniHobby@minicom.net");

	JLabel userLogin = new JLabel("User Login");
	
	Color col6 = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 20);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	
	public Admin0Login() {
		login.setBackground(Color.white);
		add(login); login.setLayout(null);
		login.add(logo); login.add(lblId); login.add(idTf);
		login.add(lblpwd); login.add(pwdTf); login.add(loginBtn);
		logo.setBounds(300,200,200,100); logo.setBackground(Color.white);
		lblId.setBounds(180,400,100,40); lblId.setFont(fnt);
		idTf.setBounds(300,400,300,40); idTf.setFont(fn);
		lblpwd.setBounds(180,460,100,40); lblpwd.setFont(fnt);
		pwdTf.setBounds(300,460,300,40); pwdTf.setFont(fn);
		loginBtn.setBounds(180,520,420,60); loginBtn.setFont(fnt3);
		loginBtn.setBackground(col6);
		
		login.add(askCompany);
		askCompany.setBounds(180,590,450,40); askCompany.setFont(fnt2);
		
		login.add(userLogin);
		userLogin.setBounds(600,900,200,40); userLogin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loginBtn.addActionListener(this);
		
		userLogin.addMouseListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		//아이디 넘겨주기
		String id = "master";
		if(obj==loginBtn) {
			char pwdSet[] = pwdTf.getPassword();
			String pwdStr = "";
			for(char cha:pwdSet) {
				Character.toString(cha);
				pwdStr += (pwdStr.equals("")) ? ""+cha+"" : ""+cha+"";
			}
			if (idTf.getText().equals("") || pwdStr.equals("")) {
				JOptionPane.showMessageDialog(this, "공란이 존재합니다. \n 아이디 또는 비밀번호가 기억나지 않으시면 관리자 문의해주십시오.");
			} else if (idTf.getText().equals("master") && pwdStr.equals("0000")) {
				JOptionPane.showMessageDialog(this, "관리자 페이지에 접속합니다.");
				this.setVisible(false);
				new Admin1Main();
				new Main3ChatServer(id);
			} else {
				JOptionPane.showMessageDialog(this, "로그인에 실패하였습니다. \n 아이디 또는 비밀번호가 기억나지 않으시면 관리자 문의해주십시오.");
				idTf.setText(""); pwdTf.setText("");
			}
			
		}
		
	}
	public void mouseReleased(MouseEvent me) {
		Object obj = me.getSource();
			if(obj.equals(userLogin)) {
					this.setVisible(false);
					new Main0Login();
			}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent me) {}

	public static void main(String[] args) {
		new Admin0Login();

	}

}
