package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.util.DbManager;

public class UserDAOImpl implements UserDAO {

	@Override
	public int insertUser(Users usesr) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Users login(String userId, String pw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = null;
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement("select * from USERS where user_id= ? and pw=?");
			ps.setString(1, userId);
			ps.setString(2, pw);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7));
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		return user;
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
