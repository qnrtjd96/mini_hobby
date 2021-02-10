package studen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class StudenSearch extends JPanel implements ActionListener {
		
	ImageIcon background = new ImageIcon("img/searchBack.gif");
	
	JPanel center = new JPanel(null); //전체 프레임의 중간에 들어갈 패널
		JLabel lbl = new JLabel("현정이가 메인을 준비중입니다. 잠시만 기다려주세요");
		JTextField searchTf = new JTextField();
		JButton searchBtn = new JButton("검색");
		
		JButton musicBtn = new JButton("음악");	JButton artBtn = new JButton("미술");
		JButton sportBtn = new JButton("스포츠"); JButton cookBtn = new JButton("요리");
		
		JTextArea classTa = new JTextArea();
		String testTa = "예약 클래스 : \n 레이아웃 빨리 끝내고 싶다... \n 날짜: 2021-02-25";
		
	Font fnt = new Font("맑은 고딕",Font.PLAIN, 15);
	Font searchFnt = new Font("맑은 고딕", Font.PLAIN, 18);
	Font btnFnt = new Font("맑은 고딕", Font.BOLD, 20);

	Color col6 = new Color(204,222,233);

	public StudenSearch() {
		add(center);
		
		center.setBackground(Color.white);
		center.add(lbl); lbl.setFont(fnt);
		
		searchTf.setBorder(new LineBorder(Color.black, 2));
		searchTf.setFont(searchFnt); searchTf.setBounds(100,250, 500,40); center.add(searchTf);
		
		//버튼에 색이 안 먹히는듯?
		searchBtn.setBackground(col6); searchBtn.setBorder(new LineBorder(Color.black, 2));
		searchBtn.setFont(btnFnt); searchBtn.setBounds(610,250, 80,40); center.add(searchBtn);
		
		musicBtn.setBackground(col6); musicBtn.setBorder(new LineBorder(Color.black, 2));
		musicBtn.setFont(btnFnt); musicBtn.setBounds(101,300, 80,40); center.add(musicBtn);
		
		artBtn.setBackground(col6); artBtn.setBorder(new LineBorder(Color.black, 2));
		artBtn.setFont(btnFnt); artBtn.setBounds(191,300, 80,40); center.add(artBtn);
		
		sportBtn.setBackground(col6); sportBtn.setBorder(new LineBorder(Color.black, 2));
		sportBtn.setFont(btnFnt); sportBtn.setBounds(281,300, 80,40); center.add(sportBtn);
		
		cookBtn.setBackground(col6); cookBtn.setBorder(new LineBorder(Color.black, 2));
		cookBtn.setFont(btnFnt); cookBtn.setBounds(371,300, 80,40); center.add(cookBtn);
		
		//textArea 가운데 정렬..? ㅠㅠ?
		classTa.setBackground(Color.white); classTa.setBorder(new LineBorder(Color.black, 1)); 
		classTa.setFont(new Font("맑은 고딕", Font.PLAIN, 13)); classTa.setBounds(580,660, 200,100);
		classTa.setText(testTa); center.add(classTa);
		
		searchBtn.addActionListener(this);
		musicBtn.addActionListener(this);	artBtn.addActionListener(this);
		sportBtn.addActionListener(this);	cookBtn.addActionListener(this);
		
		
		setVisible(true);
		setBackground(Color.white);
		
		////////////
		JPanel back = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(background.getImage(),0,0,null);
				
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		back.setBounds(0,0, 300,300);
		center.add(back);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj==searchBtn) {
			String searchWord = searchTf.getText();
			System.out.println("검색버튼을 누름");
			
			//
			matchWord();
		}else if(obj==musicBtn) {
			System.out.println("음악버튼을 누름");
		}else if(obj==artBtn) {
			System.out.println("미술버튼을 누름");
		}else if(obj==sportBtn) {
			System.out.println("스포츠버튼을 누름");
		}else if(obj==cookBtn) {
			System.out.println("요리버튼을 누름");
		}
		
	}
	
	public void matchWord() {
		JOptionPane.showMessageDialog(this, "ㅎㅎㅎㅎ");
	}


}