import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Reservation_detail extends JFrameExtends implements ActionListener, MouseListener{
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	
	JPanel upper = new TopMenu_Tea().paneTop;
	JPanel center = new JPanel();
		JPanel cal = new JPanel();
		JPanel classPane = new JPanel();
			JLabel lbl1 = new JLabel("선택한 클래스 : 클래스2");
			JLabel lbl2 = new JLabel("선택한 일자 : 2021년 2월 2일");
			JPanel select = new JPanel(new GridLayout(0,1));
				String time[] = {"13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"}; 
			JButton btn = new JButton("예약하기");
			JPanel table = new JPanel(new BorderLayout());
				JPanel title = new JPanel();
					JLabel li1 = new JLabel("클래스명");
					JLabel li3 = new JLabel("간단한 클래스 소개");
					JLabel li5 = new JLabel("경력사항");
					JLabel li7 = new JLabel("상세지역");
					JLabel li9 = new JLabel("비용(1회)");
					JLabel li11 = new JLabel("강의가능일자");
				JPanel content = new JPanel();
					JLabel li2 = new JLabel("클래스2");
					JLabel li4 = new JLabel("클래스 소개글");
					JLabel li6 = new JLabel("한식조리사자격증");
					JLabel li8 = new JLabel("구로구 오류2동");
					JLabel li10 = new JLabel("70,000원");
					JLabel li12 = new JLabel("2021년 2월 2일");
			JPanel review = new JPanel(new BorderLayout());
				JLabel re1 = new JLabel("후기글");
				JPanel rev = new JPanel(new GridLayout(0,1));
					JLabel re2 = new JLabel("별점 : ★★★★★");
					JLabel re3 = new JLabel("후기글 내용");
		JPanel detail = new JPanel(new BorderLayout());
		JScrollPane sp = new JScrollPane(detail);
		
		Dimension li3_size = new Dimension(150, 600);
		
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

	public Reservation_detail() {
		add("North", upper);
		add("Center", center);
		center.setLayout(null);
		center.add(cal); center.add(classPane); center.add(sp);
		classPane.add(lbl1); classPane.add(lbl2); classPane.add(select); classPane.add(btn);
		classPane.setLayout(null);
		cal.setBounds(20,20,400,400); calendar_Reser();
		classPane.setBounds(430,40,340,360);
		classPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbl1.setFont(fnt); lbl1.setBounds(70,20,300,50);
		lbl2.setFont(fnt); lbl2.setBounds(40,70,300,50);
		btn.setFont(fn2); btn.setBounds(120,300,120,40); btn.setBackground(Color.LIGHT_GRAY);
		select.setFont(fnt); select.setBounds(60,130,250,160);
		for (int t=0; t<time.length; t++) {
			String str = time[t];
			JCheckBox box = new JCheckBox(str);
			box.setFont(fn); box.setHorizontalAlignment(JCheckBox.CENTER);
			select.add(box);
		}
		sp.setBounds(20,450,750,400);
		detail.add("Center",table); detail.add("South", review);
		table.add("West",title); title.setPreferredSize(li3_size); title.setLayout(null); 
		table.add("Center", content); content.setLayout(null);
		title.add(li1);		content.add(li2);		title.add(li3);		content.add(li4);
		title.add(li5);		content.add(li6);		title.add(li7);		content.add(li8);
		title.add(li9);		content.add(li10);		title.add(li11);		content.add(li12);
		li1.setFont(fn); 		li2.setFont(fn);		li3.setFont(fn);		li4.setFont(fn);		
		li5.setFont(fn);		li6.setFont(fn);		li7.setFont(fn);		li8.setFont(fn);
		li9.setFont(fn);		li10.setFont(fn);		li11.setFont(fn);		li12.setFont(fn);
		li1.setBorder(new LineBorder(Color.gray)); li2.setBorder(new LineBorder(Color.gray));
		li3.setBorder(new LineBorder(Color.gray)); li4.setBorder(new LineBorder(Color.gray));
		li5.setBorder(new LineBorder(Color.gray)); li6.setBorder(new LineBorder(Color.gray));
		li7.setBorder(new LineBorder(Color.gray)); li8.setBorder(new LineBorder(Color.gray));
		li9.setBorder(new LineBorder(Color.gray)); li10.setBorder(new LineBorder(Color.gray));
		li11.setBorder(new LineBorder(Color.gray)); li12.setBorder(new LineBorder(Color.gray));
		li1.setBounds(0,0,150,50); li1.setHorizontalAlignment(JLabel.CENTER);
		li3.setBounds(0,50,150,350); li3.setHorizontalAlignment(JLabel.CENTER);
		li5.setBounds(0,400,150,50); li5.setHorizontalAlignment(JLabel.CENTER);
		li7.setBounds(0,450,150,50); li7.setHorizontalAlignment(JLabel.CENTER);
		li9.setBounds(0,500,150,50); li9.setHorizontalAlignment(JLabel.CENTER);
		li11.setBounds(0,550,150,50); li11.setHorizontalAlignment(JLabel.CENTER);
		li2.setBounds(0,0,600,50);
		li4.setBounds(0,50,600,350);
		li6.setBounds(0,400,600,50);
		li8.setBounds(0,450,600,50);
		li10.setBounds(0,500,600,50);
		li12.setBounds(0,550,600,50);
		
        
		review.add("North", re1); re1.setFont(fnt);
		review.add(rev);
		rev.add("North", re2); re2.setFont(fn);
		rev.add(re3); re3.setFont(fn);
		
		setVisible(true);
		
		btn.addActionListener(this);
		
		
	}
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		JLabel lbl = (JLabel)me.getSource();
		int date = Integer.parseInt(lbl.getText());
		lbl2.setText("선택한 일자 : "+y+"년 "+m+"월 "+date+"일");
		
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		if(obj==btn) {
			// 잔액여부에 따른 if문은 추후 추가
			JOptionPane.showMessageDialog(this, "결제가 완료되었습니다.\n결제금액 : 70,000원\n잔액 : 100,100원");
		}
		
	}
	public void calendar_Reser() {
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null);
		
		cal.add(selectPane); selectPane.setBounds(1,1,398,40);
		selectPane.add(dateLbl);
		
		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "+d+"일");
		
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
		new Reservation_detail();

	}

}
/*
 * table.setLayout(grid);
		gbc.fill=GridBagConstraints.BOTH;
gbc.weightx = 1.0;
GridBagLayout grid = new GridBagLayout();
			GridBagConstraints gbc=new GridBagConstraints();
				
			DefaultTableModel model = new DefaultTableModel(data, col);
			JTable tbl = new JTable(model);
			
gbc.weighty = 1.0;
make(li1,0,0,1,1); make(li2,1,0,2,1);
make(li3,0,1,1,1); make(li4,1,1,2,1);
make(li5,0,2,1,1); make(li6,1,2,2,1);
make(li7,0,3,1,1); make(li8,1,3,2,1);
make(li9,0,4,1,1); make(li10,1,4,2,1);
make(li11,0,5,1,1); make(li12,1,5,2,1);
public void make(JLabel c, int x, int y, int w, int h) {
		
		int i = Integer.parseInt(c.getName().substring(2));
		if (i%2==1) {
			gbc.weightx = 1.0;
		} else {
			gbc.weightx = 2.0;
		}
		
		System.out.println(c.getName());
		if(c==li1 || c==li1 || c==li1 || c==li1 || c==li1 || c==li1) {
			
		} else {
			gbc.weightx = 0.2;
		}
		
        
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        grid.setConstraints(c, gbc);
        // GridBagLayout의 GridBagConstraints의 set하는 방법
        
        table.add(c);
	}
*/
