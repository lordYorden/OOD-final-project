public class ProductsSoldInStore extends Product {

	public ProductsSoldInStore(String serialNumber, String productName, double costPrice, double sellingPrice, double productWeight, int stock) {
		super(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}
	 
}
