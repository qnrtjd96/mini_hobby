import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Admin1Main extends JFrame implements MouseListener, ActionListener{
	JPanel paneTop = new JPanel(new BorderLayout());
		ImageIcon logo = new ImageIcon("img/logo.png");
		JButton Logo = new JButton(logo);
		Dimension logoSize = new Dimension(100, 50);
		JPanel paneLabel = new JPanel();
	JPanel mainPane = new JPanel();
		//검색
		JTextField searchTf = new JTextField();
		JButton searchBtn = new JButton("검색");
		//그래프
		JPanel leftGrap = new JPanel();
			p grap = new p(); //그래프
		JPanel rightGrap = new JPanel();
			p2 grap2 = new p2(); //그래프
		//이용자 수
		JTextPane AllUserTp = new JTextPane();
		JTextPane UserTp = new JTextPane();
		
	LineBorder lineBorder = new LineBorder(Color.black);
	
	Font fntPlain12 = new Font("맑은 고딕", Font.PLAIN, 12);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	
	Color col6 = new Color(204,222,233);
	public Admin1Main() {
		StudenTopMenu();
		mainStart();
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Logo.addActionListener(this);
		searchBtn.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		if (obj==Logo) {
			mainPane.removeAll();
			mainPane.setVisible(false);
			mainStart();
			mainPane.setVisible(true);
		} else if (obj==searchBtn) {
			mainPane.removeAll();
			mainPane.setVisible(false);
			mainPane = new StudenCateList().mainPane;
			mainPane.setVisible(true);
			add("Center", mainPane);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		try {
			if(lbl.equals("이전으로")) {
				////// 구현해서 객체 호출하세요 //////
				JOptionPane.showMessageDialog(this, "구현중입니다.");
				
			}else if(lbl.equals("메세지함")) {
				////// 구현해서 객체 호출하세요 //////
				mainPane.setVisible(false);
				mainPane.removeAll();
				mainPane = new Admin2MsgFrame().tabBack;
				this.setVisible(true);
				add("Center", mainPane);
			}else if(lbl.equals("관리메뉴")) {
				mainPane.setVisible(false);
				mainPane.removeAll();
				mainPane = new Admin3Menu().tabBack;
				this.setVisible(true);
				add("Center", mainPane);
			}else if(lbl.equals("로그아웃")) {
				int answer = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃 확인", 0);
				if (answer==0) {
					this.setVisible(false);
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent me) {}
	
	public void StudenTopMenu() {
		
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,4);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"이전으로","메세지함","관리메뉴","로그아웃"};
		
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
	}
	public void mainStart() {
		mainPane.setLayout(null);
		
		//왼쪽 그래프 설정
		leftGrap.setLayout(null);
		leftGrap.add(grap);
		leftGrap.setBorder(lineBorder);
		
		//오른쪽 그래프 설정
		rightGrap.setLayout(null);
		rightGrap.add(grap2);
		rightGrap.setBorder(lineBorder);
		
		//mainPane add
		mainPane.add(searchTf); mainPane.add(searchBtn);
		mainPane.add(leftGrap); mainPane.add(rightGrap); 
		mainPane.add(AllUserTp); mainPane.add(UserTp);
		
		//위치 값
		searchTf.setBounds(20, 20, 620, 50); searchBtn.setBounds(660, 20, 100, 50);
		leftGrap.setBounds(20, 90, 370, 575); rightGrap.setBounds(389, 90, 370, 575);
		grap.setBounds(1, 1, 368, 573); grap2.setBounds(1, 1, 368, 573);
		AllUserTp.setBounds(20, 670, 370, 180); UserTp.setBounds(389, 670, 370, 180);
		
		//기본 텍스트 셋팅
		//searchTf.setText("조회하고싶은 클래스를 입력하세요.");
		AllUserTp.setText("접속중인 회원 수\n\n 30000명"); UserTp.setText("총 이용자 수\n\n 250000명");
		
		//폰트 셋팅
		searchTf.setFont(fntBold20);searchBtn.setFont(fntBold20);
		AllUserTp.setFont(fntBold30);UserTp.setFont(fntBold30);
		
		//가운데 정렬
		StyledDocument doc = AllUserTp.getStyledDocument();
		StyledDocument doc2 = UserTp.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		doc2.setParagraphAttributes(0, doc.getLength(), center, false);
		
		//배경 셋팅
		mainPane.setBackground(Color.white);
		searchBtn.setBackground(col6);
		AllUserTp.setBorder(lineBorder);
		UserTp.setBorder(lineBorder);
		
		add(BorderLayout.CENTER,mainPane);
		add(BorderLayout.NORTH,paneTop);
	}
	//결과물(그래프)이 나타날 패널
		public class p extends JPanel{
			public void paint(Graphics g) {
				int lab=100, lab1=150, lab2=200, lab3=175;
				g.clearRect(0,0,getWidth(),getHeight()); //클리어
				//테두리 그리기
				//(int x1, int y1, int x2, int y2)
				g.drawLine(0,540,550,540); //가로줄
				g.setFont(fntBold20);
				g.drawString("월별 총 수익", 140, 40);
				for (int i = 1; i< 21 ; i++) {
					//g.drawString(i*100000 + "", 0, 540-(20*i));
					g.setFont(fntPlain12);
					if(i ==5) {
						g.drawLine(0, 440, 550, 440);
						//수치 값
						g.drawString(lab + "000원", 30, 530-(lab*2));
						//월
						g.drawString("1월", 50, 560);
					}
					if(i == 10) {
						g.drawLine(0, 340, 550, 340);
						//수치 값
						g.drawString(lab1 + "0000원", 110, 530-(lab1*2));
						//월
						g.drawString("2월", 135, 560);
					}
					if(i == 15) {
						g.drawLine(0, 240, 550, 240);
						//수치 값
						g.drawString(lab2 + "0000원", 200, 530-(lab2*2));
						//월
						g.drawString("3월", 215, 560);
					}
					if(i == 20) {
						g.drawLine(0, 140, 550, 140);
						//수치 값
						g.drawString(lab3 + "0000원", 280, 530-(lab3*2));
						//월
						g.drawString("4월", 300, 560);
					}
						
				}		
				//g.drawLine(50,30,50,540); //세로줄
				
				//좌표값으로 지정해주기 //(String str, int x, int y)
//				g.drawString("11월", 130, 500); // name, x, y
//				g.drawString("12월",  235, 500);
//				g.drawString("1월", 335, 500);
//				g.drawString("2월", 435, 500);
//				g.drawString("11월", 75, 600); // name, x, y
//				g.drawString("12월",  200, 600);
//				g.drawString("1월", 350, 600);
//				g.drawString("2월", 470, 600);
				Color colOran = new Color(255,153,51,255);
				Color colYell = new Color(255,204,51,255);
				g.setColor(colOran);
				
				if(lab > 0)
					//fillRect(int x, int y, int width, int height)
					g.fillRect(30,540-lab*2, 55, lab*2);	
				if(lab1 > 0)
					g.fillRect(115,540-lab1*2, 55, lab1*2);	
				if(lab2 > 0)
					g.fillRect(200,540-lab2*2, 55, lab2*2);
				if(lab3 > 0)
					g.fillRect(285,540-lab3*2, 55, lab3*2);
			}
		}
		//결과물(그래프)이 나타날 패널
			public class p2 extends JPanel{
				public void paint(Graphics g) {
					int lab=200, lab1=100, lab2=50, lab3=175;
					g.clearRect(0,0,getWidth(),getHeight()); //클리어
					//테두리 그리기
					//(int x1, int y1, int x2, int y2)
					g.drawLine(0,540,550,540); //가로줄
					g.setFont(fntBold20);
					g.drawString("월별 결제 건수", 130, 40);
					for (int i = 1; i< 21 ; i++) {
						g.setFont(fntPlain12);
						//g.drawString(i*100000 + "", 0, 540-(20*i));
						if(i ==5) {
							g.drawLine(0, 440, 550, 440);
							//수치 값
							g.drawString(lab + "0명", 45, 530-(lab*2));
							//월
							g.drawString("1월", 50, 560);
						}
						if(i == 10) {
							g.drawLine(0, 340, 550, 340);
							//수치 값
							g.drawString(lab1 + "0명", 130, 530-(lab1*2));
							//월
							g.drawString("2월", 135, 560);
						}
						if(i == 15) {
							g.drawLine(0, 240, 550, 240);
							//수치 값
							g.drawString(lab2 + "0명", 220, 530-(lab2*2));
							//월
							g.drawString("3월", 215, 560);
						}
						if(i == 20) {
							g.drawLine(0, 140, 550, 140);
							//수치 값
							g.drawString(lab3 + "0명", 300, 530-(lab3*2));
							//월
							g.drawString("4월", 300, 560);
						}
							
					}		
					//g.drawLine(50,30,50,540); //세로줄
					
					//좌표값으로 지정해주기 //(String str, int x, int y)
//					g.drawString("11월", 130, 500); // name, x, y
//					g.drawString("12월",  235, 500);
//					g.drawString("1월", 335, 500);
//					g.drawString("2월", 435, 500);
//					g.drawString("11월", 75, 600); // name, x, y
//					g.drawString("12월",  200, 600);
//					g.drawString("1월", 350, 600);
//					g.drawString("2월", 470, 600);
					Color colOran = new Color(255,153,51,255);
					Color colYell = new Color(255,204,51,255);
					g.setColor(colYell);
					
					if(lab > 0)
						//fillRect(int x, int y, int width, int height)
						g.fillRect(30,540-lab*2, 55, lab*2);	
					if(lab1 > 0)
						g.fillRect(115,540-lab1*2, 55, lab1*2);	
					if(lab2 > 0)
						g.fillRect(200,540-lab2*2, 55, lab2*2);
					if(lab3 > 0)
						g.fillRect(285,540-lab3*2, 55, lab3*2);
				}
			}
	
	public static void main(String[] args) {
		new Admin1Main();

	}

}
