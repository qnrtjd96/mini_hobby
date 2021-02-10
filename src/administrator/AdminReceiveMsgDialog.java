package administrator;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dbConnection.ConsDAO;
import dbConnection.ConsVO;

public class AdminReceiveMsgDialog implements MouseListener{
	JFrame frame = new JFrame();
	LineBorder lineBorder = new LineBorder(Color.black);
	JPanel mainPane = new JPanel();
		JLabel titleTp, titleStrTp;
		JLabel recUserTp, recUserStrTp;
		JLabel msgLbl = new JLabel();
		JLabel sendLbl = new JLabel("<HTML><U>답장하기</U></HTML>",JLabel.CENTER);
	public AdminReceiveMsgDialog() {}
	public AdminReceiveMsgDialog(String id, int msgNum) {
		frame.setTitle("메세지 상세내용");
		mainPane.setLayout(null);
		
		//기본 셋팅
		titleTp = new JLabel("제목", JLabel.CENTER);
		recUserTp = new JLabel("보낸사람", JLabel.CENTER); 
		
		//데이터 호출
		msgDetailList(id, msgNum);
		
		//글자색
		sendLbl.setForeground(Color.blue);
		
		//텍스트 패널 / 라벨 보더 라인
		mainPane.setBorder(lineBorder);
		titleTp.setBorder(lineBorder); titleStrTp.setBorder(lineBorder);
		recUserTp.setBorder(lineBorder); recUserStrTp.setBorder(lineBorder);
		msgLbl.setBorder(lineBorder);
		
		//위치값
		titleTp.setBounds(0,0,100,30); titleStrTp.setBounds(99,0,385,30);
		recUserTp.setBounds(0,29,100,30); recUserStrTp.setBounds(99,29,385,30);
		msgLbl.setBounds(0,58,484,350);
		sendLbl.setBounds(390,420,100,30);

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
		mainPane.add(sendLbl);
		
		frame.add("Center",mainPane);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		sendLbl.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickBtn = e.getButton();
		Object obj = e.getSource();
		if(clickBtn==1) {
			if(obj == sendLbl) {
				frame.setVisible(false);
				//쪽지쓰기 다이어로그 구현
			}
		}
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//데이터 핸들링
	public void msgDetailList(String id, int msgNum) {
		ConsDAO dao = new ConsDAO();
		List<ConsVO> lst = dao.studenDiaMsgRec(id, msgNum);
		
		for(int i=0; i<lst.size(); i++) {
			ConsVO vo = lst.get(i);
			Object[] data = {" "+vo.getMsg_title(),vo.getSend(),vo.getMsg_detail()};
			//데이터 핸들링
			titleStrTp = new JLabel("  "+(String)data[0],JLabel.LEFT);
			recUserStrTp = new JLabel("  "+(String)data[1],JLabel.LEFT);
			msgLbl = new JLabel("  "+(String)data[2],JLabel.LEFT);
		}
	}
}
