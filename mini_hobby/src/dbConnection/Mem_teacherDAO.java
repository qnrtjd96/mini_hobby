package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class Mem_teacherDAO extends DBConnection {

	public Mem_teacherDAO() {}
	// 카테고리 검색시 보여지는 리스트(studenCateList.java)
	public List<Mem_teacherVO> cateList(String cate) {
		List<Mem_teacherVO> lst = new ArrayList<Mem_teacherVO>();
		try {
			getConn();
			
			sql = "select a.classname, a.city, a.cost, a.name from (select b.id, b.classname, b.city, b.cost, m.name from membertbl m join boardtbl b on m.id=b.id where m.sort=2) a join mem_teacher t on a.id=t.id where t.cate=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Mem_teacherVO vo = new Mem_teacherVO();
				vo.setClassName(rs.getString(1));
				vo.setCity(rs.getString(2));
				vo.setCost(rs.getInt(3));
				vo.settName(rs.getString(4));
				
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
