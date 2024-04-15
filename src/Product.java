import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
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
	//private ProductType productType;
	
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
	public void decreaseStock(int amountOrder) {
		this.stock = Math.min(stock - amountOrder, 0);
	}
	
	public boolean addOrder(Order order) {
		return this.orders.add(order);
	}

	public Iterator<Order> getOrders() {
		if(orders.isEmpty())
			throw new RuntimeException("Error! No products were ordered yet!");
		return orders.iterator();
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

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(serialNumber, other.serialNumber);
	}
	
	public static class OrderMemento{
		private Product product;
		private Set<Order> orders;
		
		private OrderMemento(Product product, Set<Order> orders) {
			this.product = product;
			this.orders = new HashSet<Order>(orders);
		}
		
		public void setMemento() {
			this.product.orders = this.orders; 
		}
	}
	
	public OrderMemento createOrderMemento(){
		return new OrderMemento(this, this.orders);
	}

}
