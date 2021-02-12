package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class MoneyDAO extends DBConnection{

	public MoneyDAO() {}
	// 충전하기
	public int insertMoney(String id, int money_char, int rest) {
		int result=0;
		try {
			getConn();
			
			sql = "insert into Moneytbl(id, money_char, char_date, rest) values(?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, money_char);
			pstmt.setInt(3, rest);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	
	//2021.02.11 이강산
	//회원 마이페이지(메인) 잔액가져오기
	public List<MoneyVO> getMoneyInfo(String idStr) {
		List<MoneyVO> lst = new ArrayList<MoneyVO>();	
		try {
			getConn();
			sql = "select id, money_char, to_char(char_date,'yyyy-mm-dd'), rest from moneyTbl where id=? order by char_date desc";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				MoneyVO vo = new MoneyVO();
				vo.setId(rs.getString(1));
				vo.setMoney_char(Integer.parseInt(rs.getString(2)));
				vo.setChar_date(rs.getString(3));
				vo.setBalance(Integer.parseInt(rs.getString(4)));
				
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
