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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main3ChatServer extends JFrame implements ActionListener, KeyListener, WindowListener{
	JTextArea textArea; //멤버 참조변수
	JTextField tfMsg;
	JButton btnSend;
	ServerSocket serverSocket;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	String id;
	public Main3ChatServer() {}
	public Main3ChatServer(String id) {
		this.id = id;
		setTitle(id +"의 채팅창");
		setBounds(450, 50, 500, 350);
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

		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //메인 끝나면 같이 종료
		serverThread.start();
	}
	
	//메시지 전송
	void sendMessage() {	
		String msg = tfMsg.getText(); //TextField에 써있는 글씨를 얻어오기
		tfMsg.setText(""); //입력 후 빈칸으로
		textArea.append("사용자 : " + msg + "\n");//1.TextArea(채팅창)에 표시
		textArea.setCaretPosition(textArea.getText().length()); //스크롤 따라가게

		//2.상대방(Client)에게 메시지 전송하기
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};		
		t.start();
	}	
	
	//연결되면 메시지를 지속적으로 받는 역할 수행
	class ServerThread extends Thread {
		@Override
		public void run() {			
			try {  
				//서버 소켓 생성 작업
				serverSocket = new ServerSocket(10001);
				textArea.append("이걸 끄면 실시간대화를 받기 불가능합니다. \n");			
				socket = serverSocket.accept();//클라이언트가 접속할때까지 커서(스레드)가 대기
				
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				while(true) {
					//상대방이 보내온 데이터를 읽기
					String msg = dis.readUTF();//상대방이 보낼때까지 대기
					textArea.append("상대방: " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
				}				
			}catch(IOException e) {
				textArea.append("현재는 채팅이 불가능합니다.\n");
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sendMessage();	
	}
	@Override
	public void keyPressed(KeyEvent e) {		
		//입력받은 키가 엔터인지 알아내기, KeyEvent 객체가 키에대한 정보 갖고있음
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_ENTER:
			sendMessage();
			break;
		}	
	}
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if(dos != null) dos.close();
			if(dis != null) dis.close();
			if(socket != null) socket.close();
			if(serverSocket != null) serverSocket.close();
		} catch (IOException e1) {					
			e1.printStackTrace();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
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
}