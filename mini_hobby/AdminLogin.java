
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminLogin extends JPanel implements ActionListener {
	//로고 부분 .. 이미지 삽입?
	JButton logo;
	
	//로그인 부분
	JPanel loginCenter = new JPanel(null);
		JLabel lblId = new JLabel("아 이 디");  JLabel lblpwd = new JLabel("비밀번호");
		JTextField idTf = new JTextField();  JTextField pwdTf = new JTextField();
		
		JButton loginBtn = new JButton("로 그 인");
		
		JLabel askCompany = new JLabel("문의하기 02-1234-1234 / miniHobby@minicom.net");
		
	//하단의 관리자 로그인 부분
	JLabel userLogin = new JLabel("User Login  ", JLabel.SOUTH_EAST);
	
	Font fnt = new Font("돋움체", Font.PLAIN, 15);
	
	public AdminLogin() {
		setLayout(new BorderLayout());
		loginCenter.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		loginCenter.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		//Logo
		ImageIcon img = new ImageIcon("miniHobbyLogo.png");
		logo = new JButton(img);
		logo.setBounds(290, 200, 200, 100);
		loginCenter.add(logo);
		//id
		lblId.setBounds(200,350, 250,30);
		lblId.setFont(new Font("돋움체", Font.BOLD, 30));
		idTf.setBounds(320,350, 250,35);
		loginCenter.add(lblId);	loginCenter.add(idTf);
		//pwd
		lblpwd.setFont(new Font("돋움체", Font.BOLD, 30));
		lblpwd.setBounds(200,395, 250,30);
		pwdTf.setBounds(320,395, 250,35);
		loginCenter.add(lblpwd); loginCenter.add(pwdTf);
		//loginBtn
		loginBtn.setFont(new Font("돋움체", Font.BOLD, 33));
		loginBtn.setBounds(200,440, 370,50);
		loginCenter.add(loginBtn);
		//log Label
		askCompany.setFont(fnt);	
		askCompany.setBounds(210,480, 370,50);
		loginCenter.add(askCompany);
		
		add(loginCenter);
		userLogin.setFont(fnt);
		add(BorderLayout.SOUTH, userLogin);
		
		//이벤트 
		idTf.addActionListener(this);
		pwdTf.addActionListener(this);
		
		loginBtn.addActionListener(this);
		
		userLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.println("사용자 로그인이 눌림");
				//사용자 로그인 페이지로 가기
				UserLogin userLoginPage = new UserLogin();
				setVisible(false);
				removeAll();
				add(userLoginPage);
				setVisible(true);
			}
		});
		
		setVisible(true);
		
		JFrameExtends df = new JFrameExtends();
		df.add(this);
	
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==idTf || obj==pwdTf) {
			//checkSpell();
		}else if(obj==loginBtn) {
			checkUserId();
		}
	}
	
	//로그인 아이디, 비밀번호 비교
	public void checkUserId() {
		System.out.println("로그인 버튼 누름");
		//DB와 아이디 비밀번호 대조하는 메소드 구현
		//임의 값
		String id = "1234";
		String pwd = "1234";
		// 옵션패널이 왜 안보일까 ㅠㅠ?
		if(idTf.getText().equals("")||pwdTf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "입력한 문자가 없습니다, 다시 시도해주세요.");
		}else if(idTf.getText().equals(id)&&pwdTf.getText().equals(pwd)) {
			JOptionPane.showConfirmDialog(this, "확인용 패널");
		}
		 
		System.out.println("여기까지 오나?");

	}

	
	
	
	
}
