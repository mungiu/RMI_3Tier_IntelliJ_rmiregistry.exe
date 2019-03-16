package SharedModel;

public class Customer {
	private int cprNr;
	private String fullName;
	private String address;

	public Customer(int cprNr, String fullName, String address){
		this.cprNr = cprNr;
		this.fullName = fullName;
		this.address=address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
}
