package vn.devpro.java94.controller;

import java.util.List;
import java.util.Scanner;

import vn.devpro.java94.model.Author;
import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.Category;
import vn.devpro.java94.service.AuthorService;
import vn.devpro.java94.service.BookService;
import vn.devpro.java94.service.CategoryService;

public class BookController {
	private static Scanner sc = new Scanner(System.in);

	private static BookService bookService = new BookService();
	private static CategoryService categoryService = new CategoryService();
	private static AuthorService authorService = new AuthorService();

	public static void execute() {
		do {
			try {
				System.out.println("\n\t\t QUAN LY SACH ");
				System.out.println("\t1. Hien thi danh sach");
				System.out.println("\t2. Them moi");
				System.out.println("\t3. Sua");
				System.out.println("\t4. Xoa");
				System.out.println("\t5. Sap xep theo ten");
				System.out.println("\t6. Tim kiem sach"); // THEM MOI
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
				case 6:
					searchMenu(); // THEM MOI
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
		List<Book> books = bookService.findAll();

		System.out.println("\n DANH SACH BOOK ");
		System.out.printf("%-5s %-10s %-25s %-20s %-20s %-10s %-10s\n",
				"ID", "MA SACH", "TEN", "LOAI SACH", "TAC GIA", "SO TRANG", "SO LUONG");

		for (Book book : books) {
			System.out.printf("%-5d %-10s %-25s %-20s %-20s %-10d %-10d\n",
					book.getId(),
					book.getCode(),
					book.getName(),
					book.getCategory() != null ? book.getCategory().getName() : "",
					book.getAuthor() != null ? book.getAuthor().getFullName() : "",
					book.getNumberOfPages(),
					book.getQuantity());
		}
	}

	private static void add() {
		
		try {
			System.out.println("\n\t THEM SACH");
			System.out.print("Nhap ma sach: ");
			String code = sc.nextLine();

			if (bookService.getByCode(code) != null) {
				System.out.println("Ma sach da ton tai!");
				return;
			}

			System.out.print("Nhap ten sach: ");
			String name = sc.nextLine();

			System.out.println("\nDanh sach loai sach:");
			displayCategories();
			System.out.print("Nhap ma loai sach: ");
			String categoryCode = sc.nextLine();
			Category category = categoryService.getByCode(categoryCode);

			if (category == null) {
				System.out.println("Loai sach khong ton tai!");
				return;
			}

			System.out.println("\nDanh sach tac gia:");
			displayAuthors();
			System.out.print("Nhap ma tac gia: ");
			String authorCode = sc.nextLine();
			Author author = authorService.getByCode(authorCode);

			if (author == null) {
				System.out.println("Tac gia khong ton tai!");
				return;
			}

			System.out.print("Nhap so trang: ");
			int numberOfPages = Integer.parseInt(sc.nextLine());

			System.out.print("Nhap so luong: ");
			int quantity = Integer.parseInt(sc.nextLine());

			Book book = new Book(getNextId(), code, name, category, author, numberOfPages, quantity);
			boolean result = bookService.insert(book);

			if (result) {
				System.out.println("Them moi thanh cong!");
			} else {
				System.out.println("Them moi that bai!");
			}
		} catch (Exception e) {
			System.out.println("Du lieu khong hop le!");
			return;
		}
		
	}

	private static void edit() {
		
		try {
			System.out.println("\n\t SUA SACH ");
			System.out.print("Nhap ma sach can sua: ");
			String code = sc.nextLine();

			Book oldBook = bookService.getByCode(code);
			if (oldBook == null) {
				System.out.println("Khong tim thay sach!");
				return;
			}

			System.out.print("Nhap ten sach moi: ");
			String name = sc.nextLine();

			System.out.println("\nDanh sach loai sach:");
			displayCategories();
			System.out.print("Nhap ma loai sach moi: ");
			String categoryCode = sc.nextLine();
			Category category = categoryService.getByCode(categoryCode);

			if (category == null) {
				System.out.println("Loai sach khong ton tai!");
				return;
			}

			System.out.println("\nDanh sach tac gia:");
			displayAuthors();
			System.out.print("Nhap ma tac gia moi: ");
			String authorCode = sc.nextLine();
			Author author = authorService.getByCode(authorCode);

			if (author == null) {
				System.out.println("Tac gia khong ton tai!");
				return;
			}

			System.out.print("Nhap so trang moi: ");
			int numberOfPages = Integer.parseInt(sc.nextLine());

			System.out.print("Nhap so luong moi: ");
			int quantity = Integer.parseInt(sc.nextLine());

			Book newBook = new Book(oldBook.getId(), oldBook.getCode(), name, category, author, numberOfPages, quantity);
			boolean result = bookService.update(newBook);

			if (result) {
				System.out.println("Cap nhat thanh cong!");
			} else {
				System.out.println("Cap nhat that bai!");
			}
		} catch (Exception e) {
			System.out.println("Du lieu khong hop le!");
			return;
		}
		
	}

	private static void delete() {
		System.out.println("\n\t XOA SACH ");
		System.out.print("Nhap ma sach can xoa: ");
		String code = sc.nextLine();

		boolean result = bookService.deleteByCode(code);
		if (result) {
			System.out.println("Xoa thanh cong!");
		} else {
			System.out.println("Xoa that bai hoac khong tim thay!");
		}
	}

	private static void sortByName() {
		bookService.sortByName();
		System.out.println("Da sap xep theo ten!");
		display();
	}

	private static void displayCategories() {
		List<Category> categories = categoryService.findAll();

		System.out.printf("%-5s %-12s %-20s\n", "ID", "MA LOAI SACH", "LOAI SACH");
		for (Category category : categories) {
			System.out.printf("%-5d %-10s %-20s\n",
					category.getId(),
					category.getCode(),
					category.getName());
		}
	}

	private static void displayAuthors() {
		List<Author> authors = authorService.findAll();

		System.out.printf("%-5s %-12s %-25s\n", "ID", "MA TAC GIA", "TEN TAC GIA");
		for (Author author : authors) {
			System.out.printf("%-5d %-10s %-25s\n",
					author.getId(),
					author.getCode(),
					author.getFullName());
		}
	}

	private static int getNextId() {
		List<Book> books = bookService.findAll();
		int maxId = 0;

		for (Book book : books) {
			if (book.getId() > maxId) {
				maxId = book.getId();
			}
		}

		return maxId + 1;
	}

	private static void searchMenu() {
		do {
			try {
				System.out.println("\n\t\t TIM KIEM SACH ");
				System.out.println("1. Tim theo chuong loai");
				System.out.println("2. Tim theo tac gia");
				System.out.println("3. Tim theo mot phan ten sach");
				System.out.println("0. Quay lai");
				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					searchByCategory();
					break;
				case 2:
					searchByAuthor();
					break;
				case 3:
					searchByBookName();
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

	private static void searchByCategory() {
		System.out.println("\n\t TIM SACH THEO CHUNG LOAI ");
		System.out.print("Nhap tu khoa chung loai: ");
		String keyword = sc.nextLine();

		List<Book> books = bookService.findByCategory(keyword);
		showSearchResult(books);
	}

	private static void searchByAuthor() {
		System.out.println("\n\t TIM SACH THEO TAC GIA ");
		System.out.print("Nhap tu khoa tac gia: ");
		String keyword = sc.nextLine();

		List<Book> books = bookService.findByAuthor(keyword);
		showSearchResult(books);
	}

	private static void searchByBookName() {
		System.out.println("\n\t TIM SACH THEO TEN ");
		System.out.print("Nhap mot phan ten sach: ");
		String keyword = sc.nextLine();

		List<Book> books = bookService.findByNameContains(keyword);
		showSearchResult(books);
	}

	private static void showSearchResult(List<Book> books) {
		if (books == null || books.size() == 0) {
			System.out.println("Khong tim thay sach phu hop!");
			return;
		}

		System.out.println("\n KET QUA TIM KIEM ");
		System.out.printf("%-5s %-10s %-25s %-20s %-20s %-10s %-10s\n",
				"ID", "MA SACH", "TEN", "LOAI SACH", "TAC GIA", "SO TRANG", "SO LUONG");

		for (Book book : books) {
			System.out.printf("%-5d %-10s %-25s %-20s %-20s %-10d %-10d\n",
					book.getId(),
					book.getCode(),
					book.getName(),
					book.getCategory() != null ? book.getCategory().getName() : "",
					book.getAuthor() != null ? book.getAuthor().getFullName() : "",
					book.getNumberOfPages(),
					book.getQuantity());
		}
	}
}