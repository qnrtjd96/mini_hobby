import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TeacherMain extends JFrameExtends implements ActionListener, MouseListener{
	Font fn = new Font("맑은고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	
	TeachTopMenu tt = new TeachTopMenu();
	JPanel upper = tt.paneTop;
	
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
		JPanel Down = new JPanel();
			JLabel lbl = new JLabel("예약된 수강 예정 클래스");
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

	public TeacherMain() {
		tt.setVisible(false);
		add("North", upper);
		upper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		add("Center", center);
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
		
		btn.setBackground(Color.LIGHT_GRAY); btn.addActionListener(this);
		btn_list.setBackground(Color.LIGHT_GRAY); btn_list.addActionListener(this);
		btn_new.setBackground(Color.LIGHT_GRAY); btn_new.addActionListener(this);
		btn_save.setBackground(Color.LIGHT_GRAY); btn_save.addActionListener(this);
		btn_delete.setBackground(Color.LIGHT_GRAY); btn_delete.addActionListener(this);
		
		lbl_.setHorizontalAlignment(JLabel.CENTER); lbl_2.setHorizontalAlignment(JLabel.CENTER);
		
		center.add(Down); Down.add(lbl); lbl.setFont(fn);
		Down.setBounds(20,600,740,250); Down.setBorder(new LineBorder(Color.black, 1));
		
		setSize(800,1000);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		JLabel lbl = (JLabel)me.getSource();
		int date = Integer.parseInt(lbl.getText());
		ta.setText(y+"년 "+m+"월 "+date+"일\n\n");
		
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		if(obj==btn) {
			JOptionPane.showMessageDialog(this, "검색버튼 누름!");
		} else if(obj==btn_list) {
			JOptionPane.showMessageDialog(this, "내글목록으로 넘어갑니다");
		} else if(obj==btn_new) {
			JOptionPane.showMessageDialog(this, "새글쓰기로 넘어갑니다");
		} else if(obj==btn_save) {
			JOptionPane.showMessageDialog(this, "저장되었습니다");
		} else if(obj==btn_delete) {
			ta.setText(y+"년 "+m+"월 "+d+"일\n\n");
		}
		
	}
	
	public void calendar_Tea() {
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null);
		
		cal.add(selectPane); selectPane.setBounds(1,1,398,40);
		selectPane.add(dateLbl);
		
		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "+d+"일");
		ta.setText(y+"년 "+m+"월 "+d+"일\n\n");
		dateLbl.setFont(fnt);
		selectPane.setBackground(new Color(121,191,192));
		
		cal.add(calPane); calPane.setBounds(5,40,390,350);
		calPane.setLayout(null);
		calPane.add(dayPane); calPane.add(datePane);
		dayPane.setBounds(5,0,370,50);
		datePane.setBounds(5,50,370,300);
		
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
		}
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
		new TeacherMain();

	}

}
