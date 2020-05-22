package BankApp;

import BankApp.Transaksi;

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

public class Login extends Bank {
    public static void login() throws IOException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String f0_Username, f1_Password;
        
        System.out.print("Username\t: ");
        f0_Username = scan.next(); scan.nextLine();

        System.out.print("Password\t: ");
        f1_Password = scan.next(); scan.nextLine();

		File file1 = new File(System.getProperty("user.dir")+"/customer.txt");
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));

        File file2 = new File(System.getProperty("user.dir")+"/saldo.txt");
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        
		String currentLine, currentLine2;
		boolean first = false;
		String[] fields = new String[0];
        int pilihan;

        // From address book mgmt
        while ((currentLine = reader1.readLine()) != null) {
			if (!first) {
				fields = currentLine.split(";");
				first = true;
			} else { // only if both of the user's inputs (name and surname) match a contact then i add this contact's info to an array
                String[] info = currentLine.split(";");
				if (info[3].equals(f0_Username) && info[4].equals(f1_Password)) {
                    while (true) {
                        System.out.println("\n-------------------");
                        System.out.println("W  E  L  C  O  M  E");
                        System.out.println("-------------------\n");
                        System.out.println("Selamat datang di aplikasi Bank PTI, " + info[0] + "!");
                        System.out.println("1. Setor tunai.");
                        System.out.println("2. Tarik tunai.");
                        System.out.println("3. Riwayat transaksi.");
                        System.out.println("4. Cek saldo.");
                        System.out.println("5. Informasi pengguna.");
                        System.out.println("6. LOG OUT.");
                        System.out.print("\nEnter your pilihan : ");
                        pilihan = scan.nextInt();
                        scan.nextLine();
                        switch (pilihan) {
                            case 1:
                                System.out.print("Masukkan nominal\t: ");
                                while (!scan.hasNextDouble()) {
                                    System.out.println("Nominal tidak valid!\nMasukkan ulang\t:");
                                    scan.nextLine();
                                }
                                double nominalSetor = scan.nextDouble(); scan.nextLine();
                                Transaksi.setorTunai(f0_Username, nominalSetor, new Date());
                                break;
                            case 2:
                                System.out.print("Masukkan nominal\t: ");
                                while (!scan.hasNextDouble()) {
                                    System.out.println("Nominal tidak valid!\nMasukkan ulang\t:");
                                    scan.nextLine();
                                }
                                double nominalTarik = scan.nextDouble(); scan.nextLine();
                                Transaksi.tarikTunai(f0_Username, nominalTarik, new Date());
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
                                break;
                            case 3:
                                // System.out.println("Tes3");
                                Transaksi.transactionLog(f0_Username);
                                break;
                            case 4:
                                System.out.println("\n----Cek Saldo----");
                                Transaksi.cekSaldo(f0_Username);
                                break;
                            case 5:
                                System.out.println("\n----Informasi Akun----");
					            for (int i = 0; i <= 2; i++ ) {
					            	System.out.println(fields[i] +"\t: "+ info[i]);
            					}
                                break;
                            case 6:
                                // Method menu
                                System.exit(0);
                            default:
                                System.out.println("Input tidak valid!");
                                break;
                        }
                    }
                } /* else if (!info[3].equals(f0_Username) && !info[4].equals(f1_Password)) {
                    System.out.println("----Username dan Password tidak valid----");
                } */
            }
		}
		System.out.println("-------------------");
        reader1.close();
        reader2.close();
    }
}
