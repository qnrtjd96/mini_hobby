package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.MemberDAO;
import dbConnection.MemberVO;
import dbConnection.MemoDAO;
import dbConnection.MemoVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;
import main.Main0Login;

public class Teach1JFrameExtends extends JFrame implements ActionListener, MouseListener, Runnable{
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
		JLabel login; 
		JLabel count = new JLabel("누적 수강생 수 : 0명");
		JPanel cal = new JPanel();
		JButton btn_list = new JButton("내 글 목록");
		JButton btn_new = new JButton("새 글 쓰기");
		JLabel lbl_ta = new JLabel("메모");
		JTextArea ta = new JTextArea();
		JScrollPane sp = new JScrollPane(ta);
		JLabel lbl_ = new JLabel("수강예정인 클래스 및 수강생이");
		JLabel lbl_2 = new JLabel("존재하지 않습니다.");
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
	
	MemberVO vo;
	String id, pwd;
	JPanel[] paneArr;
	int p;
	
	public Teach1JFrameExtends() {}
	public Teach1JFrameExtends(String id, String pwd) {
		this.id = id;
		System.out.println("teach frame id > " + id+", "+pwd);
		
		TeachTopMenu(id, pwd);
		TeachMain();
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new AdapterInner());
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
			center = new TeachMyList(id).mainPane;
			add(center);
			center.setVisible(true);
		} else if(obj==btn_new) { // 새글쓰기
			//center.setVisible(false);
			//center.removeAll();
			new TeachTextCreate(id);
			//center = new TeachTextCreate(id).main;
			//add(center);
			//center.setVisible(true);
			
		} else if(obj==btn_save) { // 메모저장
			MemoVO vom = new MemoVO(ta.getText().substring(0, 10), ta.getText().substring(11), vo.getId());
			MemoDAO dao = new MemoDAO();
			dao.InsertMemo(vom);
			JOptionPane.showMessageDialog(this, "메모가 저장되었습니다.");
		} else if(obj==btn_delete) { // 메모삭제
			MemoVO vom = new MemoVO(ta.getText().substring(0, 10), ta.getText().substring(11), vo.getId());
			MemoDAO dao = new MemoDAO();
			int result = dao.deleteMemo(vom);
			if (result>0) {
				JOptionPane.showMessageDialog(this, "메모가 삭제되었습니다.");
				ta.setText(vom.getMemo_date());
			}
		}
		new TeachCateList().getId(id);
	}
	//label 이벤트 오버라이딩
	@Override
	public void mouseReleased(MouseEvent me2) {
		Object obj2 = me2.getSource();
		if (obj2==ta) {
			
		} else {
			JLabel obj = (JLabel)me2.getSource();
			Object lbl = obj.getText();
			if(lbl.equals("이전으로")) {
				center.setVisible(false);
				center.removeAll();
				center=paneArr[p-1];
				center.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("메세지함")) {
				center.setVisible(false);
				center.removeAll();
				center = new Teach2MsgFrame(id).tabBack;
				center.setVisible(true);
				add("Center", center);
			}else if(lbl.equals("내정보")) {
				///////////수정금지/////////////////
				MemberDAO dao = new MemberDAO();
				List<MemberVO> lst = dao.getMemberInfo(id);
				MemberVO vo = lst.get(0);
				String pwdRe = vo.getPwd();
				String pwd = JOptionPane.showInputDialog("비밀번호를 입력하세요.");
				if (pwd==null) {
					
				} else if (pwd.equals(pwdRe)) {
					center.setVisible(false);
					center.removeAll();
					center = new Teach3MyMenu(id, pwd).paneStu;
					System.out.println("teach my menu pwd > "+pwd);
					this.setVisible(true);
					add("Center", center);
				} else {
					JOptionPane.showMessageDialog(this, "비밀번호를 다시 확인해주세요");
				}
			}else if(lbl.equals("로그아웃")) {
				int answer = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃 확인", 0);
				if (answer==0) {
					this.setVisible(false);
					this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					Acess_memDAO dao = new Acess_memDAO();
					int result = dao.LogOut(id);
					//로그아웃 말고 X누르면 지워지는것도 구현해야됨 !!!
					new Main0Login();
				}
			}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(id);
System.out.println("로그아웃?"+result);
			System.exit(0);
		}
	}
	public void mouseClicked(MouseEvent me) {
		Object obj2 = me.getSource();
		if (obj2==ta) {
			
		} else {
			Object obj = me.getSource();
			JLabel lbl = (JLabel)me.getSource();
			int date = Integer.parseInt(lbl.getText());
			MemoDAO dao = new MemoDAO();
			List<MemoVO> lst = dao.OutputMemo(ta.getText().substring(0,10), id);
			String text = "";
			if (lst.size()==0) { // 저장된 메모가 없을경우
				ta.setText(y+"-"+m+"-"+date+"\n\n");
			} else if(lst.size()>0) { // 저장메모가 있을경우
				for (int i=0; i<lst.size(); i++) {
					MemoVO vom = lst.get(i);
					text=text+vom.getMemo_detail()+"\n";
				}
				ta.setText(y+"-"+m+"-"+date+"\n\n"+text);
			}
		}
	}
	public void TeachTopMenu(String id, String pwd) {
		System.out.println("teachTopMenu id > > >  >"+id+", "+pwd);
		add("North",paneTop);
		//logoBtn
		add(Logo);
			
		//paneLabel 간격조정
		GridLayout grid = new GridLayout(0,3);
		paneLabel.setLayout(grid);
		grid.setHgap(10);
		
		//paneLabel 패널에 대입할 라벨
		String topLblStr[] = {"메세지함","내정보","로그아웃"};
		
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
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst = dao.overlapCheck(id);
		this.vo = lst.get(0);
		login = new JLabel(vo.getName()+"님 로그인 완료");
		
		table.removeAll();
		center.removeAll();
		add("Center", center);
		center.setBackground(Color.white);
		center.setLayout(null);
		center.add(tf); center.add(btn); center.add(login); center.add(count);
		center.add(cal); calendar_Tea();
		center.add(btn_list); center.add(btn_new); center.add(lbl_ta); center.add(sp);
		center.add(lbl_); center.add(btn_save); center.add(btn_delete);
		center.add(lbl_2);
		
		
		tf.setBounds(20,20,640,40); btn.setBounds(670,20,80,40);
		login.setBounds(20,70,300,40); count.setBounds(450,70,300,40);
		cal.setBounds(20,120,400,400); cal.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		btn_list.setBounds(520,130,100,40); btn_new.setBounds(650,130,100,40);
		lbl_ta.setBounds(600,190,50,30); sp.setBounds(440,230,330,290);
		lbl_.setBounds(20,540,400,20); lbl_2.setBounds(20,560,400,20);
		btn_save.setBounds(520,540,100,40); btn_delete.setBounds(650,540,100,40);

		btn.setFont(fn); login.setFont(fn); count.setFont(fn); btn_list.setFont(fn);
		btn_new.setFont(fn); lbl_ta.setFont(fnt); lbl_.setFont(fn); btn_save.setFont(fn);
		btn_delete.setFont(fn); lbl_2.setFont(fn);
		
		btn.setBackground(col); btn.addActionListener(this);
		btn_list.setBackground(col); btn_list.addActionListener(this);
		btn_new.setBackground(col); btn_new.addActionListener(this);
		btn_save.setBackground(col); btn_save.addActionListener(this);
		btn_delete.setBackground(col); btn_delete.addActionListener(this);
		
		lbl_.setHorizontalAlignment(JLabel.CENTER);
		lbl_2.setHorizontalAlignment(JLabel.CENTER);
		
		center.add(Down); Down.add(lbl); lbl.setFont(fnt); Down.add(sp2);
		Down.setBounds(20,600,740,250); Down.setBorder(new LineBorder(Color.black, 1));
		Down.setBackground(Color.white); Down.setLayout(null); 
		lbl.setBounds(250,5,300,50); sp2.setBounds(10,60,720,180);
		
		Stu_ClassDAO dao2 = new Stu_ClassDAO();
		List<Stu_ClassVO> lst2 = dao2.teachReservationList(id);
		if(lst2.size()>0) {
			for (int i=0; i<lst2.size(); i++) {
				Stu_ClassVO voClass = lst2.get(i);
				Object obj[] = {voClass.getClass_num(), voClass.getPay_class(), voClass.getClassdate(), voClass.getId()};
				model.addRow(obj);
			}
			setCountLbl(vo.getId()); setTimeLbl_(vo.getId());
		}
		
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			public Dimension getPreferredSize() {
		    Dimension d = super.getPreferredSize();
		    d.height = 50;
		    return d;
			}
		});
		//table = new JTable(model); 720 No 클래스명 예약일자 수강생
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getParent().setBackground(Color.white);
		table.setFont(fn);
		table.setRowHeight(40); 
		table.getTableHeader().setBackground(col);
		table.getTableHeader().setFont(fnt);
		table.getColumn("No").setPreferredWidth(70); table.getColumn("No").setCellRenderer(dtcr);
		table.getColumn("클래스명").setPreferredWidth(300);
		table.getColumn("예약일자").setPreferredWidth(175); table.getColumn("예약일자").setCellRenderer(dtcr);
		table.getColumn("수강생").setPreferredWidth(175); table.getColumn("수강생").setCellRenderer(dtcr);
	}
	String time;
	public void setTimeLbl_(String id) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.teachTime(id);
		Stu_ClassVO vos = lst.get(0);
		
		time = vos.getClassdate()+" "+vos.getClasstime()+":00";
		
		lbl_.setText(vos.getId()+"님이 예약하신 클래스까지");
		
		Thread t1 = new Thread(this);
		t1.start();
	}
	public void run() {
		while(true) {
			try {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd kk:mm:ss");
				String nowStr = f.format(now.getTime());
				Date d1 = f.parse(time);
				Date d2 = f.parse(nowStr);
				int diff=(int)(d2.getTime()-d1.getTime());
				int dd = diff/(1000*60*60*24);
				int hh = (diff%(1000*60*60*24))/(1000*60*60);
				int mm = (diff%(1000*60*60))/(1000*60);
				int ss = (diff%(1000*60))/1000;
				
				lbl_2.setText(Math.abs(dd)+"일"+ Math.abs(hh)+"시간"+Math.abs(mm)+"분"
						+ Math.abs(ss)+"초 남았습니다.");
			} catch(Exception e) {
				e.printStackTrace();
			}
			try { Thread.sleep(1000); }catch(Exception e) {}
		}
	}
	public void setCountLbl(String id) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		int stu = dao.teachCountStu(id);
		count.setText("누적 수강생 수 : "+stu+"명");
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
		ta.setText(y+"-"+m+"-"+d+"\n\n");
		dateLbl.setFont(fnt);
		selectPane.setBackground(new Color(204,222,233));
		
		MemoDAO dao = new MemoDAO();
		List<MemoVO> lst = dao.OutputMemo(ta.getText().substring(0,10), id);
		if (lst.size()!=0) {
			MemoVO vom = lst.get(0);
			ta.setText(y+"-"+m+"-"+d+"\n\n"+vom.getMemo_detail());
		}
		
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
		
		// 월의 1일~마지막일 까지 출력 (+라벨 배경색 바꾸기 위한 작업)
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.boardCalendar(vo.getId());
		for(int day=1; day<=lastDay; day++) {
			JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
			dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
			now.set(y, m-1, day); //날짜별로 요일을 구해서
			int colorWeek = now.get(Calendar.DAY_OF_WEEK);
			for(int i=0; i<lst.size(); i++) {
				BoardVO vob = lst.get(i);
				String date = vob.getClassdate();
				int month = Integer.parseInt(date.substring(5,7));
				if (month==m) {
					int da = Integer.parseInt(date.substring(8));
					if(da>=d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
						dayOfMonthLbl.setOpaque(true);
						dayOfMonthLbl.setBackground(col);
					} else if(da<d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
						dayOfMonthLbl.setOpaque(true);
						dayOfMonthLbl.setBackground(Color.LIGHT_GRAY);
					}
				}
			} 
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
}
