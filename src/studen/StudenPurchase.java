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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.MemberDAO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

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
	
/*	//달력 표시 
	JLabel ture1 = new JLabel("참여예정"); 
	JLabel false1 = new JLabel("참여완료");
	JLabel red = new JLabel("■");
	JLabel blue = new JLabel("■");
*/		
	//수강 예정 테이블, 전체 구매 테이블
	DefaultTableModel dueModel;
	JTable dueTable;		JScrollPane dueSp;
	String dueCol[] = {"번호", "클래스명", "예약일", "예약시간", "장소"};
	Object dueData[][] = new Object[0][dueCol.length];

	DefaultTableModel allModel;
	JTable allPurchase;		JScrollPane allSp;
	
	String allCol[] = {"번호", "클래스명", "강사", "예약일", "장소"};
	Object allData[][] = new Object[0][allCol.length];
	
	JLabel dueTitle = new JLabel("수강예정목록");
	JLabel allTitle = new JLabel("전체구매목록");
	
	int r, c;
	int allr, allc;
	
	JButton rebookBtn = new JButton("예약변경");
	JButton cancelBtn = new JButton("예약취소");
	JButton writeReview = new JButton("리뷰작성");
	
	Font fnt = new Font("맑은 고딕", Font.BOLD, 22);
	Font fnt2 = new Font("맑은 고딕", Font.PLAIN, 15);	
	
	Font btnFnt = new Font("맑은 고딕", Font.BOLD, 12);
	Font headFnt = new Font("맑은 고딕", Font.BOLD, 15);
	
	Color col6 = new Color(204,222,233);
	Color col = new Color(204,222,233);
	
	String idStr;
	int classNum;
	String changeClass;
	
	public void StudenPurchase() {	}
	 
	public StudenPurchase(String idStr) {
		this.idStr = idStr;
		
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
		dueTable.setBackground(Color.white);
		dueTable.getColumn("번호").setPreferredWidth(30);
		dueTable.getColumn("클래스명").setPreferredWidth(120);
		dueTable.getColumn("예약일").setPreferredWidth(40);
		dueTable.getColumn("예약시간").setPreferredWidth(40);
		dueTable.getColumn("장소").setPreferredWidth(40);
		dueTable.getTableHeader().setBackground(col6);	dueTable.getTableHeader().setFont(headFnt);
		dueSp = new JScrollPane(dueTable);		dueSp.setSize(300, 200);
		dueSp.setBounds(25, 390, 520, 140);		add(dueSp);
		
		//버튼 넣기.. 달력 크기 조절하고 다시 셋바운드로 맞추기
		rebookBtn.setFont(btnFnt);				rebookBtn.setBackground(col6);	
		rebookBtn.setBounds(370,530, 85,30);		add(rebookBtn);
		cancelBtn.setFont(btnFnt);				rebookBtn.setBackground(col6);
		cancelBtn.setBounds(460,530, 85,30);		add(cancelBtn);
		writeReview.setFont(btnFnt);			writeReview.setBackground(col6);
		writeReview.setBounds(460,720, 85,30);		add(writeReview);
		//전체 구매 클래스
		allModel = new DefaultTableModel(allCol, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		allPurchase = new JTable(allModel);
		allPurchase.setBackground(Color.white);
		allPurchase.getTableHeader().setReorderingAllowed(false);
		allPurchase.getColumn("번호").setPreferredWidth(30);
		allPurchase.getColumn("클래스명").setPreferredWidth(120);
		allPurchase.getColumn("강사").setPreferredWidth(40);
		allPurchase.getColumn("예약일").setPreferredWidth(40);
		allPurchase.getColumn("장소").setPreferredWidth(40);
		allPurchase.getTableHeader().setBackground(col6);	allPurchase.getTableHeader().setFont(headFnt);
		allSp = new JScrollPane(allPurchase);
		allSp.setBounds(25, 580, 520, 140);		add(allSp);
		
		dueTitle.setBackground(Color.white);	dueTitle.setFont(fntBold15);
		allTitle.setBackground(Color.white);	allTitle.setFont(fntBold15);
		dueTitle.setBounds(30,360, 100,30);	add(dueTitle);
		allTitle.setBounds(30,550, 100,30);	add(allTitle);
		
		//테이블 이벤트 
		dueTable.addMouseListener(this);
		allPurchase.addMouseListener(this);
		
		//이벤트 구현 필요 ...rebookBtn > JDialog, cancelBtn > db에서 지우기
		rebookBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		writeReview.addActionListener(this);
		
		setDuePurchase(idStr);
		setAllPurchase(idStr);
		
	}
	
	
	//학생 전체 내역 띄우기
	public void setAllPurchase(String idStr) {
		System.out.println("구매내역 아이디 받아오는지 확인 >> "+idStr);
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> allLst = dao.showAllPurchase(idStr);
		if(allLst.size()==0) {
			System.out.println("set DuePurchase erro... > 예약 중인 클래스를 찾지 못함");
		}else {
			Stu_ClassVO vo = allLst.get(0);
			System.out.println("vo.getID > > > "+vo.getId());
			if(vo.getId().equals(idStr)) {
				for(int i=0; i<allLst.size(); i++) {
					vo = allLst.get(i);
					
					Object[] dueClassObj = {
							i+1,				vo.getPay_class(),		vo.getTeach_id(),
							vo.getClassdate(), 	vo.getArea(),
					};
					
					allModel.addRow(dueClassObj);
				}
				
			}	
		}
			
	}
	
	//학생 예약 내역 띄우기
	public void setDuePurchase(String idStr) {
		System.out.println("구매내역 아이디 받아오는지 확인 >> "+idStr);
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> dueLst = dao.showDuePurchase(idStr);
		
		if(dueLst.size()==0) {
			System.out.println("set DuePurchase erro... > 예약 중인 클래스를 찾지 못함");
		}else {
			Stu_ClassVO vo = dueLst.get(0);
			System.out.println("vo에서 아이디 받아옴 ...? "+vo);
			System.out.println("vo.getID > > > "+vo.getId());
			if(vo.getId().equals(idStr)) {
				for(int i=0; i<dueLst.size(); i++) {
					vo = dueLst.get(i);

					Object[] dueClassObj = {
							i+1,
							vo.getPay_class(), vo.getClassdate(), 
							vo.getClasstime(), vo.getArea()
					};
					dueModel.addRow(dueClassObj);
				}
			}	
		}
			
	}	
	@Override	//예약날짜별 예약현황 보여주기..
	public void mousePressed(MouseEvent e) {
/*
		JLabel lbl = (JLabel)e.getSource();
		System.out.println("날짜 클릭하면 ? > > > "+lbl);
		
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.showClickPurchase(idStr);
		
		if(lst.size()==0) {
			System.out.println("set Click Purchase erro... > 예약 중인 클래스를 찾지 못함");
		}else {
			Stu_ClassVO vo = lst.get(0);
			//테이블 비워주기..
			DefaultTableModel defaultTable = (DefaultTableModel)dueTable.getModel();
			defaultTable.setNumRows(0);
			System.out.println("vo.getID > > > "+vo.getId());
			if(vo.getId().equals(idStr)) {
				//해당날짜만 안나옴 ㅠㅠ 
				for(int i=0; i<lst.size(); i++) {
					vo = lst.get(i);
					
					Object[] dueClassObj = {
							i+1,
							vo.getPay_class(), vo.getClassdate(), 
							vo.getClasstime(), vo.getArea()
					};
					
					dueModel.addRow(dueClassObj);
				}
				
			}	
		}
*/		
	}
	
	//날짜 클릭하면 테이블에 예약내역 보여주기
	@Override
	public void mouseClicked(MouseEvent me) {
		//예약예정 테이블 값 받아오기
		r = dueTable.getSelectedRow();
		c =dueTable.getSelectedColumn();
		//전체 구매목록 값 받아오기
		allr = allPurchase.getSelectedRow();
		allc = allPurchase.getSelectedColumn();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		Stu_ClassVO vo = new Stu_ClassVO();

		if(obj==rebookBtn) {
			System.out.println("예약변경 누름");
			changeClass = (String)dueTable.getValueAt(r, 1);//시간바꾸고싶은 클래스 이름 받아옴
			System.out.println("예약변경 원하는 수업의 제목 뽑아오기 .... "+changeClass);
				
			//바꾸고 싶은 클래스의 글번호 뽑아오기 
			Stu_ClassDAO dao = new Stu_ClassDAO();
			List<Stu_ClassVO> lst = dao.getChangeClass(changeClass, idStr);
			vo = lst.get(0);
			vo.getClass_num();
				
			System.out.println(" vo실행 >  > >  >  > "+vo.getClass_num());
			new duePurchaseDialog(vo.getClass_num(), idStr);
			//강산이 예약변경 안뜸 ㅠㅠ? 
		}else if(obj==cancelBtn) {
			System.out.println("예약취소 누름");
			int result = 0;
			String sendDelStr = (String) dueTable.getValueAt(r, 1);
			Stu_ClassDAO dao = new Stu_ClassDAO(); 
			result = dao.deletStuClass(idStr, sendDelStr);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "예약이 취소되었습니다.");
				dueModel.setRowCount(0);
				setDuePurchase(idStr);
			}else {
				JOptionPane.showMessageDialog(this, "예약취소가 실패되었습니다.");
			}
		}else if(obj==writeReview) {
			System.out.println("리뷰작성 누름");
			//for(int i=0; i<dueTable.getRowCount(); i++) { < < < 테이블 값 다 받아옴
				changeClass = (String)allPurchase.getValueAt(allr, 1);
				System.out.println("리뷰작성수업의 제목 ?... "+changeClass);
				//리뷰 쓰려고하는 글번호 뽑아오기 !
				Stu_ClassDAO dao = new Stu_ClassDAO();
				List<Stu_ClassVO> lst = dao.getChangeClass(changeClass, idStr);
				vo = lst.get(0);
				vo.getClass_num();
				System.out.println(" - - - - - - 리뷰 쓰려는 글 번호? "+vo.getClass_num());
				new writeReviewDialog(vo.getClass_num(), idStr);
			//}
		}
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
	//색깔변경 추가
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
		// 월의 1일~마지막일 까지 출력 (+라벨 배경색 바꾸기 위한 작업)
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.StuCalendar(idStr);
			for(int day=1; day<=lastDay; day++) {
				JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
				dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
				now.set(y, m-1, day); //날짜별로 요일을 구해서
				int colorWeek = now.get(Calendar.DAY_OF_WEEK);
				/*여기는 sysout찍으면 나옴*/
				for(int i=0; i<lst.size(); i++) {
					Stu_ClassVO vo1 = lst.get(i);
					String date = vo1.getClassdate();
					int month = Integer.parseInt(date.substring(5,7));
					if (month==m) {
						int da = Integer.parseInt(date.substring(8));
						if(da>=d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
							dayOfMonthLbl.setOpaque(true);
							dayOfMonthLbl.setBackground(col);
						} else if(da<d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
							dayOfMonthLbl.setOpaque(true);
							dayOfMonthLbl.setBackground(Color.RED);
						}
					}
				} 
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
	public void mouseReleased(MouseEvent me) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(idStr);
			System.exit(0);
		}
	}
}
