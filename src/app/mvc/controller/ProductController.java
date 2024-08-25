package app.mvc.controller;

import app.mvc.exception.NotFoundException;
import app.mvc.model.dto.Products;
import app.mvc.model.service.ProductService;
import app.mvc.view.EndView;
import app.mvc.view.FailView;

import java.sql.SQLException;
import java.util.List;

public class ProductController {

	static ProductService productService = new ProductService();

	public static void productSelect() {
		try {
			List<Products> list = productService.productSelect();
			EndView.printProductsList(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
        }
    }
}
