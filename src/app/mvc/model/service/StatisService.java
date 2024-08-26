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
}
