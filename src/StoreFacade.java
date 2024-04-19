import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class StoreFacade {
	private Website website;
	private Inventory inventory;
	private Customer customer = new Customer("yarden", "0547731355");
	private Contact DHLContact = new Contact("yarden", "0547731355");
	private Contact FedExContact = new Contact("yarin", "0534298765");
	private Stack<Product.OrderMemento> previousOrders; 
	private static StoreFacade _instance = null;
	private Invoiceable formatForAccountent = new FormatForAccountent();
	private Invoiceable formatForCustomer = new FormatForCustomer();
	
	private StoreFacade() {
		this.website = new Website();
		this.inventory = new Inventory();
		this.previousOrders = new Stack<>();
		website.addObserver(new DHL(DHLContact));
		website.addObserver(new FedEx(FedExContact));
	}

	public static StoreFacade getInstance() {
		if(_instance == null)
			_instance = new StoreFacade();
		return _instance;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Invoiceable getFormatForAccountent() {
		return formatForAccountent;
	}

	public void setFormatForAccountent(Invoiceable formatForAccountent) {
		this.formatForAccountent = formatForAccountent;
	}

	public Invoiceable getFormatForCustomer() {
		return formatForCustomer;
	}

	public void setFormatForCustomer(Invoiceable formatForCustomer) {
		this.formatForCustomer = formatForCustomer;
	}

	public void updateProductStock(String serialNumber, int newStock) {
		inventory.updateProductStock(serialNumber, newStock);
	}
	
	public void removeProduct(String serialNumber) {
		inventory.removeProduct(serialNumber);
	}

	public void addProduct(ProductType type, Product product) {
		inventory.addProduct(product);
	}

	@Override
	public String toString() {
		return inventory.toString();
	}

	public void addOrder(String serialNumber, int amount, String orderSerialNum) {
		Product product = inventory.findProduct(serialNumber);
		ShippingMethod method = product.getShippingMethod();
		
		if(amount > product.getStock()) {
			throw new IllegalArgumentException(
					String.format("Error! Ordered amount exceeded avilable stock, Currently in stock: %d",
							product.getStock()));
		}
		
		Order order = null;
		
		if(method != ShippingMethod.NoShipping) {
			order = new ShippingOrder(orderSerialNum, customer, product, amount, method);
			website.addNewOrder((ShippingOrder)order);
			System.out.println(order);
		}else {
			order = new Order(orderSerialNum, customer, product, amount);
		}
		
		previousOrders.push(product.createOrderMemento());
		product.addOrder(order);
		product.decreaseStock(amount);
	}

	public void undoOrder() {
		if(previousOrders.empty())
			throw new RuntimeException("Error! No products were ordered yet!");
		previousOrders.pop().setMemento();
	}
	
	public String getProductsOfType(ProductType type) {
		return inventory.getProductsOfType(type);
	}

	public String getOrderHistoryOfProduct(String serialNumber) {
		Product product = inventory.findProduct(serialNumber);
		Set<Invoiceable> formats = new HashSet<>();
		formats.add(formatForAccountent);
		return product.getOrderHistoryOfProduct(formats);
	}
	
	@Deprecated
	public String getOrderHistoryOfProduct(String serialNumber, Set<Invoiceable> formats) {
		Product product = inventory.findProduct(serialNumber);
		StringBuffer buffer = new StringBuffer();
		double profit = 0f;
		
		buffer.append("\nOrders:\n");
		
		Iterator<Order> orders = product.getOrders();
		while(orders.hasNext()) {
			Order order = orders.next();
			
			buffer.append(String.format("\nDetails for order number %s: \n", order.getSerialNumber()));
			buffer.append(order);
			
			buffer.append("Invoices:\n");
			
			for (Invoiceable format : formats) {
				buffer.append(order.getInvoice(format));
			}
			
			profit += order.getTotalOrderProfit();
		}
		
		buffer.append(String.format("\nTotal Profit for product: %s\n",
				Product.toStringPrice(profit, product.getCurrency())));
		
		return buffer.toString();
	}

	public String getProductInfo(String serialNumber) {
		Product product = inventory.findProduct(serialNumber);
		return product.getProductInfo();
	}
}
