import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ShippingCompany implements OrderObserver, Serializable{
	protected Contact contact;
	protected ShippingCompanies name;
	protected List<ShippingCalculator> calculators;
	
	public ShippingCompany(ShippingCompanies name, Contact contact) {
		this.name = name;
		this.contact = contact;
		this.calculators = new ArrayList<>();
		initCalculators();
	}
	
	public abstract void initCalculators();

	public void addCalculator(ShippingMethod method, ShippingCalculator calc) {
		calculators.add(calc);
	}
	
	public ShippingCalculator getCalculator(ShippingMethod method) {
		return calculators.get(method.getValue());
	}
	
	@Override
	public Offer getOffer(ShippingOrder order) {
		if(order.getShippingMethod() == ShippingMethod.NoShipping){
			throw new RuntimeException("Error! No shipping for this order!");
		}   
		
		ShippingCalculator calc = getCalculator(order.getShippingMethod());
		return calc.calcShippingFee(order);
	}

	@Override
	public String toString() {
		return String.format("%s\nContact at the company: ", name, contact);
	}
	
	
}
