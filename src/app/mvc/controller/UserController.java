package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.model.service.UserService;
import app.mvc.view.FailView;
import app.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	
	public static void insertUser(Users user) {
		try {
			userService.insertUser(user);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void infoUser(String id , String pw) {
		
		try {
			userService.infoUser(id,pw);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	public void login(String userId,String userPwd){
		try {
			Users u = UserService.login(userId, userPwd);
			if(userId.equals("admin")) {
				MenuView.printAdminMenu(userId);
			}
			else
				MenuView.printUserMenu(userId, userPwd);
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
		
	}
	
	
	public static void changeInfoUser (String id, String pw) {
		
		try {
			userService.changeInfoUser(id,pw);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	
	public static void cancleUser(String userid, String pw) {
		try {
			userService.cencleUser(userid, pw);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	
		
	}
	

	
}
