package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StudenInfo extends JPanel implements ActionListener {
	Font westFnt = new Font("맑은 고딕", Font.BOLD, 25);
	Font centerFnt = new Font("맑은 고딕", Font.PLAIN, 20);
	
		JPanel InfoSub = new JPanel(new BorderLayout());
			JLabel title = new JLabel("회원상세정보");
		JPanel InfoCenter = new JPanel(null);
			JLabel stuId = new JLabel("아이디");	JLabel stuPwd = new JLabel("비밀번호");
			JLabel stuName = new JLabel("이름");	JLabel stuBirth = new JLabel("생년월일");
			JLabel stuEmail = new JLabel("이메일"); JLabel stuTel = new JLabel("연락처");
			JLabel stuAddr = new JLabel("주소");

			JLabel stuIdLbl = new JLabel("abcd1234");
			JLabel stuPwdLbl = new JLabel("pwd1234");
			JTextField stuNameTf = new JTextField(250);		String nameTest = "세종대왕";
			JLabel stuBirthLbl = new JLabel("2000/12/25");
			JTextField stuEmailTf = new JTextField(250);	String emailTest = "abcd@naver.com";
			JTextField stuTelTf = new JTextField(250);		String telTest = "010-1234-1234";
			JTextField stuAddrTf = new JTextField(250);		String addrTest = "서울시 양천구 목3동";
		
		JPanel InfoSouth = new JPanel();
			JButton doBtn = new JButton("수정하기");
			
			
	public StudenInfo() {
		//패널 기본 설정
		add(InfoSub);
		InfoSub.setBackground(Color.white);
		InfoSub.setBorder(new LineBorder(Color.black, 1));
		InfoSub.setBackground(Color.white);
		InfoSub.add("Center",InfoCenter);	InfoCenter.setBackground(Color.white);
		
		//북쪽 패널
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		InfoSub.add("North", title);
		
		//왼쪽 필드명 라벨
		stuId.setBounds(30,80, 90,30); stuId.setFont(westFnt); InfoCenter.add(stuId);
		stuPwd.setBounds(30,135, 100,30); stuPwd.setFont(westFnt); InfoCenter.add(stuPwd);
		stuName.setBounds(30,190, 60,30); stuName.setFont(westFnt); InfoCenter.add(stuName);
		stuBirth.setBounds(30,245, 100,30); stuBirth.setFont(westFnt); InfoCenter.add(stuBirth);
		stuEmail.setBounds(30,300, 100,30); stuEmail.setFont(westFnt); InfoCenter.add(stuEmail);
		stuTel.setBounds(30,355, 100,30); stuTel.setFont(westFnt); InfoCenter.add(stuTel);
		stuAddr.setBounds(30,410, 100,30); stuAddr.setFont(westFnt); InfoCenter.add(stuAddr);
		
		//오른쪽 사용자 정보 출력란
		stuIdLbl.setBounds(150,80, 250,30); stuIdLbl.setFont(centerFnt); InfoCenter.add(stuIdLbl);
		stuIdLbl.setBorder(new LineBorder(Color.black, 1));
		stuPwdLbl.setBounds(150,135, 250,30); stuPwdLbl.setFont(centerFnt); InfoCenter.add(stuPwdLbl);
		stuPwdLbl.setBorder(new LineBorder(Color.black, 1));
		stuNameTf.setBounds(150,190, 250,30); stuNameTf.setFont(centerFnt); InfoCenter.add(stuNameTf);
		stuNameTf.setBorder(new LineBorder(Color.black, 1));
		stuBirthLbl.setBounds(150,245, 250,30); stuBirthLbl.setFont(centerFnt); InfoCenter.add(stuBirthLbl);
		stuBirthLbl.setBorder(new LineBorder(Color.black, 1));
		stuEmailTf.setBounds(150,300, 250,30); stuEmail.setFont(centerFnt); InfoCenter.add(stuEmailTf);
		stuEmailTf.setBorder(new LineBorder(Color.black, 1));
		stuTelTf.setBounds(150,355, 250,30); stuTelTf.setFont(centerFnt); InfoCenter.add(stuTelTf);
		stuTelTf.setBorder(new LineBorder(Color.black, 1));
		stuAddrTf.setBounds(150,410, 250,30); stuAddrTf.setFont(centerFnt); InfoCenter.add(stuAddrTf);
		stuAddrTf.setBorder(new LineBorder(Color.black, 1));
		
		//남쪽 패널
		InfoSouth.add(doBtn); InfoSouth.setBackground(Color.white); doBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		InfoSub.add("South", InfoSouth);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}