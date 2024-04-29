import java.io.Serializable;

public class Customer implements Serializable{
	String customerName;
	String mobile;
	
	public Customer(String name, String moblieNumber) {
		this.customerName = name;
		this.mobile = moblieNumber;
	}

	public String getName() {
		return customerName;
	}

	public void setName(String name) {
		this.customerName = name;
	}

	public String getMoblieNumber() {
		return mobile;
	}

	public void setMoblieNumber(String moblieNumber) {
		this.mobile = moblieNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(customerName);
		builder.append(", mobile: ");
		builder.append(mobile);
		return builder.toString();
	}
	
	
	
}
