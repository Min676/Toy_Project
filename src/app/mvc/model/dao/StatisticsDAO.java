package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.Map;

import app.mvc.model.dto.Statisics;

public interface StatisticsDAO {
	/**
	 * 전체 주문 통계
	 * */
	Statisics totalStat()throws SQLException;
	
	/**
	 * 일별 주문 통계
	 * */
	Statisics dayTotalStat()throws SQLException;
	
	
	/**
	 * 월별 주문 통계
	 * */
	Statisics monthTotalStat()throws SQLException;
	
	
	/**
	 * 카테고리별 주문 통계
	 * */
	Statisics categoryTotalStat(int category_seq)throws SQLException;
	
	
	/**
	 * 주문량 top10상품
	 * */
	Map<String,Integer> topSell() throws SQLException;
	
}
