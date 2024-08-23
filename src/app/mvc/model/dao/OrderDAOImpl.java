package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.Orders;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int orderInsert(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
