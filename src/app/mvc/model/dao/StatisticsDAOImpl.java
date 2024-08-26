package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.mvc.model.dto.Products;
import app.mvc.model.dto.Statisics;
import app.mvc.util.DbManager;

public class StatisticsDAOImpl implements StatisticsDAO {
	/*
	 * select sum(total_price) from orders;--총계(int) select sum(quantity) from
	 * orders join orders_item using(order_id); --총 주문(int) select
	 * category_seq,count(*) from orders join orders_item using(order_id) join
	 * products using(product_id) group by category_seq order by category_seq;
	 * --카테고리별 주문(List<int>) select *from max_product_info; --제일 많이 팔린
	 * 상품정보(Products) select name from user_max_info; --제일 많이 주문한 유저(String)
	 */
	@Override
	public Statisics totalStat() throws SQLException {
		Connection con = null;
		
		Statisics s = null;

		int total = 0;
		int pTotal = 0;
		List<Integer> listCat = new ArrayList<>();
		Products p = null;
		String userName = null;

		try {
			con = DbManager.getConnection();

			total = total(con);
			pTotal = pTotal(con);
			listCat = listCat(con);//
			p = product(con);
			userName = userName(con);
			s = new Statisics(total, pTotal, listCat, p, userName, 0);

		} finally {
			DbManager.close(con, null, null);
		}
		return s;
	}

	public int total(Connection con) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;

		try {
			
			ps = con.prepareStatement("select sum(total_price) from orders");
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return sum;
	}

	public int pTotal(Connection con) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;

		try {
			
			ps = con.prepareStatement("select sum(quantity) from orders join orders_item using(order_id)");
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return sum;
	}

	public List<Integer> listCat(Connection con) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		int catSum = 0;

		try {
			
			ps = con.prepareStatement(
					"select category_seq,count(*) from orders join orders_item using(order_id) join products using(product_id) group by category_seq order by category_seq");
			rs = ps.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getInt(1) + ""+rs.getInt(2));
				list.add(catSum = rs.getInt(2));
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return list;
	}

	public Products product(Connection con) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Products p = null;
		try {
			ps = con.prepareStatement("select *from max_product_info");
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return p;
	}

	public String userName(Connection con) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement("select name from user_max_info");
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return name;
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
