package studen;

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

public class StudenReceiveMsgDialog extends JDialog implements ActionListener{
	JPanel mainPane = new JPanel();
		JLabel title = new JLabel("제목",JLabel.CENTER);
		JLabel titleData; //제목 데이터
		JLabel sendUser = new JLabel("보낸사람",JLabel.CENTER);
		JLabel sendUserData;
		JLabel receiveUser = new JLabel("받는사람",JLabel.CENTER);
		JLabel receiveUserData;
		JLabel msgData;
		JButton sendBtn = new JButton("답장하기");
	LineBorder lineBorder = new LineBorder(Color.black);
	Color col6 = new Color(204,222,233);
	public StudenReceiveMsgDialog(String id, int msgNum) {
		setTitle("메세지 내용");
		add("Center",mainPane);
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		mainPane.setBorder(lineBorder);
		
		//데이터 호출
		msgDetailList(id, msgNum);
		
		//mainPane add
		mainPane.add(title); mainPane.add(titleData);
		mainPane.add(sendUser); mainPane.add(sendUserData);
		mainPane.add(receiveUser); mainPane.add(receiveUserData);
		mainPane.add(msgData);
		mainPane.add(sendBtn);
		
		//보더 셋팅
		title.setBorder(lineBorder); titleData.setBorder(lineBorder);
		sendUser.setBorder(lineBorder); sendUserData.setBorder(lineBorder);
		receiveUser.setBorder(lineBorder); receiveUserData.setBorder(lineBorder);
		msgData.setBorder(lineBorder);
		
		//배경 셋팅
		sendBtn.setBackground(col6);
		
		//위치 셋팅
		title.setBounds(0,0,100,30); titleData.setBounds(99,0,390,30);
		sendUser.setBounds(0,29,100,30); sendUserData.setBounds(99,29,390,30);
		receiveUser.setBounds(0,58,100,30); receiveUserData.setBounds(99,58,390,30);
		msgData.setBounds(0,87,484,430);
		sendBtn.setBounds(378,524,100,30);
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(190, 100);
		
		sendBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == sendBtn) {
			this.setVisible(false);
			//쪽지쓰기 다이어로그 연결
		}
	}
	//데이터 핸들링
	public void msgDetailList(String id, int msgNum) {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.studenDiaMsgRec(id, msgNum);
		
		for(int i=0; i<lst.size(); i++) {
			ConsVO vo = lst.get(i);
			Object[] data = {" "+vo.getMsg_title(),vo.getSend(),vo.getGet(),vo.getMsg_detail()};
			//데이터 핸들링
			titleData = new JLabel("  "+(String)data[0],JLabel.LEFT);
			sendUserData = new JLabel("  "+(String)data[1],JLabel.LEFT);
			receiveUserData = new JLabel("  "+(String)data[2],JLabel.LEFT);
			msgData = new JLabel("  "+(String)data[3],JLabel.LEFT);
		}
	}
}
