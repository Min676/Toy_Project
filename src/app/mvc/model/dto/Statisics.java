package app.mvc.model.dto;

import java.util.List;

public class Statisics {
	private int totalPrice;//총계
	private int totalOrder;//총 주문
	private List<Integer> category_oderSum_list;//카테고리별 총주문
	private String productName;//제일 많이 팔린 상품
	private String userName;//제일많이 주문한 유저
	private int increment;//전일/월 대비 증가량
	
	public Statisics() {}

	public Statisics(int totalPrice, int totalOrder, List<Integer> category_oderSum_list, String productName,
			String userName, int increment) {
		super();
		this.totalPrice = totalPrice;
		this.totalOrder = totalOrder;
		this.category_oderSum_list = category_oderSum_list;
		this.productName = productName;
		this.userName = userName;
		this.increment = increment;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

	public List<Integer> getCategory_oderSum_list() {
		return category_oderSum_list;
	}

	public void setCategory_oderSum_list(List<Integer> category_oderSum_list) {
		this.category_oderSum_list = category_oderSum_list;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Statisics [totalPrice=");
		builder.append(totalPrice);
		builder.append(", totalOrder=");
		builder.append(totalOrder);
		builder.append(", category_oderSum_list=");
		builder.append(category_oderSum_list);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", increment=");
		builder.append(increment);
		builder.append("]");
		return builder.toString();
	}
	

	
}
