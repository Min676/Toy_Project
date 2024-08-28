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

	public int insertUser(Users user) throws SQLException {
		return	userDao.insertUser(user);

	}
	public Users infoUser(String id , String pw) throws SQLException {
		
		return userDao.infoUser(id, pw); 
	
    }
	
	
	public int changeInfoUser(String id ,String currentPw, String newpwd) throws SQLException{
		
		return	userDao.changeInfoUser(id, currentPw, newpwd);
	}
	public int cencleUser(String id , String pw) throws SQLException{
		
		return	userDao.cancelUser(id, pw);
	}

	public static Users login(String userId, String userPwd) throws NotFoundException, SQLException {
		Users user = userDao.login(userId, userPwd);
		if (user == null)
			throw new NotFoundException("\n해당 유저가 존재하지 않습니다.");
		if(!userId.equals("admin")) {
			Session session = new Session(userId);

			SessionSet sessionSet = SessionSet.getInstance();

			sessionSet.add(session);
		}
		
		return user;
	}
	public int cashCharge(String userId, int money, int selec) throws NotFoundException, SQLException{
		if(selec==2) {
			money+=money*0.02;
		}else if(selec==3) {
			money+=money*0.01;
		}
		
		int chk=userDao.cashCharge(userId, money);
		return chk;
	}

}
