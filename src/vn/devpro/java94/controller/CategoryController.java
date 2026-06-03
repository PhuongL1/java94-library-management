package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.Category;
import vn.devpro.java94.service.CategoryService;

public class CategoryController {
	private static Scanner sc = new Scanner(System.in);
	private static CategoryService categoryService = new CategoryService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY LOAI SACH ");
				System.out.println("\t1. Hien thi danh sach");
				System.out.println("\t2. Them moi");
				System.out.println("\t3. Sua");
				System.out.println("\t4. Xoa");
				System.out.println("\t5. Sap xep theo ten");
				System.out.println("\t0. Quay lai");
				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					display();
					break;
				case 2:
					add();
					break;
				case 3:
					edit();
					break;
				case 4:
					delete();
					break;
				case 5:
					sortByName();
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
		List<Category> categories = categoryService.findAll();

		System.out.println("\n\t DANH SACH LOAI SACH ");
		System.out.printf("%-5s %-12s %-30s\n", "ID", "MA LOAI SACH", "LOAI SACH");

		for (Category category : categories) {
			System.out.printf("%-5d %-12s %-30s\n",
					category.getId(),
					category.getCode(),
					category.getName());
		}
	}

	private static void add() {
		System.out.println("\n THEM LOAI SACH ");
		System.out.print("Nhap ma loai sach: ");
		String code = sc.nextLine();

		if (categoryService.getByCode(code) != null) {
			System.out.println("Ma loai sach da ton tai!");
			return;
		}

		System.out.print("Nhap ten loai sach: ");
		String name = sc.nextLine();

		Category category = new Category(getNextId(), code, name);
		boolean result = categoryService.insert(category);

		if (result) {
			System.out.println("Them moi thanh cong!");
		} else {
			System.out.println("Them moi that bai!");
		}
	}

	private static void edit() {
		System.out.println("\n\t SUA LOAI SACH ");
		System.out.print("Nhap ma loai sach can sua: ");
		String code = sc.nextLine();

		Category oldCategory = categoryService.getByCode(code);
		if (oldCategory == null) {
			System.out.println("Khong tim thay loai sach!");
			return;
		}

		System.out.print("Nhap ten moi: ");
		String newName = sc.nextLine();

		Category newCategory = new Category(oldCategory.getId(), oldCategory.getCode(), newName);
		boolean result = categoryService.update(newCategory);

		if (result) {
			System.out.println("Cap nhat thanh cong!");
		} else {
			System.out.println("Cap nhat that bai!");
		}
	}

	private static void delete() {
		System.out.println("\n\t XOA LOAI SACH ");
		System.out.print("Nhap ma loai sach can xoa: ");
		String code = sc.nextLine();

		boolean result = categoryService.deleteByCode(code);
		if (result) {
			System.out.println("Xoa thanh cong!");
		} else {
			System.out.println("Xoa that bai hoac khong tim thay!");
		}
	}

	private static void sortByName() {
		categoryService.sortByName();
		System.out.println("Da sap xep theo ten!");
		display();
	}

	private static int getNextId() {
		List<Category> categories = categoryService.findAll();
		int maxId = 0;

		for (Category category : categories) {
			if (category.getId() > maxId) {
				maxId = category.getId();
			}
		}

		return maxId + 1;
	}
}