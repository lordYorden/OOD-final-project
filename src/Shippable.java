
public interface Shippable {
	public void addShippingMethod(ShippingMethod method);
	public void removeShippingMethod(ShippingMethod method);
	public boolean hasShippingMethod();
}
