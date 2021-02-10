<<<<<<< HEAD
package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO extends DBConnection{

	public MemoDAO() {
		
	}
	public int InsertMemo(MemoVO vom) {
		int result=0;
		try {
			getConn();
			
			sql = "insert into memotbl (memo_date, memo_detail, id) values (to_date(?, 'yyyy-mm-dd'),?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vom.getMemo_date());
			pstmt.setString(2, vom.getMemo_detail());
			pstmt.setString(3, vom.getId());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
		
	}
	public List<MemoVO> OutputMemo(String date, String id) {
		List<MemoVO> lst = new ArrayList<MemoVO>();
		try {
			getConn();
			
			sql="select to_char(memo_date,'yyyy-mm-dd'), memo_detail, id from memotbl "
					+ " where memo_date=(to_date(?, 'yyyy-mm-dd')) and id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoVO vom = new MemoVO();
				vom.setMemo_date(rs.getString(1));
				vom.setMemo_detail(rs.getString(2));
				vom.setId(rs.getString(3));
				
				lst.add(vom);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public int deleteMemo(MemoVO vom) {
		int result = 0;
		try {
			getConn();
			
			sql="delete from memotbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vom.getId());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}

}
=======
package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO extends DBConnection{

	public MemoDAO() {
		
	}
	public int InsertMemo(MemoVO vom) {
		int result=0;
		try {
			getConn();
			
			sql = "insert into memotbl (memo_date, memo_detail, id) values (to_date(?, 'yyyy-mm-dd'),?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vom.getMemo_date());
			pstmt.setString(2, vom.getMemo_detail());
			pstmt.setString(3, vom.getId());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
		
	}
	public List<MemoVO> OutputMemo(String date, String id) {
		List<MemoVO> lst = new ArrayList<MemoVO>();
		try {
			getConn();
			
			sql="select to_char(memo_date,'yyyy-mm-dd'), memo_detail, id from memotbl "
					+ " where memo_date=(to_date(?, 'yyyy-mm-dd')) and id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoVO vom = new MemoVO();
				vom.setMemo_date(rs.getString(1));
				vom.setMemo_detail(rs.getString(2));
				vom.setId(rs.getString(3));
				
				lst.add(vom);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public int deleteMemo(MemoVO vom) {
		int result = 0;
		try {
			getConn();
			
			sql="delete from memotbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vom.getId());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}

}
>>>>>>> refs/remotes/origin/master
