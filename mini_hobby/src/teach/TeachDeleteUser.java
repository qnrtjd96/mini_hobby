package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

public class StudenDeleteUser extends JPanel implements ActionListener{
	JPanel deletMain = new JPanel(null);
	
	JLabel deleteTitle = new JLabel("탈퇴하기");
	
	JPanel robotPane = new JPanel(new BorderLayout());
		JLabel robotLbl = new JLabel(" 다음의 이미지들 가운데 신호등을 선택하세요.");
		
		//버튼에 이미지 넣어주기
		ImageIcon answer[] = {
				new ImageIcon("randomImg/distractor11.jpg"),  new ImageIcon("randomImg/answer.png"),new ImageIcon("randomImg/distractor33.jpg"),
				new ImageIcon("randomImg/distractor1.jpg"), new ImageIcon("randomImg/distractor2.jpg"), new ImageIcon("randomImg/distractor4.jpg"),
				new ImageIcon("randomImg/distractor5.jpg"), new ImageIcon("randomImg/distractor6.jpg"), new ImageIcon("randomImg/distractor7.jpg"),
		};

		Random randomNum = new Random(); //.nextInt(9);
		
		JPanel gPane = new JPanel(new GridLayout(3,3));
		JToggleButton gBtn[] = new JToggleButton[9];
		
	JPanel robotSouth = new JPanel();
		JButton chooBtn = new JButton("선택완료");
	
	public StudenDeleteUser() {
		add(deletMain);
		setBackground(Color.white);
		
		deletMain.setBorder(new LineBorder(Color.black,1));
		deletMain.setBackground(Color.white);
		
		deleteTitle.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		deleteTitle.setBounds(10,10, 200,30); deletMain.add(deleteTitle);
		
		robotPane.setBounds(90,170, 400,400); robotPane.setBackground(Color.white);
		deletMain.add(robotPane);
		robotPane.add("North", robotLbl);	
		robotLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		//버튼 삽입
		for(int idx=0; idx<gBtn.length; idx++) {
			gBtn[idx] = new JToggleButton(answer[idx]);	// gBtn[1] > 답
			gBtn[idx].addActionListener(this);
		}
		
		//랜덤숫자로 GridLayout에 삽입
		for(int i=0; i<9; i++) {
			int a = randomNum.nextInt(9);
		    JToggleButton btn = gBtn[0];
		    gBtn[0]=gBtn[a];
		    gBtn[a]=btn;
		}
		gPane.setVisible(false);
		gPane.removeAll();
			
		for(int i=0; i<gBtn.length; i++) {
				gPane.add(gBtn[i]);
		}
		gPane.setVisible(true);
			
		robotPane.add("Center", gPane);
		
		
		
		
		chooBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		robotSouth.add(chooBtn);
		robotPane.add("South", chooBtn);
		
		robotPane.setBorder(new LineBorder(Color.black, 1));
		
		chooBtn.addActionListener(this);
		
		setVisible(true);
	}
	//버튼 이벤트 .. .ㅠㅠ 왜 안돼!!
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj==gBtn) {
			
			System.out.println("선택하기");
			System.out.println("obj");
			System.out.println("gBtn[1]");
			String inputPwd = JOptionPane.showInputDialog(this, "비밀번호를 다시 입력해주세요");
			matchPwd(inputPwd);
			//비밀번호 입력받는거로 바꾸기 ... 디비랑 대조
			//String inputPwd = JOptionPane.showInputDialog(this, "비밀번호를 다시 입력해주세요");
			//matchPwd(inputPwd);
		
		}else {
			JOptionPane.showMessageDialog(this, "다시 선택해주세요.");
		}
		
	}
	
	public void matchPwd(String inputPwd) {
		
		if(inputPwd.equals("1234")) {	//로그인한 회원의 비밀번호와 일치시
			System.out.println("회원탈퇴성공... ㅠㅠ");
		}else {
			System.out.println("회원탈퇴실패^^");
		}
	}


}
