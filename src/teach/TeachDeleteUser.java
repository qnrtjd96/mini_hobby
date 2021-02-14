package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.Mem_teacherDAO;
import dbConnection.MemberDAO;
import dbConnection.Stu_ClassDAO;
import main.Main0Login;

public class TeachDeleteUser extends JPanel implements ActionListener{
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
	
	String id, pwd;
		
	public TeachDeleteUser() {
		
	}
	
	public TeachDeleteUser(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
		System.out.println("idStr > > > "+id+", 로그인 비밀번호 > > > "+pwd);

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
		
		robotPane.setBorder(new LineBorder(Color.black, 1));
		
		setVisible(true);
	}
	//버튼 이벤트 .. .ㅠㅠ 왜 안돼!!
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj==gBtn[1]) {
			System.out.println("gBtn[1]");
			String inputPwd = JOptionPane.showInputDialog(this, "비밀번호를 다시 입력해주세요");
			System.out.println("idStr > > > "+id+", 입력 비밀번호 > "+inputPwd+", 로그인 비밀번호 > > > "+pwd);

			matchPwd(inputPwd, id, pwd);
		}else {
			JOptionPane.showMessageDialog(this, "다시 선택해주세요.");
		}
		
	}
	
	public void matchPwd(String inputPwd, String id, String pwd) {
		System.out.println("idStr > > > "+id+", 입력 비밀번호 > "+inputPwd+", 로그인 비밀번호 > > > "+pwd);

		if(inputPwd.equals(pwd)) {	//로그인한 회원의 비밀번호와 일치 시
			BoardDAO delDao = new BoardDAO();
			int delBor = delDao.deleteMember(id);
			if(delBor>0) {
				System.err.println("boardtbl 삭제 성공");
			}else {
				System.out.println("boardtbl 삭제 실패");
			}
			Mem_teacherDAO teaDao = new Mem_teacherDAO();
			int teaFkDel = teaDao.delTeaFkTbl(id);
			if(teaFkDel>0) {
				System.out.println("선생님 참조 테이블 삭제 성공");
			}else {
				System.out.println("선생님 참조 테이블 삭제 실패");
			}
			MemberDAO dao = new MemberDAO();
			int result = dao.memDelete(id);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "이용해주셔서 감사합니다, \n 언제든 다시 돌아와주세요.");
				this.setVisible(false);
				new Main0Login();
			}else {
				JOptionPane.showMessageDialog(this, "탈퇴 안됨..");
			}
		}else {
			System.out.println("회원탈퇴실패^^");
		}
	}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(id);
			System.exit(0);
		}
	}

}
