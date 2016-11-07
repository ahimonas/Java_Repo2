public class Driver {

	private String licenseId;
	private String fullName;
	private Address myAddress;

	Driver() {

	}

	Driver(String licenseId, String fullName, Address A) {

		myAddress = A;
		this.licenseId = licenseId;
		this.fullName = fullName;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getFullName() {
		return fullName;
	}

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

}