package vn.devpro.java94.model;

import java.util.ArrayList;
import java.util.List;

public class BorrowOrder {
	private int id;
	private String code;
	private Reader reader;
	private String borrowDate;
	private List<BorrowItem> borrowItems;

	public BorrowOrder() {
		this.borrowItems = new ArrayList<BorrowItem>();
	}

	public BorrowOrder(int id, String code, Reader reader, String borrowDate, List<BorrowItem> borrowItems) {
		this.id = id;
		this.code = code;
		this.reader = reader;
		this.borrowDate = borrowDate;
		this.borrowItems = borrowItems;
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

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public List<BorrowItem> getBorrowItems() {
		return borrowItems;
	}

	public void setBorrowItems(List<BorrowItem> borrowItems) {
		this.borrowItems = borrowItems;
	}

	@Override
	public String toString() {
		return "BorrowOrder [id=" + id + ", code=" + code + ", reader="
				+ (reader != null ? reader.getFullName() : "") + ", borrowDate=" + borrowDate + ", borrowItems="
				+ borrowItems + "]";
	}
}