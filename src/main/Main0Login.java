package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import administrator.Admin0Login;
import dbConnection.Acess_memDAO;
import dbConnection.Acess_memVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import studen.Studen2JFrameExtends;
import teach.Teach1JFrameExtends;

public class Main0Login extends JFrame implements ActionListener, MouseListener{
	JPanel login = new JPanel();
		ImageIcon img = new ImageIcon("mini_hobby/img/Biglogo.png");
		JButton logo = new JButton(img);
		
		JLabel lblId = new JLabel("아이디");  JLabel lblpwd = new JLabel("비밀번호");
		JTextField idTf = new JTextField();  JPasswordField pwdTf = new JPasswordField();							
	
		JButton loginBtn = new JButton("로그인");
		
		JLabel forgot = new JLabel("아이디/비밀번호 찾기");
		JLabel signIn = new JLabel("회원가입");
		
		JLabel adminLogin = new JLabel("Administrator Login");
	
	Color col6 = new Color(204,222,233);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 20);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Font fnt3 = new Font("맑은 고딕", Font.BOLD, 25);
	
	String idStr, pwdStr;
	public Main0Login() {
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
		
		login.add(forgot); login.add(signIn);
		forgot.setBounds(180,600,200,40); forgot.setFont(fnt2);
		signIn.setBounds(530,600,100,40); signIn.setFont(fnt2);
		
		login.add(adminLogin);
		adminLogin.setBounds(540,880,200,40); adminLogin.setFont(fnt);
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loginBtn.addActionListener(this);
		forgot.addMouseListener(this); signIn.addMouseListener(this);
		adminLogin.addMouseListener(this);
	}
	public void mouseReleased(MouseEvent me) {
		JLabel lbl = (JLabel)me.getSource();
		String str = lbl.getText();
		if(str.equals("아이디/비밀번호 찾기")) {
			this.setVisible(false);
			new Main1AdminIdSearch();
		} else if(str.equals("회원가입")) {
			this.setVisible(false);
			new Main2AdminMembership();
		} else if(str.equals("Administrator Login")) {
			this.setVisible(false);
			new Admin0Login();
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==loginBtn) {
			String idStr = idTf.getText();
			
			char[] pwdSet = pwdTf.getPassword();
			String pwdStr = "";
			for(char cha:pwdSet) {
				Character.toString(cha);
				pwdStr += (pwdStr.equals("")) ? ""+cha+"" : ""+cha+"";
				
			}
			sort(idStr, pwdStr);
		}
		
	}
	public void sort(String idStr, String pwdStr) {
		if(idStr.equals("") || pwdStr.equals("")) {
			JOptionPane.showMessageDialog(this, "공란없이 값을 입력해주세요");
		} else {
			MemberDAO dao = new MemberDAO();
			List<MemberVO> searchList = dao.loginStart(idStr, pwdStr);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "일치하는 회원정보가 없습니다.\n 재확인 후 로그인 해주십시오");
			} else {
				MemberVO vo = searchList.get(0);
				//로그인 정보 DB로 넘기기
				Acess_memVO amVO = new Acess_memVO(vo.getSort(), idStr, vo.getName());
				Acess_memDAO amDAO = new Acess_memDAO();
				int result = amDAO.LogIn(amVO);
				if (vo.getSort()==1) {
					JOptionPane.showMessageDialog(this, vo.getName()+"님, 환영합니다.");
					this.setVisible(false);
					new Studen2JFrameExtends(idStr, pwdStr);
					new Main3ChatServer(vo.getId());
				} else if(vo.getSort()==2) {
					JOptionPane.showMessageDialog(this, vo.getName()+"님, 환영합니다.");
					this.setVisible(false);
					new Teach1JFrameExtends(vo.getId());
					new Main3ChatServer(vo.getId());
				} else if(vo.getSort()==3) {
					JOptionPane.showMessageDialog(this, "관리자님께서는 로그인페이지 하단의 Administrator Login을 이용해주십시오");
				}
			}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent me) {}

	public static void main(String[] args) {
		new Main0Login();

	}
	

}
