package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.mvc.model.dto.Products;
import app.mvc.util.DbManager;

public class ProductsDAOImpl implements ProductsDAO {

	@Override
	public List<Products> productSelect() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Products> list = new ArrayList<>();
		try {
			con = DbManager.getConnection();
			ps= con.prepareStatement("select * from Products order by PRODUCTS_ID");
			rs = ps.executeQuery();

			while(rs.next()) {
				Products products  = new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				list.add(products);
			}
		}finally {
			DbManager.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public Products productSelectByProductId(int product_id) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Products products =null;
		try {
			con = DbManager.getConnection();
			ps= con.prepareStatement("select * from products where product_id=?");
			ps.setInt(1, product_id);
			rs = ps.executeQuery();

			if(rs.next()) {
				products  =  new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));

			}
		}finally {
			DbManager.close(con, ps, rs);
		}
		return products;
	}

	@Override
	public int productDeleteByProductId(int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement("DELETE FROM products WHERE product_id = ?");
			ps.setInt(1, product_id);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				result = 1; // 삭제 성공 시 1 반환
			}
		} finally {
			DbManager.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int productUpdateByProductId(int product_id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Products> productSeelctByCategory(int categorySeq) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
