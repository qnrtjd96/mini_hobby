import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyMenu_Tea implements MouseListener{

	//보더레이아웃 내에서 마진값 10씩주기
	JPanel paneStu = new JPanel(new BorderLayout(10,10));
		JPanel paneLeft = new JPanel();
			//왼쪽 메뉴 라벨 입력
		JPanel paneRight = new JPanel();
			//각 메뉴별 기능 구현
		JPanel paneBottom = new JPanel();
			//수강시간 구현
			JLabel lblClock = new JLabel("○○○님의 ★★클래스 수강까지 1시간 20분 43초 남았습니다.", JLabel.CENTER);
	
	TopMenu_Tea tt = new TopMenu_Tea();	
	public MyMenu_Tea() {
		////// paneLeft 구현 //////
		String menuLblStr[] = {"My Page","구매/예약 내역확인","충전하기","회원정보 확인/수정","탈퇴하기"};
		for(int i=0; i<menuLblStr.length; i++) {
			JLabel lblLeft = new JLabel(menuLblStr[i], JLabel.LEFT);
			paneLeft.add(lblLeft);
			lblLeft.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			lblLeft.addMouseListener(this);
		}
		//왼쪽 메뉴 레이아웃 및 간격조정
		GridLayout grid = new GridLayout(10,0);
		paneLeft.setLayout(grid);
		grid.setVgap(15);
		
		//paneLeft 기본셋팅
		paneLeft.setBackground(Color.white);
		paneLeft.setBorder(new LineBorder(Color.black, 1));
		
		//paneRight 기본셋팅
		paneRight.setBackground(Color.white);
		paneRight.setBorder(new LineBorder(Color.black, 1));
		
		//paneBottom 기본셋팅(테스트용으로 라벨 넣음)
		paneBottom.add(lblClock);
		
		//전체를 감싸고있는 패널에 패딩값 10씩 주기
		paneStu.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		paneStu.add(BorderLayout.WEST,paneLeft); paneStu.add(BorderLayout.CENTER,paneRight); paneStu.add(BorderLayout.SOUTH,paneBottom);
		
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		
		tt.jframeExtends.add(BorderLayout.CENTER, paneStu);
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		if(lbl.equals("My Page")) {
			////// 구현해서 객체 호출하세요 //////
			tt.jframeExtends.setVisible(false);
			//마이페이지 테스트
			new MyPageTest();
		}else if(lbl.equals("구매/예약 내역확인")) {
			////// 구현해서 객체 호출하세요 //////
			tt.jframeExtends.setVisible(false);
			//구매내역 테스트
			new PurchaseTest();
		}else if(lbl.equals("충전하기")) {
			////// 구현해서 객체 호출하세요 //////
			tt.jframeExtends.setVisible(false);
		}else if(lbl.equals("회원정보 확인/수정")) {
			////// 구현해서 객체 호출하세요 //////
			tt.jframeExtends.setVisible(false);
		}else if(lbl.equals("탈퇴하기")) {
			////// 구현해서 객체 호출하세요 //////
			tt.jframeExtends.setVisible(false);
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
