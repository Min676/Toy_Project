package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.Products;

public class ProductsDAOImpl implements ProductsDAO {

	@Override
	public List<Products> productSelect() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products productSelectByProductId(int product_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int productDeleteByProductId(int product_id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
