package app.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import app.mvc.model.dto.Products;

public interface ProductsDAO {
	/**
	 * 전체 상품
	 */
	List<Products> productSelect() throws SQLException;

	/**
	 * product_id에 해당하는 정보 검색
	 */
	Products productSelectByProductId(int product_id) throws SQLException;

	/**
	 * product_id에 해당하는 상품 삭제
	 */
	int productDeleteByProductId(int product_id) throws SQLException;
	
	
	/**
	 * product_id에 해당하는 상품 정보 수정
	 */
	int productUpdateByProductId(int product_id , Products products) throws SQLException;
	
	
	/**
	 * categorySeq에 해당하는 상품 전체 검색
	 */
	List<Products> productSeelctByCategory(int categorySeq) throws SQLException;
	
	

}
