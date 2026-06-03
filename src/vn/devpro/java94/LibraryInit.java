package vn.devpro.java94;

import java.util.Scanner;

import vn.devpro.java94.controller.*;
import vn.devpro.java94.database.StoreDb;

public class LibraryInit {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		StoreDb.initDb();

		do {
			try {
				System.out.println("\t\tCHUONG TRINH QUAN LY THU VIEN");
				System.out.println("Moi chon chuc nang quan ly");
				System.out.println("\t1. Chuc nang cua nguoi quan tri");
				System.out.println("\t2. Doc gia muon sach");
				System.out.println("\t0. Thoat");

				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					administration();
					break;
				case 2:
					reader();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Lua chon chua hop le, xin chon lai");
				}
			} catch (Exception e) {
				System.out.println("Du lieu khong hop le, vui long nhap lai!");
			}

		} while (true);
	}

	private static void reader() {
		BorrowController.execute();
	}

	private static void administration() {
		do {
			try {
				System.out.println("\t\tQUAN TRI HE THONG");
				System.out.println("Chon chuc nang");
				System.out.println("\t1. Cap nhat thong tin loai sach");
				System.out.println("\t2. Cap nhat thong tin tac gia");
				System.out.println("\t3. Cap nhat thong tin sach");
				System.out.println("\t4. Cap nhat thong tin doc gia");
				System.out.println("\t5. Quan ly phieu va thong ke");
				System.out.println("\t0. Quay lai");

				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					CategoryController.execute();
					break;
				case 2:
					AuthorController.execute();
					break;
				case 3:
					BookController.execute();
					break;
				case 4:
					ReaderController.execute();
					break;
				case 5:
					BorrowOrderManagementAndStatistics();
					break;
				case 0:
					return;
				default:
					System.out.println("Lua chon khong hop le!!!");
				}
			} catch (Exception e) {
				System.out.println("Du lieu khong hop le, vui long nhap lai!");
			}

		} while (true);
	}

	private static void BorrowOrderManagementAndStatistics() {
		do {
			try {
				System.out.println("\t\tQUAN LY PHIEU VA THONG KE");
				System.out.println("Chon chuc nang");
				System.out.println("\t1. Quan ly phieu");
				System.out.println("\t2. Xem thong ke");
				System.out.println("\t0. Quay lai");

				System.out.print("Lua chon cua ban: ");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				case 1:
					BorrowOrderController.execute();
					break;
				case 2:
					StatisticController.execute();
					break;
				case 0:
					return;
				default:
					System.out.println("Lua chon khong hop le!!!");
				}
			} catch (Exception e) {
				System.out.println("Du lieu khong hop le, vui long nhap lai!");
			}

		} while (true);
	}
}