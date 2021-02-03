
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TeachIncome extends TeachMyMenu{
	//																하단 총 수입	      하단 월 수입									
	JTextPane tp[] = {new JTextPane(), new JTextPane(), new JTextPane(), new JTextPane(), new JTextPane(),  new JTextPane(),  new JTextPane()};
	//				상단 제목
	JLabel lbl[] = {new JLabel("",JLabel.CENTER), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT), new JLabel("",JLabel.RIGHT)};
	// 추후 '카테고리','학생명','결제액' DB 데이터 add용
	List<String/*TeachVO*/> dbTp = null;
	// 직선 패널 생성
	JPanel linePane[] = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
	public TeachIncome() {
		//문자 셋팅
		lbl[0].setText("수입확인");
		tp[0].setText(/*vo.get카테고리, 학생명 +*/" 스포츠 \n\n\t   이영준(학생)님"); lbl[1].setText(/*vo.get결제액 +*/"<HTML><U>　　　　　50000원</U></HTML>");
		tp[1].setText(/*vo.get카테고리, 학생명 +*/" 음악 \n\n\t   이강산(학생)님"); lbl[2].setText(/*vo.get결제액 +*/"<HTML><U>　　　　　20000원</U></HTML>");
		tp[2].setText(/*vo.get카테고리, 학생명 +*/" 미술 \n\n\t   박세라(학생)님"); lbl[3].setText(/*vo.get결제액 +*/"<HTML><U>　　　　　60000원</U></HTML>");
		tp[3].setText(/*vo.get카테고리, 학생명 +*/" 요리 \n\n\t   김현정(학생)님"); lbl[4].setText(/*vo.get결제액 +*/"<HTML><U>　　　　　80000원</U></HTML>");
		tp[4].setText(/*vo.get카테고리, 학생명 +*/" 스포츠 \n\n\t   홍길동(학생)님"); lbl[5].setText(/*vo.get결제액 +*/"<HTML><U>　　　　100000원</U></HTML>");
		tp[5].setText(/*vo.get결제액(sum) +*/" 총 수입 \n\n      2800000원"); tp[6].setText(/*vo.get결제액(sum) +*/" 월 수입 \n\n       310000원");
		
		
		//오른쪽 패널에 add
		for(int i=0; i<tp.length; i++) {
			paneRight.add(tp[i]);
		}
		for(int i=0; i<lbl.length; i++) {
			paneRight.add(lbl[i]);
		}
		//직선 패널 add
		for(int i=0; i<linePane.length; i++) {
			paneRight.add(linePane[i]);
		}
		//tp[0~4] 보더처리
		for(int i=0; i<lbl.length-1; i++) {
			tp[i].setBorder(lineBorder);
		}
		
		paneRight.setLayout(null);
		lbl[0].setBounds(250,5,100,30);
		tp[0].setBounds(20,50,200,70); lbl[1].setBounds(240,70,300,70);
		linePane[0].setBounds(0,140,600,1); linePane[0].setBackground(Color.black);
		tp[1].setBounds(20,160,200,70); lbl[2].setBounds(240,180,300,70);
		linePane[1].setBounds(0,250,600,1); linePane[1].setBackground(Color.black);
		tp[2].setBounds(20,270,200,70); lbl[3].setBounds(240,290,300,70);
		linePane[2].setBounds(0,360,600,1); linePane[2].setBackground(Color.black);
		tp[3].setBounds(20,380,200,70); lbl[4].setBounds(240,400,300,70);
		linePane[3].setBounds(0,470,600,1); linePane[3].setBackground(Color.black);
		tp[4].setBounds(20,490,200,70); lbl[5].setBounds(240,510,300,70);
		linePane[4].setBounds(0,580,600,1); linePane[4].setBackground(Color.black);
		tp[5].setBounds(0,580,280,209); tp[5].setBorder(lineBorder);
		tp[6].setBounds(279,580,278,209); tp[6].setBorder(lineBorder);
		
		//폰트 셋팅
		lbl[0].setFont(fntBold20); 
		tp[0].setFont(fntBold15); lbl[1].setFont(fntPlain30);
		tp[1].setFont(fntBold15); lbl[2].setFont(fntPlain30);
		tp[2].setFont(fntBold15); lbl[3].setFont(fntPlain30);
		tp[3].setFont(fntBold15); lbl[4].setFont(fntPlain30);
		tp[4].setFont(fntBold15); lbl[5].setFont(fntPlain30);
		tp[5].setFont(fntBold30); tp[6].setFont(fntBold30);
		
		//입력 금지 셋팅
		tp[0].setEditable(false);
	}
}
