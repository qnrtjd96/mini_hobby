import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class StudenDeleteUser extends JPanel implements ActionListener{
	JPanel deletMain = new JPanel(null);
	
	JPanel robotPane = new JPanel(new BorderLayout());
		JLabel robotLbl = new JLabel(" 다음 중 ㅇㅇ아닌 것을 선택하세요.");
		
		/*
		JPanel gPane = new JPanel(new GridLayout(3,3));
			JButton gBtn[] = new JButton[8];
		*/
		JPanel robotSouth = new JPanel();
			JButton chooBtn = new JButton("선택");
	
	public StudenDeleteUser() {
		add(deletMain);
		setBackground(Color.white);
		
		deletMain.setBackground(Color.white);
		
		robotPane.setBounds(90,170, 400,400); robotPane.setBackground(Color.white);
		deletMain.add(robotPane);
		robotPane.add("North", robotLbl);	
		robotLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		/*
		for(int idx=0; idx<gBtn.length; idx++) {
			JPanel pane = new JPanel(gBtn[idx]);
			gPane.add(gBtn[idx]);
		}
		*/
		//robotPane.add("Center", gPane);
		
		chooBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		robotSouth.add(chooBtn);
		robotPane.add("South", chooBtn);
		
		robotPane.setBorder(new LineBorder(Color.black, 1));
		
		chooBtn.addActionListener(this);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
