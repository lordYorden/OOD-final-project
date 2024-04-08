import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	public static final Scanner input = new Scanner(System.in);
	public static final InventoryFacade inventoryFacade = new InventoryFacade();
	
	public static void main(String[] args) {
		
		
		MenuOption menuOption;
		do {
			menuOption = MenuOption.getMenuOptionFromUser(input);
			switch (menuOption) {
			case AddProduct:
				ProductType type = ProductType.getProductTypeFromUser(input);
				addProduct(type);
				break;
			case Exit:
				System.out.println("Bye Bye!");
				break;
			default:
				break;
			}
			
		}while(menuOption != MenuOption.Exit);
	}

	private static void addProduct(ProductType type) {
		System.out.println("Enter the product's serial number: ");
		String serial = input.nextLine();
		System.out.println("Enter the product's name: ");
		String name = input.nextLine();
		System.out.println("Enter the product's cost price: ");
		double costPrice = Double.parseDouble(input.nextLine());
		System.out.println("Enter the product's selling price: ");
		double sellingPrice = Double.parseDouble(input.nextLine());
		System.out.println("Enter the product's weight: ");
		double weight = Double.parseDouble(input.nextLine());
		System.out.println("How many are in stock? ");
		int stock = Integer.parseInt(input.nextLine());
		
		List<ShippingMethod> methods = null;
		if(type == ProductType.SoldThroughWebsite) {
			methods = createShippingOptions(name);
		}
		
		Product product = ProductFactory.createProduct(type, serial, name, costPrice, sellingPrice, weight, stock, methods);
		System.out.println(product);
	}

	private static List<ShippingMethod> createShippingOptions(String name) {
		boolean keepAdding = true;
		List<ShippingMethod> methods = new ArrayList<>();
		do 
		{
			methods.add(ShippingMethod.getShippingMethodFromUser(input));
			
			System.out.println("Do you want to support more shipping methods for this product?");
			keepAdding = input.nextBoolean();
			
			if(methods.size() == 0 || methods.get(0) == ShippingMethod.NoShipping) {
				System.out.println("Error!Must select at least one!");
				keepAdding = true;
			}
			
		}while(keepAdding);
		
		return methods;
		
	}
}
