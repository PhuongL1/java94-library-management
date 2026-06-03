package vn.devpro.java94.service;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.BorrowItem;
import vn.devpro.java94.model.BorrowOrder;
import vn.devpro.java94.model.Reader;
import vn.devpro.java94.model.StatisticItem;

public class StatisticService {

	public List<StatisticItem> statisticByCategory() {
		List<StatisticItem> result = new ArrayList<StatisticItem>();
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			for (BorrowItem item : order.getBorrowItems()) {
				String categoryName = item.getBook().getCategory().getName();

				StatisticItem statisticItem = findStatisticItemByName(result, categoryName);
				if (statisticItem == null) {
					result.add(new StatisticItem(categoryName, item.getQuantity()));
				} else {
					statisticItem.setQuantity(statisticItem.getQuantity() + item.getQuantity());
				}
			}
		}

		return result;
	}

	public List<StatisticItem> statisticByAuthor() {
		List<StatisticItem> result = new ArrayList<StatisticItem>();
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			for (BorrowItem item : order.getBorrowItems()) {
				String authorName = item.getBook().getAuthor().getFullName();

				StatisticItem statisticItem = findStatisticItemByName(result, authorName);
				if (statisticItem == null) {
					result.add(new StatisticItem(authorName, item.getQuantity()));
				} else {
					statisticItem.setQuantity(statisticItem.getQuantity() + item.getQuantity());
				}
			}
		}

		return result;
	}

	public List<StatisticItem> statisticByReader() {
		List<StatisticItem> result = new ArrayList<StatisticItem>();
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			String readerName = order.getReader().getFullName();
			int totalInOrder = 0;

			for (BorrowItem item : order.getBorrowItems()) {
				totalInOrder += item.getQuantity();
			}

			StatisticItem statisticItem = findStatisticItemByName(result, readerName);
			if (statisticItem == null) {
				result.add(new StatisticItem(readerName, totalInOrder));
			} else {
				statisticItem.setQuantity(statisticItem.getQuantity() + totalInOrder);
			}
		}

		return result;
	}

	public List<Book> statisticBorrowedBooks() {
		List<Book> result = new ArrayList<Book>();
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			for (BorrowItem item : order.getBorrowItems()) {
				Book book = item.getBook();

				if (!existsBook(result, book.getCode())) {
					result.add(book);
				}
			}
		}

		return result;
	}

	public int getBorrowedQuantityByBook(Book book) {
		if (book == null) {
			return 0;
		}

		int total = 0;
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			for (BorrowItem item : order.getBorrowItems()) {
				if (item.getBook() != null && item.getBook().getCode().equalsIgnoreCase(book.getCode())) {
					total += item.getQuantity();
				}
			}
		}

		return total;
	}

	public List<Book> statisticRemainingBooks() {
		return StoreDb.getBooks();
	}

	public int getTotalBorrowedByReader(Reader reader) {
		if (reader == null) {
			return 0;
		}

		int total = 0;
		List<BorrowOrder> borrowOrders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : borrowOrders) {
			if (order.getReader() != null && order.getReader().getCode().equalsIgnoreCase(reader.getCode())) {
				for (BorrowItem item : order.getBorrowItems()) {
					total += item.getQuantity();
				}
			}
		}

		return total;
	}

	private StatisticItem findStatisticItemByName(List<StatisticItem> data, String name) {
		for (StatisticItem item : data) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	private boolean existsBook(List<Book> books, String bookCode) {
		for (Book book : books) {
			if (book.getCode().equalsIgnoreCase(bookCode)) {
				return true;
			}
		}
		return false;
	}

	public List<Book> getBorrowedBooksByReader(Reader reader) {
		List<Book> result = new ArrayList<Book>();

		for (BorrowOrder order : StoreDb.getBorrowOrders()) {
			if (order.getReader() != null && order.getReader().getId() == reader.getId()) {
				for (BorrowItem item : order.getBorrowItems()) {
					Book book = item.getBook();

					boolean existed = false;
					for (Book b : result) {
						if (b.getId() == book.getId()) {
							existed = true;
							break;
						}
					}

					if (!existed) {
						result.add(book);
					}
				}
			}
		}

		return result;
	}

	public int getBorrowedQuantityByReaderAndBook(Reader reader, Book book) {
		int total = 0;

		for (BorrowOrder order : StoreDb.getBorrowOrders()) {
			if (order.getReader() != null && order.getReader().getId() == reader.getId()) {
				for (BorrowItem item : order.getBorrowItems()) {
					if (item.getBook() != null && item.getBook().getId() == book.getId()) {
						total += item.getQuantity();
					}
				}
			}
		}

		return total;
	}

}