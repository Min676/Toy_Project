package app.mvc.model.dao;

import java.sql.SQLException;

import app.mvc.model.dto.Statisics;

public class StatisticsDAOImpl implements StatisticsDAO {
	//select (select sum(total_price) from orders)as totalPrice, (select count(*) from orders)as count from orders;
	@Override
	public Statisics totalStat() throws SQLException {
		
		return null;
	}

	@Override
	public Statisics dayTotalStat(int date) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statisics monthTotalStat(int month) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statisics categoryTotalStat(int category_seq) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
