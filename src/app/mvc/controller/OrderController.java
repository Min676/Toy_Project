package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.model.dto.Orders;
import app.mvc.model.service.OrderService;
import app.mvc.view.FailView;

public class OrderController {
	private static OrderService orderService = new OrderService();
	public static void selectOrdersByUserId(String userId){
		try {
			orderService.selectOrdersByUserId(userId);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void orderInsert (Orders orders) {
		try {
			orderService.orderInsert(orders);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
