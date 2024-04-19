import java.util.HashSet;
import java.util.Set;

public class ProductsSoldInStore extends Product {
	
	private Invoiceable forCustomer;
	private Invoiceable forAccountent;

	public ProductsSoldInStore(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock, Invoiceable forCustomer, Invoiceable forAccountent) {
		super(ProductType.SoldInStore, serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		this.forAccountent = forAccountent;
		this.forCustomer = forCustomer;
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}

	@Override
	public Set<Invoiceable> getInvoicesFormat() {
		Set<Invoiceable> formats = new HashSet<>();
		formats.add(forAccountent);
		formats.add(forCustomer);
		return formats;
	}
	 
}
