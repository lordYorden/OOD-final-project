import java.io.Serializable;

public class Contact implements Serializable {
	String name;
	String whatsappNumber;
	
	public Contact(String name, String whatsappNumber) {
		this.name = name;
		this.whatsappNumber = whatsappNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWhatsappNumber() {
		return whatsappNumber;
	}

	public void setWhatsappNumber(String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", name, whatsappNumber);
	}
	
	
}
