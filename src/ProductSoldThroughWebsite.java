import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductSoldThroughWebsite extends Product {
	
	private static final Scanner input = new Scanner(System.in);
	private String destCountery;
	private List<ShippingMethod> shippping;
	
	public ProductSoldThroughWebsite(String serialNumber, String productName, double costPrice, double sellingPrice, 
			double productWeight, int stock, String destCountery, List<ShippingMethod> shippping) {
		super(ProductType.SoldThroughWebsite, serialNumber, productName, costPrice, sellingPrice, productWeight,stock);
		this.shippping = new ArrayList<>(shippping);
		this.destCountery = destCountery;
		this.currency = "$";
	}

	public String getDestCountery() {
		return destCountery;
	}

	public void setDestCountery(String destCountery) {
		this.destCountery = destCountery;
	}
	
	@Override
	public ShippingMethod getShippingMethod() {
		int i = 1;
		int selection = 0;
		
		do{
			i = 1;
			System.out.println("Select the shimpment type: ");
			for (ShippingMethod shippingMethod : shippping) {
				System.out.format("%d. %s\n",i, shippingMethod.getName());
				i++;
			}
			
			selection = Integer.parseInt(input.nextLine());
			
			
		}while(selection < 1 || selection > shippping.size());
		
		return shippping.get(--selection);
	}

	@Override
	public String getOrderInvoices(Order order) {
		throw new RuntimeException("Error! Can't output invoices for a Product intented for shipping!");
	}

}
