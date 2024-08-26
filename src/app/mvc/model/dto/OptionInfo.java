package app.mvc.model.dto;

public class OptionInfo {
    private int optionId;
    private String optionName;
    private int optionPrice;
    private int categorySeq;
    private int size;
    
    public OptionInfo() {}
	public OptionInfo(int optionId, String optionname, int optionprice, int categorySeq) {
		super();
		this.optionId = optionId;
		this.optionName = optionname;
		this.optionPrice = optionprice;
		this.categorySeq = categorySeq;
	}
	
	public OptionInfo(int optionId, String optionname, int optionprice, int categorySeq, int size) {
		super();
		this.optionId = optionId;
		this.optionName = optionname;
		this.optionPrice = optionprice;
		this.categorySeq = categorySeq;
		this.size = size;
	}
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionname) {
		this.optionName = optionname;
	}
	public int getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(int optionprice) {
		this.optionPrice = optionprice;
	}
	public int getCategorySeq() {
		return categorySeq;
	}
	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	public int getSelecSize() {
		return size;
	}
	public void setSelecSize(int selecSize) {
		this.size = selecSize;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[optionId=");
		builder.append(optionId);
		builder.append(", optionName=");
		builder.append(optionName);
		builder.append(", optionPrice=");
		builder.append(optionPrice);
		builder.append(", categorySeq=");
		builder.append(categorySeq);
		builder.append("]");
		return builder.toString();
	}
    
}
