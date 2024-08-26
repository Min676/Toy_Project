package app.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import app.mvc.model.dto.Orders;
import app.mvc.model.dto.Products;
import app.mvc.model.dto.Users;
import app.mvc.model.service.ProductService;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;
import app.mvc.view.EndView;
import app.mvc.view.FailView;

public class CartController {
	private static ProductService productService = new ProductService();
	public Users selectUser(int user_seq) {
		Users u = null;
		
		return u;
	}
	
	public static void putCart(String id, int productId, int quantity) {
		
		try {
			Products products = productService.productSelectByProductId(productId);
			
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			
			//세션에서 장바구니 찾기
			Map<Products, Integer> cart = (Map<Products,Integer>)session.getAttribute("cart"); //상품 , 수량 저장 
			
			//장바구니가 없으면 장바구니 생성
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart);
			}
			
			//장바구니에서 상품찾기
			Integer oldQuantity = cart.get(products);//goods는 장바구니 Map의 key
			if(oldQuantity != null) { //장바구니에 이미 상품이 있다면
				quantity += oldQuantity; //수량을 누적
			}
			
			cart.put(products, quantity); //장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Map<Products,Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		if(cart == null ) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printViewCart(id , cart);
		}
	}

}












