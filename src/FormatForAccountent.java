
public class FormatForAccountent implements Invoiceable {

	@Override
	public String getInvoice(Order order) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("For Accountent: \n");
		buffer.append(order);
		buffer.append(String.format("\nProfit Total: %s",
				Product.toStringPrice(order.getTotalOrderProfit(), order.getCurrency())));
		return buffer.toString();
	}

}
