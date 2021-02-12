package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main4ChatClient extends JFrame implements ActionListener, KeyListener, WindowListener{
	JTextArea textArea; //멤버 참조변수
	JTextField tfMsg;
	JButton btnSend;

	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;	
	
	String id;
	public Main4ChatClient() {}
	public Main4ChatClient(String id) {
		this.id=id;
		
		setTitle(id +"의 채팅창");
		setBounds(450, 400, 500, 350);

		textArea = new JTextArea();		
		textArea.setEditable(false); //쓰기 금지
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane,BorderLayout.CENTER);
				
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		tfMsg = new JTextField();
		btnSend = new JButton("send");
		msgPanel.add(tfMsg, BorderLayout.CENTER);
		msgPanel.add(btnSend, BorderLayout.EAST);

		add(msgPanel,BorderLayout.SOUTH);
		
		//이벤트 등록
		btnSend.addActionListener(this);
		tfMsg.addKeyListener(this);
				
		setVisible(true);
		tfMsg.requestFocus();
		
		//서버와 연결하는 네트워크 작업 : 스레드 객체 생성 및 실행
		ClientThread clientThread = new ClientThread();
		clientThread.setDaemon(true);
		clientThread.start();
		
		
	}//생성자
	
	//이너클래스 : 서버와 연결하는 네트워크 작업 스레드
	class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				socket = new Socket("192.168.0.7", 10001);
				textArea.append("1:1 대화를 시작합니다.\n");
				//데이터 전송을 위한 스트림 생성(입출력 모두)
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				//보조스트림으로 만들어서 데이터전송 작업을 편하게 ※다른 보조스트림 사용
				dis = new DataInputStream(is);
				dos = new DataOutputStream(os);	
				
				while(true) {//상대방 메시지 받기
					String msg = dis.readUTF();
					textArea.append(id +": " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
			} catch (UnknownHostException e) {
				textArea.append("서버 주소가 이상합니다.\n");
			} catch (IOException e) {
				textArea.append("서버와 연결이 끊겼습니다.\n");
			}
		}
	}
	
	//메시지 전송하는 기능 메소드
	void sendMessage() {	
		String msg = tfMsg.getText(); //TextField에 써있는 글씨를 얻어오기
		tfMsg.setText(""); //입력 후 빈칸으로
		textArea.append("사용자 : " + msg + "\n");//1.TextArea(채팅창)에 표시
		textArea.setCaretPosition(textArea.getText().length());

		//2.상대방(Server)에게 메시지 전송하기
		Thread t = new Thread() {
			@Override
			public void run() {
				try { //UTF = 유니코드의 규약(포맷), 한글 깨지지 않게 해줌
					dos.writeUTF(msg);
					dos.flush(); //계속 채팅 위해 close()하면 안됨				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();			
	}
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if(dos != null) dos.close();
			if(dis != null) dis.close();
			if(socket != null) socket.close();
		} catch (IOException e1) {					
			e1.printStackTrace();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_ENTER:
			sendMessage();
			break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		sendMessage();	
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}//class