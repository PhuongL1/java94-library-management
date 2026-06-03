package vn.devpro.java94.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.BorrowItem;
import vn.devpro.java94.dto.*;
import vn.devpro.java94.model.BorrowOrder;
import vn.devpro.java94.model.Reader;

public class BorrowService {
	private ReaderService readerService = new ReaderService();
	private BookService bookService = new BookService();

	public String chooseReader(BorrowCart borrowCart, String readerCode) {
		Reader reader = readerService.getByCode(readerCode);
		if (reader == null) {
			return "Khong tim thay doc gia!";
		}

		if (borrowCart.getReader() != null && !borrowCart.getReader().getCode().equalsIgnoreCase(readerCode)
				&& borrowCart.getBorrowItems().size() > 0) {
			return "Phieu tam dang co du lieu. Hay tao lai phieu tam truoc khi doi doc gia!";
		}

		borrowCart.setReader(reader);
		return "Da chon doc gia: " + reader.getFullName();
	}

	public String addBookToCart(BorrowCart borrowCart, String bookCode, int quantity) {
		if (borrowCart.getReader() == null) {
			return "Ban phai chon doc gia truoc!";
		}

		if (quantity != 1) {
			return "Moi sach chi duoc muon 01 cuon!";
		}

		Book book = bookService.getByCode(bookCode);
		if (book == null) {
			return "Khong tim thay sach!";
		}

		BorrowItem oldItem = borrowCart.getItemByBookCode(bookCode);
		if (oldItem != null) {
			return "Sach nay da co trong phieu muon tam. Moi sach chi duoc muon 01 cuon!";
		}

		if (borrowCart.getTotalBookTypes() >= 3) {
			return "Moi phieu chi duoc muon toi da 3 sach!";
		}

		if (hasBorrowedThisBook(borrowCart.getReader(), book)) {
			return "Doc gia da muon sach nay truoc do va chua tra!";
		}

		int currentBorrowed = getCurrentBorrowedQuantity(borrowCart.getReader());
		int maxAllowed = getMaxAllowedQuantity(borrowCart.getReader());

		if (currentBorrowed + borrowCart.getTotalQuantity() + quantity > maxAllowed) {
			return "Vuot qua gioi han cho phep! Doc gia nay chi duoc muon toi da " + maxAllowed + " cuon.";
		}

		if (book.getQuantity() < 1) {
			return "So luong sach trong kho khong du!";
		}

		BorrowItem newItem = new BorrowItem(getNextBorrowItemId(borrowCart), book, 1);
		borrowCart.getBorrowItems().add(newItem);

		return "Them sach vao phieu tam thanh cong!";
	}

	private boolean hasBorrowedThisBook(Reader reader, Book book) {
		for (BorrowOrder order : StoreDb.getBorrowOrders()) {
			if (order.getReader() != null && order.getReader().getId() == reader.getId()) {
				for (BorrowItem item : order.getBorrowItems()) {
					if (item.getBook() != null && item.getBook().getId() == book.getId()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String saveBorrowOrder(BorrowCart borrowCart) {
		String validateMessage = validateBorrowCart(borrowCart);
		if (validateMessage != null) {
			return validateMessage;
		}

		List<BorrowItem> orderItems = new ArrayList<BorrowItem>();
		for (BorrowItem item : borrowCart.getBorrowItems()) {
			BorrowItem newItem = new BorrowItem(item.getId(), item.getBook(), item.getQuantity());
			orderItems.add(newItem);
		}

		BorrowOrder borrowOrder = new BorrowOrder(getNextBorrowOrderId(), getNextBorrowOrderCode(),
				borrowCart.getReader(), LocalDate.now().toString(), orderItems);

		for (BorrowItem item : borrowCart.getBorrowItems()) {
			Book book = item.getBook();
			book.setQuantity(book.getQuantity() - item.getQuantity());
		}

		StoreDb.getBorrowOrders().add(borrowOrder);
		borrowCart.clear();

		return "Tao phieu muon thanh cong! Ma phieu: " + borrowOrder.getCode();
	}

	public int getCurrentBorrowedQuantity(Reader reader) {
		if (reader == null) {
			return 0;
		}

		int total = 0;
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder borrowOrder : borrowOrders) {
			if (borrowOrder.getReader() != null
					&& borrowOrder.getReader().getCode().equalsIgnoreCase(reader.getCode())) {
				for (BorrowItem item : borrowOrder.getBorrowItems()) {
					total += item.getQuantity();
				}
			}
		}

		return total;
	}

	private int getMaxAllowedQuantity(Reader reader) {
		if (reader.isStudent()) {
			return 8;
		}
		return 5;
	}

	private String validateBorrowCart(BorrowCart borrowCart) {
		if (borrowCart.getReader() == null) {
			return "Ban chua chon doc gia!";
		}

		if (borrowCart.getBorrowItems() == null || borrowCart.getBorrowItems().size() == 0) {
			return "Phieu tam chua co sach!";
		}

		if (borrowCart.getTotalBookTypes() > 3) {
			return "Moi phieu chi duoc muon toi da 3 dau sach!";
		}

		int currentBorrowed = getCurrentBorrowedQuantity(borrowCart.getReader());
		int maxAllowed = getMaxAllowedQuantity(borrowCart.getReader());

		if (currentBorrowed + borrowCart.getTotalQuantity() > maxAllowed) {
			return "Vuot qua gioi han cho phep! Doc gia nay chi duoc muon toi da " + maxAllowed + " cuon.";
		}

		for (BorrowItem item : borrowCart.getBorrowItems()) {
			if (item.getQuantity() <= 0) {
				return "So luong sach muon phai > 0!";
			}

			if (item.getBook() == null) {
				return "Du lieu sach khong hop le!";
			}

			if (item.getQuantity() > item.getBook().getQuantity()) {
				return "So luong sach trong kho khong du!";
			}
		}

		return null;
	}

	private int getNextBorrowOrderId() {
		int maxId = 0;
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder borrowOrder : borrowOrders) {
			if (borrowOrder.getId() > maxId) {
				maxId = borrowOrder.getId();
			}
		}

		return maxId + 1;
	}

	private String getNextBorrowOrderCode() {
		int nextId = getNextBorrowOrderId();
		return String.format("BOR%03d", nextId);
	}

	private int getNextBorrowItemId(BorrowCart borrowCart) {
		int maxId = 0;

		for (BorrowItem item : borrowCart.getBorrowItems()) {
			if (item.getId() > maxId) {
				maxId = item.getId();
			}
		}

		return maxId + 1;
	}
}
