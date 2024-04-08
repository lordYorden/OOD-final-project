import java.util.Scanner;

public enum ShippingMethod {
	
	StandardShipping(0, "Standard Shipping"), ExpressShipping(1, "Express Shipping"),
	NoShipping(-1, "Exit"); //No shipping
	
	private int value;
	private String describtion;

	private ShippingMethod(int value, String name) {
        this.value = value;
        this.describtion = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
		return describtion;
	}

	@Override
    public String toString() {
		return String.format("%d. %s", value, describtion);
    }

    public static ShippingMethod getShippingMethod(int value) {
        for(ShippingMethod v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! no such method, Try again!");
    }
    
    public static ShippingMethod getShippingMethodFromUser(Scanner input) {
		while(true) {
			System.out.println("Please select the method of shipping:");
			
			for (ShippingMethod s : ShippingMethod.values()) {
				System.out.println(s);
			}
			
			int select = 0;
			ShippingMethod shippingMethod = null;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			
			try {
				shippingMethod = ShippingMethod.getShippingMethod(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return shippingMethod;
		}
	}
}
