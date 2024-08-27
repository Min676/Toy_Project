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

	public static void productSelect(String userId) {
		try {
			List<Products> list = productService.productSelect();
			List<Products> list2 = productService.productSelectRec();
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

	public static void productUpdateByProductId(Products products) {
		try {
			productService.productUpdateByProductId(products);
			EndView.printMessage("제품번호 " + products.getProduct_id() + "가 수정되었습니다!");
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

		public static void  productInsert(Products products){
			try {
				productService.productInsert(products);
				EndView.printMessage("제품명 " + products.getName() + "가 등록되었습니다!");
			} catch (SQLException e) {
				FailView.errorMessage(e.getMessage());
			} catch (ModifyException e) {
				FailView.errorMessage(e.getMessage());
			}
		}
		
		
		public static void productSelectByRec(){
			try {
				List<Products> list2 = productService.productSelectRec();
				EndView.printProductsListREC(list2);
				
			} catch (SQLException e) {
				FailView.errorMessage(e.getMessage());
	        } catch (NotFoundException e) {
				FailView.errorMessage(e.getMessage());
	        }
	    }
		
		
		public static void productSelectUser(String userId) {
			try {
				if(!userId.equals("admin")) {
					List<Products> list3 = productService.productSelectUserRec(userId);
					EndView.printProductsListUserREC(list3);
				}
			} catch (SQLException e) {
				FailView.errorMessage(e.getMessage());
	        } catch (NotFoundException e) {
				FailView.errorMessage(e.getMessage());
	        }
	    }

}
