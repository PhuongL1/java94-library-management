package vn.devpro.java94.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Book;

public class BookService {

	public List<Book> findAll() {
		return StoreDb.getBooks();
	}

	public Book getByCode(String code) {
		List<Book> books = StoreDb.getBooks();

		for (Book book : books) {
			if (book.getCode().equalsIgnoreCase(code)) {
				return book;
			}
		}

		return null;
	}

	public boolean insert(Book book) {
		if (book == null) {
			return false;
		}

		if (getByCode(book.getCode()) != null) {
			return false;
		}

		StoreDb.getBooks().add(book);
		return true;
	}

	public boolean update(Book newBook) {
		List<Book> books = StoreDb.getBooks();

		for (int i = 0; i < books.size(); i++) {
			Book oldBook = books.get(i);

			if (oldBook.getCode().equalsIgnoreCase(newBook.getCode())) {
				books.set(i, newBook);
				return true;
			}
		}

		return false;
	}

	public boolean deleteByCode(String code) {
		List<Book> books = StoreDb.getBooks();

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getCode().equalsIgnoreCase(code)) {
				books.remove(i);
				return true;
			}
		}

		return false;
	}

	public void sortByName() {
		Collections.sort(StoreDb.getBooks(), new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}

	public List<Book> findByCategory(String keyword) {
		List<Book> result = new ArrayList<Book>();

		if (keyword == null || keyword.trim().isEmpty()) {
			return result;
		}

		String key = keyword.trim().toLowerCase();
		List<Book> books = StoreDb.getBooks();

		for (Book book : books) {
			if (book.getCategory() != null) {
				String categoryCode = book.getCategory().getCode() != null ? book.getCategory().getCode().toLowerCase()
						: "";

				String categoryName = book.getCategory().getName() != null ? book.getCategory().getName().toLowerCase()
						: "";

				if (categoryCode.contains(key) || categoryName.contains(key)) {
					result.add(book);
				}
			}
		}

		return result;
	}

	public List<Book> findByAuthor(String keyword) {
		List<Book> result = new ArrayList<Book>();

		if (keyword == null || keyword.trim().isEmpty()) {
			return result;
		}

		String key = keyword.trim().toLowerCase();
		List<Book> books = StoreDb.getBooks();

		for (Book book : books) {
			if (book.getAuthor() != null) {
				String authorCode = book.getAuthor().getCode() != null ? book.getAuthor().getCode().toLowerCase() : "";

				String authorName = book.getAuthor().getFullName() != null
						? book.getAuthor().getFullName().toLowerCase()
						: "";

				if (authorCode.contains(key) || authorName.contains(key)) {
					result.add(book);
				}
			}
		}

		return result;
	}

	public List<Book> findByNameContains(String keyword) {
		List<Book> result = new ArrayList<Book>();

		if (keyword == null || keyword.trim().isEmpty()) {
			return result;
		}

		String key = keyword.trim().toLowerCase();
		List<Book> books = StoreDb.getBooks();

		for (Book book : books) {
			String bookName = book.getName() != null ? book.getName().toLowerCase() : "";

			if (bookName.contains(key)) {
				result.add(book);
			}
		}

		return result;
	}
}