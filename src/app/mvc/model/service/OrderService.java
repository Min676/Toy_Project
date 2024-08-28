package app.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.mvc.controller.OrderController;
import app.mvc.exception.NotFoundException;
import app.mvc.model.dao.OrderDAO;
import app.mvc.model.dao.OrderDAOImpl;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	public OrderService() {	}
	
	public List<Orders> selectOrdersByUserId(String userId) throws NotFoundException, SQLException {
		List<Orders> orderList = orderDAO.selectOrdersByUserId(userId);
		if(orderList.isEmpty()) throw new NotFoundException (userId + "의 주문 내역이 존재하지 않습니다.");
		return orderList;
	}
	
	public Orders selectRecentOrdersByUserId(String userId) throws NotFoundException, SQLException {
		
		return null;
	}
	
	public int orderInsert(Orders orders,int point, int cash, int use,String id) throws SQLException, NotFoundException {
		int result =  orderDAO.orderInsert(orders,point,cash,use,id);
		if (result == 0) throw new SQLException("주문에 실패하였습니다.");
		return result;
	}
	
	public String getOptionName(int optionId) throws SQLException {
		String optionName = orderDAO.getOptionName(optionId);
		if (optionName == null) throw new SQLException("옵션 정보를 가져오는데 실패하였습니다.");
		return optionName;
	}

	public Map<Integer, Integer> userWalletInfo(String userId) throws SQLException {
		return orderDAO.selectUserWalletInfo(userId);
	}
	
	public Orders orderCartItems(String id, Map<OrderItem, Integer> cart) {
		Orders orders = new Orders(0, 0, null, 0, 0, id);
        
        int quantity = 0;
        List<OrderItem> orderItemList = orders.getOrderItemList();
        List<OrderOptionList> orderOptionList = new ArrayList<OrderOptionList>();
		
		 for(OrderItem item : cart.keySet()) {
			 int qty = cart.get(item); // map에서 key=Products에 해당하는 value=수량 조회
			 OrderItem orderItem = new OrderItem(0, 0, item.getProductId() , qty, item.getSelecSize());
			 for(OrderOptionList optionList : item.getOrderOptionList()) {
				 	orderOptionList.add(new OrderOptionList(0, 0, optionList.getOiId(), optionList.getSelecCnt()));
		 	}
			quantity += qty;
			item.setOrderOptionList(orderOptionList);
		 	orderItemList.add(orderItem);
		 }
		 
		orders.setOrderItemList(orderItemList);
		OrderController.orderProductsQuantity(quantity);
//		System.out.println("주문 메뉴 개수 : " + quantity);
		
		return orders;
	}

}
