package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class Acess_memDAO extends DBConnection {

	public Acess_memDAO() {}
	
	//2021.02.11 이강산
	//실시간채팅 학생(stuLiveChat)
	public List<Acess_memVO> LiveChattpeople() {
		//선택한 레코드를 보관할 컬렉션
		List<Acess_memVO> lst= new ArrayList<Acess_memVO>();
		try {
			getConn(); 
			sql = "select id,name from ACCESSMEMTBL where sort=2";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO2에 담고 VO2를 List에 담고
				Acess_memVO vo2 = new Acess_memVO(rs.getString(1), rs.getString(2));
				lst.add(vo2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;	
	}
	
	// 로그인 정보 DB insert
	public int LogIn(Acess_memVO vo) {
		int result =0;
		try {
			getConn();
		
			sql = "insert into accessmemtbl(id,name,sort) values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getSort());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	// 로그인 정보 DB delete
	public int LogOut(String id) {
		int result = 0;
		try {
			getConn();
			sql = "delete from accessmemtbl where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}

}
