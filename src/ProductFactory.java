import java.util.List;

public class ProductFactory {
	public static Product createProduct(ProductType type, String serialNumber, String productName, double costPrice,
			double sellingPrice, double productWeight, int stock , List<ShippingMethod> shipping) {
		
		switch(type) {
		case SoldInStore:
			return new ProductsSoldInStore(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		case SoldThroughWebsite:
			return new ProductSoldThroughWebsite(serialNumber,productName, costPrice, sellingPrice, productWeight, stock, productName, shipping);
		case SoldToWholesalers:
			return new ProductSoldToWholesalers(serialNumber,productName, costPrice, sellingPrice, productWeight, stock);
		default:
			throw new IllegalArgumentException("Error! No such type!");
		}
	}
}
