package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StudenPurchase extends JPanel implements ActionListener, MouseListener {
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JLabel spTitle = new JLabel("나의 예약내역");
	
	JPanel cal = new JPanel();
	
	// 달력에 대한 부분
	Calendar now = Calendar.getInstance();
	int y,m,d;
	JPanel selectPane = new JPanel();
	JLabel dateLbl = new JLabel();	//년, 월 보이는 부분
	// 달력 패널 
	JPanel calPane = new JPanel(); 	//요일, 일자 보이는 부분
		JPanel dayPane = new JPanel(new GridLayout(1,7)); //요일 글자출력 
		String days[] = {"일", "월", "화", "수", "목", "금", "토"}; 
		JPanel datePane = new JPanel( new GridLayout(0,7)); //날짜 출력 
	
	//수강 예정 테이블, 전체 구매 테이블
	
	DefaultTableModel dueModel;
	JTable dueTable;		JScrollPane dueSp;
	String dueCol[] = {"번호", "클래스명", "예약일", "예약시간", "장소"};
	Object dueData[][] = new Object[0][dueCol.length];

	DefaultTableModel allModel;
	JTable allPurchase;		JScrollPane allSp;
	
	String allCol[] = {"번호", "클래스명", "예약일", "장소", "리뷰작성"};
	Object allData[][] = new Object[0][allCol.length];
	
	JButton rebookBtn = new JButton("예약변경");
	JButton cancelBtn = new JButton("예약취소");
	JButton writeReview = new JButton("리뷰작성");
	
	Font fnt = new Font("맑은 고딕", Font.BOLD, 22);
	Font fnt2 = new Font("맑은 고딕", Font.PLAIN, 15);	
	
	Font btnFnt = new Font("맑은 고딕", Font.BOLD, 12);
	Font headFnt = new Font("맑은 고딕", Font.BOLD, 15);
	
	Color col6 = new Color(204,222,233);
	
	public StudenPurchase() {
		
		setBorder(new LineBorder(Color.black, 1));
		setBackground(Color.white);
		setLayout(null);
		spTitle.setFont(fnt);	spTitle.setBounds(15, 15, 150, 20);	add(spTitle);
		
		//달력 크기 조절....
		cal.add(calPane);
		cal.setFont(fnt2);	cal.setBounds(100, 45, 400, 300);	add(cal);
		cal.setBorder(new LineBorder(Color.black,1));
		calendarStu();
		
		//예약 중인 클래스
		
		dueModel = new DefaultTableModel(dueCol, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		dueTable = new JTable(dueModel);
		dueTable.getColumn("번호").setPreferredWidth(30);
		dueTable.getColumn("클래스명").setPreferredWidth(120);
		dueTable.getColumn("예약일").setPreferredWidth(40);
		dueTable.getColumn("예약시간").setPreferredWidth(40);
		dueTable.getColumn("장소").setPreferredWidth(40);
		dueTable.getTableHeader().setBackground(col6);	dueTable.getTableHeader().setFont(headFnt);
		dueSp = new JScrollPane(dueTable);		//dueSp.setSize(300, 200);
		dueSp.setBounds(25, 360, 520, 140);		add(dueSp);
		
		//버튼 넣기.. 달력 크기 조절하고 다시 셋바운드로 맞추기
		rebookBtn.setFont(btnFnt);				rebookBtn.setBackground(col6);	
		rebookBtn.setBounds(410,500, 70,30);		add(rebookBtn);
		cancelBtn.setFont(btnFnt);				rebookBtn.setBackground(col6);
		cancelBtn.setBounds(475,500, 70,30);		add(cancelBtn);
		
		//전체 구매 클래스
		allModel = new DefaultTableModel(allCol, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		allPurchase = new JTable(allModel);
		allPurchase = new JTable(allModel);
		allPurchase.getTableHeader().setReorderingAllowed(false);
		allPurchase.getColumn("번호").setPreferredWidth(30);
		allPurchase.getColumn("클래스명").setPreferredWidth(120);
		allPurchase.getColumn("예약일").setPreferredWidth(40);
		allPurchase.getColumn("장소").setPreferredWidth(40);
		allPurchase.getColumn("리뷰작성").setPreferredWidth(40);
		allPurchase.getTableHeader().setBackground(col6);	allPurchase.getTableHeader().setFont(headFnt);
		allSp = new JScrollPane(allPurchase);
		allSp.setBounds(25, 530, 520, 220);		add(allSp);
		
		rebookBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==rebookBtn) {
			System.out.println("예약변경 누름");
			//다이얼로그
			//Dialog log = new Dialog(this);
			
			//JDialog log = new JDialog();
			//log.setTitle("테스트");
			//dialStart newDial = new dialStart(new StudenTopMenu()); //클래스 하단에 클래스 만들어주기
		}else if(obj==cancelBtn) {
			System.out.println("예약취소 누름");
			//다이얼로그 ...날짜계산필요 (일주일?3일전에 변경안됨)
			int cancelCho = JOptionPane.showConfirmDialog(dueSp, "수업을 취소하시겠습니까?", "예약취소",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
			if(cancelCho==1) {
				//디비 연결해서 예약 취소하기 ..
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		
	}
	
	public void calendarStu() {
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null);
		
		cal.add(selectPane); selectPane.setBounds(0,0,400,35);
		selectPane.setBorder(new LineBorder(Color.black,1));	selectPane.add(dateLbl);
		
		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "); //+d+"일");
		dateLbl.setFont(fntBold20);
		selectPane.setBackground(col6);
		 																		//top left bottom right
		cal.add(calPane); calPane.setBounds(5,30,390,255); //calPane.setBorder(BorderFactory.createEmptyBorder(0,))
		calPane.setLayout(null);
		calPane.add(dayPane); calPane.add(datePane);
		dayPane.setBounds(3,0,390,40);
		datePane.setBounds(3,30,390,240);
		
		dateSetting(y,m);
		
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
			dayOfWeekLbl.setFont(fntPlain15);
		}
	}
	
	public void dateSetting(int y, int m) {
		int week, lastDay;
		//선택된 년도, 월의 1일로 세팅 
		now.set(y, m-1, 1); 
		//1일의 요일 구하기 
		week = now.get(Calendar.DAY_OF_WEEK); //그 달의 1일 요일
		//마지막날이 몇일인지 구하기 
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
			dayOfMonthLbl.addMouseListener(this);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent me) {}
	
}



//다이얼로그 클래스 ... 프레임 상속이 필요하다..


