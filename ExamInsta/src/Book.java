public class Book {

	private String title;
	private String author;
	private double price;
	private int quantity_in_stock;

	Author myAuthor;

	public Book(String title, String author_name, String author_email,
			double price, int quantity_in_stock) {
		myAuthor = new Author(author_name, author_email);

		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity_in_stock = quantity_in_stock;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return myAuthor.getEmail();
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity_in_stock;
	}

	public void setTitle(String Title) {
		this.title = title;
	}

	public void setAuthor(String name, String email) {
		myAuthor.setName(name);
		myAuthor.setEmail(email);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public void printBookInfo() {
		// wouldnt it be nice to know what the author name and email is
		// so we also have

		// what method do we call here myAuthor.?

		// this prints the authors info
		myAuthor.printAuthorInfo();
		// this prints the books info
		System.out.println("Title: " + this.title + "\n");
		System.out.println("Price: " + this.price + "\n");
		System.out.println("Quantity in Stock: " + this.quantity_in_stock
				+ "\n");
	}
}