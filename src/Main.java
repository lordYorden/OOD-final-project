import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Product p = new Product("Joyride", 5, 20, 0.25);
		Order o = new Order(new Customer("Yarden", "0547731355"), p, 4, ShippingMethod.ExpressShipping);
		ShippingCompany shippingCompany1 = new FedEx(new Contact("Shay", "0546092715"));
		ShippingCompany shippingCompany2 = new DHL(new Contact("itay", "0549411471"));
		
		List<OrderObserver> observers = new ArrayList<>();
		observers.add(shippingCompany1);
		observers.add(shippingCompany2);
		Offer minOffer = null;
		//gets the best offer
		for (OrderObserver observer : observers) {
			Offer curr = observer.getOffer(o); 
			if(minOffer == null || minOffer.compareTo(curr) > 0)
				minOffer = curr;
		}
		
		o.setBestOffer(minOffer);
		System.out.println(o);
	}
}
