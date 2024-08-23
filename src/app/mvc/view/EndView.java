package app.mvc.view;

import java.util.List;

import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;

public class EndView {
  /**
   * 상품 전체 출력
   * */
	public static void printProductsList(List<Products> list) {
		System.out.println("-------------상품 -------------");
		for(Products p: list) {
			System.out.println(p);
		}
		
		System.out.println();
	}
	
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
 
	/**
	 * 주문 상세보기
	 * */
	public static void printOrderByUserId(List<Orders> orderList) {
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalPrice());
		   
		   for(OrderItem orderItem : order.getOrderLineList()) {
			   System.out.println("  ▶ "+orderItem);
		   }
		   System.out.println();
	   }
		
	}
}












