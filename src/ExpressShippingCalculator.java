
public abstract class ExpressShippingCalculator implements ShippingCalculator{
	
	public static final int GLOBAL_IMPORT_TAX = 20;

	@Override
	public abstract Offer calcShippingFee(ShippingOrder order);

	@Override
	public double calcImportTax(ShippingOrder order) {
		return GLOBAL_IMPORT_TAX;
	}

}
