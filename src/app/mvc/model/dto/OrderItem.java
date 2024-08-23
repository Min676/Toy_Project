package app.mvc.model.dto;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int productId;
	private int quantity;
	private int selecSize;

	public OrderItem() {}
	public OrderItem(int orderItemId, int orderId, int productId, int quantity, int selecSize) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.selecSize = selecSize;
	}

	// Getters and Setters
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSelecSize() {
		return selecSize;
	}

	public void setSelecSize(int selecSize) {
		this.selecSize = selecSize;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[orderItemId=");
		builder.append(orderItemId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", selecSize=");
		builder.append(selecSize);
		builder.append("]");
		return builder.toString();
	}
}
