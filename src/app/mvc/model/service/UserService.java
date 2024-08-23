package app.mvc.model.service;

import java.sql.SQLException;
import app.mvc.model.dao.UserDAO;
import app.mvc.model.dao.UserDAOImpl;
import app.mvc.model.dto.Users;




public class UserService{
	static UserDAO userDao = new UserDAOImpl();
	
	public void insertUser(Users user) throws SQLException{
		userDao.insertUser(user);
		
	}
	
}
