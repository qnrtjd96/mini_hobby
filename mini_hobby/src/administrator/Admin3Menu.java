package administrator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Admin3Menu extends JPanel {	//마우스 액션이 굳이 필요한지 모르겠음, 하다가 필요하면 삽입하기
	//상위 패널 설
	
	JPanel tabBack = new JPanel(new BorderLayout());
	//탭 별로 임시로 패널 설정해둠 > 패널에 테이블을 삽입하면 될 듯함..
	JTabbedPane adminTab = new JTabbedPane();
		JPanel memList = new JPanel();	// 회원목록 + 검색기능
			//JTable memTbl = new JTable();
		JPanel payAdmin = new JPanel();	// 결제관리 > 회원누르면 JDialog pop-up > 결제취소 
		JPanel inactiveId = new JPanel();	// 그냥 테이블만 > 마지막 로그인 기점으로 n개 지난 user sorting
		
	Font TabFnt = new Font("돋움체", Font.BOLD, 18);
	Color col6 = new Color(204,222,233);
	
	public Admin3Menu() {
		setLayout(new BorderLayout());
		
		adminTab.addTab("회원목록", new AdminMemberList()); //	memList.add(memTbl);
		adminTab.addTab("결제관리", new AdminPaymentList().mainPane	);
		adminTab.addTab("휴면계정", new AdminSleepUser());
		adminTab.addTab("블랙리스트", new AdminBlackList());
		
		adminTab.setFont(TabFnt);
		adminTab.setBackground(col6);
		adminTab.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		add("Center", tabBack); tabBack.setBackground(Color.white);
		tabBack.add("Center", adminTab);
		
		setVisible(true);
		
		//CustomTabbedPaneUI 만들기
	}
}