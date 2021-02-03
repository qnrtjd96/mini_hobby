import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminMenu extends JFrameExtends {	//마우스 액션이 굳이 필요한지 모르겠음, 하다가 필요하면 삽입하기
	//상위 패널 설정
	JPanel upper = new StudenTopMenu().paneTop;
	
	//탭 별로 임시로 패널 설정해둠 > 패널에 테이블을 삽입하면 될 듯함..
	JTabbedPane adminTab = new JTabbedPane();
		JPanel memList = new JPanel();	// 회원목록 + 검색기능
			//JTable memTbl = new JTable();
		JPanel payAdmin = new JPanel();	// 결제관리 > 회원누르면 JDialog pop-up > 결제취소 
		JPanel inactiveId = new JPanel();	// 그냥 테이블만 > 마지막 로그인 기점으로 n개 지난 user sorting
		JPanel blacklist = new JPanel();	// userID로 리스트에 추가, 해제버튼, 리스트에 있는 회원 로그인 시 > JOptionpane  
		
	Font TabFnt = new Font("돋움체", Font.BOLD, 18);
	
	public AdminMenu() {
		setLayout(new BorderLayout());
		upper.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		adminTab.addTab("회원목록", memList); //	memList.add(memTbl);
		adminTab.addTab("결제관리", payAdmin);
		adminTab.addTab("휴면계정", inactiveId);
		adminTab.addTab("블랙리스트", blacklist);
		
		adminTab.setFont(TabFnt);
		add(BorderLayout.NORTH, upper);
		adminTab.setBackground(Color.white);
		adminTab.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		add("Center", adminTab);
		
		//CustomTabbedPaneUI 만들기
	}
	
	
	// 메뉴별 메소드 임의로 만듬 > 클래스 따로 만들어서 객체로 불러오는 방식으로 해도 될 듯?
	public void memberList() {
		
	}
	
	public void paymentList() {
	
	}
	
	public void inactiveMemList() {
		
	}
	
	public void blacklistMem() {
		
	}
	
	
	
	public static void main(String[] args) {
		new AdminMenu();
	}

}
