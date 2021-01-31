import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPageTest extends MyMenu_Stu{
		JLabel lbl = new JLabel("MY PAGE", JLabel.CENTER);
	public MyPageTest() {
		paneRight.setBackground(Color.white);
		paneRight.add(lbl);
		
		paneStu.add(BorderLayout.CENTER, paneRight);
	}

}
