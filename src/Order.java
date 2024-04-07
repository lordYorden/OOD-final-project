
public class Order {
	private Customer customer; 
	private Product product;
	private int amount;
	ShippingMethod shippingMethod;
	private Offer bestOffer;
	
	public Order(Customer customer, Product product, int amount, ShippingMethod shippingMethod) {
		this.customer = customer;
		this.product = product;
		this.amount = amount;
		this.shippingMethod = shippingMethod;
		this.bestOffer = null;
	}
	
	public double getTotalWeight() {
		return product.getProductWeight() * amount;
	}
	
	public double getTotalSellingPrice() {
		return product.getSellingPrice()*amount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public void setBestOffer(Offer bestOffer) {
		this.bestOffer = bestOffer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		double total = getTotalSellingPrice();
		
		builder.append("Order details: \n");
		
		builder.append(String.format("%s x %d %s\n",
				product,
				amount, 
				Product.toStringPrice(total, product.getCurrency())));
		
		if(shippingMethod != ShippingMethod.NoShipping && bestOffer != null) {
			
			//builder.append("\nShipping details: \n");
			builder.append(shippingMethod);
			builder.append("\n\n");
			builder.append(String.format("%s\n", bestOffer));
			total += bestOffer.getShippingTotal();
		}
		
		builder.append(String.format("\nTotal: %s", Product.toStringPrice(total, product.getCurrency())));
		return builder.toString();
	}
	
	

}
