import java.io.Serializable;

public class ShippingOrder extends Order implements Serializable{
	
	private Offer bestOffer;
	private ShippingMethod shippingMethod;
	

	public ShippingOrder(String serialNumber, Customer customer, Product product, int amount,
			ShippingMethod shippingMethod) {
		super(serialNumber, customer, product, amount);
		this.bestOffer = null;
		this.shippingMethod = shippingMethod;
	}
	
	
	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setBestOffer(Offer bestOffer) {
		this.bestOffer = bestOffer;
	}


	@Override
	public double getPriceTotal() {
		if(bestOffer == null)
			throw new RuntimeException("Error! No shipping offer was taken!");
		return super.getPriceTotal() + bestOffer.getShippingTotal();
	}
	
	public String getShippingdDetails() {
		if(shippingMethod != ShippingMethod.NoShipping && bestOffer != null) {
			return String.format("\nShipping information:\n%s, %s\n",shippingMethod.getName(), bestOffer);
		}
		throw new RuntimeException("Error! Order has no shipping details yet!");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getShippingdDetails());
		builder.append(super.toString());
		return builder.toString();
	}
	
	
	
	

}
