package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.model.service.UserService;
import app.mvc.view.FailView;

public class UserController {
	static UserService userService = new UserService();
	
	public static void insertUser(Users user) {
		try {
			userService.insertUser(user);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void selecUser(String userId) {
		
	}
	
	public static void login(String userId,String userPwd){}
	
}
