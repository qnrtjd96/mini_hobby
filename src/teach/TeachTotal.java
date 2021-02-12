package teach;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import dbConnection.Acess_memDAO;

public class TeachTotal extends JPanel implements MouseListener{
	Font fntBold50 = new Font("맑은 고딕", Font.BOLD, 40);
	
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JLabel total = new JLabel("월별 총 수익", JLabel.CENTER);
	
	JLabel cal1 = new JLabel("11월");
	JLabel cal2 = new JLabel("12월");
	JLabel cal3 = new JLabel("1월");
	JLabel cal4 = new JLabel("2월");
	p grap = new p();//그래프들어갈자리
	JLabel totoal = new JLabel("총 수입");
	JLabel totalwon = new JLabel("2,745,200원");
	JLabel soontotal = new JLabel("순 수입");
	JLabel soontotalwon = new JLabel("2,470,680원");
	String idStr;
	public TeachTotal(String id){
		idStr = id;
		setLayout(null);
		
		//백그라운드 변경
		setBackground(Color.white);
				
		add(total);
		add(cal1);
		add(cal2);
		add(cal3);
		add(cal4);
		
		add(grap);
		
		add(totoal); add(totalwon);
		add(soontotal); add(soontotalwon);
		
		total.setBounds(30, 20, 500, 70); total.setFont(fntBold50);
		
		grap.setBounds(5, 40, 500, 575);
		
		cal1.setBounds(80, 600, 500, 70); cal2.setBounds(190, 600, 500, 70);
		cal1.setFont(fntBold20); cal2.setFont(fntBold20);
		cal3.setBounds(320, 600, 500, 70); cal4.setBounds(440, 600, 500, 70);
		cal3.setFont(fntBold20); cal4.setFont(fntBold20);
		totoal.setBounds(50, 650, 500, 70); totalwon.setBounds(100, 680, 500, 100);
		soontotal.setBounds(320, 650, 500, 70); soontotalwon.setBounds(350, 680, 500, 100);
		
		totoal.setFont(fntBold20); totalwon.setFont(fntBold30);
		soontotal.setFont(fntBold20); soontotalwon.setFont(fntBold30);
		
		cal1.addMouseListener(this);
		cal2.addMouseListener(this);
		cal3.addMouseListener(this);
		cal4.addMouseListener(this);
	}
	//결과물(그래프)이 나타날 패널
	public class p extends JPanel{
		int lab=100, lab1=150, lab2=200, lab3=175;
		
		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight()); //클리어
			//테두리 그리기
			//(int x1, int y1, int x2, int y2)
			g.drawLine(50,570,550,570); //가로줄
			for (int i = 1; i< 21 ; i++) {
				g.drawString(i*100000 + "", 0, 540-(20*i));
				if(i ==5) {
					g.drawString(lab + "000", 80, 570-(lab*2));
					g.drawLine(50, 470, 550, 470);
				}
				if(i == 10) {
					g.drawString(lab1 + "000", 190, 570-(lab1*2));
					g.drawLine(50, 370, 550, 370);
				}
				if(i == 15) {
					g.drawString(lab2 + "000", 310, 570-(lab2*2));
					g.drawLine(50, 270, 550, 270);
				}
				if(i == 20) {
					g.drawString(lab3 + "000", 430, 570-(lab3*2));
					g.drawLine(50, 170, 550, 170);
				}
					
			}		
			g.drawLine(50,30,50,570); //세로줄
			
			//좌표값으로 지정해주기 //(String str, int x, int y)
//			g.drawString("11월", 130, 500); // name, x, y
//			g.drawString("12월",  235, 500);
//			g.drawString("1월", 335, 500);
//			g.drawString("2월", 435, 500);
//			g.drawString("11월", 75, 600); // name, x, y
//			g.drawString("12월",  200, 600);
//			g.drawString("1월", 350, 600);
//			g.drawString("2월", 470, 600);
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
		
		
		/*
		void setScore(int lab, int lab1, int lab2, int lab3) {
			this.lab = lab;
			this.lab1 = lab1;
			this.lab2 = lab2;
			this.lab3 = lab3;
		}*/
		
	}
	public void mouseReleased(MouseEvent me) {
		Object obj = me.getSource();
		// TeachIncome으로 넘어가야하는데 이벤트 구현이 잘 안됨
		if(obj==cal1) {
			//new TeachIncome();
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