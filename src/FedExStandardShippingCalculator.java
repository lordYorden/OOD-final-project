
public class FedExStandardShippingCalculator extends StandardShippingCalculator{

	private final double FEE_PER_KG = 5;
	
	@Override
	public Offer calcShippingFee(ShippingOrder order) {
		Offer offer = new Offer(ShippingCompanies.FedEx);
		offer.setShippingPrice(order.getTotalWeight()*FEE_PER_KG);
		offer.setImportTax(calcImportTax(order));
		return offer;
	}
}
