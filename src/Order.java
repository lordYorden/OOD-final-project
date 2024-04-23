import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable, Profitable{
	private Customer customer; 
	private Product product;
	private int amount;
	private String serialNumber;
	
	public Order(String serialNumber, Customer customer, Product product, int amount) {
		this.customer = customer;
		this.product = product;
		this.amount = amount;
		this.serialNumber =	serialNumber;
	}
	
	public double getTotalWeight() {
		return product.getWeight() * amount;
	}
	
	public double getProfit() {
		return product.getMargin() * amount;
	}
	
	public double getTotalSellingPrice() {
		return product.getSellingPrice()*amount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getInvoice(Invoiceable format) {
		return format.getInvoice(this);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
		
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public double getPriceTotal() {
		return getTotalSellingPrice();
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		return Objects.equals(serialNumber, other.serialNumber);
	}
	
	public String getCurrency(){
		return product.getCurrency();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("\nDetails for order number %s: \n", serialNumber));	
		builder.append(String.format("Customer: %s\n", customer));   
		
		builder.append("Product Description:\n");
		builder.append(String.format("%s x %d %s\n",
				product.getProductName(),
				amount, 
				Product.toStringPrice(getTotalSellingPrice(), product.getCurrency())));
		
		builder.append(String.format("\nTotal: %s\n", Product.toStringPrice(getPriceTotal(), product.getCurrency())));
		return builder.toString();
	}
}
