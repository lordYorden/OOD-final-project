import java.util.Iterator;
import java.util.Stack;

public class StoreFacade {
	private Website website;
	private Inventory inventory;
	private final Customer customer = new Customer("yarden", "0547731355");
	private Stack<Product.OrderMemento> previousOrders; 
	
	public StoreFacade() {
		this.website = new Website();
		this.inventory = new Inventory();
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

	public void addOrder(String serialNumber, int amount) {
		Product product = inventory.findProduct(serialNumber);
		ShippingMethod method = product.getShippingMethod();
		
		if(amount > product.getStock()) {
			throw new IllegalArgumentException(
					String.format("Error! Ordered amount exceeded avilable stock, Currently in stock: %d",
							product.getStock()));
		}
		
		Order order = new Order(serialNumber, customer, product, amount, method);
		if(product instanceof ProductSoldThroughWebsite){
			website.addNewOrder(serialNumber, order);
		}
		
		product.decreaseStock(amount);
		previousOrders.add(product.createOrderMemento());
		product.addOrder(order);
	}

	public void undoOrder() {
		if(previousOrders.empty())
			throw new RuntimeException("Error! No products were ordered yet!");
		previousOrders.pop().setMemento();
	}

	public void PrintOrdersOfProduct(String serialNumber) {
		Product product = inventory.findProduct(serialNumber);
		
		Iterator<Order> it = product.getOrders();
		System.out.format("Orders of %s:\n",product);
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

	public void printProduct(String serialNumber) {
		Product product = inventory.findProduct(serialNumber);
		System.out.println(product);
	}
	
	
	
	/*private Inventory<ProductSoldToWholesalers> storage;
	private Inventory<ProductsSoldInStore> store;
	
	public void updateProductStock(String serialNumber, int newStock) {
		Inventory<? extends Product> inventory = getProductInventory(serialNumber);
		inventory.updateProductStock(serialNumber, newStock);
	}

	public Inventory<? extends Product> getProductInventory(String serialNumber) {
		if(website.findProduct(serialNumber) != null){
			return website;
		}else if(storage.findProduct(serialNumber) != null){
			return storage;
		}else if(store.findProduct(serialNumber) != null) {
			return store;
		}
		throw new IllegalArgumentException(String.format("Error! Product with the serial number: %s dosen't exist!", serialNumber));
	}
	
	public Inventory<? extends Product> getProductInventory(ProductType type) {
		switch(type) {
		case SoldInStore:
			return store;
		case SoldThroughWebsite:
			return website;
		case SoldToWholesalers:
			return storage;
		default:
			throw new IllegalArgumentException(String.format("Error! No such product type as: %s!", type.name()));
		}
	}

	public void removeProduct(String serialNumber) {
		Inventory<? extends Product> inventory = getProductInventory(serialNumber);
		inventory.removeProduct(serialNumber);
	}

	public void addProduct(ProductType type, Product product) {
		Inventory<? extends Product> inventory = getProductInventory(type);
		inventory.addProduct(product);
	}*/
}
