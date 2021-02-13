package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class BlackListDAO extends DBConnection{

	public BlackListDAO() {
		
	}
	// 블랙리스트 목록 불러오기
	public List<BlackListVO> selectList() {
		List<BlackListVO> lst = new ArrayList<BlackListVO>();
		try {
			getConn();
			
			sql="select id, m.sort, b.reason from blacklisttbl b join membertbl m using(id)";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BlackListVO vo = new BlackListVO();
				vo.setId(rs.getString(1));
				vo.setSort(rs.getInt(2));
				vo.setWhy(rs.getString(3));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 블랙리스트 해제하기
	public int deleteList(String id) {
		int result=0;
		try {
			getConn();
			
			sql="delete from blacklisttbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// 블랙리스트 등록하기
	public int insertList(String id, String reason) {
		int result=0;
		try {
			getConn();
			
			sql="insert into blacklisttbl (id, reason, black_date) "
					+ "values(?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, reason);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
		
	}
	// 로그인할때 사유찾기
	public List<BlackListVO> searchList(String id) {
		List<BlackListVO> lst = new ArrayList<BlackListVO>();
		try {
			getConn();
			
			sql="select id, m.sort, b.reason from blacklisttbl b join membertbl m using(id) "
					+ " where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BlackListVO vo = new BlackListVO();
				vo.setId(rs.getString(1));
				vo.setSort(rs.getInt(2));
				vo.setWhy(rs.getString(3));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}

}
