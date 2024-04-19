import java.util.ArrayList;
import java.util.List;

public class Website implements OrderObservable{

	private List<OrderObserver> observers;
	
	public Website() {
		this.observers = new ArrayList<>();
	}

	//notify all method
	@Override
	public Offer getBestOffer(ShippingOrder Order) { 
		Offer minOffer = null;
		//gets the best offer
		for (OrderObserver observer : observers) {
			Offer curr = observer.getOffer(Order); 
			if(minOffer == null || minOffer.compareTo(curr) > 0)
				minOffer = curr;
		}
		return minOffer;  
		//o.setBestOffer(minOffer);
	}

	public void addNewOrder(ShippingOrder order) {
		Offer offer = getBestOffer(order);
		if(offer == null){
			throw new RuntimeException("Error! No shipping company was added!");
		}

		order.setBestOffer(offer);
	}

	@Override
	public void deleteObserver(OrderObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void addObserver(OrderObserver observer) {
		observers.add(observer);
	}
}
