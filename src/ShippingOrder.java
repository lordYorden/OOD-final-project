
public class ShippingOrder extends Order{
	
	private Offer bestOffer;

	public ShippingOrder(String serialNumber, Customer customer, Product product, int amount,
			ShippingMethod shippingMethod) {
		super(serialNumber, customer, product, amount, shippingMethod);
		this.bestOffer = null;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(shippingMethod != ShippingMethod.NoShipping && bestOffer != null) {
			builder.append(shippingMethod);
			builder.append("\n\n");
			builder.append(String.format("%s\n", bestOffer));
		}
		
		builder.append(super.toString());
		return builder.toString();
	}
	
	
	
	

}
