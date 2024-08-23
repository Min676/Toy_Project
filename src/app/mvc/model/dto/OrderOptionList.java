package app.mvc.model.dto;

public class OrderOptionList {
    private int optonId;
    private int orderItemId;
    private int oiId;
    private int selecCnt;
    
    public OrderOptionList() {}
    
    public OrderOptionList(int optonId, int orderItemId, int oiId, int selecCnt) {
		super();
		this.optonId = optonId;
		this.orderItemId = orderItemId;
		this.oiId = oiId;
		this.selecCnt = selecCnt;
	}

	// Getters and Setters
    public int getOptonId() {
        return optonId;
    }

    public void setOptonId(int optonId) {
        this.optonId = optonId;
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
    
    
}
