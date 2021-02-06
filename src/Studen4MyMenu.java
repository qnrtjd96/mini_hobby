import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Studen4MyMenu extends JPanel implements MouseListener{
	
	JPanel paneStu = new JPanel(new BorderLayout(20,20));
		JPanel paneLeft = new JPanel();
			//왼쪽 메뉴 라벨 입력
		JPanel center = new StudenMyPage();
			//각 메뉴별 기능 구현
			LineBorder lineBorder = new LineBorder(Color.black, 1);
	JPanel paneBottom = new JPanel();
		//수강시간 구현
		JLabel lblClock = new JLabel("○○○님의 ★★클래스 수강까지 1시간 20분 43초 남았습니다.", JLabel.CENTER);
			
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	public Studen4MyMenu() {
		add("Center", paneStu);
		
		paneStu.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		paneStu.setBackground(Color.white);
		paneStu.add(paneLeft); paneStu.add(paneBottom); paneStu.add(center);
		paneStu.setLayout(null);
		paneLeft.setBounds(20,20,160,800); center.setBounds(200,20,570,800);
		paneBottom.setBounds(20,835,720,50);
		
		
		////// paneLeft 구현 //////
		String menuLblStr[] = {"My Page","구매/예약 내역확인","충전하기","회원정보 확인/수정","탈퇴하기"};
		for(int i=0; i<menuLblStr.length; i++) {
			JLabel lblLeft = new JLabel(menuLblStr[i], JLabel.LEFT);
			paneLeft.add(lblLeft);
			lblLeft.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			lblLeft.setFont(fntBold15);
			lblLeft.addMouseListener(this);
		}
		//왼쪽 메뉴 레이아웃 및 간격조정
		GridLayout grid = new GridLayout(10,0);
		paneLeft.setLayout(grid);
		grid.setVgap(15);
		
		//paneLeft 기본셋팅
		paneLeft.setBackground(col6);
		paneLeft.setBorder(lineBorder);
		
		//paneRight 기본셋팅
		center.setBackground(Color.white);
		center.setBorder(lineBorder);
		
		//paneBottom 기본셋팅(테스트용으로 라벨 넣음)
		paneBottom.add(lblClock);
		//배경색상
		paneBottom.setBackground(Color.white);
		
		
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		
		setVisible(true);
		setBackground(Color.white);
		
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		if(lbl.equals("My Page")) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenMyPage();
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("구매/예약 내역확인")) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenPurchase();
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("충전하기")) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenCharge().start;
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("회원정보 확인/수정")) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenInfo().InfoSub;
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("탈퇴하기")) {
			center.setVisible(false);
			center.removeAll();
			center = new StudenDeleteUser().deletMain;
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
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
	
	
}
