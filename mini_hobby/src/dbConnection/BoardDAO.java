package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends DBConnection{

	public BoardDAO() {
		
	}
	public int insertBoard(BoardVO vob) {
		int result = 0;
		try {
			getConn();
			
			sql="insert into boardtbl (class_num, id, classname, cate, review, city, cost, intro"
					+ " career, area, writedate) values (classnum.nextval, ?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	// 관리자 결제관리 상세내역
	public List<BoardVO> PaymentSelect(String searchId) {
		//선택한 레코드를 보관할 컬렉션
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql = "select b.class_num, b.id, m.name, b.cost, to_char(s.pay_date, 'YYYY/MM/DD') from stu_class s join boardtbl b on s.class_num = b.class_num join membertbl m on b.id = m.id where s.id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO 담고 VO를 List에 담고
				BoardVO vo = new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
}
