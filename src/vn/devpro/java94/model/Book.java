package vn.devpro.java94.model;

public class Book {
	private int id;
	private String code;
	private String name;
	private Category category;
	private Author author;
	private int numberOfPages;
	private int quantity;

	public Book() {
	}

	public Book(int id, String code, String name, Category category, Author author, int numberOfPages, int quantity) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.category = category;
		this.author = author;
		this.numberOfPages = numberOfPages;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", code=" + code + ", name=" + name + ", category="
				+ (category != null ? category.getName() : "") + ", author="
				+ (author != null ? author.getFullName() : "") + ", numberOfPages=" + numberOfPages + ", quantity="
				+ quantity + "]";
	}
}