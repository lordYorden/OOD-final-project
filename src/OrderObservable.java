
public interface OrderObservable {
	
	public void addObserver(OrderObserver observer);
	public void deleteObserver(OrderObserver observer);
	public Offer getBestOffer(ShippingOrder Order);
}
