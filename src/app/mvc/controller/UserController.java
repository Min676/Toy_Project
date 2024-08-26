package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.model.service.UserService;
import app.mvc.view.FailView;
import app.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	public static void register(String userId , String userName , String userPwd) {
		
		try {   
	        // 사용자 등록 로직 호출
	        userService.insertUser(null);
	        
	        // 성공 시 사용자 메뉴 출력
	        MenuView.printUserMenu(userId);
			
		    
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
