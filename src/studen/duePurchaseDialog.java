package studen;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;

public class duePurchaseDialog extends JDialog {
	JPanel classPane = new JPanel();
		JLabel lbl1 = new JLabel("선택한 클래스 : ");
		JLabel lbl2 = new JLabel("선택한 일자 : ");
			String time, classtime;
		JPanel select = new JPanel(new GridLayout(7,0));
			JCheckBox check[];
	
	JButton changeBtn = new JButton("예약변경");
	
	String id;
	int classNum;
	
	
	public duePurchaseDialog(int classNum, String idStr) {
		this.classNum = classNum;
		this.id = id;
		
		setTitle(" 예약변경");
		setBackground(Color.white);
		
		//JDialog에 삽입
		add("Center", classPane);
		add("South", changeBtn);
	
		setSize(300,400);
		setLocation(800,100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void duePurchaseDialog() {}
}
