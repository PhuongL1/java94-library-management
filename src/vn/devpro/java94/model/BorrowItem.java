package vn.devpro.java94.model;

public class BorrowItem {
	private int id;
	private Book book;
	private int quantity;

	public BorrowItem() {
	}

	public BorrowItem(int id, Book book, int quantity) {
		this.id = id;
		this.book = book;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BorrowItem [id=" + id + ", book=" + (book != null ? book.getName() : "") + ", quantity=" + quantity
				+ "]";
	}
}
