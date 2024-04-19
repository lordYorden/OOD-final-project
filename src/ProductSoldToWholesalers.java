
public class ProductSoldToWholesalers extends Product {
	
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
	public String getOrderInvoices(Order order) {
		return forAccountent.getInvoice(order);
	}
}
