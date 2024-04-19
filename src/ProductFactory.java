import java.util.List;

public class ProductFactory {
	public static Product createProduct(ProductType type, String serialNumber, String productName, double costPrice,
			double sellingPrice, double productWeight, int stock , List<ShippingMethod> shipping) {
		
		StoreFacade facade = StoreFacade.getInstance();
		
		switch(type) {
		case SoldInStore:
			return new ProductsSoldInStore(serialNumber,productName, costPrice, sellingPrice, productWeight, stock,
					facade.getFormatForCustomer(), facade.getFormatForAccountent());
		case SoldThroughWebsite:
			return new ProductSoldThroughWebsite(serialNumber,productName, costPrice, sellingPrice, productWeight, stock, productName, shipping);
		case SoldToWholesalers:
			return new ProductSoldToWholesalers(serialNumber,productName, costPrice, sellingPrice, productWeight, stock,
					facade.getFormatForAccountent());
		default:
			throw new IllegalArgumentException("Error! No such type!");
		}
	}
}
