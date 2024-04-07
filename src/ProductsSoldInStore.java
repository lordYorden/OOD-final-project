public class ProductsSoldInStore extends Product {

	public ProductsSoldInStore(String productName, int costPrice, int sellingPrice, double productWeight) {
		super(productName, costPrice, sellingPrice, productWeight);
	}

	@Override
	public ShippingMethod getShippingMethod() {
		return ShippingMethod.NoShipping;
	}
	 
}
