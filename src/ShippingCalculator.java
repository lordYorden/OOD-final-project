
public interface ShippingCalculator {
	public Offer calcShippingFee(Order order);
	public double calcImportTax(Order order);
}
