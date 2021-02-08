package dbConnection;

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
}
