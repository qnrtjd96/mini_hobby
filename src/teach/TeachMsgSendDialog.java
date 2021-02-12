package teach;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class TeachMsgSendDialog extends JDialog {
	
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
	
	public TeachMsgSendDialog() {}
	
	public TeachMsgSendDialog(String id, int sendMsgNum) { 
		System.out.println("send dialog id > > > "+ id + ", send dialog msgnum > > > "+sendMsgNum);
		
		setTitle("메세지 내용");
		add("Center",mainPane);
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		mainPane.setBorder(lineBorder);
		
		msgDetailList(id, sendMsgNum);
		System.out.println("다이얼로그 패널 >> 쪽지쓰기에 아이디 받아오는지.... "+id+", 쪽지번호 읽히는지.... "+sendMsgNum);
		
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
	//데이터 핸들링
	public void msgDetailList(String id, int sendMsgNum) {
		System.out.println("send dialog id > > > "+ id + ", send dialog msgnum > > > "+sendMsgNum);
		
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.studenDiaMsgRec(id, sendMsgNum);
		
		for(int i=0; i<lst.size(); i++) {
			System.out.println("send dialog id > > > "+ id + ", send dialog msgnum > > > "+sendMsgNum);
			
			ConsVO vo = lst.get(i);
			Object[] data = {
					" "+vo.getMsg_title(),vo.getSend(),vo.getGet(),vo.getMsg_detail()
				};
			//데이터 핸들링
			titleData = new JLabel("  "+(String)data[0],JLabel.LEFT);
			sendUserData = new JLabel("  "+(String)data[1],JLabel.LEFT);
			receiveUserData = new JLabel("  "+(String)data[2],JLabel.LEFT);
			msgData = new JLabel("  "+(String)data[3],JLabel.LEFT);
		}
	}

}
