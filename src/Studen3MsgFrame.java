import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Studen3MsgFrame extends JPanel {
	//탭 배경설정을 위한 부모 패널 
	JPanel tabBack = new JPanel(new BorderLayout());
	//탭 패널, 하위메뉴
	JTabbedPane MsgTab = new JTabbedPane();
	
	Font TabFnt = new Font("돋움체", Font.BOLD, 20);
	Font fnt = new Font("돋움체", Font.BOLD, 18);
	
	public Studen3MsgFrame() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		add("Center", tabBack);
		
		
		
		//받은 쪽지함
		MsgTab.addTab("받은쪽지", new StudenReceiveMsg().mainPane);
		//보낸쪽지함
		MsgTab.addTab("보낸쪽지", new StudenSendMsg().mainPane);
		//쪽지쓰기
		MsgTab.addTab("쪽지쓰기", new AdminMsgWrite());
		//실시간 채팅 
		MsgTab.addTab("실시간채팅", new StudenLiveChat());
		MsgTab.setBackground(Color.white);
		
		MsgTab.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		MsgTab.setBackground(new Color(204,222,233)); //탭 패널 색깔 설정 
		
		//tabBack.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //탭 부모 패널 여백
		
		
		MsgTab.setFont(TabFnt); //탭 폰트 설정
		tabBack.add("Center", MsgTab);
		add(tabBack); tabBack.setBackground(Color.white);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new Studen3MsgFrame();
		
		
	}

}