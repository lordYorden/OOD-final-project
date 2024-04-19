public class ProductsSoldInStore extends Product {
	
	public static final FormatForCustomer forCustomer = new FormatForCustomer();
	public static final FormatForAccountent forAccountent = new FormatForAccountent();

	public ProductsSoldInStore(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		super(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		this.productType = ProductType.SoldInStore;
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}
	 
}
