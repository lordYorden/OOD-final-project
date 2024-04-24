import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.management.RuntimeErrorException;

public class ProductSoldThroughWebsite extends Product implements Serializable, Shippable{
	
	private static final Scanner input = new Scanner(System.in);
	public static final double DOLAR_RATE = 4.0;
	private List<ShippingMethod> shipppingMethods;
	
	public ProductSoldThroughWebsite(String serialNumber, String productName, double costPrice, double sellingPrice, 
			double productWeight, int stock, List<ShippingMethod> shipppingMethods) {
		super(ProductType.SoldThroughWebsite, serialNumber, productName, costPrice, sellingPrice, productWeight,stock);
		this.shipppingMethods = new ArrayList<>(shipppingMethods);
		this.currency = "$";
	}
	
	public ProductSoldThroughWebsite(String serialNumber, String productName, double costPrice, double sellingPrice, 
			double productWeight, int stock) {
		super(ProductType.SoldThroughWebsite, serialNumber, productName, costPrice, sellingPrice, productWeight,stock);
		this.shipppingMethods = new ArrayList<>();
		this.currency = "$";
	}
	
	@Override
	public ShippingMethod getShippingMethod() {
		int i = 1;
		int selection = 0;
		
		do{
			i = 1;
			System.out.println("Select the shimpment type: ");
			for (ShippingMethod shippingMethod : shipppingMethods) {
				System.out.format("%d. %s\n",i, shippingMethod.getName());
				i++;
			}
			
			selection = Integer.parseInt(input.nextLine());
			
			
		}while(selection < 1 || selection > shipppingMethods.size());
		
		return shipppingMethods.get(--selection);
	}

	@Override
	public Set<Invoiceable> getInvoicesFormat() {
		return new HashSet<>();
	}

	@Override
	public double getMargin() {
		return super.getMargin() * DOLAR_RATE;
	}

	@Override
	public void addShippingMethod(ShippingMethod method) {
		if(!shipppingMethods.contains(method)) {
			shipppingMethods.add(method);
			return;
		}
		throw new RuntimeException("Error! The product already supports this shipping method!");
		
	}

	@Override
	public void removeShippingMethod(ShippingMethod method) {
		shipppingMethods.remove(method);
		if(shipppingMethods.isEmpty()) {
			throw new RuntimeException("Error! The product dosen't have any shipping methods available anymore, Please add at least one!");
		}
	}

	@Override
	public boolean hasShippingMethod() {
		return !shipppingMethods.isEmpty();
	}
	
	

}
