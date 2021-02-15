package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.Mem_teacherDAO;
import dbConnection.Mem_teacherVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import dbConnection.MoneyDAO;
import dbConnection.MoneyVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class StudenMyPage extends JPanel implements MouseListener{
	//아이디를 가져오기위한값
	String idStr;
			
	Font fntBold50 = new Font("맑은 고딕", Font.BOLD, 60);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
	Color col = new Color(204,222,233);
	
			JLabel charge = new JLabel("나의 잔액 : ");
			JLabel charge1 = new JLabel(); String charge2= "";
			JPanel cal = new JPanel();
			JLabel ture1 = new JLabel("참여예정"); 
			JLabel false1 = new JLabel("참여완료");
			JLabel total = new JLabel("나의 이용횟수");
			p grap = new p(); //그래프예정
	
			JLabel red = new JLabel("■");
			JLabel blue = new JLabel("■");
		// 달력에 대한 부분
		Calendar now = Calendar.getInstance();
		int y,m,d;
		JPanel selectPane = new JPanel();
		JLabel dateLbl = new JLabel();
		// 달력 패널 
		JPanel calPane = new JPanel(); 
			JPanel dayPane = new JPanel(new GridLayout(1,7,40,40)); //일 ~월 글자출력 
			String days[] = {"일", "월", "화", "수", "목", "금", "토"}; 
			JPanel datePane = new JPanel( new GridLayout(0,7,38,25)); // 1~31 날짜 출력 
			
		Color col6 = new Color(204,222,233);
		Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
		Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
		Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
		Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
		Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
		Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
		Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
		
		//달력에 아이디 넣어줘야대는값
		MoneyVO vo;
		MemberVO vo10;
		
		//월별 데이터
		Calendar date; 
		int year1, year2, year3, year4 ,year5, year6;
		int month1, month2, month3, month4, month5, month6;
		String date1, date2, date3, date4, date5, date6;
	public StudenMyPage() {}
	public StudenMyPage(String idStr) {
		this.idStr = idStr;
		
		canlendarDate();
		setLayout(null);

		//패널에추가
		add(charge); add(charge1); 
		add(cal);
		add(blue);  add(red);
		add(ture1); add(false1);

		add(total); 
		add(grap);	
		//////////////////////////

		//위치잡기 폰트박기
		//int x, int y, int width, int height						
		charge.setBounds(290,5,500,70); charge.setFont(fntPlain25);
		charge1.setBounds(420, 5, 500, 70); charge1.setFont(fntPlain25);
		cal.setBounds(27, 75, 500, 400); cal.setFont(fntPlain15); cal.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		blue.setBounds(365, 480, 500, 20);  red.setBounds(455, 480, 500, 20);
		blue.setForeground(col);		red.setForeground(Color.red);
		ture1.setBounds(380, 480, 500, 20);	false1.setBounds(470, 480, 500, 20);
		ture1.setFont(fntBold15); 			false1.setFont(fntBold15);

		total.setBounds(220, 530, 500, 70);	
		total.setFont(fntBold25);			
		grap.setBounds(27, 580, 500, 200);	
		/////////////////////////////////						

		//마지막 paneRight에 추가
		setBackground(Color.white);
		
		//잔액세팅을 위해 선언
		getTeaInfo(idStr);
		
		//날짜선언
		calendar_Tea();
	}	
	public void canlendarDate() {
		date = Calendar.getInstance();
		year6 = date.get(Calendar.YEAR); //현재 날짜의 년도
		month6 = date.get(Calendar.MONTH)+1; //현재 날짜의 월
		//3달전 데이터
		year5 = year6; month5 = month6-1;
		year4 = year5; month4 = month5-1;
		year3 = year4; month3 = month4-1;
		year2 = year3; month2 = month3-1;
		year1 = year2; month1 = month2-1;

		if(month6==1) {
			year5 -= 1;	month5 = 12;
			year4 = year5; month4 = 11;
			year3 = year4; month3 = 10;
			year2 = year3; month2 = 9;
			year1 = year2; month1 = 8;
		}else if(month5==1) {
			year4 -= 1; month4 = 12;
			year3 = year4; month3 = 11;
			year2 = year3; month2 = 10;
			year1 = year2; month1 = 9;
		}else if(month4==1) {
			year3 -= 1; month3 = 12;
			year2 = year3; month2 = 11;
			year1 = year2; month1 = 10;
		}else if(month3==1) {
			year2 -= 1; month2 = 12;
			year1 = year2; month1 = 11;
		}else if(month2==1) {
			year1 -= 1; month1 = 12;
		}
		//년도 2글자로 줄이기, 월이 한글자일때 0넣기
		String year01, year02, year03, year04, year05, year06;
		String month01, month02, month03, month04, month05, month06;
		year01 = String.valueOf(year1).substring(2); month01 = String.format("%02d", month1);
		year02 = String.valueOf(year2).substring(2); month02 = String.format("%02d", month2);
		year03 = String.valueOf(year3).substring(2); month03 = String.format("%02d", month3);
		year04 = String.valueOf(year4).substring(2); month04 = String.format("%02d", month4);
		year05 = String.valueOf(year5).substring(2); month05 = String.format("%02d", month5);
		year06 = String.valueOf(year6).substring(2); month06 = String.format("%02d", month6);
		//DB where에 사용될 날짜데이터
		date1 = year01+"/"+month01;
		date2 = year02+"/"+month02;
		date3 = year03+"/"+month03;
		date4 = year04+"/"+month04;
		date5 = year05+"/"+month05;
		date6 = year06+"/"+month06;
	}
	//월별 총수익 데이터 DB
	public int sumDatePay(String date) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.payStuSum(date, idStr);
		int sum=0;
		for(int i=0; i<lst.size(); i++) {
			Stu_ClassVO vo = lst.get(i);
			sum = vo.getPay();
		}
		return sum;
	}
	
	//결과물(그래프)이 나타날 패널
	public class p extends JPanel{
		int lab=0, lab1=0, lab2=0, lab3=0, lab4=0, lab5=0;

		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight()); //클리어
			if(sumDatePay(date1)==0) {
				lab = 0;
			}else if(sumDatePay(date1)>=1 && sumDatePay(date1)<=5) {
				lab = 15;
			}else if (sumDatePay(date1)>5 && sumDatePay(date1)<=10) {
				lab = 35;
			}else if (sumDatePay(date1)>10 && sumDatePay(date1)<=15) {
				lab = 50;
			}else if (sumDatePay(date1)>15) {
				lab = 70;
			}
			if(sumDatePay(date2)==0) {
				lab1 = 0;
			}else if(sumDatePay(date2)>=1 && sumDatePay(date2)<=5) {
				lab1 = 15;
			}else if (sumDatePay(date2)>5 && sumDatePay(date2)<=10) {
				lab1 = 35;
			}else if (sumDatePay(date2)>10 && sumDatePay(date2)<=15) {
				lab1 = 55;
			}else if (sumDatePay(date2)>15) {
				lab1 = 70;
			}
			if(sumDatePay(date3)==0) {
				lab2 = 0;
			}else if(sumDatePay(date3)>=1 && sumDatePay(date3)<=5) {
				lab2 = 15;
			}else if (sumDatePay(date3)>5 && sumDatePay(date3)<=10) {
				lab2 = 35;
			}else if (sumDatePay(date3)>10 && sumDatePay(date3)<=15) {
				lab2 = 55;
			}else if (sumDatePay(date3)>15) {
				lab2 = 70;
			}
			if(sumDatePay(date4)==0) {
				lab3 = 0;
			}else if(sumDatePay(date4)>=1 && sumDatePay(date4)<=5) {
				lab3 = 10;
			}else if (sumDatePay(date4)>5 && sumDatePay(date4)<=10) {
				lab3 = 30;
			}else if (sumDatePay(date4)>10 && sumDatePay(date4)<=15) {
				lab3 = 40;
			}else if (sumDatePay(date4)>15) {
				lab3 = 80;
			}
			if(sumDatePay(date5)==0) {
				lab4 = 0;
			}else if(sumDatePay(date5)>=1 && sumDatePay(date5)<=5) {
				lab4 = 10;
			}else if (sumDatePay(date5)>5 && sumDatePay(date5)<=10) {
				lab4 = 30;
			}else if (sumDatePay(date5)>10 && sumDatePay(date5)<=15) {
				lab4 = 40;
			}else if (sumDatePay(date5)>15) {
				lab4 = 80;
			}
			
			if(sumDatePay(date6)==0) {
				lab5 = 0;
			}else if(sumDatePay(date6)>=1 && sumDatePay(date6)<=5) {
				lab5 = 15;
			}else if (sumDatePay(date6)>5 && sumDatePay(date6)<=10) {
				lab5 = 30;
			}else if (sumDatePay(date6)>10 && sumDatePay(date6)<=15) {
				lab5 = 45;
			}else if (sumDatePay(date6)>15) {
				lab5 = 60;
			}
			
			//테두리 그리기
			//(int x1, int y1, int x2, int y2)
			g.drawLine(30,160,500,160); //가로줄
			for (int i = 1; i< 16 ; i++) {
				if(i ==5) {
					g.drawLine(30, 110, 500, 110);
					g.drawString(i + "", 10, 160-(10*i)); //왼쪽에 개수 생성하는거
					g.drawString(sumDatePay(date1) + "번", 55, 160-(lab*2));
					g.drawString(sumDatePay(date2) + "번", 135, 160-(lab1*2));
					g.drawString(sumDatePay(date3) + "번", 205, 160-(lab2*2));
					g.drawString(sumDatePay(date4) + "번", 285, 160-(lab3*2));
					g.drawString(sumDatePay(date5) + "번", 365, 160-(lab4*2));
					g.drawString(sumDatePay(date6) + "번", 445, 160-(lab5*2));
				}
				if(i == 10) {
					g.drawLine(30, 60, 500, 60);
					g.drawString(i + "", 10, 160-(10*i));
					g.drawString(sumDatePay(date1) + "번", 55, 160-(lab*2));
					g.drawString(sumDatePay(date2) + "번", 135, 160-(lab1*2));
					g.drawString(sumDatePay(date3) + "번", 205, 160-(lab2*2));
					g.drawString(sumDatePay(date4) + "번", 285, 160-(lab3*2));
					g.drawString(sumDatePay(date5) + "번", 365, 160-(lab4*2));
					g.drawString(sumDatePay(date6) + "번", 445, 160-(lab5*2));
				}
				if(i == 15) {
					g.drawString(sumDatePay(date1) + "번", 55, 160-(lab*2));
					g.drawString(sumDatePay(date2) + "번", 135, 160-(lab1*2));
					g.drawString(sumDatePay(date3) + "번", 205, 160-(lab2*2));
					g.drawString(sumDatePay(date4) + "번", 285, 160-(lab3*2));
					g.drawString(sumDatePay(date5) + "번", 365, 160-(lab4*2));
					g.drawString(sumDatePay(date6) + "번", 445, 160-(lab5*2));
				}
			}		
			g.drawLine(30,10,30,160); //세로줄
			
//			g.drawString(sumDatePay(date1) + "번", 50, 160);
//			g.drawString(sumDatePay(date2) + "번", 130, 160);
//			g.drawString(sumDatePay(date3) + "번", 200, 160);
//			g.drawString(sumDatePay(date4) + "번", 280, 160);
//			g.drawString(sumDatePay(date5) + "번", 360, 160);
//			g.drawString(sumDatePay(date6) + "번", 440, 160);
			
			//좌표값으로 지정해주기 //(String str, int x, int y)
//				drawString(String str, int x, int y)
				g.drawString(date1, 50, 175);
				g.drawString(date2,  130, 175);
				g.drawString(date3,  200, 175);
				g.drawString(date4,  280, 175);
				g.drawString(date5,  360, 175);
				g.drawString(date6,  440, 175);
			g.setColor(Color.ORANGE);

			if(lab > 0)
				//fillRect(int x, int y, int width, int height)
				g.fillRect(50,160-lab*2, 30, lab*2);	
			if(lab1 > 0)
				g.fillRect(130,160-lab1*2, 30, lab1*2);	
			if(lab2 > 0)
				g.fillRect(200,160-lab2*2, 30, lab2*2);
			if(lab3 > 0)
				g.fillRect(280,160-lab3*2, 30, lab3*2);
			if(lab4 > 0)
				g.fillRect(360,160-lab4*2, 30, lab4*2);
			if(lab5 > 0)
				g.fillRect(440,160-lab5*2, 30, lab5*2);
		}
	}
	//회원정보 세팅 나의잔액때문에 세팅함
	public void getTeaInfo(String idStr) { //회원정보 세팅
		
		MoneyDAO dao = new MoneyDAO();
		List<MoneyVO> searchId = dao.getMoneyInfo(idStr);
		
		if(searchId.size()==0 ) {
			System.out.println("값이 없음");
			
			MemberDAO dao2 = new MemberDAO();
			List<MemberVO> a = dao2.idcheck(idStr);
			vo10 = a.get(0);
			
			charge2 = "0";
			charge1.setText(charge2 + "원");	
		}else if(searchId.equals(null)) {
			charge2 = "0";
			charge1.setText(charge2 + "원");	
		}else {
			vo = searchId.get(0);
			charge2 = Integer.toString(vo.getBalance());
			charge1.setText(charge2 + "원");	
		}
	}

	public void calendar_Tea() {
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null);

		cal.add(selectPane); selectPane.setBounds(1,1,498,50);
		selectPane.add(dateLbl);

		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "+d+"일");
		dateLbl.setFont(fntBold20);
		selectPane.setBackground(new Color(121,191,192));

		cal.add(calPane); calPane.setBounds(5,40,490,350);
		calPane.setLayout(null);
		calPane.add(dayPane); calPane.add(datePane);
		dayPane.setBounds(5,0,470,50);
		datePane.setBounds(5,50,470,300);

		calendarSetting(y,m);

		//일~토 넣기 
		for (int i = 0; i < days.length; i++) {
			JLabel dayOfWeekLbl = new JLabel(days[i]);
			dayOfWeekLbl.setHorizontalAlignment(SwingConstants.CENTER);
			dayPane.add(dayOfWeekLbl);
			if(i==0) {
				dayOfWeekLbl.setForeground(Color.RED);
			}else if(i==6) {
				dayOfWeekLbl.setForeground(Color.BLUE);
			}
			dayOfWeekLbl.setFont(fntPlain15);
		}
	}

	public void calendarSetting(int y, int m) {
		int week, lastDay;
		// 콤보박스에 선택된 년도, 월의 1일로 세팅 
		now.set(y, m-1, 1); 
		//1일의 요일 구하기 
		week = now.get(Calendar.DAY_OF_WEEK); //세팅된 날짜의 요일을 구함 (1일이 무슨요일인지)
		//월의 마지막날이 몇일인지 구하기 
		lastDay = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 1일 전까지 공백 출력 
		for(int space=1; space<week; space++) {
			datePane.add(new JLabel(""));
		}
		// 월의 1일~마지막일 까지 출력 (+라벨 배경색 바꾸기 위한 작업)
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst;
		if(vo != null) {
			 lst = dao.StuCalendar(vo.getId());
		}else {
			 lst = dao.StuCalendar(vo10.getId());
		}
		for(int day=1; day<=lastDay; day++) {
			JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
			dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
			now.set(y, m-1, day); //날짜별로 요일을 구해서
			int colorWeek = now.get(Calendar.DAY_OF_WEEK);
			/*여기는 sysout찍으면 나옴*/
			for(int i=0; i<lst.size(); i++) {
				Stu_ClassVO vo1 = lst.get(i);
				String date = vo1.getClassdate();
				int month = Integer.parseInt(date.substring(5,7));
				if (month==m) {
					int da = Integer.parseInt(date.substring(8));
					if(da>=d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
						dayOfMonthLbl.setOpaque(true);
						dayOfMonthLbl.setBackground(col);
					} else if(da<d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
						dayOfMonthLbl.setOpaque(true);
						dayOfMonthLbl.setBackground(Color.RED);
					}
				}
			} 
			if(colorWeek == 1) { //일요일이면 
				dayOfMonthLbl.setForeground(Color.RED);
			}else if(colorWeek == 7) { //토요일이면 
				dayOfMonthLbl.setForeground(Color.BLUE);
			}
			datePane.add(dayOfMonthLbl);
			dayOfMonthLbl.addMouseListener(this);
		}

	}
	@Override
	public void mouseReleased(MouseEvent me) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent me) {}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(idStr);
			System.exit(0);
		}
	}
	

}