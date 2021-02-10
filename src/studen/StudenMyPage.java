package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class StudenMyPage extends JPanel implements MouseListener{
	Font fntBold50 = new Font("맑은 고딕", Font.BOLD, 60);
	Font fntBold25 = new Font("맑은 고딕", Font.BOLD, 25);
		JLabel charge = new JLabel("나의 잔액 : 15,100원");
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

	public StudenMyPage() {
		setLayout(null);

		//패널에추가
		add(charge); 
		add(cal); calendar_Tea();
		add(blue);  add(red);
		add(ture1); add(false1);

		add(total); 
		add(grap);	
		//////////////////////////

		//위치잡기 폰트박기
		//int x, int y, int width, int height						
		charge.setBounds(290,5,500,70); charge.setFont(fntPlain25);
		cal.setBounds(27, 75, 500, 400); cal.setFont(fntPlain15); cal.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		blue.setBounds(365, 480, 500, 20);  red.setBounds(455, 480, 500, 20);
		blue.setForeground(Color.blue);		red.setForeground(Color.red);
		ture1.setBounds(380, 480, 500, 20);	false1.setBounds(470, 480, 500, 20);
		ture1.setFont(fntBold15); 			false1.setFont(fntBold15);

		total.setBounds(220, 530, 500, 70);	
		total.setFont(fntBold25);			
		grap.setBounds(27, 580, 500, 200);	
		/////////////////////////////////						


		//마지막 paneRight에 추가
		setBackground(Color.white);
	}	
	//결과물(그래프)이 나타날 패널
	public class p extends JPanel{
		int lab=10, lab1=7, lab2=8;

		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight()); //클리어
			//테두리 그리기
			//(int x1, int y1, int x2, int y2)
			g.drawLine(30,160,500,160); //가로줄
			for (int i = 1; i< 16 ; i++) {
				if(i ==5) {
					g.drawLine(30, 110, 500, 110);
					g.drawString(i + "", 10, 160-(10*i));
				}
				if(i == 10) {
					g.drawLine(30, 60, 500, 60);
					g.drawString(i + "", 10, 160-(10*i));
				}
				if(i ==15) {
					g.drawString(i + "", 10, 160-(10*i));
					break;
				}
			}		
			g.drawLine(30,10,30,160); //세로줄

			//좌표값으로 지정해주기 //(String str, int x, int y)
//				drawString(String str, int x, int y)
				g.drawString("12월", 55, 175);
				g.drawString("1월",  110, 175);
				g.drawString("2월",  165, 175);
			g.setColor(Color.ORANGE);

			if(lab > 0)
				//fillRect(int x, int y, int width, int height)
				g.fillRect(50,160-lab*10, 30, lab*10);	
			if(lab1 > 0)
				g.fillRect(105,160-lab1*10, 30, lab1*10);	
			if(lab2 > 0)
				g.fillRect(160,160-lab2*10, 30, lab2*10);
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
		// 월의 1일~마지막일 까지 출력 
		for(int day=1; day<=lastDay; day++) {
			JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
			dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
			now.set(y, m-1, day); //날짜별로 요일을 구해서
			int colorWeek = now.get(Calendar.DAY_OF_WEEK);
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
	

}