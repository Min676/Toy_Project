package app.mvc.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import app.mvc.exception.NotFoundException;
import app.mvc.model.dto.OrderItem;
import app.mvc.model.dto.Products;
import app.mvc.session.Session;
import app.mvc.session.SessionSet;
import app.mvc.view.EndView;

	public class CartService {
		ProductService productService = new ProductService();
		public void putCart(String id, int productId, int quantity, int size, OrderItem orderItem) throws SQLException, NotFoundException {
			Products products = productService.productSelectByProductId(productId);
			// 카트에 담아야 할 것 : 상품정보와 갯수, 사이즈(옵션?)
			
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);
			
			//세션에서 장바구니 찾기
			Map<OrderItem, Integer> cart = (Map<OrderItem,Integer>)session.getAttribute("cart"); //상품 , 수량 저장 
			
			//장바구니가 없으면 장바구니 생성
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart);
			}
			
			//장바구니에서 상품찾기
			Integer oldQuantity = cart.get(orderItem);//goods는 장바구니 Map의 key
			if(oldQuantity != null) { //장바구니에 이미 상품이 있다면
				quantity += oldQuantity; //수량을 누적
			}
			
			cart.put(orderItem, quantity); //장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");			
		}
	
}
