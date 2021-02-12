package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class ConsDAO extends DBConnection{

	public ConsDAO() {}
	//받은메세지, 보낸메세지
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
			//select msg_num, get, send, msg_title, msg_detail, send_time from constbl where send='hyunjay52' and msg_num='5';			
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
	//보낸메세지 다이어로그 
	public List<ConsVO> userSendDiaRec(String id, int msgNum){
		List<ConsVO> lst = new ArrayList<ConsVO>();
		try {
			getConn();
			sql= "select msg_num, get, send, msg_title, msg_detail, to_char(send_time, 'YYYY/MM/DD HH:MI' from constbl where send = ? and msg_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, msgNum);
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

}
