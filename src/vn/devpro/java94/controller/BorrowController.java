package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.BorrowItem;
import vn.devpro.java94.dto.*;
import vn.devpro.java94.model.Reader;
import vn.devpro.java94.service.BookService;
import vn.devpro.java94.service.BorrowService;
import vn.devpro.java94.service.ReaderService;

public class BorrowController {
	private static Scanner sc = new Scanner(System.in);

	private static BorrowService borrowService = new BorrowService();
	private static ReaderService readerService = new ReaderService();
	private static BookService bookService = new BookService();

	private static BorrowCart borrowCart = new BorrowCart();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY MUON SACH ");
				System.out.println("\t1. Chon doc gia cho phieu muon");
				System.out.println("\t2. Hien thi danh sach doc gia");
				System.out.println("\t3. Hien thi danh sach ");
				System.out.println("\t4. Them sach vao phieu tam");
				System.out.println("\t5. Xem phieu tam");
				System.out.println("\t6. Luu phieu muon");
				System.out.println("\t7. Tao lai phieu tam");
				System.out.println("\t0. Quay lai");
				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					chooseReader();
					break;
				case 2:
					displayReaders();
					break;
				case 3:
					displayBooks();
					break;
				case 4:
					addBookToCart();
					break;
				case 5:
					displayBorrowCart();
					break;
				case 6:
					saveBorrowOrder();
					break;
				case 7:
					resetBorrowCart();
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

	private static void chooseReader() {
		System.out.println("\n\t CHON DOC GIA ");
		displayReaders();
		System.out.print("Nhap ma doc gia: ");
		String readerCode = sc.nextLine();

		String message = borrowService.chooseReader(borrowCart, readerCode);
		System.out.println(message);
	}

	private static void addBookToCart() {
		System.out.println("\n\t THEM SACH VAO PHIEU TAM ");

		if (borrowCart.getReader() == null) {
			System.out.println("Ban phai chon doc gia truoc!");
			return;
		}

		displayBooks();
		System.out.print("Nhap ma sach: ");
		String bookCode = sc.nextLine();

		//System.out.print("Nhap so luong muon: ");
		//int quantity = Integer.parseInt(sc.nextLine());
		int quantity = 1;

		String message = borrowService.addBookToCart(borrowCart, bookCode, quantity);
		System.out.println(message);
	}

	private static void displayBorrowCart() {
		System.out.println("\n\t PHIEU TAM ");

		if (borrowCart.getReader() == null) {
			System.out.println("Chua chon doc gia!");
			return;
		}

		Reader reader = borrowCart.getReader();
		int currentBorrowed = borrowService.getCurrentBorrowedQuantity(reader);
		int maxAllowed = reader.isStudent() ? 8 : 5;

		System.out.println("Doc gia: " + reader.getFullName() + " - " + reader.getCode());
		System.out.println("Doi tuong doc gia: " + (reader.isStudent() ? "Hoc sinh" : "Khac"));
		System.out.println("Dang muon truoc do: " + currentBorrowed + " cuon");
		System.out.println("Trong phieu tam: " + borrowCart.getTotalQuantity() + " cuon");
		System.out.println("Gioi han toi da: " + maxAllowed + " cuon");

		if (borrowCart.getBorrowItems().size() == 0) {
			System.out.println("Phieu tam chua co sach!");
			return;
		}

		System.out.printf("%-5s %-12s %-30s %-10s\n", "ID", "MA SACH", "TEN SACH", "SO LUONG");
		for (BorrowItem item : borrowCart.getBorrowItems()) {
			System.out.printf("%-5d %-10s %-30s %-10d\n",
					item.getId(),
					item.getBook().getCode(),
					item.getBook().getName(),
					item.getQuantity());
		}
	}

	private static void saveBorrowOrder() {
		System.out.println("\n\t LUU PHIEU MUON ");
		String message = borrowService.saveBorrowOrder(borrowCart);
		System.out.println(message);
	}

	private static void resetBorrowCart() {
		borrowCart.clear();
		System.out.println("Da tao lai phieu tam!");
	}

	private static void displayReaders() {
		List<Reader> readers = readerService.findAll();

		System.out.println("\n\t DANH SACH DOC GIA ");
		System.out.printf("%-5s %-12s %-15s %-15s %-10s %-10s\n",
				"ID", "MA DOC GIA", "HO DEM", "TEN", "GIOI TINH", "HOC SINH");

		for (Reader reader : readers) {
			System.out.printf("%-5d %-12s %-15s %-15s %-10s %-10s\n",
					reader.getId(),
					reader.getCode(),
					reader.getFirstName(),
					reader.getLastName(),
					reader.getGender(),
					reader.isStudent() ? "dung" : "sai");
		}
	}

	private static void displayBooks() {
		List<Book> books = bookService.findAll();

		System.out.println("\n DANH SACH BOOK ");
		System.out.printf("%-5s %-12s %-25s %-20s %-20s %-10s\n",
				"ID", "MA SACH", "TEN SACH", "LOAI SACH", "TAC GIA", "SO LUONG");

		for (Book book : books) {
			System.out.printf("%-5d %-10s %-25s %-20s %-20s %-10d\n",
					book.getId(),
					book.getCode(),
					book.getName(),
					book.getCategory() != null ? book.getCategory().getName() : "",
					book.getAuthor() != null ? book.getAuthor().getFullName() : "",
					book.getQuantity());
		}
	}
}