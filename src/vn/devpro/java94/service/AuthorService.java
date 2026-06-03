package vn.devpro.java94.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Author;

public class AuthorService {

	public List<Author> findAll() {
		return StoreDb.getAuthors();
	}

	public Author getByCode(String code) {
		List<Author> authors = StoreDb.getAuthors();

		for (Author author : authors) {
			if (author.getCode().equalsIgnoreCase(code)) {
				return author;
			}
		}

		return null;
	}

	public boolean insert(Author author) {
		if (author == null) {
			return false;
		}

		if (getByCode(author.getCode()) != null) {
			return false;
		}

		StoreDb.getAuthors().add(author);
		return true;
	}

	public boolean update(Author newAuthor) {
		List<Author> authors = StoreDb.getAuthors();

		for (int i = 0; i < authors.size(); i++) {
			Author oldAuthor = authors.get(i);

			if (oldAuthor.getCode().equalsIgnoreCase(newAuthor.getCode())) {
				authors.set(i, newAuthor);
				return true;
			}
		}

		return false;
	}

	public boolean deleteByCode(String code) {
		List<Author> authors = StoreDb.getAuthors();

		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getCode().equalsIgnoreCase(code)) {
				authors.remove(i);
				return true;
			}
		}

		return false;
	}

	public void sortByName() {
		Collections.sort(StoreDb.getAuthors(), new Comparator<Author>() {
			@Override
			public int compare(Author o1, Author o2) {
				return o1.getFullName().compareToIgnoreCase(o2.getFullName());
			}
		});
	}
}