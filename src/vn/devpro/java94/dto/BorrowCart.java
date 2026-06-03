package vn.devpro.java94.dto;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.java94.model.BorrowItem;
import vn.devpro.java94.model.Reader;

public class BorrowCart {
	private Reader reader;
	private List<BorrowItem> borrowItems;

	public BorrowCart() {
		this.borrowItems = new ArrayList<BorrowItem>();
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public List<BorrowItem> getBorrowItems() {
		return borrowItems;
	}

	public void setBorrowItems(List<BorrowItem> borrowItems) {
		this.borrowItems = borrowItems;
	}

	public BorrowItem getItemByBookCode(String bookCode) {
		for (BorrowItem item : borrowItems) {
			if (item.getBook() != null && item.getBook().getCode().equalsIgnoreCase(bookCode)) {
				return item;
			}
		}
		return null;
	}

	public int getTotalBookTypes() {
		return borrowItems.size();
	}

	public int getTotalQuantity() {
		int total = 0;
		for (BorrowItem item : borrowItems) {
			total += item.getQuantity();
		}
		return total;
	}

	public void clear() {
		this.reader = null;
		this.borrowItems.clear();
	}
}