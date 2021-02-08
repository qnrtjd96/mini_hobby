package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
			JTextField stuPwdTf = new JTextField();		String pwdTest = "pwd1234";
			JTextField stuNameTf = new JTextField(250);		String nameTest = "세종대왕";
			JLabel stuBirthLbl = new JLabel("2000/12/25");
			JTextField stuEmailTf = new JTextField(250);	String emailTest = "abcd@naver.com";
			JTextField stuTelTf = new JTextField(250);		String telTest = "010-1234-1234";
			JTextField stuAddrTf = new JTextField(250);		String addrTest = "서울시 양천구 목3동";
		
		JPanel InfoSouth = new JPanel();
			JButton doBtn = new JButton("수정하기");
			JButton confBtn = new JButton("수정완료");
			
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
		
		//사용자 정보 세팅..db에서 가져와야함
		
		
		stuIdLbl.setBounds(150,80, 250,30); stuIdLbl.setFont(centerFnt); InfoCenter.add(stuIdLbl);
		stuIdLbl.setBorder(new LineBorder(Color.black, 1));
		stuPwdTf.setBounds(150,135, 250,30); stuPwdTf.setFont(centerFnt); InfoCenter.add(stuPwdTf);
		stuPwdTf.setText(pwdTest);	stuPwdTf.setBorder(new LineBorder(Color.black, 1));
		stuNameTf.setBounds(150,190, 250,30); stuNameTf.setFont(centerFnt); InfoCenter.add(stuNameTf);
		stuNameTf.setText(nameTest); stuNameTf.setBorder(new LineBorder(Color.black, 1));
		stuBirthLbl.setBounds(150,245, 250,30); stuBirthLbl.setFont(centerFnt); InfoCenter.add(stuBirthLbl);
		stuBirthLbl.setBorder(new LineBorder(Color.black, 1));
		stuEmailTf.setBounds(150,300, 250,30); stuEmail.setFont(centerFnt); InfoCenter.add(stuEmailTf);
		stuEmailTf.setText(emailTest); stuEmailTf.setBorder(new LineBorder(Color.black, 1));
		stuTelTf.setBounds(150,355, 250,30); stuTelTf.setFont(centerFnt); InfoCenter.add(stuTelTf);
		stuTelTf.setText(telTest);	stuTelTf.setBorder(new LineBorder(Color.black, 1));
		stuAddrTf.setBounds(150,410, 250,30); stuAddrTf.setFont(centerFnt); InfoCenter.add(stuAddrTf);
		stuAddrTf.setText(addrTest); stuAddrTf.setBorder(new LineBorder(Color.black, 1));
		
		//남쪽 패널
		InfoSouth.add(doBtn); InfoSouth.setBackground(Color.white); doBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		InfoSub.add("South", InfoSouth);
		
		//비활성화 시키기
		stuPwdTf.setEnabled(false);		stuNameTf.setEnabled(false);
		stuEmailTf.setEnabled(false);	stuTelTf.setEnabled(false);		stuAddrTf.setEnabled(false);
		//이벤트 발생
		doBtn.addActionListener(this);
		confBtn.addActionListener(this);
				
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj==doBtn) {
			System.out.println("수정하기 버튼이 눌림");
			
			stuPwdTf.setEnabled(true);		stuNameTf.setEnabled(true);
			stuEmailTf.setEnabled(true);	stuTelTf.setEnabled(true);		stuAddrTf.setEnabled(true);
			
			doBtn.setVisible(false);
			
			confBtn.setVisible(true);
			InfoSouth.add(confBtn);		confBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			
		}else if(obj==confBtn) {
			System.out.println("수정완료버튼 눌림");
			sendStuInfo();
		}
	}
	
	public void sendStuInfo() {
		JOptionPane.showMessageDialog(this, "수정완료버튼 눌림?");
		
		//수정정보 받아오는 부분
		String rewritePwd = stuPwdTf.getText();	String rewriteName = stuNameTf.getText();	
		String rewriteEmail = stuEmailTf.getText();	String rewriteTel = stuTelTf.getText();	
		String rewriteAddr = stuAddrTf.getText();
		
		System.out.println("받아오는지 확인 > "+ rewriteAddr);
		
		//디비에 updata 해주기
		
		//비활성화 시키기
		stuPwdTf.setEnabled(false);		stuNameTf.setEnabled(false);
		stuEmailTf.setEnabled(false);	stuTelTf.setEnabled(false);		stuAddrTf.setEnabled(false);
		
		confBtn.setVisible(false);
		doBtn.setVisible(true);
	}

}
