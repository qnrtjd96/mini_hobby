package studen;

import java.awt.Color;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class StudenSendMsgDialog extends JDialog {
	JPanel mainPane = new JPanel();
		JLabel title = new JLabel("제목",JLabel.CENTER);
		JLabel titleData; //제목 데이터
		JLabel sendUser = new JLabel("보낸사람",JLabel.CENTER);
		JLabel sendUserData;
		JLabel receiveUser = new JLabel("받는사람",JLabel.CENTER);
		JLabel receiveUserData;
		JLabel msgData;
	LineBorder lineBorder = new LineBorder(Color.black);
	Color col6 = new Color(204,222,233);
	
	//String idStr;
	//int sendMsgNum;
	
	public StudenSendMsgDialog() {
		
	}
	
	public StudenSendMsgDialog(String idStr, int sendMsgNum) {
		//this.idStr = idStr;
		//this.sendMsgNum = sendMsgNum;
		
		setTitle("메세지 내용");
		add("Center",mainPane);
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		mainPane.setBorder(lineBorder);

		//메시지 데이터 받아오기
		sendDetailLst(idStr, sendMsgNum);
		System.out.println("다이얼로그 패널 >> 쪽지쓰기에 아이디 받아오는지.... "+idStr+", 쪽지번호 읽히는지.... "+sendMsgNum);
							
		//mainPane add
		mainPane.add(title); mainPane.add(titleData);
		mainPane.add(sendUser); mainPane.add(sendUserData);
		mainPane.add(receiveUser); mainPane.add(receiveUserData);
		mainPane.add(msgData);
		
		//보더 셋팅
		title.setBorder(lineBorder); titleData.setBorder(lineBorder);
		sendUser.setBorder(lineBorder); sendUserData.setBorder(lineBorder);
		receiveUser.setBorder(lineBorder); receiveUserData.setBorder(lineBorder);
		msgData.setBorder(lineBorder);
		
		//위치 셋팅
		title.setBounds(0,0,100,30); titleData.setBounds(99,0,390,30);
		sendUser.setBounds(0,29,100,30); sendUserData.setBounds(99,29,390,30);
		receiveUser.setBounds(0,58,100,30); receiveUserData.setBounds(99,58,390,30);
		msgData.setBounds(0,87,484,430);
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(190, 100);
		
	}
	
	public void sendDetailLst(String idStr, int sendMsgNum) {
		System.out.println("다이얼 정보 불러오기 메소드 >  아이디 받아오는지.... "+idStr+", 쪽지번호 읽히는지.... "+sendMsgNum);
			
		ConsDAO dao = new ConsDAO();
		List<ConsVO> sendLst = dao.studenDiaMsgRec(idStr, sendMsgNum);
		
		for(int i=0; i<sendLst.size(); i++) {
			ConsVO sendVo = sendLst.get(i);
			Object data[] = {
					" " + sendVo.getMsg_title(), sendVo.getSend(), sendVo.getGet(), sendVo.getMsg_detail()
				};
			titleData = new JLabel("  "+(String)data[0],JLabel.LEFT);
			sendUserData = new JLabel("  "+(String)data[1],JLabel.LEFT);
			receiveUserData = new JLabel("  "+(String)data[2],JLabel.LEFT);
			msgData = new JLabel("  "+(String)data[3],JLabel.LEFT);
		}
	}

}
