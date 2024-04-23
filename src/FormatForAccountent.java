
public class FormatForAccountent implements Invoiceable {

	@Override
	public String getInvoice(Order order) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nFor Accountent: \n");
		buffer.append(order);
		buffer.append(String.format("\nProfit Total for Order: %s\n",
				Product.toStringPrice(order.getProfit(), order.getCurrency())));
		return buffer.toString();
	}

}
