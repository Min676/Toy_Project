package app.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.service.OrderService;
import app.mvc.model.service.ProductService;
import app.mvc.view.EndView;
import app.mvc.view.FailView;
import app.mvc.view.MenuView;

public class OrderController {
	private static OrderService orderService = new OrderService();
	private static ProductService productService = new ProductService();
	public static void selectOrdersByUserId(String userId){
		try {
			List<Orders> orderList = orderService.selectOrdersByUserId(userId);
			EndView.printOrderByUserId(orderList);
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
	
	public static OrderOptionList selectOption(int Product_id, OrderItem orderItem) {
		OrderOptionList optionList = null;
		try {
				Products product = productService.productSelectByProductId(Product_id);
				if(product.getCategory_seq() == 1) {
					optionList = MenuView.printSelectCoffeeOption(orderItem);
				} else if (product.getCategory_seq() == 2) {
					optionList = MenuView.printSelectBeverageOption(orderItem);
				}
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return optionList;
	}
}
