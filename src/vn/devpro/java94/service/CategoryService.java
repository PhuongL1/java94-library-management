package vn.devpro.java94.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.Category;

public class CategoryService {

	public List<Category> findAll() {
		return StoreDb.getCategories();
	}

	public Category getByCode(String code) {
		List<Category> categories = StoreDb.getCategories();

		for (Category category : categories) {
			if (category.getCode().equalsIgnoreCase(code)) {
				return category;
			}
		}

		return null;
	}

	public boolean insert(Category category) {
		if (category == null) {
			return false;
		}

		if (getByCode(category.getCode()) != null) {
			return false;
		}

		StoreDb.getCategories().add(category);
		return true;
	}

	public boolean update(Category newCategory) {
		List<Category> categories = StoreDb.getCategories();

		for (int i = 0; i < categories.size(); i++) {
			Category oldCategory = categories.get(i);

			if (oldCategory.getCode().equalsIgnoreCase(newCategory.getCode())) {
				categories.set(i, newCategory);
				return true;
			}
		}

		return false;
	}

	public boolean deleteByCode(String code) {
		List<Category> categories = StoreDb.getCategories();

		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCode().equalsIgnoreCase(code)) {
				categories.remove(i);
				return true;
			}
		}

		return false;
	}

	public void sortByName() {
		Collections.sort(StoreDb.getCategories(), new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
}