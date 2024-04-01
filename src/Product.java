import java.util.HashSet;
import java.util.Set;

public /* abstract */ class Product {
	
	private String productName;
	private int costPrice;
	private int sellingPrice;
	private int stock;
	private double productWeight;
	private Set<Order> orders;

	public Product(String productName, int costPrice, int sellingPrice, double productWeight, int stock) {
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.productWeight= productWeight;
		this.orders = new HashSet<>();
	}
	
	public Product(String productName, int costPrice, int sellingPrice, double productWeight) {
		this(productName, costPrice, sellingPrice, productWeight , 0);
	}

	public double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(productName);
		builder.append(" ");
		builder.append(toStringPrice(sellingPrice));
		return builder.toString();
	}

	public static String toStringPrice(double price) {
		 return String.format("%.2f$", price);
	}

}
