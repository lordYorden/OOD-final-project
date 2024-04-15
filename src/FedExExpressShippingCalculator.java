
public class FedExExpressShippingCalculator extends ExpressShippingCalculator{

	private final double FEE_PER_KG = 5;
	
	@Override
	public Offer calcShippingFee(ShippingOrder order) {
		Offer offer = new Offer(ShippingCompanies.FedEx);
		offer.setShippingPrice(FEE_PER_KG*order.getTotalWeight());
		offer.setImportTax(calcImportTax(order));
		return offer;
	}

}
