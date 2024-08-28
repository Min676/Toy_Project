package app.mvc.model.dto;

public class Products {
	private int Product_id;
	private String name;
	private String info;
	private int price;
	private int category_seq;
	private int block;

	

	public Products() {
	}

	public Products(int product_id, String name, String info, int price, int category_seq, int block) {
		super();
		Product_id = product_id;
		this.name = name;
		this.info = info;
		this.price = price;
		this.category_seq = category_seq;
		this.block = block;
	}
	public Products(int product_id, String name, String info, int price, int category_seq) {
		super();
		Product_id = product_id;
		this.name = name;
		this.info = info;
		this.price = price;
		this.category_seq = category_seq;
	}

	public Products(String name, String info, int price, int category_seq) {
		this.name = name;
		this.info = info;
		this.price = price;
		this.category_seq = category_seq;
	}

	public Products(int product_id, String name, String info, int price) {
		Product_id = product_id;
		this.name = name;
		this.info = info;
		this.price = price;
	}
	
	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getProduct_id() {
		return Product_id;
	}

	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategory_seq() {
		return category_seq;
	}

	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}

	@Override
	public String toString() {
		return Product_id + " | " + name + " | " + info + " | " + price + " | " + category_seq;
	}

}
