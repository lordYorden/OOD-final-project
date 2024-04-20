import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProductSoldToWholesalers extends Product implements Serializable {
	
	private Invoiceable forAccountent;

	public ProductSoldToWholesalers(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock, Invoiceable forAccountent) {
		super(ProductType.SoldToWholesalers, serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		this.forAccountent = forAccountent;
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}

	@Override
	public Set<Invoiceable> getInvoicesFormat() {
		Set<Invoiceable> formats = new HashSet<>();
		formats.add(forAccountent);
		return formats;
	}
}
