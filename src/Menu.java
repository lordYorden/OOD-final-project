import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	public static final Scanner input = new Scanner(System.in);
	public static final StoreFacade facade = StoreFacade.getInstance();
	public static final String FILE_NAME = "Inventory.db";
	public static final InventoryBackupable BINARY_BACKUPABLE = new BinaryFileBackup();
	
	public static void main(String[] args) throws IOException {
		MenuOption menuOption = null;
		ProductType type = null;
		do {
			try {
				menuOption = MenuOption.getMenuOptionFromUser(input);
				switch (menuOption) {
				case AddProduct:
					type = ProductType.getProductTypeFromUser(input);
					addProduct(type);
					break;
				case RemoveProduct:
					System.out.println("Enter the product's serial number: ");
					facade.removeProduct(input.nextLine());
					break;
				case UpdateProductStock:
					System.out.println("Enter the product's serial number: ");
					String serial = input.nextLine();
					System.out.println("How many are in stock now? ");
					int newStock = Integer.parseInt(input.nextLine());				
					facade.updateProductStock(serial, newStock);
					break;
				case AddOrder:
					type = ProductType.getProductTypeFromUser(input);
					System.out.println(facade.getProductsOfType(type));
					System.out.println("Enter the product's serial number: ");
					String serialNum = input.nextLine();
					System.out.println("How many would you like to order? ");
					int amount = Integer.parseInt(input.nextLine());
					System.out.println("Enter the order's serial number: ");
					String orderSerialNum = input.nextLine();
					System.out.println("To which country do you want to send it?");
					String destCountry = input.nextLine();
					facade.addOrder(serialNum, amount, orderSerialNum, destCountry);
					break;
				case UndoOrder:
					facade.undoOrder();
					System.out.println("Order was successfully canceled!");
					break;
				case ShowProductInfo:
					System.out.println("Enter the product's serial number: ");
					String serialNumber1 = input.nextLine();
					System.out.println(facade.getProductInfo(serialNumber1));
					break;
				case ShowInventory:
					System.out.println(facade);
					break;
				case PrintOrdersOfProduct:
					System.out.println("Enter the product's serial number: ");
					String serialNumber = input.nextLine();
					System.out.println(facade.getOrderHistoryOfProduct(serialNumber));
					break;
				case Backup:
					facade.save(FILE_NAME, BINARY_BACKUPABLE);
					System.out.println("Backup was successful!");
					break;
				case RestoreBackup:
					facade.load(FILE_NAME, BINARY_BACKUPABLE);
					System.out.println("Backup Restored!");
					break;
				default:
					throw new RuntimeException("Not implemented yet!");
				case Exit:
					System.out.println("Bye Bye!");
					break;
				}
			}catch(RuntimeException e) {
				System.err.format("%s\nPress any key to continue....\n",
						e.getMessage());
				System.in.read();
			}finally {
				System.out.format("%s was successful!\n", menuOption.getDescription());
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
		System.out.println("Enter the product's weight (in kg): ");
		double weight = Double.parseDouble(input.nextLine());
		System.out.println("How many are in stock? ");
		int stock = Integer.parseInt(input.nextLine());
		
		List<ShippingMethod> methods = null;
		if(type == ProductType.SoldThroughWebsite) {
			methods = createShippingOptions(name);
		}
		
		Product product = ProductFactory.createProduct(type, serial, name, costPrice, sellingPrice, weight, stock, methods);
		facade.addProduct(type, product);
	}

	private static List<ShippingMethod> createShippingOptions(String name) {
		boolean keepAdding = false;
		List<ShippingMethod> methods = new ArrayList<>();
		do 
		{
			keepAdding = false;
			methods.add(ShippingMethod.getShippingMethodFromUser(input));
			
			System.out.println("Do you want to support more shipping methods for this product?");
			keepAdding = input.nextBoolean();
			
			if (methods.size() == 0 /* || methods.get(0) == ShippingMethod.NoShipping */) {
				System.out.println("Error!Must select at least one!");
				keepAdding = true;
			}
			
		}while(keepAdding);
		
		return methods;
		
	}
}
