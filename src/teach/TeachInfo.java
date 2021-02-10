<<<<<<< HEAD
package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TeachInfo extends JPanel implements ActionListener {
	Font westFnt = new Font("맑은 고딕", Font.BOLD, 25);
	Font centerFnt = new Font("맑은 고딕", Font.PLAIN, 20);
	
		JPanel InfoSub = new JPanel(new BorderLayout());
			JLabel title = new JLabel("회원상세정보");
		JPanel InfoCenter = new JPanel(null);
			JLabel teaId = new JLabel("아이디");	JLabel teaPwd = new JLabel("비밀번호");
			JLabel teaName = new JLabel("이름");	JLabel teaBirth = new JLabel("생년월일");
			JLabel teaEmail = new JLabel("이메일"); JLabel teaTel = new JLabel("연락처");
			JLabel teaAddr = new JLabel("주소");

			JLabel teaIdLbl = new JLabel("abcd1234");
			JTextField teaPwdTf = new JTextField(250);		String pwdTest = "pwd1234";
			JTextField teaNameTf = new JTextField(250);		String nameTest = "유관순";
			JLabel teaBirthLbl = new JLabel("2000/12/25");
			JTextField teaEmailTf = new JTextField(250);	String emailTest = "foofa@naver.com";
			JTextField teaTelTf = new JTextField(250);		String telTest = "010-1121-1211";
			JTextField teaAddrTf = new JTextField(250);		String addrTest = "서울시 마포구 연희로 13다 5-25";
		
			JPanel catePane = new JPanel();
				JLabel cateLbl = new JLabel("분야");
					JPanel radioPane = new JPanel();
					ButtonGroup group = new ButtonGroup();
						String radioName[] = { "음악", "미술", "스포츠", "요리" };
					JRadioButton cate[] = new JRadioButton[4];//, cate2, cate3, cate4;
				JLabel careerLbl = new JLabel("경력");
					JTextArea careerTa = new JTextArea();
			
		JPanel InfoSouth = new JPanel();
			JButton doBtn = new JButton("수정하기");
			JButton confBtn = new JButton("수정완료");
			
			
	public TeachInfo() {
		setLayout(new BorderLayout());
		//패널 기본 설정
		add("Center", InfoSub);
		InfoSub.setBackground(Color.white);
		InfoSub.setBorder(new LineBorder(Color.black, 1));
		InfoSub.setBackground(Color.white);
		InfoSub.add("Center",InfoCenter);	InfoCenter.setBackground(Color.white);
		
		InfoCenter.setEnabled(false);
		//북쪽 패널
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		InfoSub.add("North", title);
		
		//왼쪽 필드명 라벨
		teaId.setBounds(30,80, 90,30); teaId.setFont(westFnt); InfoCenter.add(teaId);
		teaPwd.setBounds(30,135, 100,30); teaPwd.setFont(westFnt); InfoCenter.add(teaPwd);
		teaName.setBounds(30,190, 60,30); teaName.setFont(westFnt); InfoCenter.add(teaName);
		teaBirth.setBounds(30,245, 100,30); teaBirth.setFont(westFnt); InfoCenter.add(teaBirth);
		teaEmail.setBounds(30,300, 100,30); teaEmail.setFont(westFnt); InfoCenter.add(teaEmail);
		teaTel.setBounds(30,355, 100,30); teaTel.setFont(westFnt); InfoCenter.add(teaTel);
		teaAddr.setBounds(30,410, 100,30); teaAddr.setFont(westFnt); InfoCenter.add(teaAddr);
		
		//오른쪽 사용자 정보 출력란
		teaIdLbl.setBounds(150,80, 250,30); teaIdLbl.setFont(centerFnt); InfoCenter.add(teaIdLbl);
		teaIdLbl.setBorder(new LineBorder(Color.black, 1));
		teaPwdTf.setBounds(150,135, 250,30); teaPwdTf.setFont(centerFnt); InfoCenter.add(teaPwdTf);
		teaPwdTf.setText(pwdTest);	teaPwdTf.setBorder(new LineBorder(Color.black, 1));
		teaNameTf.setBounds(150,190, 250,30); teaNameTf.setFont(centerFnt); InfoCenter.add(teaNameTf);
		teaNameTf.setText(nameTest);	teaNameTf.setBorder(new LineBorder(Color.black, 1));
		teaBirthLbl.setBounds(150,245, 250,30); teaBirthLbl.setFont(centerFnt); InfoCenter.add(teaBirthLbl);
		teaBirthLbl.setBorder(new LineBorder(Color.black, 1));
		teaEmailTf.setBounds(150,300, 250,30); teaEmail.setFont(centerFnt); InfoCenter.add(teaEmailTf);
		teaEmailTf.setText(emailTest);	teaEmailTf.setBorder(new LineBorder(Color.black, 1));
		teaTelTf.setBounds(150,355, 250,30); teaTelTf.setFont(centerFnt); InfoCenter.add(teaTelTf);
		teaTelTf.setText(telTest);	teaTelTf.setBorder(new LineBorder(Color.black, 1));
		teaAddrTf.setBounds(150,410, 250,30); teaAddrTf.setFont(new Font("맑은 고딕",Font.PLAIN, 13)); InfoCenter.add(teaAddrTf);
		teaAddrTf.setText(addrTest);	teaAddrTf.setBorder(new LineBorder(Color.black, 1));
		
		cateLbl.setBounds(30,465, 100,30); cateLbl.setFont(westFnt); InfoCenter.add(cateLbl);
		radioPane.setBorder(new LineBorder(Color.black, 1)); radioPane.setBackground(Color.white);
		radioPane.setBounds(150,465, 250,35); InfoCenter.add(radioPane);
		int i=0;
		for(i=0; i<radioName.length; i++) {
			cate[i] = new JRadioButton(radioName[i]);
			cate[i].setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			group.add(cate[i]);
			radioPane.add(cate[i]);
			cate[i].setEnabled(false);
			cate[i].addActionListener(this);
		}
		
		//간단한 기술 경력 또는 경력 연차 아니라 길게 적어야하면 폰트 줄이기!
		careerLbl.setBounds(30,520, 100,30); careerLbl.setFont(westFnt); InfoCenter.add(careerLbl);
		careerTa.setBounds(150,520, 250,60); careerTa.setFont(centerFnt); InfoCenter.add(careerTa);
		careerTa.setBorder(new LineBorder(Color.black,1));
		//남쪽 패널
		InfoSouth.add(doBtn); InfoSouth.setBackground(Color.white); doBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		InfoSub.add("South", InfoSouth);
		
		//비활성화 상태로 만들기
		teaPwdTf.setEnabled(false);	teaNameTf.setEnabled(false);
		teaEmailTf.setEnabled(false); teaTelTf.setEnabled(false);
		teaAddrTf.setEnabled(false); careerTa.setEnabled(false); 
		
		doBtn.addActionListener(this);
		confBtn.addActionListener(this);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionStr = ae.getActionCommand();
		
		if(actionStr.equals(doBtn.getText())) {
			System.out.println("수정하기 선택");
			teaPwdTf.setEnabled(true);	teaNameTf.setEnabled(true);
			teaEmailTf.setEnabled(true); teaTelTf.setEnabled(true);
			teaAddrTf.setEnabled(true); careerTa.setEnabled(true);
			cate[0].setEnabled(true); cate[1].setEnabled(true);
			cate[2].setEnabled(true); cate[3].setEnabled(true);
			
			doBtn.setVisible(false);
			
			/* 내가 하고싶은것.. 수정하기 누르면 수정완료 버튼으로 바뀌고
			 * 수정완료 버튼을 누르면 옵션 패널이 떠서 확인 버튼 누르면
			 * 변경된 데이터 DB에 업데이트!
			 * 수정완료 버튼을 멤버에두고 여기에다 두고 했는데도 안됨 ㅠㅠ
			 */
			//confBtn = new JButton("수정완료");
			
			InfoSouth.add(confBtn); confBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			InfoSub.add("South", InfoSouth); //confBtn.setVisible(false);
			
			confBtn.setVisible(true);
			
			
			//syso가 안 뜬다..?
			//confBtn.addActionListener(this);
			
		}else if(actionStr.equals(confBtn.getText())) {
			System.out.println("수정완료버튼");
			JOptionPane.showConfirmDialog(this, "수정을 완료하시겠습니까?");
		}
		
		//이거를 수정완료나 수정하기 버튼 이벤트 안으로 보낼 수 있나?
		if(actionStr.equals(cate[0].getText())) {
			System.out.println("음악을 선택");
		}else if(actionStr.equals(cate[1].getText())) {
			System.out.println("미술을 선택");
		}else if(actionStr.equals(cate[2].getText())) {
			System.out.println("스포츠를 선택");
		}else if(actionStr.equals(cate[3].getText())) {
			System.out.println("요리를 선택");
		}	
	}

	public static void main(String[] args) {
		new TeachInfo();
	}


}
=======
package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TeachInfo extends JPanel implements ActionListener {
	Font westFnt = new Font("맑은 고딕", Font.BOLD, 25);
	Font centerFnt = new Font("맑은 고딕", Font.PLAIN, 20);
	
		JPanel InfoSub = new JPanel(new BorderLayout());
			JLabel title = new JLabel("회원상세정보");
		JPanel InfoCenter = new JPanel(null);
			JLabel teaId = new JLabel("아이디");	JLabel teaPwd = new JLabel("비밀번호");
			JLabel teaName = new JLabel("이름");	JLabel teaBirth = new JLabel("생년월일");
			JLabel teaEmail = new JLabel("이메일"); JLabel teaTel = new JLabel("연락처");
			JLabel teaAddr = new JLabel("주소");

			JLabel teaIdLbl = new JLabel("abcd1234");
			JTextField teaPwdTf = new JTextField(250);		String pwdTest = "pwd1234";
			JTextField teaNameTf = new JTextField(250);		String nameTest = "유관순";
			JLabel teaBirthLbl = new JLabel("2000/12/25");
			JTextField teaEmailTf = new JTextField(250);	String emailTest = "foofa@naver.com";
			JTextField teaTelTf = new JTextField(250);		String telTest = "010-1121-1211";
			JTextField teaAddrTf = new JTextField(250);		String addrTest = "서울시 마포구 연희로 13다 5-25";
		
			JPanel catePane = new JPanel();
				JLabel cateLbl = new JLabel("분야");
					JPanel radioPane = new JPanel();
					ButtonGroup group = new ButtonGroup();
						String radioName[] = { "음악", "미술", "스포츠", "요리" };
					JRadioButton cate[] = new JRadioButton[4];//, cate2, cate3, cate4;
				JLabel careerLbl = new JLabel("경력");
					JTextArea careerTa = new JTextArea();
			
		JPanel InfoSouth = new JPanel();
			JButton doBtn = new JButton("수정하기");
			JButton confBtn = new JButton("수정완료");
			
			
	public TeachInfo() {
		setLayout(new BorderLayout());
		//패널 기본 설정
		add("Center", InfoSub);
		InfoSub.setBackground(Color.white);
		InfoSub.setBorder(new LineBorder(Color.black, 1));
		InfoSub.setBackground(Color.white);
		InfoSub.add("Center",InfoCenter);	InfoCenter.setBackground(Color.white);
		
		InfoCenter.setEnabled(false);
		//북쪽 패널
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		InfoSub.add("North", title);
		
		//왼쪽 필드명 라벨
		teaId.setBounds(30,80, 90,30); teaId.setFont(westFnt); InfoCenter.add(teaId);
		teaPwd.setBounds(30,135, 100,30); teaPwd.setFont(westFnt); InfoCenter.add(teaPwd);
		teaName.setBounds(30,190, 60,30); teaName.setFont(westFnt); InfoCenter.add(teaName);
		teaBirth.setBounds(30,245, 100,30); teaBirth.setFont(westFnt); InfoCenter.add(teaBirth);
		teaEmail.setBounds(30,300, 100,30); teaEmail.setFont(westFnt); InfoCenter.add(teaEmail);
		teaTel.setBounds(30,355, 100,30); teaTel.setFont(westFnt); InfoCenter.add(teaTel);
		teaAddr.setBounds(30,410, 100,30); teaAddr.setFont(westFnt); InfoCenter.add(teaAddr);
		
		//오른쪽 사용자 정보 출력란
		teaIdLbl.setBounds(150,80, 250,30); teaIdLbl.setFont(centerFnt); InfoCenter.add(teaIdLbl);
		teaIdLbl.setBorder(new LineBorder(Color.black, 1));
		teaPwdTf.setBounds(150,135, 250,30); teaPwdTf.setFont(centerFnt); InfoCenter.add(teaPwdTf);
		teaPwdTf.setText(pwdTest);	teaPwdTf.setBorder(new LineBorder(Color.black, 1));
		teaNameTf.setBounds(150,190, 250,30); teaNameTf.setFont(centerFnt); InfoCenter.add(teaNameTf);
		teaNameTf.setText(nameTest);	teaNameTf.setBorder(new LineBorder(Color.black, 1));
		teaBirthLbl.setBounds(150,245, 250,30); teaBirthLbl.setFont(centerFnt); InfoCenter.add(teaBirthLbl);
		teaBirthLbl.setBorder(new LineBorder(Color.black, 1));
		teaEmailTf.setBounds(150,300, 250,30); teaEmail.setFont(centerFnt); InfoCenter.add(teaEmailTf);
		teaEmailTf.setText(emailTest);	teaEmailTf.setBorder(new LineBorder(Color.black, 1));
		teaTelTf.setBounds(150,355, 250,30); teaTelTf.setFont(centerFnt); InfoCenter.add(teaTelTf);
		teaTelTf.setText(telTest);	teaTelTf.setBorder(new LineBorder(Color.black, 1));
		teaAddrTf.setBounds(150,410, 250,30); teaAddrTf.setFont(new Font("맑은 고딕",Font.PLAIN, 13)); InfoCenter.add(teaAddrTf);
		teaAddrTf.setText(addrTest);	teaAddrTf.setBorder(new LineBorder(Color.black, 1));
		
		cateLbl.setBounds(30,465, 100,30); cateLbl.setFont(westFnt); InfoCenter.add(cateLbl);
		radioPane.setBorder(new LineBorder(Color.black, 1)); radioPane.setBackground(Color.white);
		radioPane.setBounds(150,465, 250,35); InfoCenter.add(radioPane);
		int i=0;
		for(i=0; i<radioName.length; i++) {
			cate[i] = new JRadioButton(radioName[i]);
			cate[i].setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			group.add(cate[i]);
			radioPane.add(cate[i]);
			cate[i].setEnabled(false);
			cate[i].addActionListener(this);
		}
		
		//간단한 기술 경력 또는 경력 연차 아니라 길게 적어야하면 폰트 줄이기!
		careerLbl.setBounds(30,520, 100,30); careerLbl.setFont(westFnt); InfoCenter.add(careerLbl);
		careerTa.setBounds(150,520, 250,60); careerTa.setFont(centerFnt); InfoCenter.add(careerTa);
		careerTa.setBorder(new LineBorder(Color.black,1));
		//남쪽 패널
		InfoSouth.add(doBtn); InfoSouth.setBackground(Color.white); doBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		InfoSub.add("South", InfoSouth);
		
		//비활성화 상태로 만들기
		teaPwdTf.setEnabled(false);	teaNameTf.setEnabled(false);
		teaEmailTf.setEnabled(false); teaTelTf.setEnabled(false);
		teaAddrTf.setEnabled(false); careerTa.setEnabled(false); 
		
		doBtn.addActionListener(this);
		confBtn.addActionListener(this);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionStr = ae.getActionCommand();
		
		if(actionStr.equals(doBtn.getText())) {
			System.out.println("수정하기 선택");
			teaPwdTf.setEnabled(true);	teaNameTf.setEnabled(true);
			teaEmailTf.setEnabled(true); teaTelTf.setEnabled(true);
			teaAddrTf.setEnabled(true); careerTa.setEnabled(true);
			cate[0].setEnabled(true); cate[1].setEnabled(true);
			cate[2].setEnabled(true); cate[3].setEnabled(true);
			
			doBtn.setVisible(false);
			
			/* 내가 하고싶은것.. 수정하기 누르면 수정완료 버튼으로 바뀌고
			 * 수정완료 버튼을 누르면 옵션 패널이 떠서 확인 버튼 누르면
			 * 변경된 데이터 DB에 업데이트!
			 * 수정완료 버튼을 멤버에두고 여기에다 두고 했는데도 안됨 ㅠㅠ
			 */
			//confBtn = new JButton("수정완료");
			
			InfoSouth.add(confBtn); confBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			InfoSub.add("South", InfoSouth); //confBtn.setVisible(false);
			
			confBtn.setVisible(true);
			
			
			//syso가 안 뜬다..?
			//confBtn.addActionListener(this);
			
		}else if(actionStr.equals(confBtn.getText())) {
			System.out.println("수정완료버튼");
			JOptionPane.showConfirmDialog(this, "수정을 완료하시겠습니까?");
		}
		
		//이거를 수정완료나 수정하기 버튼 이벤트 안으로 보낼 수 있나?
		if(actionStr.equals(cate[0].getText())) {
			System.out.println("음악을 선택");
		}else if(actionStr.equals(cate[1].getText())) {
			System.out.println("미술을 선택");
		}else if(actionStr.equals(cate[2].getText())) {
			System.out.println("스포츠를 선택");
		}else if(actionStr.equals(cate[3].getText())) {
			System.out.println("요리를 선택");
		}	
	}

	public static void main(String[] args) {
		new TeachInfo();
	}


}
>>>>>>> refs/remotes/origin/master
