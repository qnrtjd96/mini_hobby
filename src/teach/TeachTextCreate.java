package teach;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import dbConnection.Acess_memDAO;
import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.Mem_teacherDAO;
import dbConnection.Mem_teacherVO;

public class TeachTextCreate extends JDialog implements ActionListener, ItemListener, MouseListener{
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	JPanel main = new JPanel(new BorderLayout());
      JPanel mainPane = new JPanel(new BorderLayout());
	  JPanel mainCenterPane = new JPanel(new BorderLayout());
		JPanel  topPane = new JPanel(new BorderLayout());
	    JPanel topPane1 = new JPanel(new GridLayout(0, 2));
	    	JLabel cate = new JLabel("카테고리    ");
	    	JLabel cate2 = new JLabel();	String catee = "";
	    	
	    	JPanel topPane2 = new JPanel(new GridLayout(0, 2, 0, 0));
	    	JLabel dayLabel = new JLabel("날짜");
			JComboBox<String> dayLable = new JComboBox<String>();
			DefaultComboBoxModel<String> dayLabel2Model = new DefaultComboBoxModel<String>();
			    String dayLableStr[]= {"2021/02/16","2021/02/17","2021/02/18","2021/02/19","2021/02/20","2021/02/21","2021/02/22","2021/02/23","2021/02/24","2021/02/25","2021/02/26","2021/02/27","2021/02/28","2021/03/01"};
			JLabel  area = new JLabel("지역");
			JComboBox<String> area2 = new JComboBox<String>();
			DefaultComboBoxModel<String> area2Model = new DefaultComboBoxModel<String>();
			    String area2Str[]= {"경기도","서울특별시","대구광역시","울산광역시","부산광역시","인천광역시","경상남도","경상북도","전라남도","전라북도","충청남도","충청북도","제주도"};

		JPanel formLabelPane = new JPanel(new GridLayout(6, 1));
		    JLabel classname = new JLabel("클래스명");
		    JLabel classdetail = new JLabel("간단한 클래스 소개");
		    JLabel total = new JLabel("경력사항");
		    JLabel detail = new JLabel("상세지역");
		    JLabel  pay = new JLabel("비용(1회당)");
		    JLabel  time = new JLabel("수업시간");
		    
	    JPanel formCenterPane = new JPanel(new GridLayout(6, 1));
		    JTextArea classname2 = new JTextArea("EX)재미난 기타교실");
		    JTextArea classdetail2 = new JTextArea("EX)초급부터 고급까지 모두 수업가능합니다 ^^");
		    JTextArea total2 = new JTextArea("EX)네이버밴드 경력10년, 신촌밴드생활 7년");
		    JTextArea detail2 = new JTextArea("EX)서강대 바로 옆건물 2층");
		    JTextArea pay2 = new JTextArea("EX)30000");
		    
		    JPanel time2 = new JPanel(new GridLayout(4, 3));
	    		String classTime[] = {"09:00~10:00","10:00~11:00","11:00~12:00",
	    							   "12:00~13:00","13:00~14:00","14:00~15:00",
	    							   "15:00~16:00","16:00~17:00","17:00~18:00",
	    							   "18:00~18:00","20:00~21:00","21:00~22:00",};
	    		
	    JPanel mainBottomPane = new JPanel(new BorderLayout());
	    	JPanel buttonPane = new JPanel();
			     JButton updateBtn = new JButton("등록");
	
	//콤보박스,박스선택에 대한 데이터
	String id;
	String date;		     
	String dbarea;
	String TeaClasstime[] = new String[12];
	JCheckBox box[] = new JCheckBox[12]; //checkbox 선언
	String classtime, classtime2;
	public TeachTextCreate() {}
	public TeachTextCreate(String id) {
		this.id= id; //받아온 매개변수 멤버변수에 세팅
		
		//폰트넣기 + 백그라운드흰색으로 변경////////////////////////////////////
		dayLabel.setFont(fntBold15);
		cate.setFont(fntBold15);  area.setFont(fntBold15); cate2.setFont(fntBold15); 
		classname.setFont(fntBold20);classdetail.setFont(fntBold20);total.setFont(fntBold20);
		detail.setFont(fntBold20);pay.setFont(fntBold20);time.setFont(fntBold20);updateBtn.setFont(fntBold15);
		classname2.setFont(fntBold20); classdetail2.setFont(fntBold20); total2.setFont(fntBold20); detail2.setFont(fntBold20); pay2.setFont(fntBold20);
		mainCenterPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		topPane1.setBackground(Color.WHITE); topPane2.setBackground(Color.WHITE);
		formLabelPane.setBackground(Color.WHITE); formCenterPane.setBackground(Color.WHITE);
		mainBottomPane.setBackground(Color.WHITE); buttonPane.setBackground(Color.WHITE); 
		area2.setBackground(Color.WHITE); main.setBackground(Color.WHITE); dayLabel.setBackground(Color.WHITE);dayLable.setBackground(Color.white);
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		//상단패널
		for(String Name : area2Str) {
			area2Model.addElement(Name);
		}
		area2.setModel(area2Model);
		area2.setSelectedItem(area); //이거바꿔줘야함
		
		//상단패널
		for(String Name : dayLableStr) {
			dayLabel2Model.addElement(Name);
		}
		dayLable.setModel(dayLabel2Model);
		dayLable.setSelectedItem(dayLabel); //이거바꿔줘야함
		
		topPane1.add(cate); topPane1.add(cate2);
        topPane.add("West", topPane1);
        
        topPane2.add(dayLabel); topPane2.add(dayLable);
        topPane2.add(area); topPane2.add(area2);
        topPane.add("East", topPane2);
        mainCenterPane.add("North", topPane);
        
        //센터패널
        for(int i=0; i<classTime.length; i++ ) {
        	box[i] = new JCheckBox(classTime[i]);
        	box[i].setFont(fntPlain15);
        	box[i].setBackground(Color.white);
        	box[i].addItemListener(this); //체크박스이벤트 등록함
        	time2.add(box[i]);
        }
        
        classname.setBorder(new LineBorder(Color.black, 1));	classname2.setBorder(new LineBorder(Color.black, 1));
        classdetail.setBorder(new LineBorder(Color.black, 1));	classdetail2.setBorder(new LineBorder(Color.black, 1));
        total.setBorder(new LineBorder(Color.black, 1));		total2.setBorder(new LineBorder(Color.black, 1));
        detail.setBorder(new LineBorder(Color.black, 1));		detail2.setBorder(new LineBorder(Color.black, 1));
        pay.setBorder(new LineBorder(Color.black, 1));			pay2.setBorder(new LineBorder(Color.black, 1));
        time.setBorder(new LineBorder(Color.black, 1));			time2.setBorder(new LineBorder(Color.black, 1));
        
        formLabelPane.add(classname);	formCenterPane.add(classname2);
        formLabelPane.add(classdetail);	formCenterPane.add(classdetail2);
        formLabelPane.add(total);		formCenterPane.add(total2);
        formLabelPane.add(detail);		formCenterPane.add(detail2);
        formLabelPane.add(pay);			formCenterPane.add(pay2);
        formLabelPane.add(time);		formCenterPane.add(time2);
        mainCenterPane.add("West", formLabelPane);
        mainCenterPane.add("Center", formCenterPane);
        mainCenterPane.setBorder(new LineBorder(Color.black, 1));
        
        //남쪽패널
        buttonPane.add(updateBtn);
    
        mainBottomPane.add("East", buttonPane);
        mainCenterPane.add("South", mainBottomPane);
        
	    //////////패널추가
	    mainCenterPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    mainPane.add(mainCenterPane);
	    mainPane.setBorder(new LineBorder(Color.black, 1));
	    main.add(mainPane);
	      
	    add("Center",main);
	    main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setVisible(true);
		
		getTeaInfo(id);
		
		//이벤트등록
		area2.addItemListener(this); //상세지역 이벤트
		dayLable.addItemListener(this); //날짜 이벤트
		updateBtn.addActionListener(this); //등록버튼 
		classname2.addMouseListener(this);
	    classdetail2.addMouseListener(this);
	    total2.addMouseListener(this);
	    detail2.addMouseListener(this);
	    pay2.addMouseListener(this);
		
		setSize(800,920);
		setLocation(50, 100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	//회원정보 세팅
	public void getTeaInfo(String id) { //회원정보 세팅

		Mem_teacherDAO dao = new Mem_teacherDAO();
		List<Mem_teacherVO> searchId = dao.getTeaInfo(id);
		
		if(searchId.size()==0 ) {
			System.out.println("아이디를 매치를 못함...");
		}else {
			Mem_teacherVO vo = searchId.get(0);
			
			if(vo.getId().equals(id)) {
				catee = vo.getCate();			cate2.setText(catee);
			}	
		}
		
		
	}
	//회원등록
	public void getMember() {
		//폼의 데이터를 VO에 셋팅
		//아이디, 클래스명,  카테고리, 지역, 쓴날자, 강의시간
		int cost = Integer.parseInt(pay2.getText());
		//						아이디	클래스명				분야				지역		가격		간단한클래스소개			경력				상세지역			  원하는날짜
		// DB기준				 id		classname			cate			city	cost   	 intro				   career		     area			  classtime
		BoardVO vo = new BoardVO(id, classname2.getText(), cate2.getText(), dbarea, cost, classdetail2.getText(),total2.getText(), detail2.getText(), date, classtime2);
		//이름과 연락처가 있을때만 데이터베이스 작업하기
		if(classname2.getText().equals("") || pay2.getText().equals("") || classdetail2.getText().equals("") || total2.getText().equals("") || detail2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "모든부분을 입력하셔야 합니다..");
		}else{
			BoardDAO dao = new BoardDAO();
			int result = dao.insertTeaBoard(vo);
			if(result>0) {//회원등록됨
				JOptionPane.showMessageDialog(this,"글이 등록되었습니다.");
				for(int i=0; i<TeaClasstime.length; i++) {
					TeaClasstime[i] = null;
			   	  }
				
				classtime2 = ""; 
				dispose();
			}else {//회원등록실패함
				JOptionPane.showMessageDialog(this,"클래스명이 너무 길어서 글등록에 실패했습니다.");
			}
		}
		
	}
		
	@Override
	public void itemStateChanged(ItemEvent e) {
		dbarea = (String)area2.getSelectedItem();
		date = (String)dayLable.getSelectedItem();
		
		if (e.getSource() == box[1]) {
		   if(e.getStateChange() == ItemEvent.SELECTED) {// 선택했을때
			   System.out.println("1번 눌름");
			   TeaClasstime[1] = box[1].getText()+ ",";
		   }else{// 해제했을때
			   TeaClasstime[1] = " ";
		   }
	   }else if (e.getSource() == box[2]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("2번 눌름");
			   TeaClasstime[2] = box[2].getText()+ ",";
		   }else{
			   TeaClasstime[2] = " ";
		   }
	   }else if (e.getSource() == box[3]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("3번 눌름");
			   TeaClasstime[3] = box[3].getText()+ ",";
		   }else{
			   TeaClasstime[3] = " ";
			   System.out.println("dd");
		   }
	   }else if (e.getSource() == box[4]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("4번 눌름");
			   TeaClasstime[4] = box[4].getText()+ ",";
		   }else{
			   TeaClasstime[4] = " ";
		   }
	   }else if (e.getSource() == box[5]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("5번 눌름");
			   TeaClasstime[5] = box[5].getText()+ ",";
		   }else{
			   TeaClasstime[5] = " ";
		   }
	   }else if(e.getSource() == box[6]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("6번 눌름");
			   TeaClasstime[6] = box[6].getText()+ ",";
		   }else{
			   TeaClasstime[6] = " ";
		   }
	   }else if (e.getSource() == box[7]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("7번 눌름");
			   TeaClasstime[7] = box[7].getText()+ ",";
		   }else{
			   TeaClasstime[7] = " ";
		   }
	   }else if(e.getSource() == box[8]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("8번 눌름");
			   TeaClasstime[8] = box[8].getText()+ ",";
		   }else{
			   TeaClasstime[8] = " ";
		   }
	   }else if (e.getSource() == box[9]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("9번 눌름");
			   TeaClasstime[9] = box[9].getText()+ ",";
		   }else{
			   TeaClasstime[9] = " ";
		   }
	   }else if(e.getSource() == box[10]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("10번 눌름");
			   TeaClasstime[10] = box[10].getText()+ ",";
		   }else{
			   TeaClasstime[10] = " ";
		   }
	   }else if(e.getSource() == box[11]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("11번 눌름");
			   TeaClasstime[11] = box[11].getText()+ ",";
		   }else{
			   TeaClasstime[11] = " ";
		   }
	   }else if(e.getSource() == box[0]){
		   if(e.getStateChange() == ItemEvent.SELECTED) {
			   System.out.println("0번 눌름");
			   TeaClasstime[0] = box[0].getText()+ ",";
		   }else{
			   TeaClasstime[0] = " ";
		   }
	   }
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("등록")) { 
			for(int i=0; i<TeaClasstime.length; i++) {
				classtime += TeaClasstime[i];
				classtime2 = classtime.replace("null", "");
		   	  }		
		System.out.println("classtime2 = " + classtime2);
		getMember();
		}
	}
	
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(id);
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if(obj==classname2) {
			classname2.setText("");
		} else if(obj==classdetail2) {
			classdetail2.setText("");
		} else if(obj==total2) {
			total2.setText("");
		} else if(obj==detail2) {
			detail2.setText("");
		} else if(obj==pay2) {
			pay2.setText("");
		} 
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
