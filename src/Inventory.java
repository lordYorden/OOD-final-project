import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Inventory{
	
	protected Set<Product> products;

	public Inventory() {
		this.products = new HashSet<>();
	}

	public boolean addProduct(Product product) {
		if(products.contains(product))
			throw new IllegalArgumentException(String.format("Error! Product with the serial number: %s already exist!", product.getSerialNumber()));
		return products.add(product);
	}
	
	public boolean addNewOrder(String serialNumber, Order order) {
		Product product = findProduct(serialNumber);
		return product.addOrder(order);
	}
	
	public boolean removeProduct(String serialNumber) {
		Product product = findProduct(serialNumber);
		return products.remove(product);
	}
	//public abstract ProductMemento addOrderToProduct(String serialNumber, Order order);
	//public abstract void undoOrder(ProductMemento memnto);
	
	public Iterator<Order> getOrdersOfProduct(String serialNumber){
		Product product = findProduct(serialNumber);
		return product.getOrders();
	}
	
	public Iterator<Product> getProducts() {
		return products.iterator();
	}
	
//	public boolean doseProductExist(String serialNumber) {
//		return products.contains(ProductFactory.createProduct(null, serialNumber, null, 0, 0, 0, 0, null));
//	}
	
	public Product findProduct(String serialNumber) {
		for (Iterator<Product> it = products.iterator(); it.hasNext(); ) {
	        Product product = it.next();
	        if(product.getSerialNumber().equals(serialNumber)){
	        	return product;
	        }
	    }
		throw new IllegalArgumentException(String.format("Error! Product with the serial number: %s dosen't exist!", serialNumber));
	}
	
	public void updateProductStock(String serialNumber, int newStock) {
		Product product = findProduct(serialNumber);
		product.setStock(newStock);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product currently in Store:\n");
		for (Product product : products) {
			builder.append(product);
			builder.append("\n");
		}
		return builder.toString();
	}

	public String getProductsOfType(ProductType type) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(type.getDescription());
		buffer.append(":\n");
		for (Product product : products) {
			if(product.getProductType() == type) {
				buffer.append(product);
				buffer.append("\n");
			}
		}
		return buffer.toString();
		
	}
	
	
}
