package app.mvc.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import app.mvc.model.dao.OrderDAO;
import app.mvc.model.dao.OrderDAOImpl;
import app.mvc.model.dto.OptionInfo;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	public OrderService() {	}
	
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		List<Orders> orderList = orderDAO.selectOrdersByUserId(userId);
		if(orderList.isEmpty()) throw new SQLException(userId + "의 주문 내역이 존재하지 않습니다.");
		return orderList;
	}
	
	public void orderInsert(Orders orders,int point, int cash, int use,String id) throws SQLException {
		int result =  orderDAO.orderInsert(orders,point,cash,use,id);
		if (result == 0) throw new SQLException("주문에 실패하였습니다.");
	}
	
	public OptionInfo getOptionInfo(OrderOptionList orderOptionList) throws SQLException {
		OptionInfo info = orderDAO.getOptionInfo(orderOptionList);
		if (info == null) throw new SQLException("옵션 정보를 가져오는데 실패하였습니다.");
		return info;
	}

	public Map<Integer, Integer> userWalletInfo(String userId) throws SQLException {
		return orderDAO.selectUserWalletInfo(userId);
	}

}
