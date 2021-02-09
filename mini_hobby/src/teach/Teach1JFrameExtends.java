package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Teach1JFrameExtends extends JFrame implements ActionListener, MouseListener{
	JPanel paneTop = new JPanel(new BorderLayout());
		ImageIcon logo = new ImageIcon("img/logo.png");
		JButton Logo = new JButton(logo);
		Dimension logoSize = new Dimension(100, 50);
		JPanel paneLabel = new JPanel();
	
	Font fn = new Font("맑은고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Color col = new Color(204,222,233);
	
	JPanel center = new JPanel();
		JTextField tf = new JTextField();
		JButton btn = new JButton("검색");
		JLabel login = new JLabel("○○○님 로그인 완료");
		JLabel count = new JLabel("누적 수강생 수 : 12명");
		JPanel cal = new JPanel();
		JButton btn_list = new JButton("내 글 목록");
		JButton btn_new = new JButton("새 글 쓰기");
		JLabel lbl_ta = new JLabel("메모");
		JTextArea ta = new JTextArea();
		JScrollPane sp = new JScrollPane(ta);
		JLabel lbl_ = new JLabel("＃＃＃님이 예약하신 클래스까지");
		JLabel lbl_2 = new JLabel("21시간 40분 23초 남았습니다.");
		JButton btn_save = new JButton("메모저장");
		JButton btn_delete = new JButton("메모삭제");
		JPanel Down = new JPanel(new BorderLayout());
			JLabel lbl = new JLabel("예약된 수강 예정 클래스");
			String column[] = {"No","클래스명","예약일자","수강생"};
			String data[][] = new String [0][column.length];
			DefaultTableModel model = new DefaultTableModel(data, column);
			JTable table = new JTable(model);
			JScrollPane sp2 = new JScrollPane(table);
		
	// 달력에 대한 부분
	Calendar now = Calendar.getInstance();
	int y,m,d;
	JPanel selectPane = new JPanel();
	JLabel dateLbl = new JLabel();
	// 달력 패널 
	JPanel calPane = new JPanel(); 
		JPanel dayPane = new JPanel(new GridLayout(1,7,40,40)); //일 ~월 글자출력 
		String days[] = {"일", "월", "화", "수", "목", "금", "토"}; 
		JPanel datePane = new JPanel( new GridLayout(0,7,38,25)); // 1~31 날짜 출력 

	public Teach1JFrameExtends() {
		TeachTopMenu();
		TeachMain();
		
		
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//logo 버튼 이벤트 오버라이딩
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		if(obj==Logo) { // 로고버튼 누르면 -> 홈으로 돌아감
			center.setVisible(false);
			center.removeAll();
			TeachMain();
			center.setVisible(true);
			add("Center", center);
		} else if(obj==btn) { // 검색
			String searchTxt = tf.getText();
			TeachCateList tcl = new TeachCateList(searchTxt);
			tcl.searchTf.setText(searchTxt);
			center.setVisible(false);
			center.removeAll();
			center = tcl.mainPane;
			add(center);
			center.setVisible(true);
		} else if(obj==btn_list) { // 내글목록
			center.setVisible(false);
			center.removeAll();
			//center = new TeachCateList().mainPane;
			add(center);
			center.setVisible(true);
		} else if(obj==btn_new) { // 새글쓰기
			center.setVisible(false);
			center.removeAll();
			center = new TeachTextCreate().main;
			add(center);
			center.setVisible(true);
			
		} else if(obj==btn_save) {
			JOptionPane.showMessageDialog(this, "저장되었습니다");
		} else if(obj==btn_delete) {
			ta.setText(y+"년 "+m+"월 "+d+"일\n\n");
		}
	}
	//label 이벤트 오버라이딩
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		try {
			if(lbl.equals("이전으로")) {
				JOptionPane.showMessageDialog(this, "구현중입니다.");
			}else if(lbl.equals("메세지함")) {
				center.setVisible(false);
				center.removeAll();
				center = new Teach2MsgFrame().tabBack;
				center.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("내정보")) {
				String pwd = JOptionPane.showInputDialog("비밀번호를 입력하세요.");
				if (pwd==null) {
					
				} else if (pwd.equals("master1234")) {
					center.setVisible(false);
					center.removeAll();
					center = new Teach3MyMenu().paneStu;
					this.setVisible(true);
					add("Center", center);
				} else {
					JOptionPane.showMessageDialog(this, "로그인에 실패하셨습니다.");
				}
			}else if(lbl.equals("로그아웃")) {
				int answer = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃 확인", 0);
				if (answer==0) {
					this.setVisible(false);
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		JLabel lbl = (JLabel)me.getSource();
		int date = Integer.parseInt(lbl.getText());
		ta.setText(y+"년 "+m+"월 "+date+"일\n\n");
	}
	public void TeachTopMenu() {
		add("North",paneTop);
		//logoBtn
		add(Logo);
			
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,4);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"이전으로","메세지함","내정보","로그아웃"};
		
		for(int i=0; i<topLblStr.length; i++) {
			JLabel topLbl = new JLabel(topLblStr[i], JLabel.CENTER);
			paneLabel.add(topLbl);
			topLbl.setFont(fn);
			topLbl.addMouseListener(this);
		}
		
		//간격조정
		paneTop.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		paneTop.add(BorderLayout.WEST,Logo); paneTop.add(BorderLayout.EAST, paneLabel);
		
		//배경색상
		paneTop.setBackground(Color.white); Logo.setBackground(Color.white); paneLabel.setBackground(Color.white);
		Logo.setSize(logoSize);
		//내정보 패널
		//paneCenter.add(new MyMenu_Stu().paneStu);
		
		Logo.addActionListener(this);
	}
	public void TeachMain() {
		center.removeAll();
		add("Center", center);
		center.setBackground(Color.white);
		center.setLayout(null);
		center.add(tf); center.add(btn); center.add(login); center.add(count);
		center.add(cal); calendar_Tea();
		center.add(btn_list); center.add(btn_new); center.add(lbl_ta); center.add(sp);
		center.add(lbl_); center.add(lbl_2); center.add(btn_save); center.add(btn_delete); 
		
		tf.setBounds(20,20,640,40); btn.setBounds(670,20,80,40);
		login.setBounds(20,70,300,40); count.setBounds(450,70,300,40);
		cal.setBounds(20,120,400,400); cal.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		btn_list.setBounds(520,130,100,40); btn_new.setBounds(650,130,100,40);
		lbl_ta.setBounds(600,190,50,30); sp.setBounds(440,230,330,290);
		lbl_.setBounds(20,540,400,20); lbl_2.setBounds(20,560,400,20);
		btn_save.setBounds(520,540,100,40); btn_delete.setBounds(650,540,100,40);

		btn.setFont(fn); login.setFont(fn); count.setFont(fn); btn_list.setFont(fn);
		btn_new.setFont(fn); lbl_ta.setFont(fnt); lbl_.setFont(fn); btn_save.setFont(fn);
		lbl_2.setFont(fn); btn_delete.setFont(fn); 
		
		btn.setBackground(col); btn.addActionListener(this);
		btn_list.setBackground(col); btn_list.addActionListener(this);
		btn_new.setBackground(col); btn_new.addActionListener(this);
		btn_save.setBackground(col); btn_save.addActionListener(this);
		btn_delete.setBackground(col); btn_delete.addActionListener(this);
		
		lbl_.setHorizontalAlignment(JLabel.CENTER); lbl_2.setHorizontalAlignment(JLabel.CENTER);
		
		center.add(Down); Down.add(lbl); lbl.setFont(fnt); Down.add(sp2);
		Down.setBounds(20,600,740,250); Down.setBorder(new LineBorder(Color.black, 1));
		Down.setBackground(Color.white); Down.setLayout(null); 
		lbl.setBounds(250,5,300,50); sp2.setBounds(10,60,720,180);
		table.getParent().setBackground(Color.white);
		table.getParent().setFont(fn);
		table.getTableHeader().setBackground(col);
		table.getTableHeader().setFont(fnt);
	}
	public void calendar_Tea() {
		dayPane.setVisible(false); datePane.setVisible(false);
		dayPane.removeAll(); datePane.removeAll();
		
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null); cal.setBackground(Color.white);
		
		cal.add(selectPane); selectPane.setBounds(1,1,398,40);
		selectPane.add(dateLbl); 
		
		Calendar now = Calendar.getInstance();
		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "+d+"일");
		ta.setText(y+"년 "+m+"월 "+d+"일\n\n");
		dateLbl.setFont(fnt);
		selectPane.setBackground(new Color(204,222,233));
		
		cal.add(calPane); calPane.setBounds(5,40,390,350);
		calPane.setLayout(null); calPane.setBackground(Color.white);
		calPane.add(dayPane); calPane.add(datePane);
		dayPane.setBounds(5,0,370,50); dayPane.setBackground(Color.white);
		datePane.setBounds(5,50,370,300); datePane.setBackground(Color.white);
		
		calendarSetting(y,m);
		
		//일~토 넣기 
		for (int i = 0; i < days.length; i++) {
			JLabel dayOfWeekLbl = new JLabel(days[i]);
			dayOfWeekLbl.setHorizontalAlignment(SwingConstants.CENTER);
			dayPane.add(dayOfWeekLbl);
			if(i==0) {
				dayOfWeekLbl.setForeground(Color.RED);
			}else if(i==6) {
				dayOfWeekLbl.setForeground(Color.BLUE);
			}
			dayOfWeekLbl.setFont(fnt2);
			dayOfWeekLbl.setBackground(Color.white);
		}
		dayPane.setVisible(true); datePane.setVisible(true);
	}
		
	public void calendarSetting(int y, int m) {
		int week, lastDay;
		// 콤보박스에 선택된 년도, 월의 1일로 세팅 
		now.set(y, m-1, 1); 
		//1일의 요일 구하기 
		week = now.get(Calendar.DAY_OF_WEEK); //세팅된 날짜의 요일을 구함 (1일이 무슨요일인지)
		//월의 마지막날이 몇일인지 구하기 
		lastDay = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 1일 전까지 공백 출력 
		for(int space=1; space<week; space++) {
			datePane.add(new JLabel(""));
		}
		// 월의 1일~마지막일 까지 출력 
		for(int day=1; day<=lastDay; day++) {
			JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
			dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
			now.set(y, m-1, day); //날짜별로 요일을 구해서
			int colorWeek = now.get(Calendar.DAY_OF_WEEK);
			if(colorWeek == 1) { //일요일이면 
				dayOfMonthLbl.setForeground(Color.RED);
			}else if(colorWeek == 7) { //토요일이면 
				dayOfMonthLbl.setForeground(Color.BLUE);
			}
			datePane.add(dayOfMonthLbl);
			dayOfMonthLbl.setFont(fnt2);
			dayOfMonthLbl.addMouseListener(this);
		}
		
	}
	
	public static void main(String[] args) {
		new Teach1JFrameExtends();

	}
}