package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Orders;
import app.mvc.model.service.OrderService;

public class OrderController {
	private static OrderService orderService = new OrderService();
	public static void selectOrdersByUserId(String userId){
		try {
			orderService.selectOrdersByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void orderInsert (Orders orders) {
		try {
			orderService.orderInsert(orders);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
