package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends DBConnection{

	public BoardDAO() {
		
	}
	//2021-02-13 이강산
		//선생님 새글쓰기(TeachTextCreate) 안돼서 다시만듬
		public int insertTeaBoard(BoardVO vo) {
			int result = 0;
			try {
				getConn();
				sql="insert into boardtbl (class_num, id, classname, cate, city, cost, intro, "
						+ " career, area, writedate, classdate, classtime) values (classnum.nextval, ?,?,?,?,?,?,?,?,sysdate,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getClassname());
				pstmt.setString(3, vo.getCate());
				pstmt.setString(4, vo.getCity());
				pstmt.setInt(5, vo.getCost());
				pstmt.setString(6, vo.getIntro());
				pstmt.setString(7, vo.getCareer());
				pstmt.setString(8, vo.getArea());
				pstmt.setString(9, vo.getClassdate());
				pstmt.setString(10, vo.getClasstime());
				
				result = pstmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}
	// 새글쓰기 insert
		public int insertBoard(BoardVO vo) {
			int result = 0;
			try {
				getConn();
				////int class_num, String id, String classname, String cate, String review,
				//String city, int cost, String intro, String career, String area, String writedate, classtime
				sql="insert into boardtbl (class_num, id, classname, cate, city, cost, intro, "
						+ " career, area, writedate, classdate, classtime) values (classnum.nextval, ?,?,?,?,?,?,?,?,sysdate,?,?)";
				
				//vo2.getId(), classname2.getText(), vo2.getCate(), dbarea, cost, 
				//classdetail2.getText(),total2.getText(), detail2.getText(), date
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getClassname());
				pstmt.setString(3, vo.getCate());
				pstmt.setString(4, vo.getCity());
				pstmt.setInt(5, vo.getCost());
				pstmt.setString(6, vo.getIntro());
				pstmt.setString(7, vo.getCareer());
				pstmt.setString(8, vo.getArea());
				pstmt.setString(9, vo.getClassdate());
				pstmt.setString(10, vo.getClasstime());
				
				result = pstmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}
	// 관리자 결제관리 상세내역
	public List<BoardVO> PaymentSelect(String searchId) {
		//선택한 레코드를 보관할 컬렉션
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql = "select b.class_num, b.id, m.name, b.cost, to_char(s.pay_date, 'YYYY/MM/DD') from stu_class s join boardtbl b on s.class_num = b.class_num join membertbl m on b.id = m.id where s.id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO 담고 VO를 List에 담고
				BoardVO vo = new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 상세정보에서 쓸거
	public List<BoardVO> detailBoard(int class_num) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql = "select cate, to_char(classdate, 'yyyy-mm-dd'), classtime from boardtbl where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, class_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vob = new BoardVO();
				vob.setCate(rs.getString(1));
				vob.setClassdate(rs.getString(2));
				vob.setClasstime(rs.getString(3));
				
				lst.add(vob);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 테이블세팅
	public List<BoardVO> detailTable(int class_num) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql = "select class_num, id, classname, cate, city, cost, intro, career, area, "
					+ " to_char(writedate, 'yyyy-mm-dd'), to_char(classdate, 'yyyy-mm-dd'), classtime "
					+ " from boardtbl where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, class_num);;
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vob = new BoardVO();
				vob.setClass_num(rs.getInt(1));
				vob.setId(rs.getString(2));
				vob.setClassname(rs.getString(3));
				vob.setCate(rs.getString(4));
				vob.setCity(rs.getString(5));
				vob.setCost(rs.getInt(6));
				vob.setIntro(rs.getString(7));
				vob.setCareer(rs.getString(8));
				vob.setArea(rs.getString(9));
				vob.setWritedate(rs.getString(10));
				vob.setClassdate(rs.getString(11));
				vob.setClasstime(rs.getString(12));
				
				lst.add(vob);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 캘린더연동할거
	public List<BoardVO> boardCalendar(String id) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql="select to_char(classdate, 'yyyy-mm-dd') from boardtbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vob = new BoardVO();
				vob.setClassdate(rs.getString(1));
				
				lst.add(vob);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// mylist연동
	public List<BoardVO> teachMyList(String id) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql="select class_num, classname, area, to_char(classdate, 'yyyy-mm-dd'), classtime from boardtbl"
					+ " where id=? order by classdate asc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vob = new BoardVO();
				vob.setClass_num(rs.getInt(1));
				vob.setClassname(rs.getString(2));
				vob.setArea(rs.getString(3));
				vob.setClassdate(rs.getString(4));
				vob.setClasstime(rs.getString(5));
				
				lst.add(vob);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 강사 detail - 시간 수정 dialog
	public int updateTime(String selStr, String id, String classname, String time) {
		int result=0;
		try {
			getConn();
			
			sql="update boardtbl set classtime=? where id=? and classname=? and classdate=to_date(?, 'yyyy-mm-dd') ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selStr);
			pstmt.setString(2, id);
			pstmt.setString(3, classname);
			pstmt.setString(4, time);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			
		}finally {
			dbClose();
		}
		return result;
	}
	// 강사 detail - 정보 수정 dialog
	public int updateDetail(BoardVO vo) {
		int result=0;
		try {
			getConn();
			
			sql="update boardtbl set classname=?, city=?, cost=?, intro=?, career=?, area=? where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getClassname());
			pstmt.setString(2, vo.getCity());
			pstmt.setInt(3, vo.getCost());
			pstmt.setString(4, vo.getIntro());
			pstmt.setString(5, vo.getCareer());
			pstmt.setString(6, vo.getArea());
			pstmt.setInt(7, vo.getClass_num());
			
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			
		}finally {
			dbClose();
		}
		return result;
	}
	// detail - delete
	public int deleteDetail(int class_num) {
		int result=0;
		try {
			getConn();
			
			sql="delete from boardtbl where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, class_num);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			
		}finally {
			dbClose();
		}
		return result;
	}
	public List<BoardVO> deleteConfirm(int class_num) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql="select classname from boardtbl b join stu_class s using (class_num) where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, class_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setClassname(rs.getString(1));
				lst.add(vo);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 학생상세정보 - 클래스정보 얻어오기
	public List<BoardVO> studenInfo(int class_num) {
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			
			sql="select cate, to_char(classdate, 'yyyy-mm-dd'), classtime, classname from boardtbl where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, class_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCate(rs.getString(1));
				vo.setClassdate(rs.getString(2));
				vo.setClasstime(rs.getString(3));
				vo.setClassname(rs.getString(4));
				
				lst.add(vo);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 학생 - 예약완료시 클래스정보시간update
	public int updateTime(int class_num, String classtime) {
		int result = 0;
		try {
			getConn();
			
			sql="update boardtbl set classtime=? where class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classtime);
			pstmt.setInt(2, class_num);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
}
