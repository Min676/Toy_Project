package app.mvc.model.dto;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private int orderId;
	private int userSeq;
	private String orderDate;
	private int totalPrice;
	private int status;
	private String userId;
	
	public Orders() {}

	public Orders(int orderId, int userSeq, String orderDate, int totalPrice, int status, String userId) {
		super();
		this.orderId = orderId;
		this.userSeq = userSeq;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.status = status;
		this.userId = userId;
	}
	
	public Orders(int orderId, int userSeq, String orderDate, int totalPrice, int status,
			List<OrderItem> orderItemList) {
		super();
		this.orderId = orderId;
		this.userSeq = userSeq;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderItemList = orderItemList;
	}

	// 1 : 다
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList= orderItemList;
	}
	
	
	// Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[orderId=");
		builder.append(orderId);
		builder.append(", userSeq=");
		builder.append(userSeq);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", status=");
		builder.append(status);
		builder.append(", orderItemList=");
		builder.append(orderItemList);
		builder.append("]");
		return builder.toString();
	}
	

}
