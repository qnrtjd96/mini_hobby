import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopMenu_Stu extends JFrameExtends implements ActionListener, MouseListener{
	//JFrameExtends jframeExtends = new JFrameExtends();
	JPanel paneTop = new JPanel(new BorderLayout());
		JPanel paneLogo = new JPanel();
			JButton logoBtn = new JButton("로고이미지");
		JPanel paneLabel = new JPanel();
			//JLabel 대입
		
	JPanel paneCenter = new JPanel();

	public TopMenu_Stu() {
		//logoBtn
		paneLogo.add(logoBtn);
			
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
		}
		
		//간격조정
		paneTop.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		paneTop.add(BorderLayout.WEST,paneLogo); paneTop.add(BorderLayout.EAST, paneLabel);
		
		//내정보 패널
		//paneCenter.add(new MyMenu_Stu().paneStu);
		/*
		 * jframeExtends.add(BorderLayout.NORTH,paneTop);
		 */
		super.add(BorderLayout.NORTH,paneTop);
		
		logoBtn.addActionListener(this);
	}
	//logo 버튼 이벤트 오버라이딩
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		System.out.println(obj);
		if(obj==logoBtn) {
			//로고 클릭 시
			paneCenter.removeAll();
			paneCenter.setBackground(Color.RED);
			/*
			 * jframeExtends.add(BorderLayout.CENTER, paneCenter);
			 */
			super.add(BorderLayout.CENTER, paneCenter);
		}
	}
	//label 이벤트 오버라이딩
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		System.out.println(lbl);
		if(lbl.equals("이전으로")) {
			////// 구현해서 객체 호출하세요 //////
			/*
			 * this.jframeExtends.setVisible(false);
			 */
			super.setVisible(false);
			
		}else if(lbl.equals("메세지함")) {
			////// 구현해서 객체 호출하세요 //////
			super.setVisible(false);
			//메세지함 호출 테스트
			new MessegeTest();
		}else if(lbl.equals("내정보")) {
			////// 구현해서 객체 호출하세요 //////
			super.setVisible(false);
			//내정보 메뉴 호출
			new MyMenu_Stu();
		}else if(lbl.equals("로그아웃")) {
			////// 구현해서 객체 호출하세요 //////
			super.setVisible(false);
			
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent me) {}
}
