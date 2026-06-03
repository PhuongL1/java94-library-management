package vn.devpro.java94.database;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.java94.model.Author;
import vn.devpro.java94.model.Book;
import vn.devpro.java94.model.BorrowOrder;
import vn.devpro.java94.model.Category;
import vn.devpro.java94.model.Reader;
import vn.devpro.java94.model.BorrowItem;

public class StoreDb {
	private static List<Category> categories = new ArrayList<Category>();
	private static List<Author> authors = new ArrayList<Author>();
	private static List<Book> books = new ArrayList<Book>();
	private static List<Reader> readers = new ArrayList<Reader>();
	private static List<BorrowOrder> borrowOrders = new ArrayList<BorrowOrder>();

	public static void initDb() {
		initCategories();
		initAuthors();
		initReaders();
		initBooks();
		initBorrowOrders();
	}

	private static void initCategories() {
		categories.clear();

		categories.add(new Category(1, "C001", "Van hoc"));
		categories.add(new Category(2, "C002", "Khoa hoc"));
		categories.add(new Category(3, "C003", "Cong nghe"));
		categories.add(new Category(4, "C004", "Ky nang song"));
		categories.add(new Category(5, "C005", "Lich su"));
		categories.add(new Category(6, "C006", "Kinh te"));
		categories.add(new Category(7, "C007", "Tam ly"));
		categories.add(new Category(8, "C008", "Giao duc"));
		categories.add(new Category(9, "C009", "Ngoai ngu"));
		categories.add(new Category(10, "C010", "Thieu nhi"));
		categories.add(new Category(11, "C011", "Y hoc"));
		categories.add(new Category(12, "C012", "Triet hoc"));
		categories.add(new Category(13, "C013", "Nghe thuat"));
		categories.add(new Category(14, "C014", "Du lich"));
		categories.add(new Category(15, "C015", "Phap luat"));
	}

	private static void initAuthors() {
		authors.clear();

		authors.add(new Author(1, "A001", "Tran Van", "Nam"));
		authors.add(new Author(2, "A002", "Nguyen Hoai", "An"));
		authors.add(new Author(3, "A003", "Vu Minh", "Ngoc"));
		authors.add(new Author(4, "A004", "Le Quoc", "Minh"));
		authors.add(new Author(5, "A005", "Phan Thanh", "An"));
		authors.add(new Author(6, "A006", "Hoang Gia", "Bao"));
		authors.add(new Author(7, "A007", "Do Thu", "Huong"));
		authors.add(new Author(8, "A008", "Bui Hai", "Quang"));
		authors.add(new Author(9, "A009", "Vo Thanh", "Mai"));
		authors.add(new Author(10, "A010", "Dang Anh", "Tuan"));
		authors.add(new Author(11, "A011", "Ngo My", "Linh"));
		authors.add(new Author(12, "A012", "Duong Thu", "Ha"));
		authors.add(new Author(13, "A013", "Ly Gia", "Phong"));
		authors.add(new Author(14, "A014", "Trinh Bao", "Khanh"));
		authors.add(new Author(15, "A015", "Cao Ngoc", "Thao"));
	}

	private static void initReaders() {
		readers.clear();

		readers.add(new Reader(1, "R001", "Dang Thi", "Lan", "2003-05-10", "Nu", true));
		readers.add(new Reader(2, "R002", "Nguyen Quang", "Hieu", "2001-09-12", "Nam", true));
		readers.add(new Reader(3, "R003", "Tran Thi", "Hoa", "1995-03-21", "Nu", false));
		readers.add(new Reader(4, "R004", "Le Quoc", "Minh", "1990-11-02", "Nam", false));
		readers.add(new Reader(5, "R005", "Pham Thu", "Thu", "2004-07-18", "Nu", true));
		readers.add(new Reader(6, "R006", "Hoang Anh", "Tuan", "2002-02-15", "Nam", true));
		readers.add(new Reader(7, "R007", "Vo Thanh", "Mai", "1998-06-30", "Nu", false));
		readers.add(new Reader(8, "R008", "Bui Duc", "Khanh", "2000-01-25", "Nam", true));
		readers.add(new Reader(9, "R009", "Do Nhat", "Yen", "1997-12-09", "Nu", false));
		readers.add(new Reader(10, "R010", "Ngo Huu", "Phuc", "1994-08-14", "Nam", false));
		readers.add(new Reader(11, "R011", "Duong My", "Linh", "2005-04-11", "Nu", true));
		readers.add(new Reader(12, "R012", "Ly Gia", "Bao", "1999-10-05", "Nam", false));
		readers.add(new Reader(13, "R013", "Trinh Thu", "Nhi", "2003-03-19", "Nu", true));
		readers.add(new Reader(14, "R014", "Cao Tien", "Dat", "1996-07-27", "Nam", false));
		readers.add(new Reader(15, "R015", "Huynh Ngoc", "Trang", "2001-11-08", "Nu", true));
	}

	private static void initBooks() {
		books.clear();

		books.add(new Book(1, "B001", "Tat den", categories.get(0), authors.get(0), 250, 10));
		books.add(new Book(2, "B002", "De men phieu luu ky", categories.get(0), authors.get(1), 180, 8));
		books.add(new Book(3, "B003", "Co so du lieu", categories.get(2), authors.get(2), 320, 6));
		books.add(new Book(4, "B004", "Lap trinh Java co ban", categories.get(2), authors.get(3), 450, 2));
		books.add(new Book(5, "B005", "Ky nang giao tiep", categories.get(3), authors.get(4), 210, 7));
		books.add(new Book(6, "B006", "Lich su Viet Nam", categories.get(4), authors.get(2), 390, 5));
		books.add(new Book(7, "B007", "Kham pha khoa hoc", categories.get(1), authors.get(3), 275, 9));
		books.add(new Book(8, "B008", "Tu duy logic", categories.get(1), authors.get(4), 230, 11));

		books.add(new Book(9, "B009", "Nguyen ly ke toan", categories.get(5), authors.get(5), 300, 10));
		books.add(new Book(10, "B010", "Tam ly hoc ung dung", categories.get(6), authors.get(6), 280, 6));
		books.add(new Book(11, "B011", "Phuong phap hoc hien dai", categories.get(7), authors.get(7), 340, 8));
		books.add(new Book(12, "B012", "Tieng Anh giao tiep", categories.get(8), authors.get(8), 260, 15));
		books.add(new Book(13, "B013", "Truyen co tich Viet Nam", categories.get(9), authors.get(9), 190, 12));
		books.add(new Book(14, "B014", "Suc khoe va doi song", categories.get(10), authors.get(10), 310, 7));
		books.add(new Book(15, "B015", "Nhap mon triet hoc", categories.get(11), authors.get(11), 360, 5));
		books.add(new Book(16, "B016", "My thuat co ban", categories.get(12), authors.get(12), 220, 9));

		books.add(new Book(17, "B017", "Cam nang du lich Viet Nam", categories.get(13), authors.get(13), 275, 6));
		books.add(new Book(18, "B018", "Phap luat dai cuong", categories.get(14), authors.get(14), 330, 8));
		books.add(new Book(19, "B019", "Kinh te vi mo", categories.get(5), authors.get(5), 410, 4));
		books.add(new Book(20, "B020", "Rèn luyen ky nang mem", categories.get(3), authors.get(6), 205, 10));
		books.add(new Book(21, "B021", "Tri tue nhan tao co ban", categories.get(2), authors.get(7), 500, 5));
		books.add(new Book(22, "B022", "Vat ly vui", categories.get(1), authors.get(8), 240, 9));
		books.add(new Book(23, "B023", "Ke chuyen lich su", categories.get(4), authors.get(9), 270, 7));
		books.add(new Book(24, "B024", "Doc vi tre em", categories.get(9), authors.get(10), 160, 14));
	}
	
	private static void initBorrowOrders() {
		borrowOrders.clear();

		// Phieu 1: R001 muon 3 sach
		BorrowOrder order1 = createBorrowOrder(1, "BO001", readers.get(0), "26-03-2026");
		order1.getBorrowItems().add(createBorrowItem(1, books.get(14), 1)); 
		order1.getBorrowItems().add(createBorrowItem(2, books.get(21), 1));
		order1.getBorrowItems().add(createBorrowItem(3, books.get(17), 1));
		borrowOrders.add(order1);

		BorrowOrder order2 = createBorrowOrder(2, "BO002", readers.get(8), "26-03-2026");
		order2.getBorrowItems().add(createBorrowItem(4, books.get(3), 1));  
		order2.getBorrowItems().add(createBorrowItem(5, books.get(20), 1));
		borrowOrders.add(order2);

		BorrowOrder order3 = createBorrowOrder(3, "BO003", readers.get(1), "26-03-2026");
		order3.getBorrowItems().add(createBorrowItem(6, books.get(11), 1)); 
		borrowOrders.add(order3);

		BorrowOrder order4 = createBorrowOrder(4, "BO004", readers.get(4), "26-03-2026");
		order4.getBorrowItems().add(createBorrowItem(7, books.get(12), 1)); 
		order4.getBorrowItems().add(createBorrowItem(8, books.get(23), 1)); 
		borrowOrders.add(order4);

		BorrowOrder order5 = createBorrowOrder(5, "BO005", readers.get(2), "26-03-2026");
		order5.getBorrowItems().add(createBorrowItem(9, books.get(8), 1));  
		order5.getBorrowItems().add(createBorrowItem(10, books.get(18), 1)); 
		borrowOrders.add(order5);

		BorrowOrder order6 = createBorrowOrder(6, "BO006", readers.get(9), "26-03-2026");
		order6.getBorrowItems().add(createBorrowItem(11, books.get(2), 1)); 
		order6.getBorrowItems().add(createBorrowItem(12, books.get(6), 1)); 
		order6.getBorrowItems().add(createBorrowItem(13, books.get(9), 1));
		borrowOrders.add(order6);

		BorrowOrder order7 = createBorrowOrder(7, "BO007", readers.get(14), "26-03-2026");
		order7.getBorrowItems().add(createBorrowItem(14, books.get(15), 1)); 
		borrowOrders.add(order7);

		BorrowOrder order8 = createBorrowOrder(8, "BO008", readers.get(6), "26-03-2026");
		order8.getBorrowItems().add(createBorrowItem(15, books.get(4), 1));  
		order8.getBorrowItems().add(createBorrowItem(16, books.get(19), 1)); 
		borrowOrders.add(order8);
	}
	
	private static BorrowOrder createBorrowOrder(int id, String code, Reader reader, String borrowDate) {
		BorrowOrder order = new BorrowOrder();
		order.setId(id);
		order.setCode(code);
		order.setReader(reader);
		order.setBorrowDate(borrowDate);
		order.setBorrowItems(new ArrayList<BorrowItem>());
		return order;
	}
	
	private static BorrowItem createBorrowItem(int id, Book book, int quantity) {
		BorrowItem item = new BorrowItem();
		item.setId(id);
		item.setBook(book);
		item.setQuantity(quantity);
		return item;
	}

	

	public static List<Category> getCategories() {
		return categories;
	}

	public static List<Author> getAuthors() {
		return authors;
	}

	public static List<Book> getBooks() {
		return books;
	}

	public static List<Reader> getReaders() {
		return readers;
	}

	public static List<BorrowOrder> getBorrowOrders() {
		return borrowOrders;
	}
}