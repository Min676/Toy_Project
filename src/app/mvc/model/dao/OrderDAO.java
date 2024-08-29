package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import app.mvc.exception.NoCashException;
import app.mvc.exception.NotFoundException;
import app.mvc.model.dto.OptionInfo;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.OrderOptionList;
import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Wallet;

public interface OrderDAO {
  /**
   * 주문하기
   *  1) orders테이블에 insert
   *  2) order_item테이블에 insert
   *  3) 결제 기능 wallet테이블 update
   * */
	int orderInsert(Orders orders,int point, int cash, int use,String id) throws SQLException, NotFoundException,NoCashException;
	
	/**
	 * 주문 상세 등록
	 */
	int [] orderItemInsert(Connection con, Orders orders) throws SQLException;
	
	/**
	 * 주문 옵션 리스트 등록
	 */
	int [] orderOptionInsert(Connection con, OrderItem orderItem) throws SQLException;
	
	/**
	 * 사용자별 주문내역보기
	 */
	List<Orders> selectOrdersByUserId(String userId) throws NotFoundException, SQLException;
	
	/**
	 * 주문 상세 (주문품목) 보기
	 */
	List<OrderItem> selectOrderItem(int order_id, Connection con) throws SQLException;
	

	/**
	 * 총 구매금액 구하기
	 */
	int getTotalPrice(Orders orders) throws SQLException, NotFoundException;
	
	/**
	 * wallet update
	 */
	int chargeWallet(Connection con, Orders order) throws SQLException;
	
	/**
	 * wallet 잔액 확인
	 */
	Wallet checkWallet(Connection con, Orders order) throws SQLException;
	

	/**
	 * 옵션 정보 가져오기
	 */
	OptionInfo getOptionInfo(OrderOptionList orderOptionList) throws SQLException;
	
	/**
	 * 유저 지갑 정보
	 */
	Map<Integer, Integer> selectUserWalletInfo(String userId)throws SQLException; 
	
	/**
	 * 옵션 이름 가져오기
	 */
	public String getOptionName(int optionId) throws SQLException;
	
	
	/**
	 * 주문 옵션 리스트 받아오기
	 */
	public List<OrderOptionList> selectOrderOptionList(int orderItemId, Connection con) throws SQLException;
	
	/**
	 * 사용자 최근 주문내역 출력
	 */
	public Orders selectRecentOrdersByUserId(String userId) throws NotFoundException, SQLException;
	
}
