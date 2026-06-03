package vn.devpro.java94.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Reader;

public class ReaderService {

	public List<Reader> findAll() {
		return StoreDb.getReaders();
	}

	public Reader getByCode(String code) {
		List<Reader> readers = StoreDb.getReaders();

		for (Reader reader : readers) {
			if (reader.getCode().equalsIgnoreCase(code)) {
				return reader;
			}
		}

		return null;
	}

	public boolean insert(Reader reader) {
		if (reader == null) {
			return false;
		}

		if (getByCode(reader.getCode()) != null) {
			return false;
		}

		StoreDb.getReaders().add(reader);
		return true;
	}

	public boolean update(Reader newReader) {
		List<Reader> readers = StoreDb.getReaders();

		for (int i = 0; i < readers.size(); i++) {
			Reader oldReader = readers.get(i);

			if (oldReader.getCode().equalsIgnoreCase(newReader.getCode())) {
				readers.set(i, newReader);
				return true;
			}
		}

		return false;
	}

	public boolean deleteByCode(String code) {
		List<Reader> readers = StoreDb.getReaders();

		for (int i = 0; i < readers.size(); i++) {
			if (readers.get(i).getCode().equalsIgnoreCase(code)) {
				readers.remove(i);
				return true;
			}
		}

		return false;
	}

	public void sortByName() {
		Collections.sort(StoreDb.getReaders(), new Comparator<Reader>() {
			@Override
			public int compare(Reader o1, Reader o2) {
				return o1.getFullName().compareToIgnoreCase(o2.getFullName());
			}
		});
	}
}