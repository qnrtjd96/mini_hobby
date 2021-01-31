import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class PurchaseTest extends MyMenu_Stu{
	JLabel lbl = new JLabel("구매/예약 내역확인", JLabel.CENTER);
	public PurchaseTest() {
		paneRight.setBackground(Color.white);
		paneRight.add(lbl);
		
		paneStu.add(BorderLayout.CENTER, paneRight);
	}

}
