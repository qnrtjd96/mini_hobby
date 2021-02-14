package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class Stu_ClassDAO extends DBConnection{

	public Stu_ClassDAO() {
		
	}
	//2021-02-14 이강산
	//회원 탈퇴(StudenDeleteUser)
	public int deleteMember(String idStr) {
		int result = 0;
		try {
			getConn();
			sql = "delete from STU_CLASS where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.getMessage();
		}finally {
			dbClose();
		}
		return result;
	}
	//2021-02-12 이강산
	//학생 결제내역(마이페이지)(StudenMyPage)
	public List<Stu_ClassVO> payStuSum(String date, String idStr) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select count(class_num) from STU_CLASS where substr(pay_date, 0,5) = ? and id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, idStr);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setPay(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			dbClose();  
		}
		return lst;
	}
	
	//2021-02-12 이강산
	//선생님 총수익(TeachTotal)
	public List<Stu_ClassVO> paytotalSum(String date, String idStr) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select sum(a.pay) from stu_class a join BOARDTBL b on a.pay_class=b.classname where substr(a.pay_date, 0,5) = ? and b.id= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, idStr);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setPay(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			dbClose();  
		}
		return lst;
		
	}
	//2021-02-12 이강산
	//선생님 총수익(TeachTotal)
	public List<Stu_ClassVO> payTeaSum(String idStr) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select sum(a.pay) from stu_class a join BOARDTBL b on a.pay_class=b.classname where b.id= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vob = new Stu_ClassVO();
				vob.setPay(Integer.parseInt(rs.getString(1)));
				lst.add(vob);
			}
		} catch(Exception e) {
			e.getMessage();
		} finally {
			dbClose();
		}
		return lst;
	}
	
	//2021-02-11 이강산
	//학생내정보메인(StudenMypage) 캘린더 연동
	public List<Stu_ClassVO> StuCalendar(String id) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select to_char(classdate, 'yyyy-mm-dd') from stu_class where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vob = new Stu_ClassVO();
				vob.setClassdate(rs.getString(1));
				lst.add(vob);
			}
		} catch(Exception e) {
			e.getMessage();
		} finally {
			dbClose();
		}
		return lst;
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
	//teachincome 리스트
	public List<Stu_ClassVO> teachIncomeList(String id, String date) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql = "select s.pay_cate, m.name, s.pay from stu_class s join membertbl m on s.id = m.id join boardtbl b on s.pay_class = b.classname where b.id = ? and substr(s.pay_date, 0,5) = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO voClass = new Stu_ClassVO();
				voClass.setPay_cate(rs.getString(1));
				voClass.setsName(rs.getString(2));
				voClass.setPay(rs.getInt(3));
				
				lst.add(voClass);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	//학생 수업 취소 
	public int deletStuClass(String idStr, String sendDelStr) {
		int result = 0;
		try {
			getConn();
			sql = "delete from stu_class where id = ? and classdate = to_date(?, 'yyyy-mm-dd') ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			pstmt.setString(2, sendDelStr);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return result;
	}
	
	//학생 구매내역 > 달력 클릭 시 
		public List<Stu_ClassVO> showClickPurchase(String idStr) {
			System.out.println("....선택 날짜 예약 중인 클래스 DAO 실행 됨?");
			
			List<Stu_ClassVO> dueLst = new ArrayList<Stu_ClassVO>();
			
			try {
				getConn();
				
				sql = "select stu.id ,stu.pay_class, bor.id, to_char(stu.classdate, 'mm/dd'), stu.classtime, bor.area "
						+ "from stu_class stu, boardtbl bor where stu.class_num=bor.class_num "
							+ "and stu.id=? and stu.classdate >= to_char(sysdate, 'yy/mm/dd')";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, idStr);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {	//담는거 까먹지 말기 ^^
					Stu_ClassVO vo = new Stu_ClassVO(
								rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6)
							);
					
					dueLst.add(vo);
					System.out.println("반복문에 담기나?"+dueLst);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			System.out.println("dueLst에 뭐가 있는지...? "+dueLst);
			return dueLst;
			
		}
	
	//학생 구매내역 > 예약 중인 내역
	public List<Stu_ClassVO> showDuePurchase(String idStr) {
		System.out.println("....예약 중인 클래스 DAO 실행 됨?");
		
		List<Stu_ClassVO> dueLst = new ArrayList<Stu_ClassVO>();
		
		try {
			getConn();

			//stu.id ,stu.pay_class, bor.id, to_char(stu.classdate, 'mm/dd'), stu.classtime, bor.area "
			sql = "select s.id, s.pay_class, b.id, s.classdate, s.classtime, b.area from stu_class s, boardtbl b "
					+ "where s.class_num=b.class_num and s.id = ? and s.classdate >= sysdate ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	//담는거 까먹지 말기 ^^
				Stu_ClassVO vo = new Stu_ClassVO(
							rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6)
						);
				
				dueLst.add(vo);
				System.out.println("반복문에 담기나?"+dueLst);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		System.out.println("dueLst에 뭐가 있는지...? "+dueLst);
		return dueLst;
		
	}
	
	//학생 구매내역 > 전체 중인 클래스 ... 조건 달아주기
	public List<Stu_ClassVO> showAllPurchase(String idStr) {
		System.out.println("...  구매내역 DAO 실행 됨?");
		
		List<Stu_ClassVO> allLst = new ArrayList<Stu_ClassVO>();
		
		try {
			getConn();

			sql = "select stu.id ,stu.pay_class, bor.id, to_char(stu.classdate, 'mm/dd'), stu.classtime, bor.area "
					+ "from stu_class stu, boardtbl bor where stu.class_num=bor.class_num "
						+ "and stu.id=? and stu.classdate <= to_char(sysdate, 'yy/mm/dd') ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	//담는거 까먹지 말기 ^^
				Stu_ClassVO vo = new Stu_ClassVO(
							rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6)
						);
				//vo.setId(rs.getString(1));
				//vo.setPay_class(rs.getString(2));
				//vo.setTeach_id(rs.getString(3));
				//vo.setClassdate(rs.getString(4));
				//vo.setClasstime(rs.getString(5));
				//vo.setArea(rs.getString(6));
				
				allLst.add(vo);
				System.out.println("반복문에 담기나?"+allLst);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		System.out.println("dueLst에 뭐가 있는지...? "+allLst);
		return allLst;
		
	}
	// 관리자 월별 수익
	public List<Stu_ClassVO> paySum(String date){
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql = "select sum(pay) from stu_class where substr(pay_date, 0,5) = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setPay(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();  
		}
		return lst;
		
	}
	// 관리자 월별 실결제건수
	public List<Stu_ClassVO> payCount(String date){
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql = "select count(class_num) from stu_class where substr(pay_date, 0,5) = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setPay(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();  
		}
		return lst;
	}
	public int insertPay(Stu_ClassVO vo) {
		int result=0;
		try {
			getConn();
			
			sql="insert into stu_class (class_num, id, pay_class, pay_cate, pay, pay_date, classdate, classtime) "
					+ " values(?, ?, ?, ?, ?, sysdate, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getClass_num());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPay_class());
			pstmt.setString(4, vo.getPay_cate());
			pstmt.setInt(5, vo.getPay());
			pstmt.setString(6, vo.getClassdate());
			pstmt.setString(7, vo.getClasstime());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	// teach 수강클래스 내역 뽑기
	public List<Stu_ClassVO> teachList(String id) {
		List<Stu_ClassVO> lst = new ArrayList<Stu_ClassVO>();
		try {
			getConn();
			
			sql="select pay_class, pay, s.classdate, s.id from stu_class s join boardtbl b "
					+ " using(class_num) where b.id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stu_ClassVO vo = new Stu_ClassVO();
				vo.setPay_class(rs.getString(1));
				vo.setPay(rs.getInt(2));
				vo.setClassdate(rs.getString(3));
				vo.setId(rs.getString(4));
				
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
