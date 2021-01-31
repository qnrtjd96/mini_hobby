
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Teacher_Calendar extends JPanel implements ActionListener, MouseListener{
	int day=1;
	//맨위 년도, 월 선택 패널 
	JPanel selectPane = new JPanel();
		JLabel dateLbl = new JLabel();
	// 달력 패널 
	JPanel calPane = new JPanel(); 
		JPanel dayPane = new JPanel(new GridLayout(1,7,45,40)); //일 ~월 글자출력 
		String days[] = {"일", "월", "화", "수", "목", "금", "토"}; 
		JPanel datePane = new JPanel( new GridLayout(0,7,43,25)); // 1~31 날짜 출력 
	//폰트 설정 
	Font fn = new Font("맑은고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은고딕", Font.BOLD, 20);
	//캘린더 받아오기
	Calendar now = Calendar.getInstance(); //날짜 받아오기
	int nowYear, nowMonth;
	int week, lastDay, lastDate;
	
	public Teacher_Calendar(){
		//super("Calendar");
		setLayout(null);
		
		add(selectPane); selectPane.setBounds(1,1,398,40);
		selectPane.add(dateLbl);
		nowYear = now.get(Calendar.YEAR);
		nowMonth = now.get(Calendar.MONTH)+1;
		day = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(nowYear+"년 "+nowMonth+"월 "+day+"일");
		dateLbl.setFont(fnt);
		selectPane.setBackground(new Color(121,191,192));
		
		add(calPane); calPane.setBounds(5,40,390,350);
		calPane.setLayout(null);
		calPane.add(dayPane); calPane.add(datePane);
		dayPane.setBounds(5,0,370,50);
		datePane.setBounds(5,50,370,300);
		
		calendarSetting(nowYear,nowMonth);
		
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
			dayOfWeekLbl.setFont(fn);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		JLabel lbl = (JLabel)me.getSource();
		int date = Integer.parseInt(lbl.getText());
		JOptionPane.showMessageDialog(this, date+"일 누른거 맞지?");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object Obj = e.getSource();
	}
	// 달력 출력 
	public void calendarSetting(int y, int m) {
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
			dayOfMonthLbl.setFont(fn);
			dayOfMonthLbl.addMouseListener(this);
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
