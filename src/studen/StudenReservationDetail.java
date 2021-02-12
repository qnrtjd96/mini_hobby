package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.MoneyDAO;
import dbConnection.MoneyVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class StudenReservationDetail extends JDialog implements ActionListener, MouseListener, ItemListener{
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Color col6 = new Color(204,222,233);
	
	JPanel center = new JPanel();
		JPanel cal = new JPanel();
		JPanel upperClassPane = new JPanel();
			JLabel cate = new JLabel("카테고리 : ");
			JLabel city = new JLabel("지역");
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
				JComboBox<String> box = new JComboBox<String>(model);
		JPanel classPane = new JPanel();
			JLabel lbl1 = new JLabel("선택한 클래스 : ");
			JLabel lbl2 = new JLabel("선택한 일자 : ");
			JPanel select = new JPanel(new GridLayout(7,0));
				JCheckBox check[];
			JButton btn = new JButton("예약하기");
			JPanel table = new JPanel(new BorderLayout());
				JPanel title = new JPanel();
					JLabel li1 = new JLabel("클래스명");
					JLabel li3 = new JLabel("클래스 소개");
					JLabel li5 = new JLabel("경력사항");
					JLabel li7 = new JLabel("상세지역");
					JLabel li9 = new JLabel("비용(1회)");
					JLabel li11 = new JLabel("강의가능시간");
				JPanel content = new JPanel();
				JLabel li2 = new JLabel("선택된 강의가 없습니다");
				JLabel li4 = new JLabel("선택된 강의가 없습니다");
				JLabel li6 = new JLabel("선택된 강의가 없습니다");
				JLabel li8 = new JLabel("선택된 강의가 없습니다");
				JLabel li10 = new JLabel("0");
				JLabel li12 = new JLabel("선택된 강의가 없습니다");
			JPanel review = new JPanel(new BorderLayout());
				JLabel re1 = new JLabel("후기글");
				JPanel rev = new JPanel(new GridLayout(0,1));
					JLabel re2 = new JLabel("별점 : ");
					JLabel re3 = new JLabel("등록된 후기가 없습니다.");
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
			
	String id; int class_num; String classname;
	String time; String category; int costInt;
	String classtime; String stuTime; int rest;

	public StudenReservationDetail() {}
	public StudenReservationDetail(String id, int class_num, String classname) {
		this.id=id;
		this.class_num=class_num;
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.studenInfo(class_num);
		BoardVO vo = lst.get(0);
		time = vo.getClassdate();
		classtime = vo.getClasstime();
		this.classname = vo.getClassname();
		
		mainStart();
		tableSetting();
		
		lbl1.setText("선택한 클래스 : "+this.classname);
		lbl2.setText("선택한 일자 : "+time);
		
		setSize(800,920);
		setLocation(50, 100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	public void mouseClicked(MouseEvent me) {
		JLabel lbl = (JLabel)me.getSource();
		int date = Integer.parseInt(lbl.getText());
		time = y+"-"+m+"-"+date;
		
		tableSetting();
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent ae) {
		//컴포넌트 읽어오기
		Object obj = ae.getSource();
		
		if(obj==btn) {
			System.out.println("classtime="+classtime);
			System.out.println("stuTime="+stuTime);
			if (stuTime.length()>11) {
				JOptionPane.showMessageDialog(this, "시간은 중복선택이 불가능합니다. 다시 확인해주세요");
			} else {
				MoneyDAO dao = new MoneyDAO();
				List<MoneyVO> lst = dao.getMoneyInfo(id);
				classtime.replaceAll(stuTime+",", "");
				if(lst.size()>0) {
					MoneyVO vo = lst.get(0);
					this.rest = vo.getBalance();
					int rest2 = rest-costInt;
					if (rest2>=0) {
						BoardDAO daob = new BoardDAO();
						int resul = daob.updateTime(class_num, classtime);
						if(resul>0) {
							// stu_class 등록
							Stu_ClassDAO daos = new Stu_ClassDAO();
							Stu_ClassVO vos = new Stu_ClassVO(class_num, id, classname, category, costInt, time, classtime);
							int result = daos.insertPay(vos);
							if (result>0) {
								String text = "결제가 완료되었습니다.\n결제금액 : "+costInt+"원\n잔액 : "+rest2+"원";
								JOptionPane.showMessageDialog(this, text);
							}
						}
					} else {
						String text = "충전금액이 부족합니다. 충전 후 예약진행이 가능합니다.";
						JOptionPane.showMessageDialog(this, text);
						this.setVisible(false);
					}
				}
			}
		}
	}
	public void tableSetting() {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.detailTable(classname, time);
		if (lst.size()>0) {
			BoardVO vo = lst.get(0);
			this.class_num = vo.getClass_num();
			box.setSelectedItem(vo.getCity());
			this.category=vo.getCate();
			cate.setText("카테고리 : "+vo.getCate());
			li2.setText(vo.getClassname());
			li4.setText(vo.getIntro());
			li6.setText(vo.getCareer());
			li8.setText(vo.getArea());
			li10.setText(vo.getCost()+"원");
			costInt = vo.getCost();
			li12.setText(vo.getClasstime());
			this.time = vo.getClassdate();
		}
		checkBoxStart();
		
	}
	public void checkBoxStart() {
		// 첫 화면 체크박스 추가
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.detailBoard(class_num);
		select.removeAll();
		if (lst.size()>0) {
			BoardVO vob = lst.get(0);
			classtime = vob.getClasstime();
				// 여러개의 클래스타임이 존재할시 ,로 나눌거임
			StringTokenizer st = new StringTokenizer(classtime, "[,]");
			check = new JCheckBox[st.countTokens()];
			int i=0;
			while (st.hasMoreTokens()) { // 다음꺼 가지고 있으면
				String token = st.nextToken(); // 끌어내 (iterator같은느김인가베)
				if (token.equals("") || token==null) {
						
				} else {
					check[i] = new JCheckBox(token);
					check[i].setFont(fn); check[i].setHorizontalAlignment(JCheckBox.CENTER);
					check[i].setBackground(Color.white);
					select.add(check[i]);
					i++;
				}
			}
		} else {
			JLabel label = new JLabel("해당하는 일자의 정보가 없습니다.");
			select.add(label); label.setFont(fn);
		}
		
	}
	public void mainStart() {
		setBackground(Color.white);
		add("Center", center); center.setBackground(Color.white);
		center.setLayout(null);
		center.add(cal); center.add(classPane); center.add(sp); center.add(upperClassPane);
		upperClassPane.add(cate); upperClassPane.add(city); upperClassPane.add(box); box.setEnabled(false);
		city.setBackground(Color.white);
		String city_str[] = {"서울특별시","경기도","대구광역시","울산광역시","부산광역시","인천광역시","경상남도","경상북도","전라남도","전라북도","충청남도","충청북도","제주도"};
		for (int i=0; i<city_str.length; i++) {
			model.addElement(city_str[i]);
		}
		upperClassPane.setBounds(430,20,340,100); upperClassPane.setLayout(null);
		upperClassPane.setBackground(Color.white);
		cate.setFont(fn); cate.setBounds(40,10,300,30);
		city.setFont(fn); city.setBounds(40,50,50,30);
		box.setFont(fn); box.setBounds(90,50,170,30);
		classPane.add(lbl1); classPane.add(lbl2); classPane.add(select); classPane.add(btn);
		classPane.setLayout(null); classPane.setBackground(Color.white);
		cal.setBounds(20,20,400,400); calendar_Reser(); cal.setBorder(new LineBorder(Color.LIGHT_GRAY));
		classPane.setBounds(430,120,340,300); cal.setBackground(Color.white);
		classPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbl1.setFont(fn2); lbl1.setBounds(10,20,300,30);
		lbl2.setFont(fn2); lbl2.setBounds(40,50,300,30);
		btn.setFont(fn2); btn.setBounds(110,250,120,40); btn.setBackground(col6);
		select.setFont(fnt); select.setBounds(70,90,200,150); select.setBackground(Color.white);
		
		sp.setBounds(20,450,750,400);
		detail.add("Center",table); detail.add("South", review);
		table.add("West",title); title.setPreferredSize(li3_size); title.setLayout(null); 
		table.add("Center", content); content.setLayout(null);
		table.setBackground(Color.white); title.setBackground(Color.white);
		content.setBackground(Color.white);
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
		rev.setBackground(Color.white); review.setBackground(Color.white);
		
		btn.addActionListener(this);
	}
	public void calendar_Reser() {
		//맨위 년도, 월 선택 패널 
		cal.setLayout(null);
		cal.setBackground(Color.white);
		cal.add(selectPane); selectPane.setBounds(1,1,398,40);
		selectPane.add(dateLbl);
		
		y = now.get(Calendar.YEAR);
		m = now.get(Calendar.MONTH)+1;
		d = now.get(Calendar.DAY_OF_MONTH);
		dateLbl.setText(y+"년 "+m+"월 "+d+"일");
		
		dateLbl.setFont(fnt);
		selectPane.setBackground(col6);
		
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
		// 월의 1일~마지막일 까지 출력 + 체크박스 연동
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.detailBoard(class_num);
		for(int day=1; day<=lastDay; day++) {
			JLabel dayOfMonthLbl = new JLabel(Integer.toString(day));
			dayOfMonthLbl.setHorizontalAlignment(SwingConstants.CENTER);
			now.set(y, m-1, day); //날짜별로 요일을 구해서
			int colorWeek = now.get(Calendar.DAY_OF_WEEK);
			if(lst.size()>0) {
				for(int i=0; i<lst.size(); i++) {
					BoardVO vob = lst.get(i);
					String date = vob.getClassdate();
					int month = Integer.parseInt(date.substring(5,7));
					if (month==m) {
						int da = Integer.parseInt(date.substring(8,10));
						if(da>=d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
							dayOfMonthLbl.setOpaque(true);
							dayOfMonthLbl.setBackground(col6);
						} else if(da<d && Integer.parseInt(dayOfMonthLbl.getText())==da) {
							dayOfMonthLbl.setOpaque(true);
							dayOfMonthLbl.setBackground(Color.LIGHT_GRAY);
						}
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
	@Override
	public void itemStateChanged(ItemEvent ie) {
		JCheckBox che = (JCheckBox)ie.getItem();
		if (che.isSelected()) {
			if (stuTime.equals("")) {
				stuTime = che.getText();
			} else {
				JOptionPane.showMessageDialog(this, "예약은 1시간씩만 가능합니다.");
			}
			
		}
	}
}

