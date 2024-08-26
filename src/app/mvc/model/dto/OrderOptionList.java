package app.mvc.model.dto;

public class OrderOptionList {
    private int optionId;
    private int orderItemId;
    private int oiId;
    private int selecCnt;
    private int selecSize;
    
    public OrderOptionList() {}
    
    public OrderOptionList(int optionId, int orderItemId, int oiId, int selecCnt) {
		super();
		this.optionId = optionId;
		this.orderItemId = orderItemId;
		this.oiId = oiId;
		this.selecCnt = selecCnt;
	}
    
    public OrderOptionList(int optonId, int orderItemId, int oiId, int selecCnt, int selecSize) {
		super();
		this.optionId = optonId;
		this.orderItemId = orderItemId;
		this.oiId = oiId;
		this.selecCnt = selecCnt;
		this.selecSize = selecSize;
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

	public int getSelecSize() {
		return selecSize;
	}

	public void setSelecSize(int selecSize) {
		this.selecSize = selecSize;
	}
    
}
