import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProductSoldThroughWebsite extends Product implements Serializable{
	
	private static final Scanner input = new Scanner(System.in);
	public static final double DOLAR_RATE = 4.0;
	private List<ShippingMethod> shippping;
	
	public ProductSoldThroughWebsite(String serialNumber, String productName, double costPrice, double sellingPrice, 
			double productWeight, int stock, List<ShippingMethod> shippping) {
		super(ProductType.SoldThroughWebsite, serialNumber, productName, costPrice, sellingPrice, productWeight,stock);
		this.shippping = new ArrayList<>(shippping);
		this.currency = "$";
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
	public Set<Invoiceable> getInvoicesFormat() {
		return new HashSet<>();
	}

	@Override
	public double getMargin() {
		return super.getMargin() * DOLAR_RATE;
	}
	
	

}
