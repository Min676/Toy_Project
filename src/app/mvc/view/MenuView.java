package app.mvc.view;

import java.util.Scanner;

import app.mvc.controller.CartController;
import app.mvc.controller.ProductController;
import app.mvc.controller.UserController;
import app.mvc.controller.OrderController;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.Orders;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());

			MenuView.printMenu();

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				// MenuView.register(); // 가입
				break;
			case 2:
				MenuView.login();// 로그인
				break;

			case 9:
				System.exit(0);
			}
		}
	}

	public static void printMenu() {
		System.out.println("=== Heejung Shopping Mall ===");
		System.out.println("1. 가입   |   2. 로그인   |  9. 종료");
	}

	public static void printUserMenu(String userId) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); // Set객체

			System.out.println("-----" + userId + " 로그인 중 -----");
			System.out.println(" 1.로그아웃 |  2.상품보기  |  3.상품 선택  | 4. 주문내역보기  |  5.장바구니 |  6.회원정보");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				logout(userId);//
				return; // 함수를 빠져나가라.
			// break;

			case 2:
				ProductController.productSelect();// 전체 상품조회
				break;
			case 3:
				printInputOrder(userId);
				break;
			case 4:
				OrderController.selectOrdersByUserId(userId);
				break;
			case 5:
				viewCart(userId);
				break;
			case 6:
				UserController.selecUser(userId);
				break;
			}
		}

	}

	public static void printSubMenu() {
		System.out.println("1. 수정   |  2.탈퇴   | 9. 나가기");
	}


	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비번 : ");
		String userPwd = sc.nextLine();

		UserController.login(userId, userPwd);
	}

	/**
	 * 로그아웃
	 */
	public static void logout(String userId) {
		Session session = new Session(userId);

		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}

	/**
	 * 상품 선택
	 */
	public static void printInputOrder(String userId) {
		System.out.print("주문상품번호 : ");
		int goodsId = Integer.parseInt(sc.nextLine());
		System.out.print("상품 개수 : ");
		int goodsCnt = Integer.parseInt(sc.nextLine());
		System.out.print("사이즈 : ");
		int selectSize = Integer.parseInt(sc.nextLine());
		
		Orders order = new Orders(0, 0, null, 0, 0);
		OrderItem orderItem = new OrderItem(0, 0, goodsId, goodsCnt, selectSize);
		
		order.getOrderItemList().add(orderItem);
		
		OrderController.orderInsert(order);
	}


	/**
	 * 장바구니 
	 */
	public static void viewCart(String id) {
		
	}

}
