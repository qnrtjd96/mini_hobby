package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import main.Main0Login;

public class Studen2JFrameExtends extends JFrame implements ActionListener, MouseListener{
	JPanel paneTop = new JPanel(new BorderLayout());
		ImageIcon logo = new ImageIcon("img/logo.png");
		JButton Logo = new JButton(logo);
		Dimension logoSize = new Dimension(100, 50);
		JPanel paneLabel = new JPanel();
	
	ImageIcon background = new ImageIcon("mini_hobby/img/searchBack.gif");
	
	JPanel center = new JPanel(null); //전체 프레임의 중간에 들어갈 패널
		String defWord = " < < <클래스명을 입력해주세요 > > >";
		JTextField searchTf = new JTextField();
		JButton searchBtn = new JButton("검색");
		
		JButton musicBtn = new JButton("음악");	JButton artBtn = new JButton("미술");
		JButton sportBtn = new JButton("스포츠"); JButton cookBtn = new JButton("요리");
		
	//배경 설정
	JPanel back;
	int width, height;
		
	Font fnt = new Font("맑은 고딕",Font.PLAIN, 15);
	Font searchFnt = new Font("맑은 고딕", Font.PLAIN, 18);
	Font btnFnt = new Font("맑은 고딕", Font.BOLD, 20);

	Color col6 = new Color(204,222,233);
		
	
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	String idStr, pwdStr;
	
	public Studen2JFrameExtends() {
		
	}
	
	public Studen2JFrameExtends(String idStr, String pwdStr) {
		this.idStr = idStr;
		this.pwdStr = pwdStr;
		
		StudenTopMenu(idStr, pwdStr);
		StudenSearch(idStr);
		
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new AdapterInner());
	}
	
  //logo 버튼 이벤트 오버라이딩
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		if (obj==Logo) {
			center.setVisible(false);
			center.removeAll();
			StudenSearch(idStr);
			center.setVisible(true);
		} else if(obj==searchBtn) { // 검색버튼
			String searchWord = searchTf.getText();
			if(searchWord.equals("")) {
				JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
			}else {
				StudenCateList scl = new StudenCateList(searchWord, idStr);
				System.out.println("메인 검색어 > > > "+searchWord);
				scl.searchTf.setText(searchWord);
				center.setVisible(false);
				center.removeAll();
				center = scl.mainPane;
				center.setVisible(true);
				add("Center", center);
			}
		}else if(obj==musicBtn) {
			String music = musicBtn.getText();
			StudenCateList scl = new StudenCateList(music,idStr);
			scl.searchTf.setText(music);
			center.setVisible(false);
			center.removeAll();
			center = scl.mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==artBtn) {
			String art = artBtn.getText();
			StudenCateList scl = new StudenCateList(art,idStr);
			scl.searchTf.setText(art);
			center.setVisible(false);
			center.removeAll();
			center = scl.mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==sportBtn) {
			String sport = sportBtn.getText();
			StudenCateList scl = new StudenCateList(sport,idStr);
			scl.searchTf.setText(sport);
			center.setVisible(false);
			center.removeAll();
			center = scl.mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==cookBtn) {
			String cook = cookBtn.getText();
			StudenCateList scl = new StudenCateList(cook,idStr);
			scl.searchTf.setText(cook);
			center.setVisible(false);
			center.removeAll();
			center = scl.mainPane;
			center.setVisible(true);
			add("Center", center);
		}
		new StudenCateList().getId(idStr);
	}
	//label 이벤트 오버라이딩
	@Override
	public void mouseReleased(MouseEvent me) {
		Object tfObj = me.getSource();
		if(tfObj==searchTf) {
			searchTf.setText("");
		} else {
			String lbl = ((JLabel)tfObj).getText();
			if(lbl.equals("이전으로")) {
				////// 구현해서 객체 호출하세요 //////
				JOptionPane.showMessageDialog(this, "구현중입니다.");
				
			}else if(lbl.equals("메세지함")) {
				////// 구현해서 객체 호출하세요 //////
				center.setVisible(false);
				center.removeAll();
				center = new Studen3MsgFrame(idStr).tabBack;
				this.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("내정보")) {
				//내 정보 들어가기 전에 비밀번호 재입력
				String inputPwdString = JOptionPane.showInputDialog("비밀번호를 입력해주세요");
				MemberDAO dao = new MemberDAO();
				List<MemberVO> checkpwdList = dao.loginStart(idStr, pwdStr);
				MemberVO vo = checkpwdList.get(0);
				if(inputPwdString.equals(vo.getPwd())) {
					center.setVisible(false);
					center.removeAll();
					center = new Studen4MyMenu(idStr, pwdStr).paneStu;
					this.setVisible(true);
					add("Center", center);
				}else {
					JOptionPane.showMessageDialog(this ,"잘못된 비밀번호 입니다");
				}	
			}else if(lbl.equals("로그아웃")) {
				int answer = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃 확인", 0);
				if (answer==0) {
					this.setVisible(false);
					this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					Acess_memDAO dao = new Acess_memDAO();
					int result = dao.LogOut(idStr);
					System.out.println("로그아웃?"+result);
					//로그아웃 말고 X누르면 지워지는것도 구현해야됨 !!!
					new Main0Login();
				}
			}
		}
	} 
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(idStr);
			System.out.println("로그아웃?"+result);
			System.exit(0);
		}
	}
	
	public void matchWord(String searchWordS) {
		
	}
	public void StudenTopMenu(String idStr, String pwdStr) {
		
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,3);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"메세지함","내정보","로그아웃"};
		
		for(int i=0; i<topLblStr.length; i++) {
			JLabel topLbl = new JLabel(topLblStr[i], JLabel.CENTER);
			paneLabel.add(topLbl);
			topLbl.addMouseListener(this);
			topLbl.setFont(fn);
		}
		
		//간격조정
		paneTop.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		Logo.setSize(logoSize);
		paneTop.add(BorderLayout.WEST,Logo); paneTop.add(BorderLayout.EAST, paneLabel);
		
		//배경색상
		paneTop.setBackground(Color.white); Logo.setBackground(Color.white); paneLabel.setBackground(Color.white);
		
		//내정보 패널
		add(BorderLayout.NORTH,paneTop);
		
		setVisible(true);
		
		Logo.addActionListener(this);
	}

	public void StudenSearch(String idStr) {
		
		add(center);
		
		center.setBackground(Color.white);
		
		back = new JPanel() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("img/searchBack.gif");
			public void paint(Graphics g) {
				width = img.getWidth(this);
				height = img.getHeight(this);
				
				g.drawImage(img, 0,0,width+200,height+70, 100,0,width,height, this);
				
				setOpaque(false);
			}
		};
		back.setBackground(Color.white);
		back.setBounds(0,65, 1000,500);
		
		//searchTf.setText(defWord);
		searchTf.setBorder(new LineBorder(Color.black, 2));
		searchTf.setFont(searchFnt); searchTf.setBounds(100,250, 500,40); center.add(searchTf);
		
		//버튼에 색이 안 먹히는듯?
		searchBtn.setBackground(col6); searchBtn.setBorder(new LineBorder(Color.black, 2));
		searchBtn.setFont(btnFnt); searchBtn.setBounds(610,250, 80,40); center.add(searchBtn);
		
		musicBtn.setBackground(col6); musicBtn.setBorder(new LineBorder(Color.black, 2));
		musicBtn.setFont(btnFnt); musicBtn.setBounds(101,300, 80,40); center.add(musicBtn);
		
		artBtn.setBackground(col6); artBtn.setBorder(new LineBorder(Color.black, 2));
		artBtn.setFont(btnFnt); artBtn.setBounds(191,300, 80,40); center.add(artBtn);
		
		sportBtn.setBackground(col6); sportBtn.setBorder(new LineBorder(Color.black, 2));
		sportBtn.setFont(btnFnt); sportBtn.setBounds(281,300, 80,40); center.add(sportBtn);
		
		cookBtn.setBackground(col6); cookBtn.setBorder(new LineBorder(Color.black, 2));
		cookBtn.setFont(btnFnt); cookBtn.setBounds(371,300, 80,40); center.add(cookBtn);
		
		center.add(back);
		//버튼 이벤트 
		searchBtn.addActionListener(this);
		musicBtn.addActionListener(this);	artBtn.addActionListener(this);
		sportBtn.addActionListener(this);	cookBtn.addActionListener(this);
		
		searchTf.addMouseListener(this);
		
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent mc) {}
	
	
		
	public static void main(String[] args) {
		new Studen2JFrameExtends();
	}

}
