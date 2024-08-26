package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Users;
import app.mvc.model.dto.Wallet;
import app.mvc.util.DbManager;

public class OrderDAOImpl implements OrderDAO {
	
	ProductsDAO productsDAO = new ProductsDAOImpl();
	UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public int orderInsert(Orders order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ORDERS (ORDER_ID, USER_SEQ, ORDER_DATE, TOTAL_PRICE, STATUS)"
				+ "VALUES (ORDER_ID.NEXTVAL, ?, SYSDATE, ?, 1)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false); // 자동커밋 해지
			ps = con.prepareStatement(sql);
			//ps.setInt(1, 상품가격*개수+상품가격*개수)
			int totalPrice = this.getTotalPrice(order);
			order.setTotalPrice(totalPrice);
			Users user = userDAO.getUserSeq(con, order.getUserId());
			order.setUserSeq(user.getUserSeq());
			ps.setInt(1, user.getUserSeq());
			ps.setInt(2, totalPrice);
			result = ps.executeUpdate();
			if (result ==0) { // 트랜잭션 롤백
				con.rollback();
				throw new SQLException("주문 실패");
			}
			else { // 주문상세 등록
				int resultTwo [] = this.orderItemInsert(con, order);
				for (int i : resultTwo) {
					if(i != 1) {
						con.rollback();
						throw new SQLException("주문 상세 등록 실패");
					}
				}
				
				
				// wallet 업데이트
				Wallet wallet = this.checkWallet(con, order);
				if(order.getTotalPrice() > wallet.getCash()) throw new SQLException("지갑 잔액이 부족합니다.");
				
				result = this.chargeWallet(con, order);					
				con.commit();
			}
				
			
		} catch (SQLException e) {
			if(con != null) {
				con.rollback();
			} 
			throw e;
		} finally {
			
			DbManager.close(con, ps, null);
		}

		return result;
	}
	
	
	/**
	 * 주문 상세 등록
	 */
	public int [] orderItemInsert(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		String sql = "INSERT INTO ORDERS_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, SELECT_SIZE)"
				+ "VALUES (ORDER_ITEM_ID_SEQ.NEXTVAL, ORDER_ID.CURRVAL, ?, ?, ?)";
		int [] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			for(OrderItem item : order.getOrderItemList()) {
				ps.setInt(1, item.getProductId());
				ps.setInt(2, item.getQuantity());
				ps.setInt(3, item.getSelecSize());
				
				ps.addBatch();
				
				result = this.orderOptionInsert(con, item);
				for (int i : result) {
					if(i != 1) {
						con.rollback();
						throw new SQLException("주문 옵션 항목 등록 실패");
					}
				}
				
				ps.clearParameters();
			}
			
			result = ps.executeBatch();
			
		} finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
	}
	
	/**
	 * 주문 옵션 리스트 등록
	 */
	public int [] orderOptionInsert(Connection con, OrderItem orderItem) throws SQLException {
		PreparedStatement ps = null;
		String sql = "INSERT INTO ORDER_OPTION_LIST (OPTION_ID, ORDER_OPTION_ID, ORDER_ITEM_ID, SELEC_CNT)"
				+ "VALUES (OPTION_ID_SEQ.NEXTVAL, ?, ?, ?)"; // ORDER_OPTION_ID 가 어떤 항목인지 확인 필요
		int [] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			//(int optionId, int orderItemId, int oiId, int selecCnt)
			for(OrderOptionList option : orderItem.getOrderOptionList()) {
				ps.setInt(1, option.getOiId());
				ps.setInt(2, option.getOrderItemId());
				ps.setInt(3, option.getSelecCnt());
				
				ps.addBatch();
				ps.clearParameters();
			}
			
			result = ps.executeBatch();
			
		} finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
	}
	

	/**
	 * select * from orders where user_seq = (select user_seq from users where user_id = ?) order by order_id desc
	 */
	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<>();
		String sql = "select * from orders where user_seq = (select user_seq from users where user_id = ?) order by order_id desc";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Orders orders = new Orders (rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), userId);
				
				List<OrderItem> orderItemList = this.selectOrderItem(orders.getOrderId());
				orders.setOrderItemList(orderItemList);
				
				list.add(orders);
			}
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return list;
	}
	
	public List<OrderItem> selectOrderItem (int order_id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderItem> list = new ArrayList<>();
		String sql = "select * from orders_item where order_id = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, order_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem (rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				
				list.add(orderItem);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}
		
		return list;
	}
	
	
	/**
	 * 총 구매금액 구하는 메소드
	 */
	public int getTotalPrice(Orders orders) throws SQLException {
		List<OrderItem> orderItemList = orders.getOrderItemList();
		int total = 0;
		for(OrderItem item : orderItemList) {
			Products products = productsDAO.productSelectByProductId(item.getProductId()); // 상품정보 검색
			
			if(products==null) throw new SQLException();
			
			total += item.getQuantity() * products.getPrice(); // 주문수량 * 가격 합계
		}
		return total;
	}
	
	/**
	 * wallet update
	 */
	public int chargeWallet(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		int cash = 0;
		String sql = "UPDATE WALLET SET CASH=CASH-? WHERE USER_SEQ = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, order.getTotalPrice());
			ps.setInt(2, order.getUserSeq());
			
			result = ps.executeUpdate();
			
			
		} finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
	}
	
	/**
	 * wallet 잔액 확인
	 */
	public Wallet checkWallet(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Wallet wallet = null;
		
		String sql = "SELECT * FROM WALLET WHERE USER_SEQ = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, order.getUserSeq());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				wallet = new Wallet(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
			
		} finally {
			DbManager.close(null, ps, null);
		}
		
		return wallet;
	}
	



}
