import java.util.Scanner;

public enum ProductType {

	SoldInStore(1, "Products that are sold in Store"),
	SoldThroughWebsite(2, "Products that are sold Through the Website"),
	SoldToWholesalers(3, "Products that is sold directly to Wholesalers");
	
	int value;
	String description;
	
	private ProductType(int value, String describtion) {
		this.value = value;
		description = describtion;
	}
	
	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	@Override
    public String toString() {
        return String.format("%d. %s", value, description);
    }

    public static ProductType getProductType(int value) {
        for(ProductType v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! no such Product Type, Try again!");
    }
    
    public static ProductType getProductTypeFromUser(Scanner input) {
		while(true) {
			System.out.println("Please select the type of product:");
			for (ProductType m : ProductType.values()) {
				System.out.println(m);
			}
			
			int select = 0;
			ProductType productType = null;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			
			try {
				productType = ProductType.getProductType(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return productType;
		}
	}
	
}
