package app.mvc.controller;

import app.mvc.view.EndView;
import java.sql.SQLException;

import app.mvc.model.dto.Orders;
import app.mvc.model.service.OrderService;
import app.mvc.view.FailView;
import java.util.List;

public class OrderController {
	private static OrderService orderService = new OrderService();
	public static void selectOrdersByUserId(String userId){
		try {
			List<Orders> list = orderService.selectOrdersByUserId(userId);
			EndView.printOrderByUserId(list);
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
