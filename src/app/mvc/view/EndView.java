package app.mvc.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import app.mvc.controller.CartController;
import app.mvc.controller.OrderController;
import app.mvc.controller.ProductController;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Statisics;
import app.mvc.model.dto.Users;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;

public class EndView {
	private static Scanner sc = new Scanner(System.in);

	/**
	 * 상품 전체 출력
	 */
	public static void printProductsList(List<Products> list) {
		System.out.println("-------------상품 -------------");
		for (Products p : list) {
			if(p.getBlock() == 1)
				System.out.println(p);
			else
				System.out.println("[X]"+p+" [SOLD OUT!!!]");
		}

		System.out.println();
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * 주문 상세보기
	 */
	public static void printOrderByUserId(List<Orders> orderList) {
		System.out.println("====================주문 목록====================");
		for (Orders order : orderList) {
			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalPrice());

			for (OrderItem orderItem : order.getOrderItemList()) {
				String size = null;
				if (orderItem.getSelecSize() == 1) {
					size = "Tall";
				} else {
					size = "Grande";
				}
				System.out.println("  ▶ 주문번호 : " + orderItem.getOrderId() + " | 메뉴명 : "
						+ ProductController.productName(orderItem.getProductId()).getName() + " | 개수 : "
						+ orderItem.getQuantity() + " | 사이즈 : " + size);
				for (OrderOptionList optionList : orderItem.getOrderOptionList()) {
					System.out.println("      ▶ 주문옵션 : " + OrderController.getOptionName(optionList.getOiId())
							+ " | 옵션 수량 : " + optionList.getSelecCnt());
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * 마지막 주문 상품 보기
	 */
	public static void printRecentOrderByUserId(Orders order) {
		System.out.println("====================주문 목록====================");

			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalPrice());

			for (OrderItem orderItem : order.getOrderItemList()) {
				String size = null;
				if (orderItem.getSelecSize() == 1) {
					size = "Tall";
				} else {
					size = "Grande";
				}
				System.out.println("  ▶ 주문번호 : " + orderItem.getOrderId() + " | 메뉴명 : "
						+ ProductController.productName(orderItem.getProductId()).getName() + " | 개수 : "
						+ orderItem.getQuantity() + " | 사이즈 : " + size);
				for (OrderOptionList optionList : orderItem.getOrderOptionList()) {
					System.out.println("      ▶ 주문옵션 : " + OrderController.getOptionName(optionList.getOiId())
							+ " | 옵션 수량 : " + optionList.getSelecCnt());
				}
			}
			System.out.println();
		
	}

	public static void printTotalMessage(Statisics stat) {
		int seq = 1;
		if (stat.getProduct() != null) {
			System.out.println("-----------------총 판매량----------------------");
			System.out.println("<총판매액> : " + stat.getTotalPrice());
			System.out.println("<총주문수> : " + stat.getTotalOrder());
			if (stat.getCategory_oderSum_list() != null) {
				System.out.println("<카테고리별 주문>");
				stat.getCategory_oderSum_list().forEach((k, v) -> {
					if (k == 1)
						System.out.println("	커피 : " + v);
					if (k == 2)
						System.out.println("	음료 : " + v);
					if (k == 3)
						System.out.println("	디저트 : " + v);
				});
			}
			System.out.print("<최고 판매량 상품>");
			System.out.println("\n\t상품명: " + stat.getProduct().getName() + "\n\tinfo: " + stat.getProduct().getInfo());
			System.out.print("<최고 구매 유저 > : ");
			System.out.println(stat.getUserName());
			System.out.println("---------------------------------------");
		} else {
			System.out.println("주문데이터 없음!!");
		}
	}

	public static void printTotalMessageDate(Statisics stat, String date) {
		if (stat.getProduct() != null) {
			System.out.println("-------------------" + date + "간 판매 통계----------------------");
			System.out.println("<전" + date + "대비 판매액> : " + stat.getIncrement());
			System.out.println("<총판매액> : " + stat.getTotalPrice());
			System.out.println("<총주문수> : " + stat.getTotalOrder());
			if (stat.getCategory_oderSum_list() != null) {
				System.out.println("<카테고리별 주문>");
				stat.getCategory_oderSum_list().forEach((k, v) -> {
					if (k == 1)
						System.out.println("	커피 : " + v);
					if (k == 2)
						System.out.println("	음료 : " + v);
					if (k == 3)
						System.out.println("	디저트 : " + v);
				});
			}
			System.out.print("<최고 판매량 상품>");
			System.out.println("\n\t상품명: " + stat.getProduct().getName() + "\n\tinfo: " + stat.getProduct().getInfo());
			System.out.print("<최고 구매 유저 > : ");
			System.out.println(stat.getUserName());
			System.out.println("------------------------------------------------------------");

		} else {
			System.out.println("주문데이터 없음!!");
		}
	}

	public static void printTopSell(Map<String, Integer> map) {
		System.out.println("-----------------판매Top10------------------");
		System.out.println("|        상품명\t||     주문수    |");
		System.out.println("---------------------------------------");
		map.forEach((k, v) -> {
			System.out.print("상품명 : " + k);
			System.out.println("=============" + v + "건");
		});
		System.out.println();
	}

	public static void printProductsListREC(List<Products> list) {
		System.out.println("-------------추천 상품 -------------");
		for (Products p : list) {
			System.out.println(p);
		}

		System.out.println();
	}

	public static void printProductsListUserREC(List<Products> list) {
		System.out.println("-------------주문 기반 추천 상품 -------------");
		for (Products p : list) {
			System.out.println(p);
		}

		System.out.println();
	}

	public static void printViewCart(String id, Map<OrderItem, Integer> cart) {
		System.out.println("===========================장바구니===========================");

		for (OrderItem orderItem : cart.keySet()) {
			int productsId = orderItem.getProductId();// 상품번호
			Products products = ProductController.productName(orderItem.getProductId());
			String name = products.getName();// 상품이름
			int price = products.getPrice();// 상품가격

			int quantity = cart.get(orderItem);// key에 해당하는 value즉 수량
			System.out.println(
					"상품번호 : " + productsId + " | 상품명 : " + name + "\t | 가격 : " + price + " \t| 수량 : " + quantity);
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch (sc.nextInt()) {
		case 1:
            Orders orders = new Orders(0, 0, null, 0, 0, id);
            
            int quantity = 0;
            List<OrderItem> orderItemList = orders.getOrderItemList();
            List<OrderOptionList> orderOptionList = new ArrayList<OrderOptionList>();
			
			 for(OrderItem item : cart.keySet()) {
				 int qty = cart.get(item); // map에서 key=Products에 해당하는 value=수량 조회
				 OrderItem orderItem = new OrderItem(0, 0, item.getProductId() , qty, item.getSelecSize());
				 for(OrderOptionList optionList : item.getOrderOptionList()) {
					 	orderOptionList.add(new OrderOptionList(0, 0, optionList.getOiId(), optionList.getSelecCnt()));
			 	}
				quantity += qty;
				item.setOrderOptionList(orderOptionList);
			 	orderItemList.add(orderItem);
			 }
			 
			orders.setOrderItemList(orderItemList);
			System.out.println("주문 메뉴 개수 : " + quantity);
			
			
			OrderController oc = new OrderController();
			Map<Integer, Integer> map = oc.userWalletInfo(id);
			Iterator<Integer> iter = map.keySet().iterator();
			int point = iter.next();
			int cash = map.get(point);

			int use = isPointUse(point);
			
			int result = OrderController.orderInsert(orders, point, cash, use,id);// 주문 + 주문상세
			

			// 장바구니비우기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			session.removeAttribute("cart");
			if(result == 1) {
				System.out.println("주문 완료되었습니다!^^");
			}
			break;
			
		case 9:
			break;
		}

	}

	public static int isPointUse(int point) {
		int money = 0;
		System.out.print("포인트를 사용하시겠습니까?(1.사용O | 2.사용x) : ");
		int answer = sc.nextInt();

		if (answer == 1) {
			System.out.println("사용 금액을 입력해주세요 ");
			System.out.println("현재 포인트 잔액 : " + point);
			System.out.print("사용 금액 : ");
			while (true) {
				money = sc.nextInt();
				if (money <= point) {
					System.out.println(money + "원 해당 금액 만큼 사용하겠습니다");
					break;
				} else
					System.out.println("입력 금액이 Point보다 큽니다. ");
			}

			return money;

		} else
			return 0;
	}

	public static void userPrintSucc() {

		System.out.println("\n성공적으로 회원가입이 완료되었습니다.");

	}

	public static void userinfoSucc() {

		System.out.println("\n성공적으로 수정이 완료되었습니다.");

	}

	public static void userDelSucc() {

		System.out.println("\n성공적으로 회원탈퇴가 완료되었습니다.");

	}

	public static Users userSelectAll(Users user) {
		System.out.println("\n---------------유저정보--------------");
		System.out.println(user);

		return user;
	}

}
