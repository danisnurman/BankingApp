package BankApp;

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

public class AkunBank extends Bank {
    public static void akunBank() throws IOException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String f0_Username, f1_Password;
        
        System.out.print("Username\t: ");
        f0_Username = scan.next(); scan.nextLine();

        System.out.print("Password\t: ");
        f1_Password = scan.next(); scan.nextLine();

		File file = new File(System.getProperty("user.dir")+"/customer.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
        List<String> lines = new ArrayList<String>();
        int pilihan;

        // From address book mgmt
        while ((currentLine = reader.readLine()) != null) {
			if (!first) {
				fields = currentLine.split(",");
				first = true;
			} else { // only if both of the user's inputs (name and surname) match a contact then i add this contact's info to an array
                String[] info = currentLine.split(",");
                // Original Condition
				if (info[3].equals(f0_Username) && info[4].equals(f1_Password)) {
					// System.out.println("\n----There is an account for the information you gave----");
					// for (int i = 0; i < fields.length; i++ ) {
					// 	System.out.println(fields[i] +": "+ info[i]);
					// }
                    // lines.add(currentLine);
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
                                /* System.out.print("Masukkan nominal\t: ");
                                while (!scan.hasNextDouble()) {
                                    System.out.println("Nominal deposit tidak valid!\nMasukkan ulang\t:");
                                    scan.nextLine();
                                }
                                nominal = scan.nextDouble();
                                scan.nextLine();
                                customer.deposit(nominal,new Date()); */
                                System.out.println("Tes1");
                                break;
                            case 2:
                                /* System.out.print("Username tujuan\t: ");
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
                                } */
                                System.out.println("Tes2");
                                break;
                            case 3:
                                /* for (String transaksi : customer.transaksi) {
                                    System.out.println(transaksi);
                                } */
                                System.out.println("Tes3");
                                break;
                            case 4:
                                /* System.out.println("Nama Pemegang Rekening\t: "+customer.name);
                                System.out.println("Alamat Pemegang Rekening\t: "+customer.alamatRumah);
                                System.out.println("Nomor Ponsel Pemegang Rekening\t: "+customer.notelp); */
                                System.out.println("Tes4");
                                break;
                            case 5:
                                System.exit(0);
                            default:
                                System.out.println("Input tidak valid!");
                        }
                    }
				} else if (info[3].equals(f0_Username) && !info[4].equals(f1_Password)) {
					System.out.println("----Username dan Password tidak valid----");
				} else if (!info[3].equals(f0_Username) && info[4].equals(f1_Password)) {
					System.out.println("----Username tidak ditemukan----");
                }				
			}
		}
		System.out.println("-------------------");
		reader.close();

        // Original File
        /* if (bank.customerMap.containsKey(namaPengguna)) {
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
        } */
    }
}