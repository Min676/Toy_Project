package app.mvc.controller;

import java.sql.SQLException;
import java.util.Scanner;

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

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public void login(String userId, String userPwd) {
		try {
			Users u = UserService.login(userId, userPwd);
			if (userId.equals("admin")) {
				MenuView.printAdminMenu();
			} else
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
				MenuView.printSubMenu(id, currentPw );
				EndView.userinfoSucc();
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
	

}
