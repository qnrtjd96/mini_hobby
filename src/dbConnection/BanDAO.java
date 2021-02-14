package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class BanDAO extends DBConnection{

	public BanDAO() {}
	//제약어 추가
	public int insertBan(BanVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into bantbl(dont) values(?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDont());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//제약어 중복확인
	public List<BanVO> overlapCheck(String searchBan) {
		List<BanVO> lst = new ArrayList<BanVO>();
		try {
			getConn();
			
			sql="select dont from bantbl where dont like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchBan);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BanVO vo = new BanVO();
				vo.setDont(rs.getString(1));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;	
	}
	//제약어 가져오기
	public List<BanVO> banList() {
		List<BanVO> lst = new ArrayList<BanVO>();
		try {
			getConn();
			
			sql="select dont from bantbl";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BanVO vo = new BanVO(rs.getString(1));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;	
	}
	//제약어 삭제하기
	public int banDelete(String select) {
		int result = 0;
		try {
			getConn();
			sql = "delete from bantbl where dont=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, select);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	// 쪽지보낼때 제약어
	public List<BanVO> overlapWrite(String searchBan) {
		List<BanVO> lst = new ArrayList<BanVO>();
		try {
			getConn();
			
			sql="select dont from bantbl where ? like '%'||dont||'%'";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchBan);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BanVO vo = new BanVO();
				vo.setDont(rs.getString(1));
				
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
