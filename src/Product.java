import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Product implements Serializable, Profitable, Iterable<Order> {
	
	protected String productName;
	protected double costPrice;
	protected double sellingPrice;
	protected int stock;
	protected double weight;
	protected Set<Order> orders;
	protected String currency;
	private String serialNumber;
	protected ProductType productType;
	public static final String DEFAULT_CURRENCY = "₪";

	public Product(ProductType productType, String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock, String currency) {
		this.currency = currency;
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.weight= productWeight;
		this.serialNumber = serialNumber;
		this.orders = new LinkedHashSet<>();
		this.productType = productType;
	}
	
	public Product(ProductType productType, String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		this(productType, serialNumber, productName, costPrice, sellingPrice, productWeight, stock, DEFAULT_CURRENCY);
	}
	
	
	public abstract ShippingMethod getShippingMethod();
	
	public double getProfit() {
		double profit = 0f;
		for (Order order : orders) {
			profit += order.getProfit();
		}
		return profit;
	}

	public ProductType getProductType() {
		return productType;
	}
	
	public abstract Set<Invoiceable> getInvoicesFormat();

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
	
	public double getMargin() {
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
		this.stock = Math.max(stock, 0);
	}
	
	public void decreaseStock(int amountOrder) {
		setStock(stock - amountOrder);
	}
	
	public void addOrder(Order order) {
		
		if(order.getAmount() > stock) {
			throw new IllegalArgumentException(
					String.format("Error! Ordered amount exceeded avilable stock, Currently in stock: %d",
							stock));
		}
		
		if(orders.add(order)) {
			decreaseStock(order.getAmount());
			return;
		}
		
		throw new IllegalArgumentException(String.format("Error! Couldn't complete order number %s!", order.getSerialNumber()));	
	}

	@Override
	public Iterator<Order> iterator() {
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
		builder.append(String.format("\nType: %s\n",productType.getDescription()));
		return builder.toString();
	}
	
	public String getOrderHistoryOfProduct(Set<Invoiceable> formats) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\nOrders:\n");
		
		if(orders.isEmpty()){
			buffer.append("No Orders for this product yet!");
		}
		
		Iterator<Order> it = this.orders.iterator();
		while(it.hasNext()) {
			Order order = it.next();
			
			//buffer.append(String.format("\nDetails for order number %s: \n", order.getSerialNumber()));
			buffer.append(order);
			
			buffer.append("Invoices:\n");
			
			if(formats.isEmpty()){
				buffer.append("No invoices for this order!");
			}
			
			for (Invoiceable format : formats) {
				buffer.append(order.getInvoice(format));
			}
		}
		
		buffer.append(String.format("\nTotal Profit for product: %s\n",
				Product.toStringPrice(getProfit(), currency)));
		
		return buffer.toString();
	}
	
	public String getProductInfo() {

		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("Type: %s",
				productType.getDescription()));
		
		buffer.append(String.format("\nName: %s\nSerialNo: %s\nCurrent stock: %d",
				productName, serialNumber, stock));
		
		buffer.append(getOrderHistoryOfProduct(getInvoicesFormat()));
		
		return buffer.toString();
	}
	
	public static String toStringPrice(double price) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%.2f", price));
		sb.append(DEFAULT_CURRENCY);
		return sb.toString();
	}

	public static String toStringPrice(double price, String currency) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%.2f", price));
		sb.append(currency);
		return sb.toString();
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
	
	public static class OrderMemento implements Serializable{
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
