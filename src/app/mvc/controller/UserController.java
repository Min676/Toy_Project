package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.model.service.UserService;
import app.mvc.view.FailView;
import app.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	public static void register(String userId,String userName,String userPwd) {
		
		try {
			
			// 유효성 검사
            if (userId == null || userId.isEmpty() || userName == null || userName.isEmpty() || userPwd == null || userPwd.isEmpty()) {
               
            }
            
            
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	
	public static void insertUser(Users user) {
		try {
			userService.insertUser(user);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void selecUser(String userId) {
		
	}
	
	public void login(String userId,String userPwd){
		try {
			Users u = UserService.login(userId, userPwd);
			if(userId.equals("admin")) {
				MenuView.printAdminMenu();
			}
			else
				MenuView.printUserMenu(userId);
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
		
	}
	
}
