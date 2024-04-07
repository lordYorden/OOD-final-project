import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Product {
	
	protected String productName;
	protected int costPrice;
	protected int sellingPrice;
	protected int stock;
	protected double productWeight;
	protected Set<Order> orders;
	protected String currency;
	
	public abstract ShippingMethod getShippingMethod();

	public Product(String productName, int costPrice, int sellingPrice, double productWeight, int stock) {
		this.currency = " nis";
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.productWeight= productWeight;
		this.orders = new LinkedHashSet<>();
	}
	
	public Product(String productName, int costPrice, int sellingPrice, double productWeight) {
		this(productName, costPrice, sellingPrice, productWeight , 0);
	}

	public String getCurrency() {
		return currency;
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
		builder.append(toStringPrice(sellingPrice, currency));
		return builder.toString();
	}

	public static String toStringPrice(double price, String currency) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%.2f", price));
		sb.append(currency);
		return sb.toString();//String.format("%.2f%c", price, currency);
	}

}
