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
	int productUpdateByProductId(Products products) throws SQLException;
	
	
	/**
	 * categorySeq에 해당하는 상품 전체 검색
	 */
	List<Products> productSelectByCategory(int categorySeq) throws SQLException;

	/**
	 * Product 등록하기
	 */
	int productInsert(Products products) throws SQLException;

	
	/**
	 * 주문량 기반 판매 상품 10건
	 */
	List<Products> productSelectRec()throws SQLException;
	
	/**
	 * login User 주문 기반 판매 상품 5건
	 */
	List<Products> productSelectUserRec(String userId)throws SQLException;
	
	
	/**
	 * 제품 block상태 확인
	 */
	int blockChk(int productId)throws SQLException;
	
	
	/**
	 * 제품 block상태 변환
	 */
	void productUpdateBlock(int id, int chk)throws SQLException;
}
