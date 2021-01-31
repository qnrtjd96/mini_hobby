import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopMenu_Stu implements ActionListener{
	JFrameExtends jframeExtends = new JFrameExtends();
	JPanel paneTop = new JPanel(new BorderLayout());
		JPanel paneLogo = new JPanel();
			JButton logoBtn = new JButton("로고이미지");
		JPanel paneLabel = new JPanel();
			//JLabel 대입
		
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
		}
		
		//간격조정
		paneTop.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		paneTop.add(BorderLayout.WEST,paneLogo); paneTop.add(BorderLayout.EAST, paneLabel);
		jframeExtends.add(BorderLayout.NORTH,paneTop);
	}
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		//라벨 읽어오기
		String lbl = ae.getActionCommand();
		if(obj==logoBtn) {
			//로고 클릭 시
		}else if(lbl.equals("이전으로")) {
			//이전으로 클릭 시
		}else if(lbl.equals("메세지함")) {
			//메세지함 클릭 시
		}else if(lbl.equals("내정보")) {
			//내정보 클릭 시 
			MyMenu_Stu myMenuStu = new MyMenu_Stu();
			jframeExtends.add(BorderLayout.CENTER,myMenuStu.paneMain);
		}else if(lbl.equals("로그아웃")) {
			//로그아웃 클릭 시
		}
	}
}
