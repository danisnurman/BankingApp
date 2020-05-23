package BankApp;

import BankApp.BukaRekening;
import BankApp.Login;
import BankApp.Transaksi;

import java.io.IOException;
import java.util.Scanner;

class Bank {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int pilihan;

		while(true) {
			System.out.println("\n--------------------");
			System.out.println("BANK TABUNGAN RAKYAT");
			System.out.println("--------------------\n");
			System.out.println("1. Belum punya rekening? DAFTAR!");
			System.out.println("2. Login akun");
			System.out.println("3. Keluar aplikasi");
			System.out.print("\nPilihan\t: ");
			pilihan = scan.nextInt(); scan.nextLine();
			switch (pilihan) {
				case 1:
					BukaRekening.bukaRekening();
					break;
				case 2:
					Login.login();
					break;
				case 3:
					System.out.println("\nTerima kasih telah menggunakan aplikasi BTR Banking."); 
					System.exit(1);
					break;
				default:
					System.out.println(">>> Input tidak valid!");
			}
		}
	}
}
