package app.mvc.view;

import java.util.List;
import java.util.Map;

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

			for (OrderItem orderItem : order.getOrderItemList()) {
				System.out.println("  ▶ " + orderItem);
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

}
