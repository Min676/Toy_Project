package app.mvc.view;

import app.mvc.model.dto.Products;
import java.util.Scanner;

import app.mvc.controller.OrderController;
import app.mvc.controller.ProductController;
import app.mvc.controller.StatisController;
import app.mvc.controller.UserController;
import app.mvc.controller.OrderController;
import app.mvc.model.dto.Orders;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	static UserController userController = new UserController();

	public static void menu() {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());

			MenuView.printMenu();

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.register(); // 가입
				break;
			case 2:
				MenuView.login();// 로그인
				break;
			case 3:
				MenuView.printAdminMenu();// 로그인
				break;

			case 9:
				System.exit(0);
			}
		}
	}

	public static void printMenu() {
		System.out.println("=================Coffe Shop===============");
		System.out.println("1. 가입   |   2. 로그인   | 3.바로가기  9. 종료");
	}

	public static void printUserMenu(String userId) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); // Set객체

			System.out.println("-----" + userId + " 로그인 중 -----");
			System.out.println(" 1.로그아웃 |  2.상품보기  |  3.상품 선택  | 4. 주문내역보기  |  5.장바구니 |  6.회원정보");
			int menu = sc.nextInt();
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
	 * 회원가입 메뉴
	 */

	private static void register() {
		System.out.println("아이디 등록 : ");
		String userId = sc.nextLine();
		System.out.println("회원성명 등록: ");
		String userName = sc.nextLine();
		System.out.println("비밀번호 등록: ");
		String userPwd = sc.nextLine();

		UserController.register(userId, userName, userPwd);

	}

	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비번 : ");
		String userPwd = sc.nextLine();

		userController.login(userId, userPwd);
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
		int breakPoint = 1;
		while (breakPoint == 1) {
			System.out.print("주문상품번호 : ");
			String goodsId = sc.nextLine();
		}
	}

	/**
	 * 상품보기
	 */
	public static void printProduct() {
		System.out.println("====================== 상품 보기 ===============================");
		System.out.println("1.상품 전체 보기 |  2.카테고별로 보기  |  3.판매순으로 보기 ");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			ProductController.productSelect();// 전체 상품조회
			break;
		case 2:
			System.out.println("카테고리를 선택해주세요 (1. 커피 2. 음료 3. 디저트 4. 기타 ");
			int categoryNum = sc.nextInt();
			ProductController.productSelectByCategory(categoryNum);
			break;
		case 3:

			break;
		}
	}

	/**
	 * 장바구니
	 */
	public static void viewCart(String id) {

	}

	public static void printAdminMenu() {
		while (true) {
			System.out.println("======================ADMIN_MENU===============================");
			System.out.println("1.로그아웃 |  2.상품보기  |  3.상품 등록 | 4.상품 수정 | 5.상품 삭제 | 6. 통계  ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				menu();
				return;
			case 2:
				ProductController.productSelect();// 전체 상품조회
				break;
			case 3:
				productInsert();
				break;
			case 4:
				totalMenu();
				productUpdate();
				break;
			case 5:
				productDelete();
				break;
			case 6:
				totalMenu();
				break;
			}
		}

	}

	public static void totalMenu() {
		while (true) {
			System.out.println("======================통계===============================");
			System.out.println("1.전체 통계 |  2.카테고리별 통계 |  3.일별 통계  | 4. 월별 통계  ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				StatisController.selectTotal();
				break;
			case 2:
				cat_selec();
				break;
			case 3:

				break;
			case 4:
				StatisController.selectTotal();
				break;
			}
		}
	}

	public static void cat_selec() {
		System.out.println("\t 1.커피");
		System.out.println("\t 2.음료");
		System.out.println("\t 3.디저트");
		System.out.print("입력 : ");
		int selecNum = sc.nextInt();
		
		switch (selecNum) {
		case 1:
			StatisController.selectTotal();
			break;
		case 2:
			StatisController.selectTotal();
			break;
		case 3:
			StatisController.selectTotal();
			break;
		}
	}

	public static void productInsert() {

		System.out.println("====================== 상품 등록 ===============================");
		System.out.println("등록할 상품명을 입력해주세요");
		String name = sc.next();
		System.out.println("등록할 상품정보를 입력해주세요");
		String info = sc.next();
		System.out.println("등록할 상품의 가격을 입력해주세요");
		int price = sc.nextInt();
		System.out.println("등록할 상품의 카테고리 분류를 입력해주세요(1. 커피 2. 음료 3. 디저트 4. 기타");
		int category = sc.nextInt();

		Products products = new Products(name, info, price, category);
		ProductController.productInsert(products);
	}

	public static void productUpdate() {

		System.out.println("====================== 상품 수정 ===============================");
		System.out.println("수정할 상품명ID를 입력해주세요");
		int id = sc.nextInt();
		System.out.println("등록할 상품명을 입력해주세요");
		String name = sc.next();
		System.out.println("등록할 상품정보를 입력해주세요");
		String info = sc.next();
		System.out.println("등록할 상품의 가격을 입력해주세요");
		int price = sc.nextInt();

		Products products = new Products(id, name, info, price);
		ProductController.productUpdateByProductId(products);
	}

	public static void productDelete() {

		System.out.println("====================== 상품 삭제 ===============================");
		System.out.println("삭제할 상품명ID를 입력해주세요");
		int id = sc.nextInt();
		ProductController.productDeleteByProductId(id);

	}
}
