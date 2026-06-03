package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.Reader;
import vn.devpro.java94.service.ReaderService;
import vn.devpro.java94.service.StatisticService;

public class StatisticController {
	private static Scanner sc = new Scanner(System.in);
	private static StatisticService statisticService = new StatisticService();
	private static ReaderService readerService = new ReaderService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t THONG KE THU VIEN ");
				System.out.println("\t1. Thong ke theo loai sach");
				System.out.println("\t2. Thong ke theo tac gia");
				System.out.println("\t3. Thong ke sach dang muon");
				System.out.println("\t4. Thong ke sach con lai");
				System.out.println("\t5. Thong ke theo doc gia");
				System.out.println("\t0. Quay lai");
				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					statisticByCategory();
					break;
				case 2:
					statisticByAuthor();
					break;
				case 3:
					statisticBorrowedBooks();
					break;
				case 4:
					statisticRemainingBooks();
					break;
				case 5:
					statisticByReader();
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

	private static void statisticByCategory() {
		List<Book> books = statisticService.statisticBorrowedBooks();

		System.out.println("\n\t THONG KE THEO LOAI SACH ");
		System.out.printf("%-20s %-35s %-15s\n",
				"LOAI SACH", "TEN SACH", "TONG SACH MUON");

		if (books.isEmpty()) {
			System.out.println("Chua co du lieu muon sach!");
			return;
		}

		books.sort(new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				String c1 = b1.getCategory() != null ? b1.getCategory().getName() : "";
				String c2 = b2.getCategory() != null ? b2.getCategory().getName() : "";

				int compareCategory = c1.compareToIgnoreCase(c2);
				if (compareCategory != 0) {
					return compareCategory;
				}

				return b1.getName().compareToIgnoreCase(b2.getName());
			}
		});

		String currentCategory = "";

		for (Book book : books) {
			int borrowedQuantity = statisticService.getBorrowedQuantityByBook(book);
			if (borrowedQuantity <= 0) {
				continue;
			}

			String categoryName = book.getCategory() != null ? book.getCategory().getName() : "";

			if (!categoryName.equals(currentCategory)) {
				System.out.printf("%-20s %-35s %-15s\n", categoryName, "", "");
				currentCategory = categoryName;
			}

			System.out.printf("%-20s %-35s %-15d\n", "", book.getName(), borrowedQuantity);
		}
	}

	private static void statisticByAuthor() {
		List<Book> books = statisticService.statisticBorrowedBooks();

		System.out.println("\n\t THONG KE THEO TAC GIA ");
		System.out.printf("%-25s %-35s %-15s\n",
				"TAC GIA", "TEN SACH", "TONG SACH MUON");

		if (books.isEmpty()) {
			System.out.println("Chua co du lieu muon sach!");
			return;
		}

		books.sort(new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				String a1 = "";
				String a2 = "";

				if (b1.getAuthor() != null) {
					a1 = b1.getAuthor().getLastName() + " " + b1.getAuthor().getFirstName();
				}

				if (b2.getAuthor() != null) {
					a2 = b2.getAuthor().getLastName() + " " + b2.getAuthor().getFirstName();
				}

				int compareAuthor = a1.compareToIgnoreCase(a2);
				if (compareAuthor != 0) {
					return compareAuthor;
				}

				return b1.getName().compareToIgnoreCase(b2.getName());
			}
		});

		String currentAuthor = "";

		for (Book book : books) {
			int borrowedQuantity = statisticService.getBorrowedQuantityByBook(book);
			if (borrowedQuantity <= 0) {
				continue;
			}

			String authorName = "";
			if (book.getAuthor() != null) {
				authorName = book.getAuthor().getLastName() + " " + book.getAuthor().getFirstName();
			}

			if (!authorName.equals(currentAuthor)) {
				System.out.printf("%-25s %-35s %-15s\n", authorName, "", "");
				currentAuthor = authorName;
			}

			System.out.printf("%-25s %-35s %-15d\n", "", book.getName(), borrowedQuantity);
		}
	}

	private static void statisticBorrowedBooks() {
		List<Book> books = statisticService.statisticBorrowedBooks();

		System.out.println("\n\t THONG KE SACH DANG MUON ");
		System.out.printf("%-12s %-30s %-20s %-15s\n",
				"MA SACH", "TEN SACH", "LOAI SACH", "SACH DA MUON");

		if (books.isEmpty()) {
			System.out.println("Chua co du lieu muon sach!");
			return;
		}

		for (Book book : books) {
			System.out.printf("%-12s %-30s %-20s %-15d\n",
					book.getCode(),
					book.getName(),
					book.getCategory() != null ? book.getCategory().getName() : "",
					statisticService.getBorrowedQuantityByBook(book));
		}
	}

	private static void statisticRemainingBooks() {
		List<Book> books = statisticService.statisticRemainingBooks();

		System.out.println("\n\t THONG KE SACH CON LAI ");
		System.out.printf("%-12s %-30s %-20s %-20s %-10s\n",
				"MA SACH", "TEN SACH", "LOAI SACH", "TAC GIA", "SO LUONG");

		if (books.isEmpty()) {
			System.out.println("Khong co du lieu sach!");
			return;
		}

		for (Book book : books) {
			System.out.printf("%-12s %-30s %-20s %-20s %-10d\n",
					book.getCode(),
					book.getName(),
					book.getCategory() != null ? book.getCategory().getName() : "",
					book.getAuthor() != null ? book.getAuthor().getFullName() : "",
					book.getQuantity());
		}
	}

	private static void statisticByReader() {
		List<Reader> readers = readerService.findAll();

		System.out.println("\n THONG KE THEO DOC GIA ");
		System.out.printf("%-12s %-25s %-12s %-35s %-15s\n",
				"MA DOC GIA", "TEN", "LOAI", "TEN SACH", "TONG SACH MUON");

		if (readers.isEmpty()) {
			System.out.println("Khong co du lieu doc gia!");
			return;
		}

		for (Reader reader : readers) {
			List<Book> borrowedBooks = statisticService.getBorrowedBooksByReader(reader);
			if (borrowedBooks.isEmpty()) {
				System.out.printf("%-12s %-25s %-12s %-35s %-15d\n",
						reader.getCode(),
						reader.getFullName(),
						reader.isStudent() ? "Hoc sinh" : "Khac",
						"",
						0);
			} else {
				System.out.printf("%-12s %-25s %-12s %-35s %-15s\n",
						reader.getCode(),
						reader.getFullName(),
						reader.isStudent() ? "Hoc sinh" : "Khac",
						"",
						"");

				for (Book book : borrowedBooks) {
					int quantity = statisticService.getBorrowedQuantityByReaderAndBook(reader, book);
					System.out.printf("%-12s %-25s %-12s %-35s %-15d\n",
							"",
							"",
							"",
							book.getName(),
							quantity);
				}
			}
		}
	}
}