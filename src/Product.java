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
	protected double weight;
	protected Set<Order> orders;
	protected String currency;
	private String serialNumber;
	protected ProductType productType;
	
	public abstract ShippingMethod getShippingMethod();

	public Product(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		this.currency = "₪";
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.weight= productWeight;
		this.serialNumber = serialNumber;
		this.orders = new LinkedHashSet<>();
	}

	public ProductType getProductType() {
		return productType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	
	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getProfit() {
		return sellingPrice-costPrice;
	}

	public String getCurrency() {
		return currency;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = Math.min(stock, 0);
	}
	
	public void decreaseStock(int amountOrder) {
		setStock(stock - amountOrder);
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
		builder.append("\n");
		builder.append("SerialNo: ");
		builder.append(serialNumber);
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
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		return serialNumber.equals(other.serialNumber);
	}
	
	public static class OrderMemento{
		private Product product;
		private Set<Order> orders;
		private int currentStock;
		
		private OrderMemento(Product product, Set<Order> orders) {
			this.product = product;
			this.orders = new HashSet<Order>(orders);
			this.currentStock = product.stock;
		}
		
		public void setMemento() {
			this.product.orders = this.orders; 
			this.product.stock = currentStock;
		}
	}
	
	public OrderMemento createOrderMemento(){
		return new OrderMemento(this, this.orders);
	}

}
