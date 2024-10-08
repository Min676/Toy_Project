package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import app.mvc.model.dto.Users;
import app.mvc.util.DbManager;

public class UserDAOImpl implements UserDAO {

	/**
	 * 회원가입 구현부
	 */
	@Override
	public int insertUser(Users user) throws SQLException { // user인수를 받는다. 예외는 sqlexception으로 던진다.

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0; // 초기화

		if (checkId(user.getUserId())) { // 아이디가 중복되었을때 SQLException 을 발생시키고, 메세지 출력
			throw new SQLException("중복된 아이디 입니다. \n회원가입이 불가능합니다.\n");
		}

		try {
			String sql = "insert into USERS (user_seq, user_id, name, pw, point, MEMBERSHIP_LEVEL,OCOUNT) values (USER_SEQ.NEXTVAL,?,?,?,10000 ,1,0) "; 																																		
																																			
			con = DbManager.getConnection(); // db에 연결
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId()); // 회원가입 유저 id 인풋
			ps.setString(2, user.getName()); // 회원가입 이름 인풋
			ps.setString(3, user.getPw()); // 회원가입 비밀번호 인풋

			result = ps.executeUpdate(); // 저장

		} finally {
			DbManager.close(con, ps, rs); // 자원반환
		}
		return result;

	}

	/**
	 * 회원ID 중복검사
	 */
	public boolean checkId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDuplicate = false;

		try {
			String query = "SELECT COUNT(USER_ID) FROM USERS WHERE USER_ID = ?";
			con = DbManager.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				isDuplicate = rs.getInt(1) > 0; // 중복되면 true 반환
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		return isDuplicate;
	}

	/**
	 * 로그인 구현부
	 */
	@Override
	public Users login(String userId, String pw) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = null;
		try {
			con = DbManager.getConnection(); // db연결
			ps = con.prepareStatement("select * from USERS where user_id= ? and pw=?"); // sql문 입력
			ps.setString(1, userId); // userid입력받음
			ps.setString(2, pw); // pw 입력받음

			rs = ps.executeQuery(); // sql쿼리생성 , rs에 집어넣는줄.
			if (rs.next()) {
				user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7));
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		return user;
	}

	/**
	 * 회원정보 보기
	 * 
	 * @return
	 */
	public Users infoUser(String id, String pw) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = null;
		try {
			con = DbManager.getConnection(); // db연결
			ps = con.prepareStatement(
					"select * from USERS U JOIN WALLET W ON U.USER_SEQ = W.USER_SEQ WHERE U.USER_ID like ? AND U.PW = ?"); // sql문
																													
			ps.setString(1, id); // id 입력받음
			ps.setString(2, pw); // pw 입력받음

			rs = ps.executeQuery();

			if (rs.next()) {
				// ResultSet에서 데이터 읽어 Users 객체에 담기
				user = new Users(rs.getInt("user_seq"), rs.getString("user_id"), rs.getString("name"),
						rs.getString("pw"), rs.getInt("point"), rs.getInt("membership_level"), rs.getInt("ocount"),
						rs.getInt("wallet_seq"), rs.getInt("cash"));
			}

		} finally {
			DbManager.close(con, ps, rs);
		}
		return user;

	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public int changeInfoUser(String userId, String currentPwd, String newPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;

		try {
			con = DbManager.getConnection(); // 데이터베이스 연결 설정

			// 두 번째 쿼리: 비밀번호를 업데이트함
			String updateSql = "UPDATE users SET pw = ? WHERE user_seq = ?";

			ps = con.prepareStatement(updateSql);
			ps.setString(1, newPwd); // 새 비밀번호 설정
			ps.setInt(2, this.getUserSeq(con, userId).getUserSeq()); // user_seq 설정

			result = ps.executeUpdate(); // 쿼리 실행 및 영향받은 행 수 반환

		} finally {
			DbManager.close(con, ps, null); // 리소스 해제
		}
		return result; // 실행 결과 반환
	}

	/**
	 * 회원정보 삭제
	 */
	@Override
	public int cancelUser(String userId, String pw) throws SQLException {

		Connection con = null;
		PreparedStatement psDeleteUser = null;
		int result = 0;
		Scanner scanner = new Scanner(System.in);

		// 탈퇴 여부 확인

		System.out.print("\n정말 회원 탈퇴를 하시겠습니까? (Y/N): ");
		String confirmation = scanner.nextLine().trim().toLowerCase();

		if (!confirmation.equals("y")) {

			return 0;
		}

		try {
			con = DbManager.getConnection(); // 데이터베이스 연결 설정

			// 부모 레코드 삭제 (자식 레코드는 ON DELETE CASCADE에 의해 자동 삭제)
			String deleteUserSQL = "DELETE FROM USERS WHERE user_id = ? AND pw = ?";
			psDeleteUser = con.prepareStatement(deleteUserSQL);
			psDeleteUser.setString(1, userId);
			psDeleteUser.setString(2, pw);

			result = psDeleteUser.executeUpdate();

		} finally {
			DbManager.close(con, psDeleteUser, null); // 리소스 해제
		}

		return result; // 실행 결과 반환

	}

	/**
	 * user_seq 가져오는 메소드
	 */
	public Users getUserSeq(Connection con, String userId) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * FROM USERS WHERE USER_ID = ?";
		Users user = null;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7));
			}
		} finally {
			DbManager.close(null, ps, rs);
		}
		return user;
	}

	@Override
	public int cashCharge(String userId, int money) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int seq=0;
		int result = 0;

		try {
			con = DbManager.getConnection(); 
			seq=findSeq(userId);
			
			ps = con.prepareStatement("UPDATE wallet SET cash  = cash+? WHERE user_seq = ?");
			ps.setInt(1, money); 
			ps.setInt(2, seq);

			result = ps.executeUpdate(); 

		} finally {
			DbManager.close(con, ps, null); 
		}
		return result;
	}

	public int findSeq(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int req = 0;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement("SELECT user_seq FROM USERS WHERE USER_ID = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				req = rs.getInt(1);
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		return req;
	}
	
	@Override
	public int selecCash(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int req = 0;
		int seq=0;

		try {
			con = DbManager.getConnection();
			seq=findSeq(id);
			ps = con.prepareStatement("SELECT cash FROM wallet WHERE user_seq = ?");
			ps.setInt(1, seq);

			rs = ps.executeQuery();
			if (rs.next()) {
				req = rs.getInt(1);
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		return req;
	}

}
