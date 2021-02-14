package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class ConsDAO extends DBConnection{

	public ConsDAO() {}
	//받은메세지
	public List<ConsVO> studenMsgRec(String id){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			
			sql = "select msg_num, get, send, msg_title, msg_detail, to_char(send_time, 'YYYY/MM/DD HH:MI') from constbl where get = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	//받은메세지 삭제
	public int msgDelete(int num) {
		int result = 0;
		try {
			getConn();
			sql = "delete from constbl where msg_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//받은메세지 다이어로그
	public List<ConsVO> studenDiaMsgRec(String id, int msgNum){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			sql = "select msg_num, get, send, msg_title, msg_detail, to_char(send_time, 'YYYY/MM/DD HH:MI') from constbl where get = ? and msg_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, msgNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	//관리자 보낸메세지 
	public List<ConsVO> adminSendMsgRec(){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			
			sql = "select c.msg_num, c.get, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.get=m.id where send = 'master' ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO();
				vo.setMsg_num(rs.getInt(1));
				vo.setGet(rs.getString(2));	
				vo.setSort(rs.getInt(3));
				vo.setMsg_title(rs.getString(4));
				vo.setMsg_detail(rs.getString(5));
				vo.setSend_time(rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	//보낸 메세지함 관련 ..
	public List<ConsVO> userSendMsgRec(String id){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			
			sql = "select msg_num, get, send, msg_title, msg_detail, to_char(send_time, 'YYYY/MM/DD HH:MI') from constbl where send = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	//관리자 받은메세지
	public List<ConsVO> adminMsgRec(){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			
			sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	//관리자 받은메세지 정렬하기
	public List<ConsVO> adminMsgSort(String sort){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		
		try {
			getConn();
			if(sort.equals("글번호")) {
				sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master' order by c.msg_num";
			}else if(sort.equals("보낸사람")) {
				sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master' order by c.send";
			}else if(sort.equals("분류")) {
				sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master' order by m.sort";
			}else if(sort.equals("수신시간")) {
				sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master' order by c.send_time";
			}else {
				sql = "select c.msg_num, c.send, m.sort, c.msg_title, c.msg_detail, to_char(c.send_time, 'YYYY/MM/DD HH:MI') from constbl c join membertbl m on c.send=m.id where get = 'master'";
			}
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	// Teach 답장하기
	public List<ConsVO> replyInfo(int msgNum) {
		List<ConsVO> lst = new ArrayList<ConsVO>();
		try {
			getConn();
			
			sql="select get, send, msg_title from constbl where msg_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, msgNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConsVO vo = new ConsVO();
				vo.setGet(rs.getString(1));
				vo.setSend(rs.getString(2));
				vo.setMsg_title(rs.getString(3));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	public int insertReply(ConsVO vo) {
		int result=0;
		try {
			getConn();
			
			sql="insert into constbl(msg_num, get, send, msg_title, msg_detail, send_time) "
					+ " values(msg_num.nextval, ?, ?, ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getGet());
			pstmt.setString(2, vo.getSend());
			pstmt.setString(3, vo.getMsg_title());
			pstmt.setString(4, vo.getMsg_detail());
			
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}

}
