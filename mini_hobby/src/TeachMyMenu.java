import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TeachMyMenu implements MouseListener{

	//보더레이아웃 내에서 마진값 10씩주기
	JPanel paneStu = new JPanel(new BorderLayout(20,20));
		JPanel paneLeft = new JPanel();
			//왼쪽 메뉴 라벨 입력
		JPanel paneRight = new JPanel();
			//각 메뉴별 기능 구현
			//오른쪽 패널 타이틀 보더 설정
			LineBorder lineBorder = new LineBorder(Color.black, 1);
		JPanel paneBottom = new JPanel();
			//수강시간 구현
			JLabel lblClock = new JLabel("○○○님의 ★★클래스 수강까지 1시간 20분 43초 남았습니다.", JLabel.CENTER);
	
	TeachTopMenu tt = new TeachTopMenu();
	
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	public TeachMyMenu() {
		////// paneLeft 구현 //////
		String menuLblStr[] = {"My Page","회원정보 확인/수정","예약내역 및 리뷰확인","수입확인","탈퇴하기"};
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
		paneLeft.setBackground(Color.pink);
		paneLeft.setBorder(lineBorder);
		
		//paneRight 기본셋팅
		paneRight.setBackground(Color.white);
		//타이틀 보더 설정
		paneRight.setBorder(lineBorder);
		
		//paneBottom 기본셋팅(테스트용으로 라벨 넣음)
		paneBottom.add(lblClock);
		
		//배경색상
		paneBottom.setBackground(Color.white);
		//전체를 감싸고있는 패널에 패딩값 20씩 주기
		paneStu.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		paneStu.add(BorderLayout.WEST,paneLeft); paneStu.add(BorderLayout.CENTER,paneRight); paneStu.add(BorderLayout.SOUTH,paneBottom);
		paneStu.setBackground(Color.white);
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		
		tt.add(BorderLayout.CENTER, paneStu);
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		if(lbl.equals("My Page")) {
			////// 구현해서 객체 호출하세요 //////
			tt.setVisible(false);
			//마이페이지 테스트
			new StudenMyPageTest();
		}else if(lbl.equals("회원정보 확인/수정")) {
			////// 구현해서 객체 호출하세요 //////
			tt.setVisible(false);
			//구매내역 테스트
			new StudenPurchaseTest();
		}else if(lbl.equals("예약내역 및 리뷰확인")) {
			////// 구현해서 객체 호출하세요 //////
			tt.setVisible(false);
		}else if(lbl.equals("수입확인")) {
			tt.setVisible(false);
			//수입확인
			new TeachIncome();
		}else if(lbl.equals("탈퇴하기")) {
			////// 구현해서 객체 호출하세요 //////
			tt.setVisible(false);
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
