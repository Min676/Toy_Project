package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;

public class OrderDAOImpl implements OrderDAO {
	
	private static OrderDAO instance = new OrderDAOImpl();
	
	@Override
	public int orderInsert(Orders order) throws SQLException {
		
		return 0;
	}

	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
