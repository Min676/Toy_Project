package app.mvc.view;

import app.mvc.model.dao.ProductsDAO;
import app.mvc.model.dao.ProductsDAOImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import app.mvc.controller.OrderController;
import app.mvc.model.dto.OrderItem;
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
			System.out.println(p);
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
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalPrice());


			 ProductsDAO productsDAO = new ProductsDAOImpl();

		   for(OrderItem orderItem : order.getOrderItemList()) {
				 String productName = null;
			   String size = null;
				 try {
					 Products product = productsDAO.productSelectByProductId(orderItem.getProductId());
					 productName = product != null ? product.getName() : "상품명 없음";
				 } catch (SQLException e) {
					 e.printStackTrace();
					 productName = "상품명 조회 실패";
				 }
			   if(orderItem.getSelecSize() == 1) {
				   size = "Tall";
			   } else {
				   size = "Grande";
			   }
			   System.out.println("  ▶ 주문번호 : " + orderItem.getOrderId() + " | 상품번호 : " + orderItem.getProductId() + " | 상품명 : " + productName + " | 개수 : " + orderItem.getQuantity() + " | 사이즈 : " + size);
		   }
		   System.out.println();
	   }
	}

	public static void printTotalMessage(Statisics stat) {
		int seq = 1;
		if (stat.getProduct() != null) {
			System.out.println("-----------------총 판매량----------------------");
			System.out.println("<총판매액> : " + stat.getTotalPrice());
			System.out.println("<총주문수> : " + stat.getTotalOrder());
			if (stat.getCategory_oderSum_list() != null) {
				System.out.println("<카테고리별 주문>");
				stat.getCategory_oderSum_list().forEach((k,v)-> {
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
				stat.getCategory_oderSum_list().forEach((k,v)-> {
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
	
	public static void printTopSell(Map<String,Integer>map) {
		System.out.println("---------------------------------------");
		System.out.println("|        상품명\t||     주문수    |");
		System.out.println("---------------------------------------");
		map.forEach((k,v)-> {
			 System.out.print("상품명 : "+k);
			 System.out.println("============="+v+"건");
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
	
	public static void printViewCart(String id , Map<Products,Integer> cart) {
		System.out.println("장바구니내용....");
		
		for(Products products: cart.keySet()) {
			int productsId = products.getProduct_id();//상품번호
			String name = products.getName();//상품이름
			int price = products.getPrice();//상품가격
			
			int quantity = cart.get(products);//key에 해당하는 value즉 수량 
			System.out.println(productsId+" : "+name+" : "+price+" \t "+quantity);
		}
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch(sc.nextInt()) {
		case 1:
			
			 Orders orders = new Orders(0, 0, null, 0, 0, id);
			 
			 List<OrderItem> orderItemList = orders.getOrderItemList();
			 
			 for(Products productsKey : cart.keySet()) {
				 int qty = cart.get(productsKey); // map에서 key=Products에 해당하는 value=수량 조회
				 OrderItem orderItem = new OrderItem(0, 0, productsKey.getProduct_id() , qty, 1); // size를 가져올 방법이 필요. 옵션정보에 넣기?
				 orderItemList.add(orderItem);
			 }
			 
			 
			 System.out.println("orderItemList 개수 : " + orderItemList.size());
			 
			 OrderController.orderInsert(orders);// 주문 + 주문상세
			 
			 //장바구니비우기
			 SessionSet ss = SessionSet.getInstance();
			 Session session = ss.get(id);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
	}
	
	public static void userPrintSucc() {
		
		System.out.println("성공적으로 회원가입이 완료되었습니다.");
		
	}
	public static void userinfoSucc() {
		
		System.out.println("성공적으로 수정이 완료되었습니다.");
		
	}
	
	public static void userDelSucc() {
		
		System.out.println("성공적으로 회원탈퇴가 완료되었습니다.");
		
	}
	public static void userSelectAll(Users user) {
		System.out.println("---------------유저정보--------------");
		System.out.println(user);
	}
	
	
}
	
