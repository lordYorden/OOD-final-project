
public abstract class StandardShippingCalculator implements ShippingCalculator {

	@Override
	public abstract Offer calcShippingFee(ShippingOrder order);

	@Override
	public double calcImportTax(ShippingOrder order) {
		return -1f;
	}

}
