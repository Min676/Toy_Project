package app.mvc.view;

import java.util.List;

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
		for (Orders order : orderList) {
			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalPrice());

			for (OrderItem orderItem : order.getOrderLineList()) {
				System.out.println("  ▶ " + orderItem);
			}
			System.out.println();
		}

	}

	public static void printTotalMessage(Statisics stat) {
		int seq = 0;
		if (stat.getProduct() != null) {
			System.out.println("-----------------총 판매량----------------------");
			System.out.println("<총판매액> : " + stat.getTotalPrice());
			System.out.println("<총주문수> : " + stat.getTotalOrder());
			System.out.println("<카테고리별 주문>");
			if (stat.getCategory_oderSum_list() != null)
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
			System.out.println("\n\t상품명: " + stat.getProduct().getName() + "\n\tinfo: " + stat.getProduct().getInfo());
			System.out.print("<최고 구매 유저 > : ");
			System.out.println(stat.getUserName());
			System.out.println("---------------------------------------");
		} else {
			System.out.println("주문데이터 없음!!");
		}
	}

	public static void printTotalMessageDate(Statisics stat, String date) {
		int seq = 0;
		if (stat.getProduct() != null) {
			System.out.println("-------------------" + date + "간 판매 통계----------------------");
			System.out.println("<전" + date + "대비 판매액> : " + stat.getIncrement());
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
			System.out.println("\n\t상품명: " + stat.getProduct().getName() + "\n\tinfo: " + stat.getProduct().getInfo());
			System.out.print("<최고 구매 유저 > : ");
			System.out.println(stat.getUserName());
			System.out.println("------------------------------------------------------------");

		} else {
			System.out.println("주문데이터 없음!!");
		}
	}

}
