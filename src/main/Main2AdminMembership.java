package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import dbConnection.Mem_teacherVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import dbConnection.MoneyDAO;

public class Main2AdminMembership extends JFrame implements ItemListener, ActionListener, MouseListener{
	JPanel pane_membership = new JPanel();
		// 상단 로고 / 회원가입 라벨 / 학생,선생님 라디오버튼
		ImageIcon img = new ImageIcon("img/Biglogo.png");
		JButton logo = new JButton(img);
		JLabel memLbl = new JLabel("회원가입 페이지", JLabel.CENTER);
		JRadioButton checkStu = new JRadioButton("학생");
		JRadioButton checkTea = new JRadioButton("선생님");
		ButtonGroup checkGroup = new ButtonGroup();
		// 중앙 라벨 / 텍스트 필드 / 중복확인 버튼
		JLabel lblStr[] = {new JLabel("아이디"),new JLabel("비밀번호"),new JLabel("비밀번호 확인"),new JLabel("이름"),new JLabel("생년월일"),new JLabel("이메일 주소"),
				new JLabel("휴대폰 번호"),new JLabel("거주지 주소")};
		JButton btn_stuRht = new JButton("중복확인");
		LineBorder lineBorder = new LineBorder(Color.RED);
		JTextPane taId = new JTextPane();
		JTextField tfStr[] = {new JTextField("아이디를 입력하세요."),new JTextField("비밀번호를 입력하세요."),new JTextField("비밀번호를 다시 입력하세요."),
				new JTextField("이름을 입력하세요."),new JTextField("생년월일을 입력하세요."),new JTextField("이메일 주소를 입력하세요."),new JTextField("휴대폰 번호를 입력하세요."),new JTextField("거주지 주소를 입력하세요.")};
		JTextPane taPwd = new JTextPane();
		// 하단 선생님 입력단 / 입력완료 버튼
		JLabel lbl_cate = new JLabel("분야", JLabel.LEFT);
		JRadioButton checkMusic = new JRadioButton("음악");
		JRadioButton checkPaint = new JRadioButton("미술");
		JRadioButton checkSports = new JRadioButton("스포츠");
		JRadioButton checkCook = new JRadioButton("요리");
		ButtonGroup cateGroup = new ButtonGroup();
		JLabel lbl_carr = new JLabel("경력", JLabel.LEFT);
		JTextField tf_carr = new JTextField("경력을 입력하세요.");
		JButton btmBtn = new JButton("입력완료");
		
		Font fntPlain = new Font("맑은 고딕", Font.PLAIN, 15);
		Font fntBold = new Font("맑은 고딕", Font.BOLD, 15);
		
		int check=0; int overlap=0;
		String cate;
	public Main2AdminMembership() {
		add("Center",pane_membership);
		pane_membership.setLayout(null);
		pane_membership.add(logo); pane_membership.add(memLbl);
		pane_membership.add(checkStu); pane_membership.add(checkTea);
		checkGroup.add(checkStu); checkGroup.add(checkTea);
		// 선생님 추가 입력 부분
		pane_membership.add(lbl_cate); pane_membership.add(checkMusic); pane_membership.add(checkPaint); pane_membership.add(checkSports); pane_membership.add(checkCook); 
		cateGroup.add(checkMusic); cateGroup.add(checkPaint); cateGroup.add(checkSports); cateGroup.add(checkCook);
		pane_membership.add(lbl_carr); pane_membership.add(tf_carr); 
		//입력완료 버튼
		pane_membership.add(btmBtn);
		
		for(int i=0; i<lblStr.length; i++) {
			lblStr[i].setFont(fntBold);
			pane_membership.add(lblStr[i]);
		}
		for(int i=0; i<tfStr.length; i++) {
			tfStr[i].setFont(fntPlain);
			pane_membership.add(tfStr[i]);
			tfStr[i].addMouseListener(this);
		}
		// 아이디 중복 여부 및 패스워드 제약조건
		taId.setText("중복여부를 확인하세요."); taPwd.setText("비밀번호는 8글자 이상 기재해야합니다.");
		pane_membership.add(taId); pane_membership.add(taPwd);
		// 아이디 중복 여부 및 패스워드 제약조건 텍스트 가운데 정렬
		StyledDocument document = taId.getStyledDocument();
		StyledDocument document2 = taPwd.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		document.setParagraphAttributes(0, document.getLength(), center, false);
		document2.setParagraphAttributes(0, document2.getLength(), center, false);
		// 아이디 중복 여부 및 패스워드 제약조건 텍스트 보더 라인 색상 적용 / 텍스트 입력 불가 설정
		taId.setBorder(lineBorder); taPwd.setBorder(lineBorder);
		taId.setEditable(false); taPwd.setEditable(false);
		
		// 중복확인 버튼
		pane_membership.add(btn_stuRht);
		
		// 로고 및 회원가입 페이지 문구
		logo.setBounds(300,30,200,100); logo.setBackground(Color.white);
		memLbl.setBounds(550,70,150,100);
		// 학생, 선생님 체크
		checkStu.setBounds(250,180,100,50); checkTea.setBounds(500,180,100,50);
		// 라벨 및 텍스트 필드, 중복확인 버튼
		lblStr[0].setBounds(20,200,100,100); tfStr[0].setBounds(150,240,490,30); btn_stuRht.setBounds(658,240,100,30);
		lblStr[1].setBounds(20,300,100,100); taId.setBounds(150,290,610,30); // 아이디 중복확인 필드
		lblStr[2].setBounds(20,350,100,100); tfStr[1].setBounds(150,340,610,30);
		lblStr[3].setBounds(20,450,100,100); tfStr[2].setBounds(150,390,610,30);
		lblStr[4].setBounds(20,500,100,100); taPwd.setBounds(150,440,610,30); // 비밀번호 입력 조건 필드
		lblStr[5].setBounds(20,550,100,100); tfStr[3].setBounds(150,490,610,30);
		lblStr[6].setBounds(20,600,100,100); tfStr[4].setBounds(150,540,610,30);
		lblStr[7].setBounds(20,650,100,100); tfStr[5].setBounds(150,590,610,30);
											 tfStr[6].setBounds(150,640,610,30);
											 tfStr[7].setBounds(150,690,610,30);
		// 선생님 추가 입력 부분
		lbl_cate.setBounds(20,725,100,100); checkMusic.setBounds(200,760,100,30); checkPaint.setBounds(350,760,100,30); checkSports.setBounds(500,760,100,30); checkCook.setBounds(650,760,100,30);
		lbl_carr.setBounds(20,790,100,100); tf_carr.setBounds(150,830,610,30);
		// 최하단 입력완료 버튼
		btmBtn.setBounds(330, 900, 130, 40);
		
		// 학생 선택 시 숨기기
		//lbl_cate.setVisible(false); checkMusic.setVisible(false); checkPaint.setVisible(false); checkSports.setVisible(false); checkCook.setVisible(false);
		//lbl_carr.setVisible(false); tf_carr.setVisible(false);
		
		// 폰트 조절 부분
		memLbl.setFont(fntBold);
		checkStu.setFont(fntBold); checkTea.setFont(fntBold);
		btn_stuRht.setFont(fntBold);
		taId.setFont(fntBold); taPwd.setFont(fntBold);
		lbl_cate.setFont(fntBold); checkMusic.setFont(fntBold); checkPaint.setFont(fntBold); checkSports.setFont(fntBold); checkCook.setFont(fntBold);
		lbl_carr.setFont(fntBold); tf_carr.setFont(fntPlain);
		btmBtn.setFont(fntBold);
		
		// 배경 색상 셋팅
		pane_membership.setBackground(Color.white);
		checkStu.setBackground(Color.white); checkTea.setBackground(Color.white);
		checkMusic.setBackground(Color.white); checkPaint.setBackground(Color.white); checkSports.setBackground(Color.white); checkCook.setBackground(Color.white);
		
		setVisible(true);
		setSize(800,1000);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//탭 이벤트 호출
		setShortCut();
		
		checkStu.addItemListener(this);
		checkTea.addItemListener(this);
		btmBtn.addActionListener(this);
		btn_stuRht.addActionListener(this);
		checkMusic.addItemListener(this);
		checkPaint.addItemListener(this);
		checkSports.addItemListener(this);
		checkCook.addItemListener(this);
		tf_carr.addMouseListener(this);
		logo.addActionListener(this);
	}
	public void itemStateChanged(ItemEvent ie) {
		Object obj = ie.getSource();
		//학생, 선생님 구분
		if(obj==checkStu) {
			check=1;
			lbl_cate.setVisible(false); checkMusic.setVisible(false); checkPaint.setVisible(false); checkSports.setVisible(false); checkCook.setVisible(false);
			lbl_carr.setVisible(false); tf_carr.setVisible(false);
		}else if(obj==checkTea) {
			check=2;
			lbl_cate.setVisible(true); checkMusic.setVisible(true); checkPaint.setVisible(true); checkSports.setVisible(true); checkCook.setVisible(true);
			lbl_carr.setVisible(true); tf_carr.setVisible(true);
		}
		//카테고리 리턴
		if(obj == checkMusic) {
			cate = "음악";
		}else if(obj == checkPaint) {
			cate = "미술";
		}else if(obj == checkSports) {
			cate = "스포츠";
		}else if(obj == checkCook) {
			cate = "요리";
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj==btmBtn) {
			setMember();
		} else if (obj==btn_stuRht) {
			String idStr = tfStr[0].getText();
			overlapMember(idStr);
		} else if(obj==logo) {
			this.setVisible(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			new Main0Login();
		}
	}
	public void mouseReleased(MouseEvent me) {
		Object obj = me.getSource();
		if (obj==tfStr[0]) {tfStr[0].setText("");}
		else if(obj==tfStr[1]) {tfStr[1].setText("");}
		else if(obj==tfStr[2]) {tfStr[2].setText("");}
		else if(obj==tfStr[3]) {tfStr[3].setText("");}
		else if(obj==tfStr[4]) {tfStr[4].setText("");}
		else if(obj==tfStr[5]) {tfStr[5].setText("");}
		else if(obj==tfStr[6]) {tfStr[6].setText("");}
		else if(obj==tfStr[7]) {tfStr[7].setText("");}
		if(obj==tf_carr) tf_carr.setText("");
	}
	public void overlapMember(String idStr) {
		if(idStr.equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
		} else {
			MemberDAO dao = new MemberDAO();
			List<MemberVO> searchList = dao.overlapCheck(idStr);
			if(searchList.size()==0) {
				overlap=2;
				JOptionPane.showMessageDialog(this, "가입가능한 ID입니다.");
			} else {
				overlap=1;
				JOptionPane.showMessageDialog(this, "중복 ID입니다. 다시 확인해주세요");
			}
			
		}
	}
	public void setMember() {
		String id = tfStr[0].getText();
		MemberVO vo = new MemberVO(id, tfStr[1].getText(), tfStr[3].getText(),
				tfStr[4].getText(), tfStr[5].getText(), tfStr[6].getText(), tfStr[7].getText(), check, 1);
		if(vo.getId().equals("")||vo.getPwd().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디와 비밀번호는 필수 입력조건입니다.");
		} else if (vo.getName().equals("")||vo.getMail().equals("")||vo.getTel().equals("")) {
			JOptionPane.showMessageDialog(this, "이름, 메일주소, 연락처는 필수 입력조건입니다."); 
		} else if (vo.getSort()!=1 && vo.getSort()!=2) {
			JOptionPane.showMessageDialog(this, "사용자/강사 여부를 선택해주셔야 합니다.");
		} else if(!(tfStr[1].getText().equals(tfStr[2].getText()))) {
			JOptionPane.showMessageDialog(this, "비밀번호와 확인비밀번호가 일치하지 않습니다. 다시 확인하세요.");
		} else if(tfStr[1].getText().length()<8) {
			JOptionPane.showMessageDialog(this, "비밀번호는 8글자 이상 기재해야 합니다.");
		} else if(overlap==0) {
			JOptionPane.showMessageDialog(this, "ID 중복확인 후 가입신청이 가능합니다.");
		} else if(overlap==1) {
			JOptionPane.showMessageDialog(this, "중복된 ID로는 가입이 불가합니다.");
		} else {
			MemberDAO dao = new MemberDAO();
			int result = dao.memberInsert(vo);
			if(check == 2) {
				Mem_teacherVO mem_teachvo = new Mem_teacherVO(Integer.parseInt(tf_carr.getText()),cate,id);
				if(cate==null) {
					JOptionPane.showMessageDialog(this, "카테고리는 반드시 선택해주셔야합니다.");
				}else if(tf_carr.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "경력란에 경력을 입력하셔야합니다.(ex)3,5,9)");
				}else {
					int result2 = dao.Mem_teachInsert(mem_teachvo);
					if (result2>0) {
						tf_carr.setText("");
					}
				}
			}
			if (result>0) { // result값이 생겼다는건 등록된 줄이 발생했다는 것임!
				JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다. \n 로그인페이지로 돌아갑니다.");
				this.setVisible(false);
				new Main0Login();
				for(int i=0; i<tfStr.length; i++) {
					tfStr[i].setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다.");
			}
			MoneyDAO mdao = new MoneyDAO();
			mdao.setMoney(id);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent me) {}
	//tab 설정
	public void setShortCut() {
		tfStr[0].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[0].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[1].setText("");
					tfStr[1].requestFocus();
				}
			}
		});
		tfStr[1].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[1].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[2].setText("");
					tfStr[2].requestFocus();
				}
			}
		});
		tfStr[2].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[2].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[3].setText("");
					tfStr[3].requestFocus();
				}
			}
		});
		tfStr[3].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[3].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[4].setText("");
					tfStr[4].requestFocus();
				}
			}
		});
		tfStr[4].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[4].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[5].setText("");
					tfStr[5].requestFocus();
				}
			}
		});
		tfStr[5].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[5].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[6].setText("");
					tfStr[6].requestFocus();
				}
			}
		});
		tfStr[6].setFocusTraversalKeysEnabled(false);//텍스트필드에서 탭키를 이용해 이동을 할 것인지를 설정
		tfStr[6].addKeyListener(new java.awt.event.KeyAdapter() {//키리스너를 설정하고
			public void keyPressed(java.awt.event.KeyEvent e) {//키보드의 키를 누를때 이벤트 발생
				if(e.getKeyCode() == 9){//탭키의 키코드 값이 "9"입니다.
					//이부분에 이벤트를 작성합니다.
					tfStr[7].setText("");
					tfStr[7].requestFocus();
				}
			}
		});
	}
}
