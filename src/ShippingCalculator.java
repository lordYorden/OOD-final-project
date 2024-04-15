
public interface ShippingCalculator {
	public Offer calcShippingFee(ShippingOrder order);
	public double calcImportTax(ShippingOrder order);
}
