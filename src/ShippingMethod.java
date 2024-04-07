
public enum ShippingMethod {
	
	StandardShipping(0, "Standard Shipping"), ExpressShipping(1, "Express Shipping"), NoShipping(-1, "No Shipping");
	
	private int value;
	private String name;

	private ShippingMethod(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
		return name;
	}

	@Override
    public String toString() {
        return name;
    }

    public static ShippingMethod getShippingMethod(int value) {
        for(ShippingMethod v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! no such method, Try again!");
    }
}
