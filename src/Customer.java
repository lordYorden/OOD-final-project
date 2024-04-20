import java.io.Serializable;

public class Customer implements Serializable{
	String name;
	String mobile;
	
	public Customer(String name, String moblieNumber) {
		this.name = name;
		this.mobile = moblieNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoblieNumber() {
		return mobile;
	}

	public void setMoblieNumber(String moblieNumber) {
		this.mobile = moblieNumber;
	}
	
}
