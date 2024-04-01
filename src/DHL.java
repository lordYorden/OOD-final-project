
public class DHL extends ShippingCompany{
	
	public DHL(Contact contact) {
		super(ShippingCompanies.DHL, contact);
	}

	@Override
	public void initCalculators() {
		addCalculator(ShippingMethod.StandardShipping, new DHLStandardShippingCalculator());
		addCalculator(ShippingMethod.ExpressShipping, new DHLExpressShippingCalculator());
	}
	
	
}
