
public class ProductSoldToWholesalers extends Product {

	public ProductSoldToWholesalers(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		super(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}

}
