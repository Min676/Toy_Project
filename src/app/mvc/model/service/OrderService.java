package app.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dao.OrderDAO;
import app.mvc.model.dao.OrderDAOImpl;
import app.mvc.model.dto.Orders;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	public OrderService() {	}
	
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		List<Orders> orderList = orderDAO.selectOrdersByUserId(userId);
		if(orderList.isEmpty()) return null;
		return orderList;
	}
	
	public void orderInsert(Orders orders) throws SQLException {
		int result = OrderDAO.orderInsert(orders);
	}

}
