package dbConnection;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConnection{

	public MemberDAO() {
		
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
			
			sql = "select id, pwd, name, sort from membertbl where id=? and pwd=?";
			
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
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();	
		} finally {
			dbClose();
		}
		return lst;
	}
	// 내정보 로그인하기
	public String loginInfo(String id) {
		String pwd = null;
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			
			sql="select pwd from membertbl where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setPwd(rs.getString(1));
				
				lst.add(vo);
			}
			pwd = lst.get(0).getPwd();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return pwd;
	}

}
