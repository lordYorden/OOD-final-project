
public class FedEx extends ShippingCompany{

	public FedEx(Contact contact) {
		super(ShippingCompanies.FedEx, contact);
	}
	
	@Override
	public void initCalculators() {
		addCalculator(ShippingMethod.StandardShipping, new FedExStandardShippingCalculator());
		addCalculator(ShippingMethod.ExpressShipping, new FedExExpressShippingCalculator());
	}
	
	@Override
	public String toString() {
		return String.format("FedEx\n%s", super.toString());
	}

}
