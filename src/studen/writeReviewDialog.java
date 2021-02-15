package studen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import dbConnection.ReviewDAO;
import dbConnection.ReviewVO;

public class writeReviewDialog extends JDialog implements ActionListener, ItemListener {
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Color col6 = new Color(204,222,233);
	
	JPanel boxPane = new JPanel();
		JLabel score = new JLabel("별점 : ");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		JComboBox<String> box = new JComboBox<String>(model);

	JPanel centerPane = new JPanel();
		JTextArea typeReview = new JTextArea();		String testTa = "리뷰 작성";
	JPanel btnPane = new JPanel();		
		JButton doneBtn = new JButton("작성완료");
	
	String id;
	int reviewNum;
	
	public writeReviewDialog() {
		
	}
	
	public writeReviewDialog (int reviewNum, String id) {
		this.id=id;
		this.reviewNum = reviewNum;
		
		setTitle("리뷰 작성하기");
		
		String star[] = {"★", "★★", "★★★", "★★★★", "★★★★★"};
		for(int i=0; i<star.length; i++) {
			model.addElement(star[i]);
		}
		
		score.setBackground(Color.white);	score.setFont(fn2);
		
		boxPane.setBackground(Color.white);
		boxPane.add(score);
		boxPane.add(box);
		
		typeReview.setBackground(Color.white);	typeReview.setFont(fn);
		
		centerPane.setBackground(Color.white);	centerPane.setBorder(new LineBorder(Color.BLACK, 2));
		typeReview.setText(testTa);
		centerPane.add(typeReview);
		
		doneBtn.setBackground(col6);	doneBtn.setFont(fn2);
		btnPane.setBackground(Color.white);
		btnPane.add(doneBtn);
		
		//다이얼로그 패널에 넣기
		add("North", boxPane);
		add("Center", centerPane);
		add("South", btnPane);
		
		setBackground(Color.white);
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		doneBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==doneBtn) {
			System.out.println("리뷰작성완료 버튼이 눌림...");
			ReviewVO vo = new ReviewVO();
			String writeRivew = typeReview.getText();
			
			String getBoxScore = box.getSelectedItem().toString();
			System.out.println("콤보박스 값 리턴? "+getBoxScore);
			if(getBoxScore.equals("★")) {
				getBoxScore = "1";
			}else if(getBoxScore.equals("★★")) {
				getBoxScore = "2";
			}else if(getBoxScore.equals("★★★")) {
				getBoxScore = "3";
			}else if(getBoxScore.equals("★★★★")) {
				getBoxScore = "4";
			}else if(getBoxScore.equals("★★★★★")) {
				getBoxScore = "5";
			}
			System.out.println(" > > > 콤보박스값 String 변환 "+getBoxScore);
			
			int intBox = Integer.parseInt(getBoxScore);
			System.out.println(" class - - - 텍스트 에리어 값 리턴 ? ? ? "+writeRivew);
			System.out.println(" class - - - 콤보박스 값 리턴 ? ? ? "+intBox);
			System.out.println(", , , , , "+reviewNum);
			ReviewDAO dao = new ReviewDAO();
			int result = dao.commitReview(reviewNum, intBox, writeRivew);
			
			
			if(result>0) {
				JOptionPane.showMessageDialog(this, "리뷰작성이 완료됐습니다.");
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(this, "이미 리뷰를 작성하셨습니다.");
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	
}
