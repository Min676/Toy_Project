package app.mvc.model.service;

import java.util.List;

import app.mvc.model.dao.OrderDAO;
import app.mvc.model.dao.OrderDAOImpl;
import app.mvc.model.dto.Orders;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	public OrderService() {	}
	
	public List<Orders> selectOrdersByUserId(String userId) {
		
		return null;
	}

}
