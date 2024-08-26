package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		Map<Integer,Integer> listCat = new HashMap<Integer,Integer>();
		Products p = null;
		String userName = null;

		try {
			con = DbManager.getConnection();

			total = total(con, "select sum(total_price) from orders", 0);
			pTotal = pTotal(con, "select sum(quantity) from orders join orders_item using(order_id)", 0);
			listCat = listCat(con, "select category_seq,count(*) from orders join orders_item using(order_id) join "
					+ "products using(product_id) group by category_seq order by category_seq");//
			p = product(con, "select *from max_product_info");
			userName = userName(con, "select name from user_max_info",0);
			s = new Statisics(total, pTotal, listCat, p, userName, 0);

		} finally {
			DbManager.close(con, null, null);
		}
		return s;
	}

	@Override
	public Statisics dayTotalStat() throws SQLException {
		Connection con = null;
		Statisics s = null;

		int total = 0;
		int pTotal = 0;
		Map<Integer,Integer> listCat = new HashMap<Integer,Integer>();
		Products p = null;
		String userName = null;
		int incRes = 0;

		try {
			con = DbManager.getConnection();

			total = total(con,
					"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'MMDD') = TO_CHAR(SYSDATE, 'MMDD')",
					0);
			pTotal = pTotal(con,
					"select sum(quantity) from orders join orders_item using(order_id) WHERE TO_CHAR(order_date, 'MMDD') = TO_CHAR(SYSDATE, 'MMDD')",
					0);
			listCat = listCat(con, "select category_seq,count(*) from orders join orders_item using(order_id)"
					+ "join products using(product_id) where TO_CHAR(order_date, 'MMDD') = TO_CHAR(SYSDATE, 'MMDD')"
					+ "group by category_seq  order by category_seq");
			p = product(con, "select *from max_product_info_day");
			userName = userName(con, "select name from user_max_info_day",0);
			incRes = incDay(con,
					"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'MMDD') = TO_CHAR(SYSDATE, 'MMDD')")
					- incDay(con,
							"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'MMDD') = TO_CHAR(SYSDATE -1, 'MMDD')");

			s = new Statisics(total, pTotal, listCat, p, userName, incRes);

		} finally {
			DbManager.close(con, null, null);
		}
		return s;
	}

	@Override
	public Statisics monthTotalStat() throws SQLException {
		Connection con = null;
		Statisics s = null;

		int total = 0;
		int pTotal = 0;
		Map<Integer,Integer> listCat = new HashMap<Integer,Integer>();
		Products p = null;
		String userName = null;
		int incRes = 0;

		try {
			con = DbManager.getConnection();

			total = total(con,
					"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'YYMM') = TO_CHAR(SYSDATE, 'YYMM')",
					0);
			pTotal = pTotal(con,
					"select sum(quantity) from orders join orders_item using(order_id) WHERE TO_CHAR(order_date, 'YYMM') = TO_CHAR(SYSDATE, 'YYMM')",
					0);
			listCat = listCat(con, "select category_seq,count(*) from orders join orders_item using(order_id)"
					+ "join products using(product_id) where TO_CHAR(order_date, 'YYMM') = TO_CHAR(SYSDATE, 'YYMM')"
					+ "group by category_seq  order by category_seq");
			p = product(con, "select *from max_product_info_month");
			userName = userName(con, "select name from user_max_info_month",0);
			incRes = incDay(con,
					"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'YYMM') = TO_CHAR(SYSDATE, 'YYMM')")
					- incDay(con,
							"SELECT sum(total_price) FROM orders WHERE TO_CHAR(order_date, 'YYMM') = TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYMM')");

			s = new Statisics(total, pTotal, listCat, p, userName, incRes);

		} finally {
			DbManager.close(con, null, null);
		}
		return s;
	}

	@Override
	public Statisics categoryTotalStat(int category_seq) throws SQLException {
		Connection con = null;
		Statisics s = null;

		int total = 0;
		int pTotal = 0;
		Map<Integer,Integer> listCat = new HashMap<Integer,Integer>();
		Products p = null;
		String userName = null;

		try {
			con = DbManager.getConnection();

			total = total(con,
					"select sum(price * quantity) from orders join orders_item using(order_id) join products using(product_id) where category_seq =?",
					category_seq);
			pTotal = pTotal(con,
					"select sum(quantity) from orders join orders_item using(order_id) join products using(product_id) where category_seq =?",
					category_seq);
			if (category_seq == 1)
				p = product(con, "select *from max_product_info_cat");
			else if (category_seq == 2)
				p = product(con, "select *from max_product_info_cat2");
			else if (category_seq == 3)
				p = product(con, "select *from max_product_info_cat3");
			
			userName = userName(con, "select u.name, SUM(oi.QUANTITY) AS TOTAL_QUANTITY FROM ORDERS o join ORDERS_ITEM oi using(order_id) "
									+ "join PRODUCTS p using(product_id) join USERS u using(user_seq) where p.CATEGORY_SEQ = ?"
									+ "group BY u.name order BY TOTAL_QUANTITY desc FETCH FIRST 1 ROWS ONLY ",category_seq);

			s = new Statisics(total, pTotal, null, p, userName, 0);

		} finally { 
			DbManager.close(con, null, null);
		}
		return s;
	}

	public int total(Connection con, String sql, int cat) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;

		try {

			ps = con.prepareStatement(sql);
			if (cat > 0)
				ps.setInt(1, cat);

			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return sum;
	}

	// 각 값을 구하는 메서드 영역

	public int pTotal(Connection con, String sql, int cat) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;

		try {

			ps = con.prepareStatement(sql);
			if (cat > 0)
				ps.setInt(1, cat);
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return sum;
	}

	public Map<Integer,Integer> listCat(Connection con, String sql) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<Integer,Integer> list = new HashMap<Integer, Integer>();
		

		try {

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.put(rs.getInt(1),rs.getInt(2));
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return list;
	}

	public Products product(Connection con, String sql) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Products p = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return p;
	}

	public String userName(Connection con, String sql, int cat) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			if(cat>0)
				ps.setInt(1, cat);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return name;
	}

	public int incDay(Connection con, String sql) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} finally {
			DbManager.close(null, ps, rs);
		}

		return sum;
	}

	@Override
	public Map<String, Integer> topSell() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		try {
			con = DbManager.getConnection();
			ps= con.prepareStatement("select * from (select name, count(*) cnt from orders join orders_item using(order_id) "
					+ "join products using(product_id)GROUP BY name order by cnt desc) where ROWNUM <= 10");
			rs = ps.executeQuery();

			while(rs.next()) {
				map.put(rs.getString(1),rs.getInt(2));
			}
		}finally {
			DbManager.close(con, ps, rs);
		}
		return map;
	}

}
