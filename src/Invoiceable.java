import java.io.Serializable;

public interface Invoiceable extends Serializable {
	public String getInvoice(Order order);
}
