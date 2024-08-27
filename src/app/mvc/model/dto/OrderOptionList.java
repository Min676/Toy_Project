package app.mvc.model.dto;

public class OrderOptionList {
    private int optionId;
    private int orderItemId;
    private int oiId;
    private int selecCnt;
    
    public OrderOptionList() {}
    
    public OrderOptionList(int optionId, int orderItemId, int oiId, int selecCnt) {
		super();
		this.optionId = optionId;
		this.orderItemId = orderItemId;
		this.oiId = oiId;
		this.selecCnt = selecCnt;
	}

	// Getters and Setters
    public int getOptonId() {
        return optionId;
    }

    public void setOptonId(int optonId) {
        this.optionId = optonId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOiId() {
        return oiId;
    }

    public void setOiId(int oiId) {
        this.oiId = oiId;
    }

    public int getSelecCnt() {
        return selecCnt;
    }

    public void setSelecCnt(int selecCnt) {
        this.selecCnt = selecCnt;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderOptionList [optionId=");
		builder.append(optionId);
		builder.append(", orderItemId=");
		builder.append(orderItemId);
		builder.append(", oiId=");
		builder.append(oiId);
		builder.append(", selecCnt=");
		builder.append(selecCnt);
		builder.append("]");
		return builder.toString();
	}
    
    
}
