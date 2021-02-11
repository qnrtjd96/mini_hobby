package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbConnection.Mem_teacherDAO;
import dbConnection.Mem_teacherVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;

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

			JTextField teaIdTf = new JTextField();			String idTest = "";
			JTextField teaPwdTf = new JTextField(250);		String pwdTest = "";
			JTextField teaNameTf = new JTextField(250);		String nameTest = "";
			JLabel teaBirthLbl = new JLabel();				String birthTest = "";
			JTextField teaEmailTf = new JTextField(250);	String emailTest = "";
			JTextField teaTelTf = new JTextField(250);		String telTest = "";
			JTextField teaAddrTf = new JTextField(250);		String addrTest = "";
		
			JPanel catePane = new JPanel();
				JLabel cateLbl = new JLabel("분야");
					JPanel radioPane = new JPanel();
					ButtonGroup group = new ButtonGroup();
						String radioName[] = { "음악", "미술", "스포츠", "요리" };
					JRadioButton cate[] = new JRadioButton[4];//, cate2, cate3, cate4;
				JLabel careerLbl = new JLabel("경력");
					JTextField careerTf = new JTextField();	String c_yearTest;
			
		JPanel InfoSouth = new JPanel();
			JButton doBtn = new JButton("수정하기");
			JButton confBtn = new JButton("수정완료");
			
		String id;	
		
	public TeachInfo() {
	
	}
			
	public TeachInfo(String id) {
		this.id = id;
		System.out.println("Teach info id > > >"+ id);
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
		teaIdTf.setBounds(150,80, 250,30); teaIdTf.setFont(centerFnt); InfoCenter.add(teaIdTf);
		teaIdTf.setText(idTest);	teaIdTf.setBorder(new LineBorder(Color.black, 1));
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
		careerTf.setBounds(150,520, 250,30); careerTf.setFont(centerFnt); InfoCenter.add(careerTf);
		careerTf.setText(c_yearTest); careerTf.setBorder(new LineBorder(Color.black,1));
		//남쪽 패널
		InfoSouth.add(doBtn); InfoSouth.setBackground(Color.white); doBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		InfoSub.add("South", InfoSouth);
		
		//비활성화 상태로 만들기
		teaPwdTf.setEnabled(false);	teaNameTf.setEnabled(false);
		teaEmailTf.setEnabled(false); teaTelTf.setEnabled(false);
		teaAddrTf.setEnabled(false); careerTf.setEnabled(false); 
		
		doBtn.addActionListener(this);
		confBtn.addActionListener(this);
		
		setVisible(true);
		
		getTeachInfo(id);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionStr = ae.getActionCommand();
		Mem_teacherVO vo = new Mem_teacherVO();
		if(actionStr.equals(doBtn.getText())) {
			System.out.println("수정하기 선택");
			teaPwdTf.setEnabled(true);	teaNameTf.setEnabled(true);
			teaEmailTf.setEnabled(true); teaTelTf.setEnabled(true);
			teaAddrTf.setEnabled(true); careerTf.setEnabled(true);
			cate[0].setEnabled(true); cate[1].setEnabled(true);
			cate[2].setEnabled(true); cate[3].setEnabled(true);
			
			doBtn.setVisible(false);
			
			InfoSouth.add(confBtn); confBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			InfoSub.add("South", InfoSouth); 
			
			confBtn.setVisible(true);
		}else if(actionStr.equals(confBtn.getText())) {
			System.out.println("수정완료버튼");
			
			sendTeachInfo(id, ae);
		}
		//카테고리 버튼 이벤트 
/*		if(actionStr.equals(cate[0].getText())) {
			System.out.println("음악을 선택");
			vo.setCate(cate[0].getText());
		}else if(actionStr.equals(cate[1].getText())) {
			System.out.println("미술을 선택");
			vo.setCate(cate[1].getText());
		}else if(actionStr.equals(cate[2].getText())) {
			System.out.println("스포츠를 선택");
			vo.setCate(cate[2].getText());
		}else if(actionStr.equals(cate[3].getText())) {
			System.out.println("요리를 선택");
			vo.setCate(cate[3].getText());
		}	*/	
	}
	
	//회원정보 세팅
	public void getTeachInfo(String id) { //회원정보 세팅
		System.out.println("선생님 정보 세팅용 id--------------------" + id);
		Mem_teacherDAO dao = new Mem_teacherDAO();
		List<Mem_teacherVO> searchId = dao.getTeachInfo(id);
		System.out.println("ssssss ssss  sss "+searchId);
			
		if(searchId.size()==0 ) {
			System.out.println("아이디를 매치를 못함...");
		}else {
			Mem_teacherVO vo = searchId.get(0);
			
			if(vo.getId().equals(id)) {
				idTest = vo.getId();			teaIdTf.setText(idTest);	
				pwdTest = vo.gettPwd();			teaPwdTf.setText(pwdTest);
				nameTest = vo.gettName();		teaNameTf.setText(nameTest);
				birthTest = vo.gettBirth();		teaBirthLbl.setText(birthTest);
				telTest = vo.gettTel();			teaTelTf.setText(telTest);
				emailTest = vo.gettMail();		teaEmailTf.setText(emailTest);
				addrTest = vo.gettAddr();		teaAddrTf.setText(addrTest);
				//카테고리랑 경력 연수 추가
				if(vo.getCate().equals("음악")) {
					cate[0].setSelected(true);
				}else if(vo.getCate().equals("미술")) {
					cate[1].setSelected(true);
				}else if(vo.getCate().equals("스포츠")) {
					cate[2].setSelected(true);
				}else if(vo.getCate().equals("요리")) {
					cate[3].setSelected(true);
				}
				c_yearTest = String.valueOf(vo.getCareer_year());		careerTf.setText(c_yearTest);
			}	
		}
	}
	//회원정보 수정
	public void sendTeachInfo(String id, ActionEvent ae) {
		
		Mem_teacherVO vo = new Mem_teacherVO();
		vo.settPwd(teaPwdTf.getText());		vo.settName(teaNameTf.getText());
		vo.settMail(teaEmailTf.getText());	vo.settTel(teaTelTf.getText());
		vo.settAddr(teaAddrTf.getText());	vo.setCareer_year(Integer.parseInt(careerTf.getText()));
		
		String test1 = ae.getActionCommand();
		
		//카테고리랑 경력 연수 추가
		if(test1.equals("음악")) {
			vo.setCate(cate[0].getText());
			cate[0].setSelected(true);
		}else if(test1.equals("미술")) {
			vo.setCate(cate[1].getText());
			cate[1].setSelected(true);
		}else if(test1.equals("스포츠")) {
			vo.setCate("스포츠");
			cate[2].setSelected(true);
		}else if(test1.equals("요리")) {
			vo.setCate("요리");
			cate[3].setSelected(true);
		}
		
		Mem_teacherDAO dao = new Mem_teacherDAO();
		
		int result = dao.teachInfoUpdate(vo, id);
		int result2 = dao.teachInfoUpdate2(vo, id);
		
		System.out.println("카테고리 부분 수정? > > > "+result2);
		if(result>0 && result2>0) { //수정완료 확인부분
			JOptionPane.showMessageDialog(this, "수정이 완료되었습니다!");
			//비활성화 시키기
			teaPwdTf.setEnabled(false);		teaNameTf.setEnabled(false);	careerTf.setEnabled(false);
			teaEmailTf.setEnabled(false);	teaTelTf.setEnabled(false);		teaAddrTf.setEnabled(false);
			
			for(int i=0; i<radioName.length; i++) {
				cate[i].setEnabled(false);
			}
			
			confBtn.setVisible(false);
			doBtn.setVisible(true);
			
			getTeachInfo(id);
		}else {
			JOptionPane.showMessageDialog(this, "수정이 실패됐습니다, 다시 시도해주세요.");
		}
	}
	
	
	public static void main(String[] args) {
		new TeachInfo();
	}


}
