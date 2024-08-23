package app.mvc.model.dto;

public class Category {
    private int categorySeq;
    private String name;
    
    public Category() {}
    public Category(int categorySeq, String name) {
		super();
		this.categorySeq = categorySeq;
		this.name = name;
	}

	// Getters and Setters
    public int getCategorySeq() {
        return categorySeq;
    }

    public void setCategorySeq(int categorySeq) {
        this.categorySeq = categorySeq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[categorySeq=");
		builder.append(categorySeq);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
    
    
}
