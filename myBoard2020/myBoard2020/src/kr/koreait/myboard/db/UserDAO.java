package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.koreait.myboard.vo.UserImgVO;
import kr.koreait.myboard.vo.UserVO;

public class UserDAO {
	public static int joinUser(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_user "
				+ " (u_id, u_pw, u_nickname, email, ph, addr, sex, birth) "
				+ " values "
				+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, param.getU_id());
			ps.setString(2, param.getU_pw());
			ps.setString(3, param.getU_nickname());
			ps.setString(4, param.getEmail());
			ps.setString(5, param.getPh());
			ps.setString(6, param.getAddr());
			ps.setInt(7, param.getSex());
			ps.setString(8, param.getBirth());
			
			result = ps.executeUpdate();
//			레코드 몇개가 영향을 미치는지 result에 저장된다
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
			
		return result;	
	}

//	0: 알수 없는 에러발생
//	1: 로긴 성공
//	2: 아이디 없음
//	3: 비밀번호 틀림
	public static int doLogin(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_user WHERE u_id = ? ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getU_id());
//				DB에서 id가져온다
			rs = ps.executeQuery();
			
//			ps.execute(); -> result가 boolean
//			ps.executeUpdate(); -> result
//			ps.executeQuery();
			
			if(rs.next()) {
//				rs.next는 레코드 값의 수, 있다면 로그인된 상태 없으면 로그아웃상태
				// rs에 id 주소를 입력받았고 id가 유니크하기때문에 1, 0값만 갖는다
				String u_pw = rs.getString("u_pw");
//				DB에 u_pw 가져온다
				if(u_pw.equals(param.getU_pw())) {
					result = 1;
					
					String nickNm = rs.getString("u_nickname");
					int pkNm = rs.getInt("i_user");
					
					param.setU_pw(null);
					param.setU_nickname(nickNm);
					param.setI_user(pkNm);
					
				} else {
					result = 3;
				}
			} else {
				result = 2;
			}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		return result;
	}
	
// regUserImg
	public static int regUserImg(UserImgVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
				
		String sql = " INSERT INTO t_user_img "
				+ " (i_user, seq, img) "
				+ " VALUES "
				+ " (?, 1, ?) ";
		
		try {
			
			con = DbBridge.getCon();
			// getCon의 예외 던진것을 해결하기위해 try 적는다
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getI_user());
			ps.setString(2, vo.getImg());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
		
		return result;
	}

	// 최신 img파일 가져오기
	public static String getProfileImg(int i_user) {
		String img = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT img "
				+ " FROM t_user_img "
				+ " WHERE i_user = ? "
				+ " AND seq = 1";
		
			try {
			
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_user);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				img = rs.getString("img");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		return img;
	}
	
	public static List<UserImgVO> getProfileImgList(int i_user) {
		List<UserImgVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT seq, img "
				+ " FROM t_user_img "
				+ " WHERE i_user = ? "
				+ " ORDER BY seq DESC ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_user);			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String img = rs.getString("img");
				
				UserImgVO vo = new UserImgVO();
				vo.setSeq(seq);
				vo.setImg(img);
				
				list.add(vo);				
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		
		return list;
	}
	
	public  static void upUserImgAddSeq(UserImgVO param) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_user_img "
				+ " SET seq = seq + 1 "
				+ " WHERE i_user = ? "
				+ " ORDER BY seq DESC ";
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_user());			
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
	}
	

}
