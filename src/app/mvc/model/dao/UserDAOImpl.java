package app.mvc.model.dao;

import java.sql.SQLException;

import app.mvc.model.dto.Users;

public class UserDAOImpl implements UserDAO {

	@Override
	public int insertUser(Users usesr) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Users login(String userId, String pw) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUser(String userId, String pw) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancleUser(String userId, String pw) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
