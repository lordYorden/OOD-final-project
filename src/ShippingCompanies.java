
public enum ShippingCompanies {
	
	DHL("DHL"), FedEx("FedEx");
	
	private String name;

	ShippingCompanies(String name) {
		this.name = name;
	}
//
//    public String getName() {
//        return name;
//    }

    @Override
    public String toString() {
        return name;
    }

    public static ShippingCompanies getEnumValue(String name) {
        for(ShippingCompanies v : values())
            if(v.name == name) return v;
        throw new IllegalArgumentException("Error! no such method, Try again!");
    }
}
