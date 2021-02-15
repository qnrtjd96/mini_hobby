package studen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dbConnection.BoardDAO;
import dbConnection.BoardVO;
import dbConnection.Stu_ClassDAO;
import dbConnection.Stu_ClassVO;

public class duePurchaseDialog extends JDialog implements ActionListener, ItemListener{
	Font fn = new Font("맑은 고딕",Font.PLAIN, 15);
	Font fnt = new Font("맑은 고딕",Font.BOLD, 20);
	Font fn2 = new Font("맑은 고딕", Font.BOLD, 18);
	Font fnt2 = new Font("맑은 고딕",Font.PLAIN, 18);
	Color col6 = new Color(204,222,233);
	
	JPanel nthPane = new JPanel();
			JLabel chooClass = new JLabel("원하는 시간을 골라주세요.");

	JPanel classPane = new JPanel();
			String time, classtime;
		JPanel select = new JPanel(new GridLayout(7,0));
			JCheckBox check[];
	
	JPanel sthPane = new JPanel();
		JButton changeBtn = new JButton("예약변경");
	
	TreeSet<String> selectCheck = new TreeSet<String>();
	
	String id;
	int classNum;
	
	public duePurchaseDialog(int classNum, String idStr) {
		this.classNum = classNum;
		this.id = idStr;
		
		System.out.println("예약변경 다이얼로그에 값 받아옴 ? "+classNum+", "+idStr);
		
		setTitle(" 예약변경");
		setBackground(Color.white);
		
		nthPane.setBackground(Color.white);
		
		chooClass.setBackground(Color.white);		chooClass.setFont(fnt2);
		
		nthPane.add(chooClass);
		
		classPane.setLayout(null); classPane.setBackground(Color.white);
		classPane.setBorder(new LineBorder(Color.black, 2));
		classPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		select.setFont(fnt); select.setBounds(5,5, 300,400); select.setBackground(Color.white);
		
		classPane.add(select);
		
		changeBtn.setFont(fn2);		changeBtn.setBackground(col6);
		
		sthPane.setBackground(Color.white);
		sthPane.add(changeBtn);
		
		//JDialog에 삽입
		add("North", nthPane);
		add("Center", classPane);
		add("South", sthPane);
		
		setSize(300,400);
		setLocation(800,100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		checkBoxStart(classNum);
		
		changeBtn.addActionListener(this);
	}

	public void duePurchaseDialog() {}
	
	public void checkBoxStart(int classNum) {
		//패널에 체크박스 추가.. 해당 수업의 시간들 
		BoardDAO dao = new BoardDAO();
		List<BoardVO> lst = dao.detailBoard(classNum);
		select.removeAll();
		if (lst.size()>0) {
			BoardVO vob = lst.get(0);
			classtime = vob.getClasstime();
			System.out.println("???  checkbox , , , "+classtime);
				//클래스타임이 존재할시 ,로 나눔
			StringTokenizer st = new StringTokenizer(classtime, "[,]");
			check = new JCheckBox[st.countTokens()];
			int i=0;
			while (st.hasMoreTokens()) { //무한 루프..?
				String token = st.nextToken();
				if (token.equals("") || token==null) {
					//break;
				} else {
					check[i] = new JCheckBox(token);
					check[i].setFont(fn); check[i].setHorizontalAlignment(JCheckBox.CENTER);
					check[i].setBackground(Color.white);
					check[i].addItemListener(this);
					select.add(check[i]);
					i++;
				}	
			}	
		} else {
			JLabel label = new JLabel("해당하는 일자의 정보가 없습니다.");
			select.add(label); label.setFont(fn);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==changeBtn) {
			String str = "";
			Iterator<String> it = selectCheck.iterator(); //트리셋에서 값 받아오기
			while(it.hasNext()) {
				String checkStr = it.next();
				System.out.println("       selectCheck iterator 1 > "+checkStr);
				str = str+checkStr+",";
				classtime = str.substring(0,str.length()-1);
				System.out.println("- - - -  - - -  -"+classtime);
			}
			Stu_ClassDAO dao = new Stu_ClassDAO();
			int result = dao.stuChangeClasstime(classtime, id, classNum);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "예약변경이 완료되었습니다.");
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(this, "예약변경이 실패되었습니다.");
			}
			
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		JCheckBox dateCheck = (JCheckBox)ie.getItem();
		String str = dateCheck.getText();
		if (dateCheck.isSelected()) {
			selectCheck.add(str);
		} else {
			Iterator<String> st = selectCheck.iterator();
			while(st.hasNext()) { 
				String str2 = st.next();
				if (str2.equals(str)) {
					selectCheck.remove(str2); break;
				}
			}
		}
	}
}
