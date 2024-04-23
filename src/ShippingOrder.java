import java.io.Serializable;

public class ShippingOrder extends Order implements Serializable{
	
	private Offer bestOffer;
	private String destCountery;
	private ShippingMethod shippingMethod;
	

	public ShippingOrder(String serialNumber, Customer customer, Product product, int amount,
			ShippingMethod shippingMethod, String destCountery) {
		super(serialNumber, customer, product, amount);
		this.bestOffer = null;
		this.shippingMethod = shippingMethod;
		this.destCountery = destCountery;
	}

	public String getDestCountery() {
		return destCountery;
	}

	public void setDestCountery(String destCountery) {
		this.destCountery = destCountery;
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
