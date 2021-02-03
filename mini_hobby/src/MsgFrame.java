import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MsgFrame extends JFrameExtends {
	//상위 패널 설정
	JPanel upper = new StudenTopMenu().paneTop;
	
	//탭 패널, 하위메뉴
	JTabbedPane MsgTab = new JTabbedPane();
		JPanel getMsgPane = new JPanel();
		JPanel sendMsgPane = new JPanel();
		JPanel writeMsgPane = new JPanel(new BorderLayout());
			JPanel writeNorth = new JPanel(new BorderLayout());
				JPanel writeWest = new JPanel(new GridLayout(2,1));
					JLabel title = new JLabel("제목"); 
					JLabel getter = new JLabel("보낸쪽지");
				JPanel writeCenter = new JPanel(new GridLayout(2,1));
					JTextField titleTf = new JTextField(50);
					JTextField getterTf = new JTextField();
		JPanel liveChat = new JPanel();
	
	Font TabFnt = new Font("돋움체", Font.BOLD, 20);
	Font fnt = new Font("돋움체", Font.BOLD, 18);
	
	public MsgFrame() {
		//Tab 배경 바꾸는 방법 찾기.
		
		setLayout(new BorderLayout());
		upper.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		getMsgPane.add(new JLabel("왜 안돼?"));
		getMsgPane.setBackground(Color.white);
		MsgTab.addTab("받은쪽지", getMsgPane);
		
		MsgTab.addTab("보낸쪽지", sendMsgPane);
		writeWest.setBackground(Color.white);
		writeWest.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		title.setFont(fnt);		writeWest.add(title);		writeNorth.add("West", writeWest);
		titleTf.setFont(fnt);	writeCenter.add(titleTf);	writeNorth.add(writeCenter);
		writeCenter.setBackground(Color.white);
		writeCenter.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		getter.setFont(fnt);	writeWest.add(getter);		writeNorth.add("West", writeWest);
		getterTf.setFont(fnt); 	writeCenter.add(getterTf);	writeNorth.add(writeCenter);
		writeNorth.setBackground(Color.white);
		writeMsgPane.setBackground(Color.white);
		writeMsgPane.add("North", writeNorth);
		
		MsgTab.addTab("쪽지쓰기", writeMsgPane);
		add(BorderLayout.NORTH, upper);
		MsgTab.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		MsgTab.setBackground(Color.white);
		MsgTab.setFont(TabFnt);
		add(MsgTab);
		
		MsgTab.addTab("실시간채팅", liveChat);
		MsgTab.setBackground(Color.white);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new MsgFrame();
		
		
	}

}
