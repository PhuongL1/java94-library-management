package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.BorrowOrder;
import vn.devpro.java94.service.BorrowOrderService;

public class BorrowOrderController {
	private static Scanner sc = new Scanner(System.in);
	private static BorrowOrderService borrowOrderService = new BorrowOrderService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY PHIEU MUON ");
				System.out.println("\t1. Hien thi danh sach phieu muon");
				System.out.println("\t2. Xoa phieu muon");
				System.out.println("\t0. Quay lai");
				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					display();
					break;
				case 2:
					delete();
					break;
				case 0:
					return;
				default:
					System.out.println("Lua chon khong hop le!");
					break;
				}
			} catch (Exception e) {
				System.out.println("Du lieu khong hop le, vui long nhap lai!");
			}

		} while (true);
	}

	public static void display() {
		List<BorrowOrder> orders = borrowOrderService.findAll();

		System.out.println("\n DANH SACH PHIEU MUON ");
		System.out.printf("%-8s %-12s %-25s %-15s %-15s\n", "ID", "MA PHIEU", "TEN DOC GIA", "SO LOAI SACH",
				"TONG SO SACH");

		if (orders == null || orders.size() == 0) {
			System.out.println("Chua co phieu muon nao!");
			return;
		}

		for (BorrowOrder order : orders) {
			System.out.printf("%-8d %-12s %-25s %-15d %-15d\n", order.getId(),
					order.getReader() != null ? order.getReader().getCode() : "",
					order.getReader() != null ? order.getReader().getFullName() : "",
					borrowOrderService.getTotalBookTypes(order), borrowOrderService.getTotalBooks(order));
		}
	}

	private static void delete() {
		try {
			System.out.println("\n\t XOA PHIEU MUON ");
			System.out.print("Nhap ID phieu muon can xoa: ");
			int id = Integer.parseInt(sc.nextLine());

			BorrowOrder order = borrowOrderService.getById(id);
			if (order == null) {
				System.out.println("Khong tim thay phieu muon!");
				return;
			}

			boolean result = borrowOrderService.deleteById(id);

			if (result) {
				System.out.println("Xoa phieu muon thanh cong!");
			} else {
				System.out.println("Xoa phieu muon that bai!");
			}
		} catch (Exception e) {
			System.out.println("Du lieu khong hop le!");
		}
	}
}