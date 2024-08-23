package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.Orders;

public interface OrderDAO {
  /**
   * 주문하기
   *  1) orders테이블에 insert
   *  2) order_item테이블에 insert
   *  3) 결제 기능 wallet테이블 update
   * */
	int orderInsert(Orders order)throws SQLException;
	
	
	/**
	 * 주문내역보기
	 * */
	List<Orders> selectOrdersByUserId(String userId)throws SQLException;
	
	
	
}
