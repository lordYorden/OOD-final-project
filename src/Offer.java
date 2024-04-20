import java.io.Serializable;

public class Offer implements Comparable<Offer>, Serializable{	
	private double baseShippingFee;
	private double importTax;
	private boolean includingImportTax;
	private ShippingCompanies shippingCompany;
	public String SHIPPING_CURRENCY = "$";

	public Offer(ShippingCompanies shippingCompany) {
		this.shippingCompany = shippingCompany;
		this.importTax = 0f;
		includingImportTax = false;
		this.baseShippingFee = 0f;
	}
	
	public void setShippingPrice(double shippingPrice) {
		this.baseShippingFee = shippingPrice;
	}
	public void setImportTax(double importTax) {
		includingImportTax = importTax >= 0;
		
		if(includingImportTax)
			this.importTax = importTax;
	}
	
	public double getShippingTotal() {
		if(includingImportTax)
			return baseShippingFee+importTax;
		return baseShippingFee;
	}

	@Override
	public int compareTo(Offer o) {
		return (int) Math.ceil(this.getShippingTotal() - o.getShippingTotal());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Shipped via %s\n", shippingCompany));
		builder.append(String.format("Shipping fee: %.2f$\n", baseShippingFee));
		builder.append("Import tax: ");
		builder.append((includingImportTax) ? Product.toStringPrice(importTax, SHIPPING_CURRENCY) : "not included!");
		return builder.toString();
	}
	
	
	
	
}
