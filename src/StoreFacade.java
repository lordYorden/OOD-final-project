import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StoreFacade {
	private Website website;
	private Inventory inventory;
	private Customer customer = new Customer("yarden", "0547731355");
	private Contact DHLContact = new Contact("yarden", "0547731355");
	private Contact FedExContact = new Contact("yarin", "0534298765"); 
	private static StoreFacade _instance = null;
	private Invoiceable formatForAccountent = new FormatForAccountent();
	private Invoiceable formatForCustomer = new FormatForCustomer();
	
	private StoreFacade() {
		this.website = new Website();
		this.inventory = new Inventory();
		//this.previousOrders = new Stack<>();
		website.addObserver(new DHL(DHLContact));
		website.addObserver(new FedEx(FedExContact));
	}

	public static StoreFacade getInstance() {
		if(_instance == null)
			_instance = new StoreFacade();
		return _instance;
	}
	
	public void load(String filePath, InventoryBackupable backupable) {
		try {
			this.inventory = backupable.loadInventory(filePath);
		} catch (Exception e) {
			if(inventory == null)
				this.inventory = new Inventory();
			throw new RuntimeException(String.format("Error! Failed to load the previous state!\nError Details:%s\n",
					e.getMessage()));
		}
	}
	
	public void save(String filePath, InventoryBackupable backupable) {
		try {
			backupable.saveInventory(filePath, this.inventory);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Error! Failed to save the current state!\nError Details:%s\n",
					e.getMessage()));
		}
	}
	
	public void hardCodedSystem() {
		ProductType type = null;
		Product product = null;
		
		type = ProductType.SoldThroughWebsite;
		product = ProductFactory.createProduct(type, "QhBIiZs8", "Bamaba bisly mix", 5.20, 11.90, 0.7, 12);
		((Shippable) product).addShippingMethod(ShippingMethod.ExpressShipping);
		addProduct(type,product);
		
		addOrder(product, 2,"P8maOpR3", "Isreal", ShippingMethod.ExpressShipping);
		addOrder(product, 6,"WddbgwbS", "Massachusetts", ShippingMethod.ExpressShipping);
		addOrder(product, 3, "Qw4Zx771", "France", ShippingMethod.ExpressShipping);
		
		
		//type = ProductType.SoldThroughWebsite;
		product = ProductFactory.createProduct(type, "2KmP9nmW", "Big Mouth Billy Bass", 21.5, 42.98, 0.89, 50);
		((Shippable) product).addShippingMethod(ShippingMethod.ExpressShipping);
		((Shippable) product).addShippingMethod(ShippingMethod.StandardShipping);
		addProduct(type,product);
		
		addOrder(product, 1,"BfopAp70", "Isreal", ShippingMethod.StandardShipping);
		addOrder(product, 21,"ANgIuLYK", "Missouri", ShippingMethod.ExpressShipping);
		addOrder(product, 1, "GAHGTp6q", "Italy", ShippingMethod.StandardShipping);
		
		
		
		product = ProductFactory.createProduct(type, "msJ3sijt", "Darth Wader's Lightsaber", 12.69, 40.99, 1, 12);
		((Shippable) product).addShippingMethod(ShippingMethod.ExpressShipping); //very delicate
		addProduct(type,product);
		
		addOrder(product, 5,"BfopAp70", "Isreal", ShippingMethod.StandardShipping);
		addOrder(product, 1,"ANgIuLYK", "Missouri", ShippingMethod.ExpressShipping);
		addOrder(product, 2, "GAHGTp6q", "Ohio", ShippingMethod.StandardShipping);
		
		type = ProductType.SoldToWholesalers;
		product = ProductFactory.createProduct(type, "rAwj6nze", "240oz of Sweet corn", 200, 311.99, 7.4, 100);
		addProduct(type,product);
		
		addOrder(product, 20,"qbwVOowE", null, ShippingMethod.NoShipping);
		addOrder(product, 25,"PivEpkmx", null, ShippingMethod.NoShipping);
		addOrder(product, 12,"W8HAxtj0", null, ShippingMethod.NoShipping);
		
		product = ProductFactory.createProduct(type, "dSkTvsTF", "100 pack of Toilet Paper", 400, 620.99, 4.3, 100);
		addProduct(type,product);
		
		addOrder(product, 23,"i8VaZxvK", null, ShippingMethod.NoShipping);
		addOrder(product, 15,"zsImoUqB", null, ShippingMethod.NoShipping);
		addOrder(product, 12,"2Yq7xNTu", null, ShippingMethod.NoShipping);
		
		product = ProductFactory.createProduct(type, "XToWdT6Y", "Cheder Cheese wheel", 322.6, 450.45, 35, 20);
		addProduct(type,product);
		
		addOrder(product, 1,"0Tsr1ecp", null, ShippingMethod.NoShipping);
		addOrder(product, 1,"vIAKmIzF", null, ShippingMethod.NoShipping);
		addOrder(product, 1,"pJ90S1hT", null, ShippingMethod.NoShipping);
		
		type = ProductType.SoldInStore;
		product = ProductFactory.createProduct(type, "W15NJRzh", "Canned Sweet corn", 3, 10, 0.55, 30);
		addProduct(type,product);
		
		addOrder(product, 2,"ecKU63Bc", null, ShippingMethod.NoShipping);
		addOrder(product, 3,"DPXQYJWX", null, ShippingMethod.NoShipping);
		addOrder(product, 5,"6BrTc4LR", null, ShippingMethod.NoShipping);
		
		product = ProductFactory.createProduct(type, "6Ok0sJ5M", "15 pack of Toilet Paper", 15.9, 45.99, 0.83, 20);
		addProduct(type,product);
		
		addOrder(product, 2,"Ab4V8hyj", null, ShippingMethod.NoShipping);
		addOrder(product, 1,"bCKRYNqo", null, ShippingMethod.NoShipping);
		addOrder(product, 1,"VwF57OBs", null, ShippingMethod.NoShipping);
		
		product = ProductFactory.createProduct(type, "3dkWHTfB", "Cheder Cheese 200 gram", 8, 11, 0.25, 15);
		addProduct(type,product);
		
		addOrder(product, 1,"vuI4pifI", null, ShippingMethod.NoShipping);
		addOrder(product, 2,"E82q0yCN", null, ShippingMethod.NoShipping);
		addOrder(product, 1,"1bA9JfBh", null, ShippingMethod.NoShipping);
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
		if(product instanceof Shippable && !((Shippable) product).hasShippingMethod()) {
			throw new IllegalArgumentException("Error! A shiippble product has no available shipping method!");
		}

		inventory.addProduct(product);
	}

	@Override
	public String toString() {
		return inventory.toString();
	}
	
	public void addOrder(Product product, int amount, String orderSerialNum, String destCountry, ShippingMethod method) {
		//ShippingMethod method = product.getShippingMethod();
		
		if(amount > product.getStock()) {
			throw new IllegalArgumentException(
					String.format("Error! Ordered amount exceeded avilable stock, Currently in stock: %d",
							product.getStock()));
		}
		
		Order order = null;
		if(method != ShippingMethod.NoShipping) {
			order = new ShippingOrder(orderSerialNum, customer, product, amount, method, destCountry);
			website.addNewOrder((ShippingOrder)order);
			System.out.println(order);
		}else {
			order = new Order(orderSerialNum, customer, product, amount);
		}
		
		inventory.saveOrderSate(product.createOrderMemento());
		product.addOrder(order);
	}
	
	public void addOrder(String serialNumber, int amount, String orderSerialNum) {
		Product product = inventory.findProduct(serialNumber);
		ShippingMethod method = product.getShippingMethod();
		addOrder(product, amount, orderSerialNum, null, method);
	}

	public void addOrder(String serialNumber, int amount, String orderSerialNum, String destCountry) {
		Product product = inventory.findProduct(serialNumber);
		ShippingMethod method = product.getShippingMethod();
		addOrder(product, amount, orderSerialNum, destCountry, method);
	}

	public void undoOrder() {
		inventory.undoOrder();
	}
	
	public String getSimpleInventory() {
		StringBuffer buffer = new StringBuffer();
		Iterator<Product> it = inventory.iterator();
		buffer.append("Product currently in Store:\n");
		while (it.hasNext()) {
			Product product = (Product) it.next();
			buffer.append(product);
			buffer.append("\n");
		}
		return buffer.toString();
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

	public String getProductInfo(String serialNumber) {
		Product product = inventory.findProduct(serialNumber);
		return product.getProductInfo();
	}
}
