import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudenMsgTest {
	JPanel paneMessege = new JPanel(new BorderLayout());
	JLabel lbl = new JLabel("메세지함", JLabel.CENTER);
	public StudenMsgTest() {
		paneMessege.setBackground(Color.white);
		paneMessege.add(lbl);
		
		//상단바 메뉴 눌렀을때 이벤트 처리를 위한 호출
		StudenTopMenu ts = new StudenTopMenu();
		ts.add(BorderLayout.CENTER, paneMessege);
	}

}