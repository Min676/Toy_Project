package app.mvc.controller;

import app.mvc.exception.ModifyException;
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

	public static void productSelectByProductId(int product_id) {
		try {
			Products products = productService.productSelectByProductId(product_id);
			EndView.printMessage(products.toString());
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void productUpdateByProductId(int product_id, Products products) {
		try {
			productService.productUpdateByProductId(product_id, products);
			EndView.printMessage("제품번호 " + product_id + "가 수정되었습니다!");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } catch (ModifyException e) {
			FailView.errorMessage(e.getMessage());
        }
    }

	public static void productDeleteByProductId(int product_id) {
		try {
			productService.productDeleteByProductId(product_id);
			EndView.printMessage("제품번호 " + product_id + "가 삭제되었습니다!");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		} catch (ModifyException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void productSelectByCategory(int categorySeq){
		try {
			List<Products> list = productService.productSelectByCategory(categorySeq);
			EndView.printProductsList(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
        }
    }

}
