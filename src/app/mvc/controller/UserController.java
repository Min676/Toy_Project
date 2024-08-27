package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.model.service.UserService;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;
import app.mvc.view.EndView;
import app.mvc.view.FailView;
import app.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	
	public static void insertUser(Users user) {
		
		
		try {
			userService.insertUser(user); 
			 // 저장이 성공했을 때
               
			 EndView.userPrintSucc();  
            
			
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
				MenuView.printAdminMenu();
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
				EndView.userPrintSucc();
			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		 
	}
	
	
	
	public static void cancelUser(String userid, String pw) {
		try {
			
			
			userService.cencleUser(userid, pw); // 삭제된 행이 있는 경우
				    EndView.userPrintSucc();
	                Session session = new Session(userid);
	        		SessionSet ss = SessionSet.getInstance();
	        		ss.remove(session);
	               
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	
		
	}
	

	
}
