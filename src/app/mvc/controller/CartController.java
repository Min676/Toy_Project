package app.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import app.mvc.exception.NotFoundException;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Users;
import app.mvc.model.service.CartService;
import app.mvc.model.service.OrderService;
import app.mvc.model.service.ProductService;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;
import app.mvc.view.EndView;
import app.mvc.view.FailView;

public class CartController {
	private static ProductService productService = new ProductService();
	private static OrderService orderService = new OrderService();
	private static CartService cartService = new CartService();
	public Users selectUser(int user_seq) {
		Users u = null;
		
		return u;
	}
	
	public static void putCart(String id, int productId, int quantity, int size, OrderItem orderItem) {
		
		try {
			cartService.putCart(id, productId, quantity, size, orderItem);
			
		} catch (SQLException | NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Map<OrderItem,Integer> cart = (Map<OrderItem, Integer>) session.getAttribute("cart");
		if(cart == null ) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printViewCart(id , cart);
		}
	}

}












