
public class DHLStandardShippingCalculator extends StandardShippingCalculator{

	private final double PRECENT_FEE = 0.1;
	private final double MAX_BASE_FEE = 100f;
	
	@Override
	public Offer calcShippingFee(Order order) {
		Offer offer = new Offer(ShippingCompanies.DHL);
		offer.setShippingPrice(Math.min(PRECENT_FEE*order.getTotalSellingPrice(), MAX_BASE_FEE));
		offer.setImportTax(calcImportTax(order));
		return offer;
	}
}
