package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.Author;
import vn.devpro.java94.service.AuthorService;

public class AuthorController {
	private static Scanner sc = new Scanner(System.in);
	private static AuthorService authorService = new AuthorService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY AUTHOR ");
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
		List<Author> authors = authorService.findAll();

		System.out.println("\n\t\t DANH SACH TAC GIA ");
		System.out.printf("%-5s %-12s %-20s %-20s\n", "ID", "MA TAC GIA", "HO DEM", "TEN");

		for (Author author : authors) {
			System.out.printf("%-5d %-12s %-20s %-20s\n",
					author.getId(),
					author.getCode(),
					author.getFirstName(),
					author.getLastName());
		}
	}

	private static void add() {
		System.out.println("\n\t THEM TAC GIA ");
		System.out.print("Nhap ma tac gia: ");
		String code = sc.nextLine();

		if (authorService.getByCode(code) != null) {
			System.out.println("ma tac gia da ton tai!");
			return;
		}

		System.out.print("Nhap ho dem: ");
		String firstName = sc.nextLine();

		System.out.print("Nhap ten: ");
		String lastName = sc.nextLine();

		Author author = new Author(getNextId(), code, firstName, lastName);
		boolean result = authorService.insert(author);

		if (result) {
			System.out.println("Them moi thanh cong!");
		} else {
			System.out.println("Them moi that bai!");
		}
	}

	private static void edit() {
		System.out.println("\n\t SUA TAC GIA ");
		System.out.print("Nhap ma tac gia can sua: ");
		String code = sc.nextLine();

		Author oldAuthor = authorService.getByCode(code);
		if (oldAuthor == null) {
			System.out.println("Khong tim thay tac gia!");
			return;
		}

		System.out.print("Nhap ho dem moi: ");
		String firstName = sc.nextLine();

		System.out.print("Nhap ten moi: ");
		String lastName = sc.nextLine();

		Author newAuthor = new Author(oldAuthor.getId(), oldAuthor.getCode(), firstName, lastName);
		boolean result = authorService.update(newAuthor);

		if (result) {
			System.out.println("Cap nhat thanh cong!");
		} else {
			System.out.println("Cap nhat that bai!");
		}
	}

	private static void delete() {
		System.out.println("\n\t XOA AUTHOR ");
		System.out.print("Nhap ma tac gia can xoa: ");
		String code = sc.nextLine();

		boolean result = authorService.deleteByCode(code);
		if (result) {
			System.out.println("Xoa thanh cong!");
		} else {
			System.out.println("Xoa that bai hoac khong tim thay!");
		}
	}

	private static void sortByName() {
		authorService.sortByName();
		System.out.println("Da sap xep theo ten!");
		display();
	}

	private static int getNextId() {
		List<Author> authors = authorService.findAll();
		int maxId = 0;

		for (Author author : authors) {
			if (author.getId() > maxId) {
				maxId = author.getId();
			}
		}

		return maxId + 1;
	}
}