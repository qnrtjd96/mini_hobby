package teach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;

public class TeachTextUpdateDelete extends JDialog implements ActionListener{
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
		    	JLabel cate = new JLabel("카테고리     ");
		    	JLabel cate2 = new JLabel("요리");
		    	JPanel topPane2 = new JPanel(new GridLayout(0, 2));
				JLabel  area = new JLabel("지역");
				JComboBox<String> area2 = new JComboBox<String>();
				DefaultComboBoxModel<String> area2Model = new DefaultComboBoxModel<String>();
				    String area2Str[]= {"경기도","서울특별시","대구광역시","울산광역시","부산광역시","인천광역시","경상남도","경상북도","전라남도","전라북도","충청남도","충청북도","제주도"};
		    JPanel formLabelPane = new JPanel(new GridLayout(5, 1));
			    JLabel classname = new JLabel("클래스명");
			    JLabel classdetail = new JLabel("클래스 소개             ");
			    JLabel total = new JLabel("경력사항");
			    JLabel detail = new JLabel("상세지역");
			    JLabel  pay = new JLabel("비용(1회당)");

		    JPanel formCenterPane = new JPanel(new GridLayout(5, 1));
			    JTextField classname2 = new JTextField();
			    JTextField classdetail2 = new JTextField();
			    JTextField total2 = new JTextField();
			    JTextField detail2 = new JTextField();
			    JTextField pay2 = new JTextField();
			    
		    JPanel mainBottomPane = new JPanel(new BorderLayout());
		    	JPanel buttonPane = new JPanel();
				     JButton updateBtn = new JButton("수정완료");
				     JButton deleteBtn = new JButton("내용초기화");

	BoardVO vo;
	int costInt;
	public TeachTextUpdateDelete() {}

	public TeachTextUpdateDelete(BoardVO vo) {
		this.vo = vo;
		this.costInt = vo.getCost();
		
		cate.setFont(fntBold15);  area.setFont(fntBold15); cate2.setFont(fntBold15); 
		classname.setFont(fntBold20);classdetail.setFont(fntBold20);total.setFont(fntBold20);
		detail.setFont(fntBold20);pay.setFont(fntBold20);updateBtn.setFont(fntBold15); deleteBtn.setFont(fntBold15);
		mainCenterPane.setBackground(Color.WHITE); topPane.setBackground(Color.WHITE);
		topPane1.setBackground(Color.WHITE); topPane2.setBackground(Color.WHITE);
		formLabelPane.setBackground(Color.WHITE); formCenterPane.setBackground(Color.WHITE);
		mainBottomPane.setBackground(Color.WHITE); buttonPane.setBackground(Color.WHITE);
		area2.setBackground(Color.WHITE); main.setBackground(Color.WHITE);

		//상단패널
		for(String Name : area2Str) {
			area2Model.addElement(Name);
		}
		area2.setModel(area2Model);
		area2.setSelectedItem(area); //이거바꿔줘야함

		topPane1.add(cate); topPane1.add(cate2);
        topPane.add("West", topPane1);
        topPane2.add(area); topPane2.add(area2);
        topPane.add("East", topPane2);
        mainCenterPane.add("North", topPane);


        classname.setBorder(new LineBorder(Color.lightGray, 1));	classname2.setBorder(new LineBorder(Color.lightGray, 1));
        classdetail.setBorder(new LineBorder(Color.lightGray, 1));	classdetail2.setBorder(new LineBorder(Color.lightGray, 1));
        total.setBorder(new LineBorder(Color.lightGray, 1));		total2.setBorder(new LineBorder(Color.lightGray, 1));
        detail.setBorder(new LineBorder(Color.lightGray, 1));		detail2.setBorder(new LineBorder(Color.lightGray, 1));
        pay.setBorder(new LineBorder(Color.lightGray, 1));			pay2.setBorder(new LineBorder(Color.lightGray, 1));
        
        formLabelPane.add(classname);	formCenterPane.add(classname2);
        formLabelPane.add(classdetail);	formCenterPane.add(classdetail2);
        formLabelPane.add(total);		formCenterPane.add(total2);
        formLabelPane.add(detail);		formCenterPane.add(detail2);
        formLabelPane.add(pay);			formCenterPane.add(pay2);
        mainCenterPane.add("West", formLabelPane);
        mainCenterPane.add("Center", formCenterPane);

        //남쪽패널
        buttonPane.add(updateBtn);
        buttonPane.add(deleteBtn);

        mainBottomPane.add("East", buttonPane);
        mainCenterPane.add("South", mainBottomPane);

        //////////패널추가
        mainCenterPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPane.add(mainCenterPane);
        main.add(mainPane);

        add("Center",main);
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        area2.setSelectedItem(vo.getCity());
        classname2.setText(vo.getClassname()); classname2.setFont(fntBold15);
		classdetail2.setText(vo.getIntro()); classdetail2.setFont(fntBold15);
		total2.setText(vo.getCareer()); total2.setFont(fntBold15);
		detail2.setText(vo.getArea()); detail2.setFont(fntBold15);
		pay2.setText(vo.getCost()+"원"); pay2.setFont(fntBold15);
       
        setSize(750,500);
		setLocation(20, 350);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj==updateBtn) {
			if (classname2.getText().equals("")||classdetail2.getText().equals("")||total2.getText().equals("")||
					detail2.getText().equals("")||pay2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "입력되지 않은 항목이 있습니다. 다시 확인해주세요");
			} else if(pay2.getText().contains(",") || pay2.getText().contains("원")) {
				JOptionPane.showMessageDialog(this, "1회당 비용에는 , 또는 원 표시 없이 금액만 입력해주세요");
			} else{
				BoardVO vob = new BoardVO(vo.getClass_num(), vo.getId(), classname2.getText(), vo.getCate(), (String)area2.getSelectedItem(),
						Integer.parseInt(pay2.getText()), classdetail2.getText(), total2.getText(), detail2.getText());
				BoardDAO dao = new BoardDAO();
				int result = dao.updateDetail(vob);
				List<BoardVO> lst = dao.detailBoard(vo.getClass_num());
				BoardVO vob2 = lst.get(0);
				if (result>0) {
					JOptionPane.showMessageDialog(this, "수정이 완료 되었습니다.");
					this.setVisible(false);
					new TeachReservationDetail(vob.getId(), vob.getClassname(), vob.getClass_num(), vob2.getClassdate());
				}
			}
		} else if (obj==deleteBtn) {
			classname2.setText("");
			classdetail2.setText("");
			total2.setText("");
			detail2.setText("");
			pay2.setText("");
		}
		
	}

	

}