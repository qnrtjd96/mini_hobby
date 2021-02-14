package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dbConnection.Acess_memDAO;

public class Teach3MyMenu extends JPanel implements MouseListener{

	//보더레이아웃 내에서 마진값 10씩주기
	JPanel paneStu = new JPanel(new BorderLayout(20,20));
		JPanel paneLeft = new JPanel();
			//왼쪽 메뉴 라벨 입력
		JPanel center;
			//각 메뉴별 기능 구현
			//오른쪽 패널 타이틀 보더 설정
			LineBorder lineBorder = new LineBorder(Color.black, 1);
		JPanel paneBottom = new JPanel();

	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	String id, pwd;
	public Teach3MyMenu() {
		
	}
	
	public Teach3MyMenu(String id, String pwd) {
		this.id = id; 
		this.pwd = pwd;
		
		System.out.println("- - - - - - - - teach3MyMenu id > > > "+id+ " , "+pwd);
		
		add("Center", paneStu);
		center = new TeachInfo(id);
		paneStu.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		paneStu.setBackground(Color.white);
		paneStu.add(paneLeft); paneStu.add(paneBottom); paneStu.add(center);
		paneStu.setLayout(null);
		paneLeft.setBounds(20,20,170,800); center.setBounds(200,20,570,800);
		paneBottom.setBounds(20,835,720,50);
		////// paneLeft 구현 //////
		String menuLblStr[] = {"My Page","회원정보 확인/수정","예약내역 및 리뷰확인","수입확인","탈퇴하기"};
		for(int i=0; i<menuLblStr.length; i++) {
			JLabel lblLeft = new JLabel(menuLblStr[i], JLabel.LEFT);
			paneLeft.add(lblLeft);
			lblLeft.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			lblLeft.setFont(fntBold15);
			lblLeft.addMouseListener(this);
		} 
		//왼쪽 메뉴 레이아웃 및 간격조정
		GridLayout grid = new GridLayout(10,0);
		paneLeft.setLayout(grid);
		grid.setVgap(15);
		
		//paneLeft 기본셋팅
		paneLeft.setBackground(col6);
		paneLeft.setBorder(lineBorder);
		
		//paneRight 기본셋팅
		center.setBackground(Color.white);
		//타이틀 보더 설정
		center.setBorder(lineBorder);
		
		setVisible(true);
		setBackground(col6);
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		JLabel obj = (JLabel)me.getSource();
		Object lbl = obj.getText();
		if(lbl.equals("My Page")) {
			JOptionPane.showMessageDialog(this, "아래에서 원하는 메뉴를 선택하세요");
		}else if(lbl.equals("회원정보 확인/수정")) {
			center.setVisible(false);
			center.removeAll();
			center = new TeachInfo(id);
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("예약내역 및 리뷰확인")) {
			center.setVisible(false);
			center.removeAll();
			center = new TeachClassList(id).start;
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("수입확인")) {
			center.setVisible(false);
			center.removeAll();
			center = new TeachTotal(id);
			center.setBorder(new LineBorder(Color.black, 1));
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
		}else if(lbl.equals("탈퇴하기")) {
			center.setVisible(false);
			center.removeAll();
			center = new TeachDeleteUser(id,pwd).deletMain;
			System.out.println("회원탈퇴  > >  > "+id+", "+pwd);
			paneStu.add(center); paneStu.setLayout(null);
			center.setBounds(200,20,570,800);
			center.setVisible(true);
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
	//프레임 X 눌렀을때의 이벤트
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void windowClosing(WindowEvent we) {
			Acess_memDAO dao = new Acess_memDAO();
			int result = dao.LogOut(id);
			System.exit(0);
		}
	}

}
