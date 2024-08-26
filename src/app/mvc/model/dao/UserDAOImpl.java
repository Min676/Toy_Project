package app.mvc.model.dao;

import java.sql.SQLException;

import app.mvc.model.dto.Users;

public class UserDAOImpl implements UserDAO {

	/**
	 * 회원가입 구현부
	 * */
	@Override
	public int insertUser(Users usesr) throws SQLException {
	
		return 0;
	}

	/**
	 * 로그인 구현부
	 * */
	@Override
	public Users login(String userId, String pw) throws SQLException {
	
		return null;
	}
	/**
	 * 회원정보 수정
	 * */
	@Override
	public int selectUser(String userId, String pw) throws SQLException {
	
		return 0;
	}
	/**
	 * 회원정보 삭제
	 * */
	@Override
	public int cancleUser(String userId, String pw) throws SQLException {
		
		return 0;
	}

}
