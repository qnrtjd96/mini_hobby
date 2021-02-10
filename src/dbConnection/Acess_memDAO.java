package dbConnection;
public class Acess_memDAO extends DBConnection {

	public Acess_memDAO() {}

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
