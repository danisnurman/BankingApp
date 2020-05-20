package BankApp;
import BankApp.Rekening;
import BankApp.BukaRekening;

import java.util.*;
import java.text.*;
import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

class Bank {
	Map<String, Customer> customerMap;
	Bank() {
		customerMap = new HashMap<String,Customer>();
	}
	public static void main(String[] args) throws IOException {
		Scanner scan  =  new Scanner(System.in);
		Customer customer;
		String namaPengguna, password;
		double nominal;
		Bank bank  =  new Bank();
		int pilihan;
	outer:	while(true) {
			System.out.println("\n-------------------");
			System.out.println("BANK    OF     JAVA");
			System.out.println("-------------------\n");
			System.out.println("1. Daftar akun.");
			System.out.println("2. Masuk.");
			System.out.println("3. Perbarui akun.");
			System.out.println("4. Keluar.");
			System.out.print("\nMasukkan pilihan\t: ");
			pilihan = scan.nextInt(); scan.nextLine();
			switch (pilihan) {
				case 1:
					BukaRekening.bukaRekening();
					break;
				case 2:
					System.out.println("Username\t: ");
					namaPengguna = scan.next();
					scan.nextLine();
					System.out.println("Password\t: ");
					password = scan.next();
					scan.nextLine();
					if (bank.customerMap.containsKey(namaPengguna)) {
						customer = bank.customerMap.get(namaPengguna);
						if (customer.password.equals(password)) {
							while (true) {
								System.out.println("\n-------------------");
								System.out.println("W  E  L  C  O  M  E");
								System.out.println("-------------------\n");
								System.out.println("1. Deposit.");
								System.out.println("2. Transfer.");
								System.out.println("3. Riwayat transaksi.");
								System.out.println("4. Informasi pengguna.");
								System.out.println("5. LOG OUT.");
								System.out.print("\nEnter your pilihan : ");
								pilihan = scan.nextInt();
								scan.nextLine();
								switch (pilihan) {
									case 1:
										System.out.print("Masukkan nominal\t: ");
										while (!scan.hasNextDouble()) {
											System.out.println("Nominal deposit tidak valid!\nMasukkan ulang\t:");
											scan.nextLine();
										}
										nominal = scan.nextDouble();
										scan.nextLine();
										customer.deposit(nominal,new Date());
										break;
									case 2:
										System.out.print("Username tujuan\t: ");
										namaPengguna = scan.next();
										scan.nextLine();
										System.out.println("Nominal transfer\t: ");
										while (!scan.hasNextDouble()) {
											System.out.println("Nominal transfer tidak valid!\nMasukkan ulang\t: ");
											scan.nextLine();
										}
										nominal = scan.nextDouble(); scan.nextLine();
										if (nominal > 300000) {
											System.out.println("Batas transfer terlampaui!\nHarap hubungi customer service BMS.");
											break;
										}
										if (bank.customerMap.containsKey(namaPengguna)) {
											Customer payee = bank.customerMap.get(namaPengguna);
											payee.deposit(nominal,new Date());
											customer.withdraw(nominal,new Date());
										} else {
											System.out.println("Username tidak ditemukan!");
										}
										break;
									case 3:
										for (String transaksi : customer.transaksi) {
											System.out.println(transaksi);
										}
										break;
									case 4:
										System.out.println("Nama Pemegang Rekening\t: "+customer.name);
										System.out.println("Alamat Pemegang Rekening\t: "+customer.alamatRumah);
										System.out.println("Nomor Ponsel Pemegang Rekening\t: "+customer.notelp);
										break;
									case 5:
									    continue outer;
								    default:
									    System.out.println("Input tidak valid!");
								}
							}
						} else {
							System.out.println("Username/password salah!");
						}
					} else {
						System.out.println("Username/password salah!");
					}
					break;
				case 3:
					System.out.println("Masukkan username\t: ");
					namaPengguna = scan.next();
					if (bank.customerMap.containsKey(namaPengguna)) {
						bank.customerMap.get(namaPengguna).update(new Date());
					}
					else {
						System.out.println("Username tidak ditemukan!");
					}
					break;
				case 4:
					System.out.println("\nTerima kasih telah memakai aplikasi BMS Mobile Banking."); 
					System.exit(1);
					break;
				default:
					System.out.println("Input tidak valid!");
			}
		}
	}
}