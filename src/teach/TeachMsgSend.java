package teach;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TeachMsgSend extends JPanel implements MouseListener{
   JPanel mainPane = new JPanel();
      JTable table;
         JScrollPane sp;
         DefaultTableModel model;
         
   // 테이블 필드명
   Object headList[] = {"선택","메세지내용","받는사람","보낸시간"};
   // 테이블 레코드 테스트 값
   Object recoList1[] = {"○","<HTML><U> ??? </U></HTML>","ㄱㅎㅈ","2021.02.04 13:55"};
   Object recoList2[] = {"○","<HTML><U> ???? </U></HTML>","ㄱㅎㅈ","2021.02.04 16:11"};
   
   DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
   
   Color col6 = new Color(204,222,233);
   Font fntPlain15 = new Font("맑은 고딕", Font.PLAIN, 15);
   Font fntBold15 = new Font("맑은 고딕", Font.BOLD, 15);
   
   JLabel delLbl = new JLabel("삭제하기", JLabel.CENTER);
   public TeachMsgSend() {
      mainPane.setLayout(null);
      mainPane.setBackground(Color.white);

      //JTable
      // 내용 수정 불가
      model = new DefaultTableModel(headList, 0) {
          public boolean isCellEditable(int i, int c){ return false; }
      };
      table = new JTable(model);
      sp = new JScrollPane(table);
      
      // 컬럼 너비 조절
      table.getColumn("선택").setPreferredWidth(30);
      table.getColumn("메세지내용").setPreferredWidth(300);
      table.getColumn("받는사람").setPreferredWidth(100);
      table.getColumn("보낸시간").setPreferredWidth(100);
      table.setAlignmentX(JLabel.CENTER);
      
      // 테이블 필드명 높이 조절
      table.setTableHeader(new JTableHeader(table.getColumnModel()) {
         public Dimension getPreferredSize() {
          Dimension d = super.getPreferredSize();
          d.height = 40;
          return d;
         }
      });
      
      // 셀 이동 불가
      table.getTableHeader().setReorderingAllowed(false);
      
      // 테이블 레코드 높이 조절
      table.setRowHeight(30);
      
      // 폰트 설정
      table.getTableHeader().setFont(fntBold15);
      table.setFont(fntPlain15);
      delLbl.setFont(fntPlain15);
      
      // 배경색 설정
      table.getTableHeader().setBackground(Color.lightGray);
      table.getParent().setBackground(Color.white);
      
      // 가운데 정렬
      tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcmSchedule = table.getColumnModel();
      tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
      tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
      tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
      
      //mainPane에 add
      mainPane.add(sp); mainPane.add(delLbl);
      
      model.addRow(recoList1);
      model.addRow(recoList2);
      
      // setBounds
      sp.setBounds(0,0,745,750); delLbl.setBounds(640,750,100,50);
      table.addMouseListener(this);
      delLbl.addMouseListener(this);
   }
   @Override
   public void mouseClicked(MouseEvent e) {
      int clickBtn = e.getButton();
      if(clickBtn==1) {
         //선택한 컬럼의 데이터 가져오기
         int row = table.getSelectedRow();
         int col = table.getSelectedColumn();
         Object value = table.getValueAt(row, col);
         if(value.equals("○")) {
            table.setValueAt("●", row, col);
         }else if(value.equals("●")) {
            table.setValueAt("○", row, col);
         }else if(col==2) {
            //학생 메세지쓰기 호출하세욘//
         }
      }
   }
   
   public void mouseReleased(MouseEvent e) {
      /* 삭제 구현 못함 .... 
        
      Object delStr = e.getSource();
      if(delStr == delLbl) {
         // 데이터 삭제 구현
         
      }
      
      */
   }
   public void mousePressed(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   
}