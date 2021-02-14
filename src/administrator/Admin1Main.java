package administrator;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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

import dbConnection.Acess_memDAO;
import dbConnection.Acess_memVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;
import studen.StudenCateList;

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
	
	//월별 데이터
	Calendar date; 
	int year1, year2, year3, year4;
	int month1, month2, month3, month4;
	String date1, date2, date3, date4;
	public Admin1Main() {
		StudenTopMenu();
		mainStart();
		canlendarDate();
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Logo.addActionListener(this);
		searchBtn.addActionListener(this);
		searchTf.addMouseListener(this);

	}
	//월별 데이터 핸들링
	public void canlendarDate() {
		date = Calendar.getInstance();
		year4 = date.get(Calendar.YEAR); //현재 날짜의 년도
		month4 = date.get(Calendar.MONTH)+1; //현재 날짜의 월
		//3달전 데이터
		year3 = year4; month3 = month4-1;
		year2 = year3; month2 = month3-1;
		year1 = year2; month1 = month2-1;
		if(month4==1) {
			year3 -= 1;	month3 = 12;
			year2 = year3; month2 = 11;
			year1 = year2; month1 = 10;
		}else if(month3==1) {
			year2 -= 1; month2 = 12;
			year1 = year2; month1 = 11;
		}else if(month2==1) {
			year1 -= 1; month1 = 12;
		}
		//년도 2글자로 줄이기, 월이 한글자일때 0넣기
		String year01, year02, year03, year04;
		String month01, month02, month03, month04;
		year01 = String.valueOf(year1).substring(2); month01 = String.format("%02d", month1);
		year02 = String.valueOf(year2).substring(2); month02 = String.format("%02d", month2);
		year03 = String.valueOf(year3).substring(2); month03 = String.format("%02d", month3);
		year04 = String.valueOf(year4).substring(2); month04 = String.format("%02d", month4);
		//DB where에 사용될 날짜데이터
		date1 = year01+"/"+month01;
		date2 = year02+"/"+month02;
		date3 = year03+"/"+month03;
		date4 = year04+"/"+month04;
		
	}
	//월별 총수익 데이터 DB
	public int sumDatePay(String date) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.paySum(date);
		int sum=0;
		for(int i=0; i<lst.size(); i++) {
			Stu_ClassVO vo = lst.get(i);
			sum = vo.getPay();
		}
		return sum;
	}
	//월별 실결제건수 데이터 DB
	public int countDatePay(String date) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.payCount(date);
		int sum=0;
		for(int i=0; i<lst.size(); i++) {
			Stu_ClassVO vo = lst.get(i);
			sum = vo.getPay();
		}
		return sum;
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
			if(searchTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "요리,스포츠,음악,미술 중 입력후 검색하세요.");
			}else {
				mainPane.removeAll();
				mainPane.setVisible(false);
				String cate = searchTf.getText();
				this.mainPane = new StudenCateList(cate,"master").mainPane;
				mainPane.setVisible(true);
				add("Center", mainPane);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent me) {
		Object tf = me.getSource();
		int clickBtn = me.getButton();
		if(clickBtn == 1 && tf == searchTf) {
			searchTf.setText("");
		}else {
			JLabel obj = (JLabel)me.getSource();
			Object lbl = obj.getText();
			try {
				if(lbl.equals("메세지함")) {
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
	}
	
	public void StudenTopMenu() {
		
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,3);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"메세지함","관리메뉴","로그아웃"};
		
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
		AllUserTp.setText("총 이용자 수\n\n "+ userAll() + "명"); UserTp.setText("접속중인 회원 수\n\n " + userLive() + "명");
		
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
			int lab=0, lab1=0, lab2=0, lab3=0;
			//그래프 막대기 핸들링
			if(sumDatePay(date1)==0) {
				lab = 3;
			}else if(sumDatePay(date1)>=1 && sumDatePay(date1)<300000) {
				lab = 30;
			}else if (sumDatePay(date1)>300000 && sumDatePay(date1)<=600000) {
				lab = 80;
			}else if (sumDatePay(date1)>600000 && sumDatePay(date1)<=900000) {
				lab = 130;
			}else if (sumDatePay(date1)>12000000) {
				lab = 200;
			}
			if(sumDatePay(date2)==0) {
				lab1 = 3;
			}else if(sumDatePay(date2)>=1 && sumDatePay(date2)<300000) {
				lab1 = 30;
			}else if (sumDatePay(date2)>300000 && sumDatePay(date2)<=600000) {
				lab1 = 80;
			}else if (sumDatePay(date2)>600000 && sumDatePay(date2)<=900000) {
				lab1 = 130;
			}else if (sumDatePay(date2)>12000000) {
				lab1 = 200;
			}
			if(sumDatePay(date3)==0) {
				lab2 = 3;
			}else if(sumDatePay(date3)>=1 && sumDatePay(date3)<300000) {
				lab2 = 30;
			}else if (sumDatePay(date3)>300000 && sumDatePay(date3)<=600000) {
				lab2 = 80;
			}else if (sumDatePay(date3)>600000 && sumDatePay(date3)<=900000) {
				lab2 = 130;
			}else if (sumDatePay(date3)>12000000) {
				lab2 = 200;
			}
			if(sumDatePay(date4)==0) {
				lab3 = 3;
			}else if(sumDatePay(date4)>=1 && sumDatePay(date4)<300000) {
				lab3 = 30;
			}else if (sumDatePay(date4)>300000 && sumDatePay(date4)<=600000) {
				lab3 = 80;
			}else if (sumDatePay(date4)>600000 && sumDatePay(date4)<=900000) {
				lab3 = 130;
			}else if (sumDatePay(date4)>12000000) {
				lab3 = 200;
			}
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
					g.drawString(sumDatePay(date1)+"원", 30, 530-(lab*2));
					//월
					g.drawString(year1+"."+month1, 35, 560);
				}
				if(i == 10) {
					g.drawLine(0, 340, 550, 340);
					//수치 값
					g.drawString(sumDatePay(date2) + "원", 110, 530-(lab1*2));
					//월
					g.drawString(year2+"."+month2, 120, 560);
				}
				if(i == 15) {
					g.drawLine(0, 240, 550, 240);
					//수치 값
					g.drawString(sumDatePay(date3) + "원", 200, 530-(lab2*2));
					//월
					g.drawString(year3+"."+month3, 210, 560);
				}
				if(i == 20) {
					g.drawLine(0, 140, 550, 140);
					//수치 값
					g.drawString(sumDatePay(date4) + "원", 280, 530-(lab3*2));
					//월
					g.drawString(year4+"."+month4, 295, 560);
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
			int lab=0, lab1=0, lab2=0, lab3=0;
			if(countDatePay(date1)==0) {
				lab = 3;
			}else if(countDatePay(date1)>=1 && countDatePay(date1)<=10) {
				lab = 30;
			}else if (countDatePay(date1)>10 && countDatePay(date1)<=50) {
				lab = 80;
			}else if (countDatePay(date1)>50 && countDatePay(date1)<=100) {
				lab = 130;
			}else if (countDatePay(date1)>100) {
				lab = 200;
			}
			if(countDatePay(date2)==0) {
				lab1 = 3;
			}else if(countDatePay(date2)>=1 && countDatePay(date2)<=10) {
				lab1 = 30;
			}else if (countDatePay(date2)>10 && countDatePay(date2)<=50) {
				lab1 = 80;
			}else if (countDatePay(date2)>50 && countDatePay(date2)<=100) {
				lab1 = 130;
			}else if (countDatePay(date2)>100) {
				lab1 = 200;
			}
			if(countDatePay(date3)==0) {
				lab2 = 3;
			}else if(countDatePay(date3)>=1 && countDatePay(date3)<=10) {
				lab2 = 30;
			}else if (countDatePay(date3)>10 && countDatePay(date3)<50) {
				lab2 = 80;
			}else if (countDatePay(date3)>50 && countDatePay(date3)<100) {
				lab2 = 130;
			}else if (countDatePay(date3)>100) {
				lab2 = 200;
			}
			if(countDatePay(date4)==0) {
				lab3 = 3;
			}else if(countDatePay(date4)>=1 && countDatePay(date4)<=10) {
				lab3 = 30;
			}else if (countDatePay(date4)>10 && countDatePay(date4)<50) {
				lab3 = 80;
			}else if (countDatePay(date4)>50 && countDatePay(date4)<100) {
				lab3 = 130;
			}else if (countDatePay(date4)>100) {
				lab3 = 200;
			}
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
					g.drawString(countDatePay(date1)+"명", 30, 530-(lab*2));
					//월
					g.drawString(year1+"."+month1, 35, 560);
				}
				if(i == 10) {
					g.drawLine(0, 340, 550, 340);
					//수치 값
					g.drawString(countDatePay(date2) + "명", 110, 530-(lab1*2));
					//월
					g.drawString(year2+"."+month2, 120, 560);
				}
				if(i == 15) {
					g.drawLine(0, 240, 550, 240);
					//수치 값
					g.drawString(countDatePay(date3) + "명", 200, 530-(lab2*2));
					//월
					g.drawString(year3+"."+month3, 210, 560);
				}
				if(i == 20) {
					g.drawLine(0, 140, 550, 140);
					//수치 값
					g.drawString(countDatePay(date4) + "명", 280, 530-(lab3*2));
					//월
					g.drawString(year4+"."+month4, 295, 560);
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
	// 총 이용자수 데이터 핸들링
	public int userAll() {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst = dao.allUserCnt();
		int userAllCnt=0;
		for(int i=0; i<lst.size(); i++) {
			MemberVO vo = lst.get(i);
			userAllCnt = vo.getUserCnt();
		}
		return userAllCnt;
	}
	// 실시간 이용자수 데이터 핸들링
	public int userLive() {
		Acess_memDAO dao = new Acess_memDAO();
		List<Acess_memVO> lst = dao.liveUserList();
		int userLiveCnt =0;
		for(int i=0; i<lst.size(); i++) {
			Acess_memVO vo = lst.get(i);
			userLiveCnt = vo.getUserCnt();
		}
		return userLiveCnt;
	}
	public static void main(String[] args) {
		new Admin1Main();

	}

}
