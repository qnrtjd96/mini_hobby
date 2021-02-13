package teach;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class TeachIncome extends JDialog{
	Color col6 = new Color(204,222,233);
	Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
	Font fntPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	Font fntPlain25 = new Font("맑은 고딕", Font.PLAIN, 25);
	Font fntPlain30 = new Font("맑은 고딕", Font.PLAIN, 30);
	Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
	Font fntBold20 = new Font("맑은 고딕", Font.BOLD, 20);
	Font fntBold30 = new Font("맑은 고딕", Font.BOLD, 30);
	
	//																하단 총 수입	      하단 월 수입									
	JTextPane tp[] = {new JTextPane(), new JTextPane(), new JTextPane(), new JTextPane(), new JTextPane(),  new JTextPane(),  new JTextPane()};
	//				상단 제목
	JLabel topLbl = new JLabel("", JLabel.CENTER);
	JLabel lbl[] = {new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT)};
	// 추후 '카테고리','학생명','결제액' DB 데이터 add용
	List<String/*TeachVO*/> dbTp = null;
	// 직선 패널 생성
	JPanel linePane[] = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
	
	String id;
	String date;
	public TeachIncome() {}
	public TeachIncome(String id, String date) {
		this.id=id;
		this.date=date;
		
		setSize(570,800);
		setVisible(true);
		setLocation(190, 100);
		//문자 셋팅
		topLbl.setText("수입확인");
		
		//데이터 호출
		incomeList(id, date);
		
		//오른쪽 패널에 add
		for(int i=0; i<tp.length; i++) {
			add(tp[i]);
		}
		for(int i=0; i<lbl.length; i++) {
			add(lbl[i]);
		}
		add(topLbl);
		//직선 패널 add
		for(int i=0; i<linePane.length; i++) {
			add(linePane[i]);
		}
		//tp[0~4] 보더처리
		for(int i=0; i<lbl.length; i++) {
			tp[i].setBorder(new LineBorder(Color.black));
		}
		
		setLayout(null);
		topLbl.setBounds(250,5,100,30);
		tp[0].setBounds(20,50,200,70); lbl[0].setBounds(240,70,300,70);
		linePane[0].setBounds(0,140,600,1); linePane[0].setBackground(Color.black);
		tp[1].setBounds(20,160,200,70); lbl[1].setBounds(240,180,300,70);
		linePane[1].setBounds(0,250,600,1); linePane[1].setBackground(Color.black);
		tp[2].setBounds(20,270,200,70); lbl[2].setBounds(240,290,300,70);
		linePane[2].setBounds(0,360,600,1); linePane[2].setBackground(Color.black);
		tp[3].setBounds(20,380,200,70); lbl[3].setBounds(240,400,300,70);
		linePane[3].setBounds(0,470,600,1); linePane[3].setBackground(Color.black);
		tp[4].setBounds(20,490,200,70); lbl[4].setBounds(240,510,300,70);
		linePane[4].setBounds(0,580,600,1); linePane[4].setBackground(Color.black);
		tp[5].setBounds(0,580,280,209); tp[5].setBorder(new LineBorder(Color.black));
		tp[6].setBounds(279,580,278,209); tp[6].setBorder(new LineBorder(Color.black));
		
		//폰트 셋팅
		topLbl.setFont(fntBold20); 
		tp[0].setFont(fntBold15); lbl[0].setFont(fntPlain30);
		tp[1].setFont(fntBold15); lbl[1].setFont(fntPlain30);
		tp[2].setFont(fntBold15); lbl[2].setFont(fntPlain30);
		tp[3].setFont(fntBold15); lbl[3].setFont(fntPlain30);
		tp[4].setFont(fntBold15); lbl[4].setFont(fntPlain30);
		tp[5].setFont(fntBold30); tp[6].setFont(fntBold30);
		
		//입력 금지 셋팅
		tp[0].setEditable(false);
	}
	//데이터 메소드
	public void incomeList(String id, String date) {
		Stu_ClassDAO dao = new Stu_ClassDAO();
		List<Stu_ClassVO> lst = dao.teachIncomeList(id, date);
		
		int cnt5 = 0; // 최근수입
		int cntAll = 0; // 총수입
		int lstCnt = lst.size();
		if(lstCnt>=5) {
			lstCnt = 5;
		}
		for(int i=0; i<lstCnt; i++) {
			Stu_ClassVO vo = lst.get(i);
			tp[i].setText(vo.getPay_cate() +"\n\n\t\t"+ vo.getsName()+"님");
			cnt5 += vo.getPay();
			lbl[i].setText(String.valueOf(vo.getPay())+" 원");
		}
		tp[6].setText(" 최근 5개 수입 \n\n\t"+cnt5 + " 원");
		for(int i=0; i<lst.size(); i++) {
			Stu_ClassVO vo = lst.get(i);
			cntAll += vo.getPay();
		}
		tp[5].setText(" 총 수입 \n\n\t"+cntAll + " 원");
	}
}
