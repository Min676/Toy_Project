package app.mvc.model.service;

import java.sql.SQLException;

import app.mvc.exception.NotFoundException;
import app.mvc.model.dao.UserDAO;
import app.mvc.model.dao.UserDAOImpl;
import app.mvc.model.dto.Users;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;

public class UserService {
	static UserDAO userDao = new UserDAOImpl();

	public void insertUser(Users user) throws SQLException {
		userDao.insertUser(user);

	}

	public static Users login(String userId, String userPwd) throws NotFoundException, SQLException {
		Users user = userDao.login(userId, userPwd);
		if (user == null)
			throw new NotFoundException("유저 정보 못 찾음");
		
		Session session = new Session(userId);

		SessionSet sessionSet = SessionSet.getInstance();

		sessionSet.add(session);

		return user;
	}

}
