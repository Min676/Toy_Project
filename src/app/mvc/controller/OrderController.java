package app.mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.mvc.exception.NoCashException;
import app.mvc.exception.NotFoundException;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.service.OrderService;
import app.mvc.model.service.ProductService;
import app.mvc.model.service.UserService;
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
		} catch (NotFoundException | SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void selectRecentOrdersByUserId(String userId){
		try {
			Orders order = orderService.selectRecentOrdersByUserId(userId);
			EndView.printRecentOrderByUserId(order);
		} catch (NotFoundException | SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static int orderInsert (Orders orders,int point, int cash, int use,String id) {
		int result = 0;
		try {
			result = orderService.orderInsert(orders,point,cash,use,id);
		} catch (NotFoundException | SQLException e) {
			FailView.errorMessage(e.getMessage());
		} catch(NoCashException n) {
			FailView.errorMessage(n.getMessage());
			MenuView.cashCharge(id);
		}
		return result;
	}
	
	public static OrderItem selectOption(int Product_id, OrderItem orderItem) {
		OrderOptionList optionList = null;
		OrderItem item = orderItem;
		try {
				Products product = productService.productSelectByProductId(Product_id);
				if(product.getCategory_seq() == 1) {
					item = MenuView.printSelectCoffeeOption(item);
				} else if (product.getCategory_seq() == 2) {
					item = MenuView.printSelectBeverageOption(item);
				} else if (product.getCategory_seq() == 3) {
					item = MenuView.printSelectDessertOption(item);
				}
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return item;
	}
	
	public static String getOptionName(int optionId) {
		String optionName = null;
		try {
			optionName = orderService.getOptionName(optionId);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		return optionName;
	}
	
	
	public Map<Integer, Integer> userWalletInfo(String userId) {
		Map<Integer, Integer> map =new HashMap<>() ;
		try {
			map = orderService.userWalletInfo(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Orders orderCartItems(String id, Map<OrderItem, Integer> cart) {
		Orders orders = null;
		orders = orderService.orderCartItems(id, cart);
		if(orders != null)
			return orders;
		return null;
	}
	
	public static void orderProductsQuantity(int quantity) {
		EndView.orderProductsQuantity(quantity);
	}
}
