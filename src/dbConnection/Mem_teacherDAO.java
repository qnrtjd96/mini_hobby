package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class Mem_teacherDAO extends DBConnection {

	public Mem_teacherDAO() {}
	//글쓰기 회원정보받아오기
	//2021-02-10 이강산
	public List<Mem_teacherVO> getTeaInfo(String id){	
			
		List<Mem_teacherVO> lst = new ArrayList<Mem_teacherVO>();
			
		try {
			getConn();

			sql = "select career_year, cate, id from mem_teacher where id=? ";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				Mem_teacherVO vo = new Mem_teacherVO();
				vo.setCareer_year(Integer.parseInt(rs.getString(1)));
				vo.setCate(rs.getString(2));
				vo.setId(rs.getString(3));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 학생 카테고리 검색시 보여지는 리스트(studenCateList.java)
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
	// 강사 카테고리 검색시 보여지는 리스트(studenCateList.java)
		public List<Mem_teacherVO> teaCateList(String cate) {
			List<Mem_teacherVO> lst = new ArrayList<Mem_teacherVO>();
			try {
				getConn();
				
				sql = "select a.classname, a.city, a.name, a.career from (select b.id, b.classname, b.city, b.career, m.name from membertbl m join boardtbl b on m.id=b.id where m.sort=2) a join mem_teacher t on a.id=t.id where t.cate=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cate);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Mem_teacherVO vo = new Mem_teacherVO();
					vo.setClassName(rs.getString(1));
					vo.setCity(rs.getString(2));
					vo.settName(rs.getString(3));
					vo.setCareer(rs.getString(4));
					
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
