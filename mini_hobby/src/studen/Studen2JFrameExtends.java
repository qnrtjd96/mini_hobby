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

public class Studen2JFrameExtends extends JFrame implements ActionListener, MouseListener{
	JPanel paneTop = new JPanel(new BorderLayout());
		ImageIcon logo = new ImageIcon("img/logo.png");
		JButton Logo = new JButton(logo);
		Dimension logoSize = new Dimension(100, 50);
		JPanel paneLabel = new JPanel();
	
	ImageIcon background = new ImageIcon("img/searchBack.gif");
	
	JPanel center = new JPanel(null); //전체 프레임의 중간에 들어갈 패널
		String defWord = "클래스명 또는 강사의 이름을 입력해주세요.";
		JTextField searchTf = new JTextField();
		JButton searchBtn = new JButton("검색");
		
		JButton musicBtn = new JButton("음악");	JButton artBtn = new JButton("미술");
		JButton sportBtn = new JButton("스포츠"); JButton cookBtn = new JButton("요리");
		
		JTextArea classTa = new JTextArea();
		String testTa = "예약 클래스 : \n 레이아웃 빨리 끝내고 싶다... \n 날짜: 2021-02-25";
		
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
	
	String idStr;
	
	public Studen2JFrameExtends() {
		
	}
	
	public Studen2JFrameExtends() {
		this.idStr = idStr;
		
		StudenTopMenu(idStr);
		StudenSearch(idStr);
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		} else if(obj==searchBtn) {
			String searchWord = searchTf.getText();
			center.setVisible(false);
			center.removeAll();
			center = new StudenCateList().mainPane;
			center.setVisible(true);
			add("Center", center);
			System.out.println("검색버튼을 누름");
			matchWord(searchWord);
		}else if(obj==musicBtn) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenCateList().mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==artBtn) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenCateList().mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==sportBtn) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenCateList().mainPane;
			center.setVisible(true);
			add("Center", center);
		}else if(obj==cookBtn) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenCateList().mainPane;
			center.setVisible(true);
			add("Center", center);
		}
	}
	//label 이벤트 오버라이딩
	@Override
	public void mouseReleased(MouseEvent me) {
		this.idStr = idStr;
		
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		
		//JTextField stuObj = (JTextField)me.getSource(); textfield nono textarea
		
		try {
			if(lbl.equals("이전으로")) {
				////// 구현해서 객체 호출하세요 //////
				JOptionPane.showMessageDialog(this, "구현중입니다.");
				
			}else if(lbl.equals("메세지함")) {
				////// 구현해서 객체 호출하세요 //////
				center.setVisible(false);
				center.removeAll();
				center = new Studen3MsgFrame().tabBack;
				this.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("내정보")) {
				center.setVisible(false);
				center.removeAll();
				center = new Studen4MyMenu(idStr).paneStu;
				this.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("로그아웃")) {
				int answer = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃 확인", 0);
				if (answer==0) {
					this.setVisible(false);
					System.exit(0);
				}
			}/* else if(stuObj==searchTf) {
				System.out.println("텍필 눌림");
				searchTf.setText("");
			} */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void StudenTopMenu() {
		
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,4);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"이전으로","메세지함","내정보","로그아웃"};
		
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
		//paneCenter.add(new MyMenu_Stu().paneStu);
		add(BorderLayout.NORTH,paneTop);
		
		setVisible(true);
		
		Logo.addActionListener(this);
	}

	public void StudenSearch() {
		
		add(center);
		
		center.setBackground(Color.white);
		
		back = new JPanel() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("img/searchBack.gif");
			public void paint(Graphics g) {
				width = img.getWidth(this);
				height = img.getHeight(this);
				
				g.drawImage(img, 0,0,1000,height+200, 100,0,width,height, this);
				
				setOpaque(false);
				//super.paintComponent(g);
			}
		};
		back.setBackground(Color.white);
		back.setBounds(0,65, 1000,500);
		//center.add(back);
		
		searchTf.setText(defWord);
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
		
		//textArea 가운데 정렬..? ㅠㅠ?
		classTa.setBackground(Color.white); classTa.setBorder(new LineBorder(Color.black, 1)); 
		classTa.setFont(new Font("맑은 고딕", Font.PLAIN, 15)); classTa.setBounds(580,700, 200,100);
		classTa.setText(testTa); 
		center.add(classTa);
		center.add(back);
		//버튼 이벤트 
		searchBtn.addActionListener(this);
		musicBtn.addActionListener(this);	artBtn.addActionListener(this);
		sportBtn.addActionListener(this);	cookBtn.addActionListener(this);
		
		
		
	}
	
	public void matchWord(String searchWord) {
		System.out.println("검색눌림");
		JOptionPane.showMessageDialog(this, "ㅎㅎㅎㅎ");
		
	}
	
	//이벤트 구현 여기서 해야하나?
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent mc) {
	}
	
	
		
	public static void main(String[] args) {
		new Studen2JFrameExtends();
	}

}
