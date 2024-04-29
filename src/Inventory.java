import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class Inventory implements Serializable, Profitable, Iterable<Product>{
	
	protected Set<Product> products;
	private Stack<Product.OrderMemento> previousOrders;

	public Inventory() {
		this.products = new HashSet<>();
		this.previousOrders = new Stack<>();
	}
	
	public boolean addProduct(Product product) {
		if(products.contains(product))
			throw new IllegalArgumentException(String.format("Error! Product with the serial number: %s already exist!", product.getSerialNumber()));
		return products.add(product);
	}
	
	public boolean saveOrderSate(Product.OrderMemento orderState) {
		return previousOrders.add(orderState);
	}
	
	public void undoOrder() {
		if(previousOrders.empty())
			throw new RuntimeException("Error! No products were ordered yet!");
		previousOrders.pop().setMemento();
	}
	
	
	public boolean removeProduct(String serialNumber) {
		Product product = findProduct(serialNumber);
		return products.remove(product);
	}
	//public abstract ProductMemento addOrderToProduct(String serialNumber, Order order);
	//public abstract void undoOrder(ProductMemento memnto);
	
	public Iterator<Order> getOrdersOfProduct(String serialNumber){
		Product product = findProduct(serialNumber);
		return product.iterator();
	}
	
	public Iterator<Product> getProducts() {
		return products.iterator();
	}
	
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
		
		Iterator<Product> it = iterator();
		while (it.hasNext()) {
			Product product = (Product) it.next();
			builder.append(product.getProductInfo());
		}
		
		builder.append(String.format("\nTotal Profit for Store: %s\n",
				Product.toStringPrice(getProfit())));;
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

	@Override
	public double getProfit() {
		double profit = 0f;
		for (Product product : products) {
			profit += product.getProfit();
		}
		return profit;
	}

	@Override
	public Iterator<Product> iterator() {
		if(products.isEmpty())
			throw new RuntimeException("Error! No products were added yet!");
		return products.iterator();
	}
	
	
}
