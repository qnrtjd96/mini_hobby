public class MsgFrame extends JFrameExtends {
	JPanel upper = new StudenTopMenu().paneTop;
	//탭 배경설정을 위한 부모 패널 
	JPanel tabBack = new JPanel();
	//탭 패널, 하위메뉴
	JTabbedPane MsgTab = new JTabbedPane();
		JPanel getMsgPane = new JPanel();
		JPanel sendMsgPane = new JPanel();
		JPanel writeMsgPane = new JPanel(new BorderLayout());
			JPanel writeNorth = new JPanel(new BorderLayout());
				JPanel writeWest = new JPanel(new GridLayout(2,1)); //WriteNorth - west
					JLabel title = new JLabel("제목"); 
					JLabel getter = new JLabel("보낸쪽지");
				JPanel writeCenter = new JPanel(new GridLayout(2,1)); //WriteNorth - center
					JTextField titleTf = new JTextField(50);
					JTextField getterTf = new JTextField();
				JTextArea writeTa = new JTextArea();
		JPanel liveChat = new JPanel();
	
	Font TabFnt = new Font("돋움체", Font.BOLD, 20);
	Font fnt = new Font("돋움체", Font.BOLD, 18);
	
	public MsgFrame() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		add("North", upper);
		
		//받은 쪽지함
		getMsgPane.add(new JLabel("왜 안돼?"));
		getMsgPane.setBackground(Color.white);
		
		//보낸쪽지함
		sendMsgPane.setBackground(Color.white);
		MsgTab.addTab("보낸쪽지", sendMsgPane);
		
		//쪽지쓰기
		writeNorth.setBackground(Color.white);
		writeWest.setBackground(Color.white);
		writeWest.setBorder(BorderFactory.createEmptyBorder(20,20,20,20)); //쪽지쓰기 north에 지정
		title.setFont(fnt);		writeWest.add(title);		writeNorth.add("West", writeWest);
		titleTf.setFont(fnt);	writeCenter.add(titleTf);	writeNorth.add(writeCenter);
		getter.setFont(fnt);	writeWest.add(getter);		writeNorth.add("West", writeWest);
		getterTf.setFont(fnt); 	writeCenter.add(getterTf);	writeNorth.add(writeCenter);

		writeCenter.setBackground(Color.white); //쪽지쓰기에 center에 textarea 
		writeCenter.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		writeCenter.add(writeTa);
		
		writeMsgPane.setBackground(Color.white);
		writeMsgPane.add("North", writeNorth);
		MsgTab.addTab("쪽지쓰기", writeMsgPane);
		
		//실시간 채팅 
		liveChat.setBackground(Color.white);
		MsgTab.addTab("실시간채팅", liveChat);
		MsgTab.setBackground(Color.white);
		
		MsgTab.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		MsgTab.setBackground(new Color(204,222,233)); //탭 패널 색깔 설정 
		//MsgTab.setBackground(Color.white);
		//tabBack.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //탭 부모 패널 여백
		//tabBack.setBackground(Color.white); //탭 부모 패널 배경설정
		//tabBack.add(MsgTab);
		MsgTab.setFont(TabFnt); //탭 폰트 설정
		add(MsgTab);
		//add(tabBack);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new MsgFrame();
		
		
	}

}
