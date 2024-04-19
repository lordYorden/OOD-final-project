
public class FormatForCustomer implements Invoiceable{

	public static final double TAX_RATE = 0.17;
	
	@Override
	public String getInvoice(Order order) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(order);
		buffer.append(String.format("\nTax Total: %s",
				Product.toStringPrice(calcSalesTax(order.getPriceTotal()), order.getCurrency())));
		return buffer.toString();
	}
	
	public static double calcSalesTax(double price) {
		return TAX_RATE*price;
	}

}
