package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import app.mvc.model.dto.Users;

public interface UserDAO {
	/**
	 * 회원 가입
	 */
	int insertUser(Users user) throws SQLException;
	
	/**
	 * 로그인하기
	 */
	Users login(String userId, String pw) throws SQLException;
	
	/**
	 * 회원 정보 보기
	 * */
	
	Users infoUser(String userId, String pw) throws SQLException;
	
	/**
	 * 회원 정보 수정
	 * @return 
	 */
	int changeInfoUser(String userId, String pw) throws SQLException;
	
	/**
	 * 회원 삭제(탈퇴)
	 */
	int cancleUser(String userId, String pw) throws SQLException;
	
	/**
	 * user_seq 가져오는 메소드
	 */
	Users getUserSeq(Connection con, String userId) throws SQLException;
}
