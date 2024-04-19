
public class ProductSoldToWholesalers extends Product {
	
	public static final FormatForAccountent forAccountent = new FormatForAccountent();

	public ProductSoldToWholesalers(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		super(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		this.productType = ProductType.SoldToWholesalers;
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}
}
