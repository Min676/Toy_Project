package app.mvc.controller;

import app.mvc.model.dao.ProductsDAOImpl;
import app.mvc.model.dto.Products;

import java.util.List;

import app.mvc.model.dao.ProductsDAO;

public class ProductController {
	static ProductsDAO dao =new ProductsDAOImpl();
	
	public static void productSelect() {
		try {
			List<Products> productsList = dao.productSelect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
