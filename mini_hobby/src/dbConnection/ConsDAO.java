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
	// 메시지 보내기 (constbl 저장)
	public int sendMsg(ConsVO vo) {
		int result=0;
		try {
			getConn();
			
			sql="insert into constbl (msg_num, get, send, msg_title, msg_detail, send_time)"
					+ " values(msg_num.nextval, ?, ?, ?, ?, sysdate)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getGet());
			pstmt.setString(2, vo.getSend());
			pstmt.setString(3, vo.getMsg_title());
			pstmt.setString(4, vo.getMsg_detail());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}

}
