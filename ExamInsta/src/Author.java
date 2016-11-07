
public class Author {

	private String name;
	private String email;

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printAuthorInfo() {
		System.out
				.println("Name: " + this.name + "\n" + "Email: " + this.email);
	}
}
