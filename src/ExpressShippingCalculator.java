
public abstract class ExpressShippingCalculator implements ShippingCalculator{
	
	public static final int GLOBAL_IMPORT_TAX = 20;

	@Override
	public abstract Offer calcShippingFee(Order order);

	@Override
	public double calcImportTax(Order order) {
		return GLOBAL_IMPORT_TAX;
	}

}
