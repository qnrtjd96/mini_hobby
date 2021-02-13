package teach;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import dbConnection.Acess_memDAO;
import dbConnection.MoneyDAO;
import dbConnection.MoneyVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class TeachTotal extends JPanel implements MouseListener{
	Font fntBold50 = new Font("맑은 고딕", Font.BOLD, 40);
	
	Color col6 = new Color(204,222,233);
	Font fntPlain12 = new Font("맑은 고딕", Font.PLAIN, 12);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JLabel total = new JLabel("월별 총 수익", JLabel.CENTER);
	
	JLabel cal1 = new JLabel("11월"); String cal11;
	JLabel cal2 = new JLabel(); String cal21;
	JLabel cal3 = new JLabel(); String cal31;
	JLabel cal4 = new JLabel(); String cal41;
	 	p grap = new p();//그래프들어갈자리
	JLabel totoal = new JLabel("총 수입");
	JLabel totalwonn = new JLabel(); String dbwon= "0";
	JLabel soontotal = new JLabel("순 수입");
	JLabel soonwonn = new JLabel(); String soonwon="0";
	
	String idStr;
	
	//월별 데이터
	Calendar date; 
	int year1, year2, year3, year4;
	int month1, month2, month3, month4;
	String date1, date2, date3, date4;
	public TeachTotal(){}
	public TeachTotal(String id){
		idStr = id;
		setLayout(null);
		
		canlendarDate();
		
		//백그라운드 변경
		setBackground(Color.white);
		
		///////////////////////////////////////////////////////////////////////
		///위치 폰트 받기////////////
		total.setBounds(30, 20, 500, 70); total.setFont(fntBold50);
		
		grap.setBounds(5, 40, 560, 575);
		
		cal1.setBounds(70, 600, 100, 70); cal2.setBounds(180, 600, 100, 70);
		cal1.setFont(fntBold20); cal2.setFont(fntBold20);
		cal3.setBounds(310, 600, 100, 70); cal4.setBounds(425, 600, 100, 70);
		cal3.setFont(fntBold20); cal4.setFont(fntBold20);
		totoal.setBounds(50, 650, 500, 70);  //totalwon.setBounds(100, 680, 500, 100);
		totalwonn.setBounds(100, 680, 500, 100); soonwonn.setBounds(350, 680, 500, 100);
		soontotal.setBounds(320, 650, 500, 70);  //soontotalwon.setBounds(350, 680, 500, 100);
		
		totoal.setFont(fntBold20); 
		totalwonn.setFont(fntBold30); soonwonn.setFont(fntBold30);
		soontotal.setFont(fntBold20); 
		////////////////////////////////////////////////////////////////////
		getCharInfo(idStr);
		
		add(total);
		add(cal1);
		add(cal2);
		add(cal3);
		add(cal4);
		
		add(grap);
		
		add(totoal); 
		add(totalwonn); add(soonwonn);
		add(soontotal); 
		
		//기능주입
		cal1.addMouseListener(this);
		cal2.addMouseListener(this);
		cal3.addMouseListener(this);
		cal4.addMouseListener(this);
	}
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
		
		cal11 = (String)(year1+"."+month1);
		cal21 = (String)(year2+"."+month2);
		cal31 = (String)(year3+"."+month3);
		cal41 = (String)(year4+"."+month4);
		
		cal1.setText(cal11);
		cal2.setText(cal21);
		cal3.setText(cal31);
		cal4.setText(cal41);
		
	}
	//월별 총수익 데이터 DB
	public int sumDatePay(String date) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.paytotalSum(date, idStr);
		int sum=0;
		for(int i=0; i<lst.size(); i++) {
			Stu_ClassVO vo = lst.get(i);
			sum = vo.getPay();
		}
		return sum;
	}
	//결과물(그래프)이 나타날 패널
	public class p extends JPanel{
		int lab=0, lab1=0, lab2=0, lab3=0;
		
		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight()); //클리어
			if(sumDatePay(date1)==0) {
				lab = 3;
			}else if(sumDatePay(date1)>10000 && sumDatePay(date1)<50000) {
				lab = 30;
			}else if (sumDatePay(date1)>50000 && sumDatePay(date1)<200000) {
				lab = 80;
			}else if (sumDatePay(date1)>200000 && sumDatePay(date1)<500000) {
				lab = 130;
			}else if (sumDatePay(date1)>500000) {
				lab = 200;
			}
			if(sumDatePay(date2)==0) {
				lab1 = 3;
			}else if(sumDatePay(date2)>10000 && sumDatePay(date2)<50000) {
				lab1 = 30;
			}else if (sumDatePay(date2)>50000 && sumDatePay(date2)<200000) {
				lab1 = 80;
			}else if (sumDatePay(date2)>200000 && sumDatePay(date2)<500000) {
				lab1 = 130;
			}else if (sumDatePay(date2)>500000) {
				lab1 = 200;
			}
			if(sumDatePay(date3)==0) {
				lab2 = 3;
			}else if(sumDatePay(date3)>10000 && sumDatePay(date3)<50000) {
				lab2 = 30;
			}else if (sumDatePay(date3)>50000 && sumDatePay(date3)<200000) {
				lab2 = 80;
			}else if (sumDatePay(date3)>200000 && sumDatePay(date3)<500000) {
				lab2 = 130;
			}else if (sumDatePay(date3)>500000) {
				lab2 = 200;
			}
			if(sumDatePay(date4)==0) {
				lab3 = 3;
			}else if(sumDatePay(date4)>10000 && sumDatePay(date4)<50000) {
				lab3 = 30;
			}else if (sumDatePay(date4)>50000 && sumDatePay(date4)<200000) {
				lab3 = 80;
			}else if (sumDatePay(date4)>200000 && sumDatePay(date4)<500000) {
				lab3 = 130;
			}else if (sumDatePay(date4)>500000) {
				lab3 = 200;
			}
			g.clearRect(0,0,getWidth(),getHeight()); //클리어
			//테두리 그리기
			//(int x1, int y1, int x2, int y2)
			g.drawLine(5,570,650,570); //가로줄
			g.setFont(fntBold20);
			for (int i = 1; i< 21 ; i++) {
				//g.drawString(i*100000 + "", 0, 540-(20*i));
				g.setFont(fntPlain12);
				if(i ==5) {
					g.drawString(sumDatePay(date1)+"원", 75, 570-(lab*2));
					g.drawLine(5, 470, 650, 470);
				}
				if(i == 10) {
					g.drawString(sumDatePay(date2) + "원", 185, 570-(lab1*2));
					g.drawLine(5, 370, 650, 370);
				}
				if(i == 15) {
					g.drawString(sumDatePay(date3) + "원", 305, 570-(lab2*2));
					g.drawLine(5, 270, 650, 270);
				}
				if(i == 20) {
					g.drawString(sumDatePay(date4) + "원", 425, 570-(lab3*2));
					g.drawLine(5, 170, 650, 170);
				}
					
			}		
			g.drawLine(5,30,5,570); //세로줄
			
			//좌표값으로 지정해주기 //(String str, int x, int y)
			g.setColor(Color.ORANGE);
			
			if(lab > 0)
				//fillRect(int x, int y, int width, int height)
				g.fillRect(70,570-lab*2, 60, lab*2);	
			if(lab1 > 0)
				g.fillRect(180,570-lab1*2, 60, lab1*2);	
			if(lab2 > 0)
				g.fillRect(300,570-lab2*2, 60, lab2*2);
			if(lab3 > 0)
				g.fillRect(420,570-lab3*2, 60, lab3*2);
		}
	}
	//총이익 가져오는 메소드
	public void getCharInfo(String idStr) { //회원정보 세팅
		
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.payTeaSum(idStr);
		
		if(lst.size()==0 ) {
			System.out.println("아이디를 매치를 못함...");
			soonwonn.setText("0원");
			totalwonn.setText("0원");
		}else if(lst.equals(null)) {
			soonwonn.setText("0원");
			totalwonn.setText("0원");			
		}else {
			Stu_ClassVO won = lst.get(0);
			dbwon = Integer.toString(won.getPay());
			soonwon = Integer.toString((int)(won.getPay()*0.9));
			soonwonn.setText(soonwon + "원");
			totalwonn.setText(dbwon + "원");	
		}
	}
	public void mouseReleased(MouseEvent me) {
		Object obj = me.getSource();
		if(obj==cal1) {
			new TeachIncome(idStr, date1);
		}else if(obj==cal2) {
			new TeachIncome(idStr, date2);
		}else if(obj==cal3) {
			new TeachIncome(idStr, date3);
		}else if(obj==cal4) {
			new TeachIncome(idStr, date4);
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