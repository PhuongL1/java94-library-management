package vn.devpro.java94.service;

import java.util.List;

import vn.devpro.java94.database.StoreDb;
import vn.devpro.java94.model.BorrowItem;
import vn.devpro.java94.model.BorrowOrder;

public class BorrowOrderService {

	public List<BorrowOrder> findAll() {
		return StoreDb.getBorrowOrders();
	}

	public BorrowOrder getById(int id) {
		List<BorrowOrder> orders = StoreDb.getBorrowOrders();

		for (BorrowOrder order : orders) {
			if (order.getId() == id) {
				return order;
			}
		}

		return null;
	}

	public boolean deleteById(int id) {
		List<BorrowOrder> orders = StoreDb.getBorrowOrders();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == id) {
				orders.remove(i);
				return true;
			}
		}

		return false;
	}

	public int getTotalBookTypes(BorrowOrder order) {
		if (order == null || order.getBorrowItems() == null) {
			return 0;
		}

		return order.getBorrowItems().size();
	}

	public int getTotalBooks(BorrowOrder order) {
		if (order == null || order.getBorrowItems() == null) {
			return 0;
		}

		int total = 0;

		for (BorrowItem item : order.getBorrowItems()) {
			total += item.getQuantity();
		}

		return total;
	}
}