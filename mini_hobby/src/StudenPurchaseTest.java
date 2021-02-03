import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class StudenPurchaseTest extends StudenMyMenu{
	JLabel lbl = new JLabel("구매/예약 내역확인", JLabel.CENTER);
	public StudenPurchaseTest() {
		paneRight.setBackground(Color.white);
		paneRight.add(lbl);
		
	}

}
