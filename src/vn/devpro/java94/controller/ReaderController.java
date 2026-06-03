package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.Reader;
import vn.devpro.java94.service.ReaderService;

public class ReaderController {
	private static Scanner sc = new Scanner(System.in);
	private static ReaderService readerService = new ReaderService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY DOC GIA ");
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
		List<Reader> readers = readerService.findAll();

		System.out.println("\n\t DANH SACH READER ");
		System.out.printf("%-5s %-10s %-15s %-15s %-15s %-10s %-10s\n",
				"ID", "MA DOC GIA", "HO DEM", "TEN", "NGAY SINH", "GIOI TINH", "HOC SINH");

		for (Reader reader : readers) {
			System.out.printf("%-5d %-12s %-15s %-15s %-15s %-10s %-10s\n",
					reader.getId(),
					reader.getCode(),
					reader.getFirstName(),
					reader.getLastName(),
					reader.getDateOfBirth(),
					reader.getGender(),
					reader.isStudent() ? "dung" : "sai");
		}
	}

	private static void add() {
		System.out.println("\n\t THEM DOC GIA ");
		System.out.print("Nhap ma doc gia: ");
		String code = sc.nextLine();

		if (readerService.getByCode(code) != null) {
			System.out.println("Ma doc gia da ton tai!");
			return;
		}

		System.out.print("Nhap ho dem: ");
		String firstName = sc.nextLine();

		System.out.print("Nhap ten: ");
		String lastName = sc.nextLine();

		System.out.print("Nhap ngay sinh: ");
		String dateOfBirth = sc.nextLine();

		System.out.print("Nhap gioi tinh: ");
		String gender = sc.nextLine();

		System.out.print("La hoc sinh? (dung/sai): ");
		boolean student = Boolean.parseBoolean(sc.nextLine());

		Reader reader = new Reader(getNextId(), code, firstName, lastName, dateOfBirth, gender, student);
		boolean result = readerService.insert(reader);

		if (result) {
			System.out.println("Them moi thanh cong!");
		} else {
			System.out.println("Them moi that bai!");
		}
	}

	private static void edit() {
		System.out.println("\n\t SUA DOC GIA ");
		System.out.print("Nhap ma doc gia can sua: ");
		String code = sc.nextLine();

		Reader oldReader = readerService.getByCode(code);
		if (oldReader == null) {
			System.out.println("Khong tim thay doc gia!");
			return;
		}

		System.out.print("Nhap ho dem moi: ");
		String firstName = sc.nextLine();

		System.out.print("Nhap ten moi: ");
		String lastName = sc.nextLine();

		System.out.print("Nhap ngay sinh moi: ");
		String dateOfBirth = sc.nextLine();

		System.out.print("Nhap gioi tinh moi: ");
		String gender = sc.nextLine();

		System.out.print("La hoc sinh? (dung/sai): ");
		boolean student = Boolean.parseBoolean(sc.nextLine());

		Reader newReader = new Reader(oldReader.getId(), oldReader.getCode(), firstName, lastName, dateOfBirth, gender,
				student);
		boolean result = readerService.update(newReader);

		if (result) {
			System.out.println("Cap nhat thanh cong!");
		} else {
			System.out.println("Cap nhat that bai!");
		}
	}

	private static void delete() {
		System.out.println("\n XOA DOC GIA ");
		System.out.print("Nhap ma doc gia can xoa: ");
		String code = sc.nextLine();

		boolean result = readerService.deleteByCode(code);
		if (result) {
			System.out.println("Xoa thanh cong!");
		} else {
			System.out.println("Xoa that bai hoac khong tim thay!");
		}
	}

	private static void sortByName() {
		readerService.sortByName();
		System.out.println("Da sap xep theo ten!");
		display();
	}

	private static int getNextId() {
		List<Reader> readers = readerService.findAll();
		int maxId = 0;

		for (Reader reader : readers) {
			if (reader.getId() > maxId) {
				maxId = reader.getId();
			}
		}

		return maxId + 1;
	}
}
