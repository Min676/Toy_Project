package app.mvc.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Statisics;

public class EndView {
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
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalPrice());
		   
		   for(OrderItem orderItem : order.getOrderItemList()) {
			   System.out.println("  ▶ "+orderItem);
		   }
		   System.out.println();
	   }
	}

	public static void printTotalMessage(Statisics stat) {
		int seq = 0;
		System.out.println("---------------------------------------");
		System.out.println("<총판매액> : " + stat.getTotalPrice());
		System.out.println("<총주문수> : " + stat.getTotalOrder());
		System.out.println("<카테고리별 주문>");
		for (int c : stat.getCategory_oderSum_list()) {
			if (seq == 0)
				System.out.println("	커피 : " + c);
			else if (seq == 1)
				System.out.println("	음료 : " + c);
			else if (seq == 2)
				System.out.println("	디저트 : " + c);
			seq++;
		}
		System.out.print("<최고 판매량 상품>");
		System.out.println("\n\t상품명: "+stat.getProduct().getName()+"\n\tinfo: "+stat.getProduct().getInfo());
		System.out.print("<최고 구매 유저 > : ");
		System.out.println(stat.getUserName());
		System.out.println("---------------------------------------");

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
		
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("1.주문하기  |  9.나가기");
//		switch(sc.nextInt()) {
//		case 1:
//			
//			 Orders orders = new Orders(0, 0, id, 0);
//			 
//			 List<OrderItem> orderItemList = orders.getOrderItemList();
//			 
//			 for(Goods goodsKey : cart.keySet()) {
//				 int qty = cart.get(goodsKey); // map에서 key=Goods에 해당하는 value=수량 조회
//				 OrderLine orderLine = new OrderLine(0, 0, goodsKey.getGoodsId() , 0, qty, 0);
//				 orderLineList.add(orderLine);
//			 }
//			 
//			 
//			 System.out.println("orderLineList 개수 : " + orderLineList.size());
//			 
//			 OrderController.insertOrders(orders);// 주문 + 주문상세
//			 
//			 //장바구니비우기
//			 SessionSet ss = SessionSet.getInstance();
//			 Session session = ss.get(id);
//			 session.removeAttribute("cart");
//			break;
//			
//		case 9:
//			break;
		}
}
