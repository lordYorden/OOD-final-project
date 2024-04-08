import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Product {
	
	protected String productName;
	protected double costPrice;
	protected double sellingPrice;
	protected int stock;
	protected double productWeight;
	protected Set<Order> orders;
	protected String currency;
	private String serialNumber;
	
	public abstract ShippingMethod getShippingMethod();

	public Product(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		this.currency = "₪";
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.productWeight= productWeight;
		this.serialNumber = serialNumber;
		this.orders = new LinkedHashSet<>();
	}
	
	/*
	 * public Product(String serialNumber, String productName, double costPrice,
	 * double sellingPrice, double productWeight) { this(serialNumber, productName,
	 * costPrice, sellingPrice, productWeight , 0); }
	 */

	public String getSerialNumber() {
		return serialNumber;
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
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellingPrice() {
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
