
public class DHLExpressShippingCalculator extends ExpressShippingCalculator{
	
	private final double BASE_FEE = 100f;
	
	@Override
	public Offer calcShippingFee(ShippingOrder order) {
		Offer offer = new Offer(ShippingCompanies.DHL);
		offer.setShippingPrice(BASE_FEE);
		offer.setImportTax(calcImportTax(order));
		return offer;
	}
}
