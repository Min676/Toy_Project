package app.mvc.view;

import java.util.Scanner;

import app.mvc.controller.CartController;
import app.mvc.controller.OrderController;
import app.mvc.controller.ProductController;
import app.mvc.controller.StatisController;
import app.mvc.controller.UserController;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Users;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	static UserController userController = new UserController();

	public static void menu() {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet());

			MenuView.printMenu();
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				MenuView.register(); // 가입
				break;
			case 2:
				MenuView.login();// 로그인
				break;
			case 9:
				System.out.println("\n============== 커피 키오스크 종료합니다 ==============");
				System.exit(0);
			}
		}
	}

	public static void printMenu() {
		System.out.println("\n===================Coffee Shop=================");
		System.out.println("1. 가입   |   2. 로그인   |   9. 종료");
	}

	public static void printUserMenu(String userId, String userPw) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet()); // Set객체

			System.out.println("\n----------" + userId + "님 로그인 중 ----------");
			System.out.println(" 1.로그아웃 |  2.상품보기  |  3.주문할 상품 선택  | 4. 주문내역보기  |  5.장바구니 |  6.회원정보");

			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				logout(userId);//
				return; // 함수를 빠져나가라.
			case 2:
				printProduct(userId);
				break;
			case 3:
//				printInputOrder(userId);
				putCart(userId);
				break;
			case 4:
				OrderController.selectOrdersByUserId(userId);
				break;
			case 5:
				viewCart(userId);
				break;
			case 6:
				MenuView.printSubMenu(userId, userPw);
				break;
			}
		}

	}

	public static void printSubMenu(String userId, String userPw) {

		System.out.println("\n1. 정보확인   |	 2.비밀번호 수정  |  3. 탈퇴   | 9. 나가기");

		int menu = sc.nextInt();

		switch (menu) {
		case 1:
			UserController.infoUser(userId, userPw);
			break;

		case 2:
			changeInfoUser(userId, userPw);
			break;
		case 3:
			cancelUser(userId, userPw);

			break;
		case 4:
			return;
		}

	}

	/**
	 * 회원가입 메뉴
	 */
	private static void register() {
		sc.nextLine();

		System.out.print("아이디 등록 : ");
		String userId = sc.nextLine();

		System.out.print("회원성명 등록: ");
		String userName = sc.nextLine();

		System.out.print("비밀번호 등록: ");
		String userPwd = sc.nextLine();

		Users user = new Users(userId, userName, userPwd);
		UserController.insertUser(user);
	}

	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		sc.nextLine();
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();

		System.out.print("비밀번호 입력 : ");
		String userPwd = sc.nextLine();

		userController.login(userId, userPwd);
	}

	/**
	 * 
	 * 회원 비밀번호 수정
	 */

	public static void changeInfoUser(String userId, String userPw) {
		System.out.print("\n새로운 비밀번호 : ");
		sc.nextLine();
		String newpw = sc.nextLine();

		UserController.changeInfoUser(userId, userPw, newpw);
	}

	/**
	 * 회원 탈퇴
	 */
	public static void cancelUser(String userId, String userPw) {

		UserController.cancelUser(userId, userPw);

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
	 * 상품 선택해서 장바구니에 넣기
	 **/
	public static void putCart(String userId) {
		System.out.print("주문상품번호 : ");
		int productsId = sc.nextInt();
		System.out.print("상품 개수 : ");
		int goodsCnt = sc.nextInt();

		int chk = ProductController.BlockChk(productsId);
		if (chk == 1) {
			Orders order = new Orders(0, 0, null, 0, 0, userId);
			OrderItem orderItem = new OrderItem(0, 0, productsId, goodsCnt, 0);
			order.getOrderItemList().add(orderItem);

			orderItem = OrderController.selectOption(productsId, orderItem);
			if (orderItem != null)
				CartController.putCart(userId, productsId, goodsCnt, orderItem.getSelecSize(), orderItem);
		}
	}

	// 옵션 선택 메뉴 띄워주기
	public static OrderItem printSelectCoffeeOption(OrderItem orderItem) {
		int option = 0;
		int selectSize = 0;
		OrderOptionList optionList = null;
		System.out.println("\n=====================커피 옵션 선택=====================");
		while (true) {
			while (selectSize == 0) {
				System.out.print("사이즈 | Tall은 1, Grande는 2를 선택해주세요 : ");
				selectSize = sc.nextInt();
				if (selectSize != 1 && selectSize != 2) {
					System.out.println("다시 선택해 주세요");
				} else {
					break;
				}
			}

			System.out.println("1. 샷 추가 | 2. 시럽 추가 | 3. 아이스크림 토핑 추가 | 4. 선택 완료");
			option = sc.nextInt();
			if (option == 4)
				break;
			if (option < 1 || option > 4) {
				System.out.println("다시 선택해 주세요");
				continue;
			}

			System.out.print("옵션 수량 선택 : ");
			int optionCnt = sc.nextInt();

			orderItem.setSelecSize(selectSize);
			optionList = new OrderOptionList(0, option, 0, optionCnt);
			orderItem.getOrderOptionList().add(optionList);
		}
		return orderItem;
	}

	public static OrderItem printSelectBeverageOption(OrderItem orderItem) {
		int option = 0;
		int selectSize = 0;
		OrderOptionList optionList = null;
		System.out.println("\n=====================음료 옵션 선택=====================");
		while (option != 7) {
			while (selectSize == 0) {
				System.out.print("사이즈 | Tall은 1, Grande는 2를 선택해주세요 : ");
				selectSize = sc.nextInt();
				if (selectSize != 1 && selectSize != 2) {
					System.out.println("다시 선택해 주세요");
				} else {
					break;
				}
			}
			System.out.println("4. 펄 추가 | 5. 망고 추가 | 6. 샷 추가 | 7. 선택 종료");
			option = sc.nextInt();
			if (option == 7)
				break;
			if (option < 4 || option > 7) {
				System.out.println("다시 선택해 주세요");
				continue;
			}

			System.out.print("옵션 수량 선택 : ");
			int optionCnt = sc.nextInt();

			orderItem.setSelecSize(selectSize);
			optionList = new OrderOptionList(0, option, 0, optionCnt);
			orderItem.getOrderOptionList().add(optionList);
		}
		return orderItem;
	}

	public static OrderItem printSelectDessertOption(OrderItem orderItem) {
		int option = 0;
		OrderOptionList optionList = null;
		System.out.println("\n=====================디저트 옵션 선택=====================");
		while (option != 11) {
			System.out.println("7. 아이스크림 추가 | 11. 선택 종료");
			option = sc.nextInt();
			if (option == 11)
				break;
			if (option != 7) {
				System.out.println("다시 선택해 주세요");
				continue;
			}

			optionList = new OrderOptionList(0, option, 0, 1);
			orderItem.getOrderOptionList().add(optionList);
		}
		return orderItem;
	}

	/**
	 * 상품보기
	 */
	public static void printProduct(String userId) {
		while (true) {
			System.out.println(
					"\n============================================ 상품 보기 =====================================================   ");
			if (!userId.equals("admin"))
				System.out.println("1.상품 전체 보기 |  2.카테고별로 보기  |  3.판매순으로 보기  |  4.나의 주문 추천  |  5.돌아가기");
			else
				System.out.println("1.상품 전체 보기 |  2.카테고별로 보기  |  3.판매순으로 보기  |  5.돌아가기");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				ProductController.productSelect(userId);// 전체 상품조회
				break;
			case 2:
				System.out.println("카테고리를 선택해주세요 || 1. 커피 2. 음료 3. 디저트 ");
				int categoryNum = sc.nextInt();

				ProductController.productSelectByCategory(categoryNum);
				break;
			case 3:
				ProductController.productSelectByRec();// 판매순
				break;
			case 4:
				if (!userId.equals("admin"))
					ProductController.productSelectUser(userId);// 나의 주문 추천
				break;
			case 5:
				return;
			}
		}

	}

	/**
	 * 장바구니
	 */
	public static void viewCart(String id) {
		CartController.viewCart(id);
	}

	public static void printAdminMenu(String userId) {
		while (true) {
			System.out.println(
					"================================================ADMIN_MENU===================================================");
			System.out.println("1.로그아웃 |  2.상품보기  |  3.상품 등록 | 4.상품 수정 | 5.상품 삭제 | 6. 통계  | 7.상품 품절 관리");

			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				menu();
				return;
			case 2:
				printProduct(userId);
				break;
			case 3:
				productInsert();
				break;
			case 4:
				productUpdate();
				break;
			case 5:
				productDelete();
				break;
			case 6:
				totalMenu(userId);
				break;
			case 7:
				productBlock(userId);
				break;
			}
		}

	}

	public static void totalMenu(String userId) {
		while (true) {
			System.out.println(
					"\n============================================통계============================================");
			System.out.println("1.전체 통계 |  2.카테고리별 통계 |  3.일별 통계  | 4. 월별 통계  | 5. top10 메뉴  | 6. 메뉴 돌아가기");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				StatisController.selectTotal();
				break;
			case 2:
				cat_selec();
				break;
			case 3:
				StatisController.selectDayTotal();
				break;
			case 4:
				StatisController.selectMonthTotal();
				break;
			case 5:
				StatisController.selectTopSellTen();
				break;
			case 6:
				printAdminMenu(userId);
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
			StatisController.selectCatTotal(1);
			break;
		case 2:
			StatisController.selectCatTotal(2);
			break;
		case 3:
			StatisController.selectCatTotal(3);
			break;
		}
	}

	public static void productInsert() {

		System.out.println("\n====================== 상품 등록 ===============================");
		System.out.println("등록할 상품명을 입력해주세요");
		sc.nextLine(); // 버퍼 비우기
		String name = sc.nextLine();
		System.out.println("등록할 상품정보를 입력해주세요");
		String info = sc.nextLine();
		System.out.println("등록할 상품의 가격을 입력해주세요");
		int price = sc.nextInt();
		System.out.println("등록할 상품의 카테고리 분류를 입력해주세요(1. 커피 2. 음료 3. 디저트 4. 기타");
		int category = sc.nextInt();

		Products products = new Products(name, info, price, category);
		ProductController.productInsert(products);
	}

	public static void productUpdate() {

		System.out.println("\n====================== 상품 수정 ===============================");
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

		System.out.println("\n====================== 상품 삭제 ===============================");
		System.out.println("삭제할 상품명ID를 입력해주세요");
		int id = sc.nextInt();
		ProductController.productDeleteByProductId(id);

	}

	public static void productBlock(String userId) {
		int id = 0;
		int chk = 0;
		System.out.println("====================== 품절 상태===============================");

		ProductController.productSelect(userId);

		while (true) {
			System.out.print("품절 상태를 바꿀 상품ID를 입력해주세요 : ");
			id = sc.nextInt();
			System.out.print("바꿀 상태 (0.품절 상품 등록 | 1.품절 상품 해제) : ");
			chk = sc.nextInt();
			if (chk == 0 || chk == 1)
				break;
			else
				System.out.println("잘못된 입력 다시");
		}

		ProductController.productUpdateBlock(id, chk);

	}

	public static int inputPermitOrder() {
		int chk = 0;
		while (true) {
			System.out.println("주문 승인하시겠습니까(1. 승인 | 2.거부) ?");
			chk = sc.nextInt();
			if (chk == 1)
				break;
			else if (chk == 2) {
				chk = 0;
				break;
			}
		}
		return chk;
	}

}
