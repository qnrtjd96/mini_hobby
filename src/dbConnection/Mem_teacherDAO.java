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
			
			sql = "select a.classname, a.city, a.cost, a.name, a.class_num from (select b.id, b.classname, b.city, b.cost, m.name, b.class_num, b.classdate from membertbl m join boardtbl b on m.id=b.id where m.sort=2) a join mem_teacher t on a.id=t.id where t.cate=? and a.classdate >= to_char(sysdate,'yy/mm/dd')";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Mem_teacherVO vo = new Mem_teacherVO();
				vo.setClassName(rs.getString(1));
				vo.setCity(rs.getString(2));
				vo.setCost(rs.getInt(3));
				vo.settName(rs.getString(4));
				vo.setClass_num(rs.getInt(5));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<Mem_teacherVO> getSearch(String searchWord) {
		List<Mem_teacherVO> lst = new ArrayList<Mem_teacherVO>();
		
		try {
			getConn(); 
System.out.println("getSearch > > > "+searchWord);
			sql = "select class_num, classname, city, cost, mem.name from boardtbl b, membertbl mem "
				+ "where b.id=mem.id and (b.classname like ? or mem.name like ? ) ";	//or mem.name like ?
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");;
			pstmt.setString(2, "%"+searchWord+"%");
							
			rs = pstmt.executeQuery();
							
			while(rs.next()) {
				Mem_teacherVO vo = new Mem_teacherVO();
				//글번호, 수업명, 지, 비용, 강사
				vo.setClass_num(rs.getInt(1));
				vo.setClassName(rs.getString(2));
				vo.setCity(rs.getString(3));
				vo.setCost(rs.getInt(4));
				vo.settName(rs.getString(5));
				
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
				
				sql = "select a.classname, a.city, a.name, a.career, a.class_num from (select b.id, b.classname, b.city, b.career, m.name ,b.class_num, b.classdate from membertbl m join boardtbl b on m.id=b.id where m.sort=2) a join mem_teacher t on a.id=t.id where t.cate=? and a.classdate >= to_char(sysdate,'yy/mm/dd')";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cate);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Mem_teacherVO vo = new Mem_teacherVO();
					vo.setClassName(rs.getString(1));
					vo.setCity(rs.getString(2));
					vo.settName(rs.getString(3));
					vo.setCareer(rs.getString(4));
					vo.setClass_num(rs.getInt(5));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return lst;
		}
	//선생님 회원정보 받아오기
	public List<Mem_teacherVO> getTeachInfo(String id){	//String teacher id
			
		System.out.println("DAO teacher 회원정보 받아오기 > > > "+id);
		List<Mem_teacherVO> lst = new ArrayList<Mem_teacherVO>();
				
		try {
			getConn();
			//select mem.id, pwd, name, to_char(birth, 'yyyy/mm/dd'), mail, tel, addr, cate, career_year 
			//from membertbl mem, mem_teacher tea where mem.id=tea.id and tea.id='hyunjay52';
			sql = "select mem.id, pwd, name, to_char(birth, 'YYYY/MM/DD'), mail, tel, addr, cate, career_year"
					+ " from membertbl mem, mem_teacher tea where mem.id=tea.id and tea.id=? ";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
					
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				Mem_teacherVO vo = new Mem_teacherVO();
				System.out.println("rs.getString(1) : id >>> "+rs.getString(1));
					
				vo.setId(rs.getString(1));
				vo.settPwd(rs.getString(2));
				vo.settName(rs.getString(3));
				vo.settBirth(rs.getString(4));
				vo.settMail(rs.getString(5));
				vo.settTel(rs.getString(6));
				vo.settAddr(rs.getString(7));
				vo.setCate(rs.getString(8));
				vo.setCareer_year(rs.getInt(9));
					
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	//선생님 회원정보 수정
	public int teachInfoUpdate(Mem_teacherVO vo, String id){
		int result=0;
				
		try {
			getConn();
				
			sql = "update membertbl set pwd=?, name=?, mail=?, tel=?, addr=? where id=? ";
						
			pstmt = conn.prepareStatement(sql);
						
			pstmt.setString(1, vo.gettPwd());
			pstmt.setString(2, vo.gettName());
			pstmt.setString(3, vo.gettMail());
			pstmt.setString(4, vo.gettTel());
			pstmt.setString(5, vo.gettAddr());
			pstmt.setString(6, id);
					
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//선생님 회원정보 수정2
	public int teachInfoUpdate2(Mem_teacherVO vo, String id) {
		System.out.println("DAO 선생님 정보수정 2 실행? ");
		System.out.println("2 id > >  > "+id);
		int result=0;
		try {
			getConn();
			//update mem_teacher set cate='미술', career_year=3 where id='hyunjay52';
			sql = "update mem_teacher set cate=?, career_year=? where id=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getCate());
			pstmt.setInt(2, vo.getCareer_year());
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			System.out.println("DAO 실행결과 > "+result);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//선생님 계정 삭제 참조 테이블 삭제
	public int delTeaFkTbl(String id) {
		int result = 0;
		try {
			getConn();
			sql = "delete from mem_teacher where id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}

}
