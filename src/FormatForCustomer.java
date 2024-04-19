
public class FormatForCustomer implements Invoiceable{

	public static final double TAX_RATE = 0.17;
	
	@Override
	public String getInvoice(Order order) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nFor Customer: \n");
		buffer.append(order);
		buffer.append(String.format("\nTax Total for Order: %s\n",
				Product.toStringPrice(calcSalesTax(order.getPriceTotal()), order.getCurrency())));
		return buffer.toString();
	}
	
	public static double calcSalesTax(double price) {
		return TAX_RATE*price;
	}

}
