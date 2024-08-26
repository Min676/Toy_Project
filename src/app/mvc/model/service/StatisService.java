package app.mvc.model.service;

import java.sql.SQLException;

import app.mvc.model.dao.StatisticsDAO;
import app.mvc.model.dao.StatisticsDAOImpl;
import app.mvc.model.dto.Statisics;

public class StatisService {
	StatisticsDAO dao = new StatisticsDAOImpl();
	
	public Statisics selectTotal() throws SQLException {
		Statisics s= dao.totalStat();
		return s;
	}
	
	public Statisics selectTotalDay() throws SQLException {
		Statisics s= dao.dayTotalStat();
		return s;
	}
	
	public Statisics selectTotalMonth() throws SQLException {
		Statisics s= dao.monthTotalStat();
		return s;
	}

	public Statisics selectCatTotal(int n) throws SQLException {
		Statisics s= dao.categoryTotalStat(n);
		return s;
	}
	
	
}
