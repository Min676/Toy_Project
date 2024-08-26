package app.mvc.controller;

import app.mvc.model.service.OrderService;

public class OrderController {
	private static OrderService orderService = new OrderService();
	public static void selectOrdersByUserId(String userId){
		try {
			orderService.selectOrdersByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
