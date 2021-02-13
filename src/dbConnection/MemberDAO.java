package dbConnection;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConnection{

	public MemberDAO() {
		
	}
	//휴먼계정 조회
	//2020-02-09 이강산
	public List<MemberVO> sleepingAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MemberVO> lst= new ArrayList<MemberVO>();
		try {
			getConn(); 
			sql = "SELECT id, sort, TO_Char(login_date, 'YY/MM/DD') login_date FROM MEMBERTBL WHERE login_date <= TO_CHAR(SYSDATE-365,'YYYYMMDD')";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO2에 담고 VO2를 List에 담고
				MemberVO vo2 = new MemberVO(rs.getString(1), rs.getInt(2), rs.getString(3));
				lst.add(vo2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;	
	}
	
	//관리자에서 회원조회할떄 필요  AdminMemberList , 선생님부분
	//2021-02-08 이강산
	public List<MemberVO> TeacherAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MemberVO> lst2= new ArrayList<MemberVO>();
		try {
			getConn(); 
			sql = "select a.id, a.name, b.cate from memberTbl a join mem_teacher b on a.id = b.id where a.sort=2";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO2에 담고 VO2를 List에 담고
				MemberVO vo2 = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3));
				lst2.add(vo2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst2;
	}
		
	//관리자에서 회원조회할떄 필요 , 회원출력부분  AdminMemberList
	//2021-02-08 이강산
	public List<MemberVO> memberSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MemberVO> lst= new ArrayList<MemberVO>();
		try {
			getConn(); 
			sql = "select id, name, TO_Char(birth, 'YY/MM/DD') birth" + 
				  " from memberTbl where sort=1 order by id asc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담고
				MemberVO vo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3));
				lst.add(vo);
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 레코드 추가 - 회원등록
	public int memberInsert(MemberVO vo) {
		int result=0;
		try {
			getConn();
			
			sql = "insert into membertbl(id, pwd, name, birth, mail, tel, addr, sort) "
					+ " values(?,?,?,?,?,?,?,?)";
			// login_date는 sysdate가 default라서 뺐음
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getMail());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getAddr());
			pstmt.setInt(8, vo.getSort());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	//레코드 추가 - 강사
	public int Mem_teachInsert(Mem_teacherVO vo) {
		int result=0;
		try {
			getConn();
			
			sql = "insert into mem_teacher(career_year, cate, Id) "
					+ " values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCareer_year());
			pstmt.setString(2, vo.getCate());
			pstmt.setString(3, vo.getId());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	// 관리자 결제관리
	public List<MemberVO> memberAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql = "select id, name, count(id) from (select m.id, m.pwd, m.name, m.birth, m.mail, m.tel, m.addr, m.sort from membertbl m join stu_class s on m.id = s.id where m.sort=1) group by id, name";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO 담고 VO를 List에 담고
				MemberVO vo = new MemberVO(rs.getString(1),rs.getString(2),rs.getInt(3));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	// 아이디 중복확인
	public List<MemberVO> overlapCheck(String searchId) {
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql="select id, name from membertbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;	
	}
	// 메일주소로 아이디 찾기
	public List<MemberVO> idSearch(String searchId) {
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql = "select id, name, mail from membertbl where mail=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setMail(rs.getString(3));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// 아이디와 메일주소로 패스워드 찾기
	public List<MemberVO> pwdSearch(String searchId, String searchMail) {
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql = "select id, pwd, name, mail from membertbl where id=? and mail=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			pstmt.setString(2, searchMail);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setMail(rs.getString(4));
				
				lst.add(vo);
			}
		
		} catch(Exception e) {
			e.printStackTrace();	
		} finally {
			dbClose();
		}
		return lst;
	}
	// 로그인 하기
	public List<MemberVO> loginStart(String searchId, String searchPwd) {
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql = "select id, pwd, name, sort, black from membertbl where id=? and pwd=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			pstmt.setString(2, searchPwd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setSort(rs.getInt(4));
				vo.setBlack(rs.getInt(5));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();	
		} finally {
			dbClose();
		}
		return lst;
	}
	
	//회원정보 받아오기
	public List<MemberVO> getMemberInfo(String idStr){	//String stuId
			
		List<MemberVO> lst = new ArrayList<MemberVO>();
			
		try {
			getConn();

			sql = "select id, pwd, name, to_char(birth, 'YYYY/MM/DD'), mail, tel, addr, sort from membertbl where id=? ";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				System.out.println("rs.getString(1) : id >>> "+rs.getString(1));
				vo.setId(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setBirth(rs.getString(4));
				vo.setMail(rs.getString(5));
				vo.setTel(rs.getString(6));
				vo.setAddr(rs.getString(7));
				vo.setSort(rs.getInt(8));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
		
	//회원정보 수정
	public int memberUpdate(MemberVO vo, String idStr){
			
		int result=0;
			
		try {
			getConn();
				
			sql = "update membertbl set pwd=?, name=?, mail=?, tel=?, addr=? where id=? ";
				
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getMail());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, idStr);
				
			result=pstmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
			
		return result;
	}
	//2021.02.11 이강산
	//휴면계정 admin sleep user
	public int memberDelete(String id) {
		System.out.println("dao id ? ? ? "+id);
		int result = 0;
		try {
			getConn();
			sql = "delete from membertbl where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println("dao id ? ? ? "+id);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		System.out.println("result? ? ? "+result);
		return result;
	}
	//학생 계정 탈퇴
	public int stuDelete(String idStr) {
		System.out.println("delete dao > > > "+idStr);
		int result = 0;
		try {
			getConn();
			sql = "delete from membertbl where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idStr);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//adminMain 총 이용자 수
	public List<MemberVO> allUserCnt() {
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			sql = "select count(id) from membertbl";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserCnt(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	// blacklist update
	public int blackUpdate(String id) {
		int result=0;
		try {
			getConn();
			
			sql="update membertbl set black=2 where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// blacklist delete
	public int blackDelete(String id) {
		int result=0;
		try {
			getConn();
			
			sql="update membertbl set black=1 where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// blacklist check
	public int blackSearch(String id) {
		int result=0;
		try {
			getConn();
			
			sql="select black from membertbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	
}
