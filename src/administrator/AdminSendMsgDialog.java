package administrator;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class AdminSendMsgDialog {
	JFrame frame = new JFrame();
	LineBorder lineBorder = new LineBorder(Color.black);
	JPanel mainPane = new JPanel();
		JLabel titleTp, titleStrTp;
		JLabel recUserTp, recUserStrTp;
		JLabel msgLbl = new JLabel();
		
	public AdminSendMsgDialog() {}
	public AdminSendMsgDialog(String master, int sendMsgNum) {
		frame.setTitle("메세지 상세내용");
		mainPane.setLayout(null);
		//기본 셋팅
		titleTp = new JLabel("제목", JLabel.CENTER);
		recUserTp = new JLabel("받는사람", JLabel.CENTER); 
				
		//데이터 호출
		sendMsgDetailList(master, sendMsgNum);
				
		//텍스트 패널 / 라벨 보더 라인
		mainPane.setBorder(lineBorder);
		titleTp.setBorder(lineBorder); titleStrTp.setBorder(lineBorder);
		recUserTp.setBorder(lineBorder); recUserStrTp.setBorder(lineBorder);
		msgLbl.setBorder(lineBorder);
				
		//위치값
		titleTp.setBounds(0,0,100,30); titleStrTp.setBounds(99,0,385,30);
		recUserTp.setBounds(0,29,100,30); recUserStrTp.setBounds(99,29,385,30);
		msgLbl.setBounds(0,58,484,350);

		//배경색
		mainPane.setBackground(Color.white);
		titleTp.setOpaque(true); titleTp.setBackground(Color.white);
		titleStrTp.setOpaque(true); titleStrTp.setBackground(Color.white);
		recUserTp.setOpaque(true); recUserTp.setBackground(Color.white);
		recUserStrTp.setOpaque(true); recUserStrTp.setBackground(Color.white);
		msgLbl.setOpaque(true); msgLbl.setBackground(Color.white);
				
		//add
		mainPane.add(titleTp); mainPane.add(titleStrTp);
		mainPane.add(recUserTp); mainPane.add(recUserStrTp);
		mainPane.add(msgLbl);
				
		frame.add("Center",mainPane);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	}
	
	public void sendMsgDetailList(String master, int sendMsgNum) {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.studenDiaMsgRec(master, sendMsgNum);
		for(int i=0; i<lst.size(); i++) {
			ConsVO vo = lst.get(i);
			Object data[] = {
					" "+vo.getMsg_title(),vo.getGet(),vo.getMsg_detail()
			};
			titleStrTp = new JLabel("  "+(String)data[0],JLabel.LEFT);
			recUserStrTp = new JLabel("  "+(String)data[1],JLabel.LEFT);
			msgLbl = new JLabel("  "+(String)data[2],JLabel.LEFT);
		}
		
	}
}
