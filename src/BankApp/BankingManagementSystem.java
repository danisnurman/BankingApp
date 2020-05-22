package BankApp;

import BankApp.BukaRekening;
import BankApp.Login;
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

class Bank {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int pilihan;

		while(true) {
			System.out.println("\n-------------------");
			System.out.println("BANK     OF     PTI");
			System.out.println("-------------------\n");
			System.out.println("1. Daftar akun.");
			System.out.println("2. Masuk.");
			System.out.println("3. Keluar.");
			System.out.print("\nPilih menu\t: ");
			pilihan = scan.nextInt(); scan.nextLine();
			switch (pilihan) {
				case 1:
					BukaRekening.bukaRekening();
					break;
				case 2:
					Login.login();
					break;
				case 3:
					System.out.println("\nTerima kasih telah menggunakan aplikasi PTI Mobile Banking."); 
					System.exit(1);
					break;
				default:
					System.out.println("Input tidak valid!");
			}
		}
	}
}