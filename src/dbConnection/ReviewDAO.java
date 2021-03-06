package dbConnection;

import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends DBConnection{

	public ReviewDAO() {
		// TODO Auto-generated constructor stub
	}
	// 
	public int commitReview(int reviewNum, int intBox, String taDetail) {
		int result = 0;
		
		try {
			getConn();
			sql = "insert into reviewtbl(class_num, score, review_detail) values (?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNum);
			pstmt.setInt(2, intBox);
			pstmt.setString(3, taDetail);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	// teach myreview
	public List<ReviewVO> teachReviewList(String id) {
		List<ReviewVO> lst = new ArrayList<ReviewVO>();
		try {
			getConn();
			
			sql="select score, review_detail from reviewtbl r join boardtbl b using(class_num) "
					+ " where b.id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setScore(rs.getInt(1));
				vo.setReview_detail(rs.getString(2));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	// table review setting
	public List<ReviewVO> tableReview(int class_num) {
		List<ReviewVO> lst = new ArrayList<ReviewVO>();
		try {
			getConn();
			
			sql="select score, review_detail from reviewtbl where class_num=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,class_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setScore(rs.getInt(1));
				vo.setReview_detail(rs.getString(2));
				
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
