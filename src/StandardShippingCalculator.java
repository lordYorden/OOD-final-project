
public abstract class StandardShippingCalculator implements ShippingCalculator {

	@Override
	public abstract Offer calcShippingFee(Order order);

	@Override
	public double calcImportTax(Order order) {
		return -1f;
	}

}
