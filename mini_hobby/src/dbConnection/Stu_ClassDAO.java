package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class Stu_ClassDAO extends DBConnection{

	public Stu_ClassDAO() {
		
	}
	// teachmain에서 목록띄우기
	public List<Stu_ClassVO> teachReservationList(String id) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql = "select class_num, s.pay_class, to_char(s.classdate, 'yyyy-mm-dd'), s.id from stu_class s join boardtbl b "
					+ " using(class_num) where b.id=? and s.classdate>sysdate order by s.classtime";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO voClass = new Stu_ClassVO();
				voClass.setClass_num(rs.getInt(1));
				voClass.setPay_class(rs.getString(2));
				voClass.setClassdate(rs.getString(3));
				voClass.setId(rs.getString(4));
				
				lst.add(voClass);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 누적수강생 수 받아오기
	public int teachCountStu(String id) {
		int result = 0;
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select s.class_num from stu_class s join boardtbl b "
					+ " on s.class_num=b.class_num where b.id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setClass_num(rs.getInt(1));
				lst.add(vo);
			}
			result = lst.size();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	// 시간라벨적용하기
	public List<Stu_ClassVO> teachTime(String id) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select to_char(s.classdate, 'yyyy-mm-dd'), substr(s.classtime,0,5), s.id from stu_class s "
					+ " join boardtbl b using(class_num) where b.id=? and s.classdate>sysdate "
					+ " order by s.classdate asc, s.classtime asc";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setClassdate(rs.getString(1));
				vo.setClasstime(rs.getString(2));
				vo.setId(rs.getString(3));
				
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
