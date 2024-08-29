package app.mvc.controller;

import java.sql.SQLException;
import java.util.Scanner;

import app.mvc.exception.NotFoundException;
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
			MenuView.menu();

		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void infoUser(String id, String pw) {

		try {
			EndView.userSelectAll(userService.infoUser(id, pw));
			MenuView.printSubMenu(id, pw);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public void login(String userId, String userPwd) {
		try {
			Users u = UserService.login(userId, userPwd);


			if(userId.equals("admin")) {
				MenuView.printAdminMenu(userId);
			}
			else
				MenuView.printUserMenu(userId, userPwd);
			// MenuView.menu();
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}

	}

	public static void changeInfoUser(String id, String currentPw,String newpwd) {
		
			

		try {
			
			int user = userService.changeInfoUser(id, currentPw, newpwd);

			if (user > 0) {
				EndView.userinfoSucc();
				MenuView.printSubMenu(id, newpwd);
				
			} else {
				FailView.errorMessage("수정에 실패하였습니다.");
			}

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void cancelUser(String userid, String pw) {
		try {

			int user = userService.cencleUser(userid, pw); // 삭제된 행이 있는 경우
			if (user > 0) {
				EndView.userDelSucc();
				Session session = new Session(userid);
				SessionSet ss = SessionSet.getInstance();
				ss.remove(session);
				MenuView.menu();
			} else {
				FailView.errorMessage("회원 탈퇴에 실패하셨습니다.");
			}

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static int cashCharge(String userId, int money, int selec) {
		int chk=0;
		try {
			chk=userService.cashCharge(userId, money, selec);
			if(chk>0)
				EndView.SuccesMessage("충전 성공");
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}
	
	public static int selecCash(String userId){
		int chk=0;
		try {
			chk = userService.selecCash(userId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

}
