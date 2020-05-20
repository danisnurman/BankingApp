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

public class BukaRekening extends Bank {
    public static void bukaRekening() throws IOException, FileNotFoundException {
        File file1 = new File(System.getProperty("user.dir")+"/customer.txt");// we get the file
		BufferedReader reader1 = new BufferedReader(new FileReader(file1)); // we get reader for the file
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/customer.txt", true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);//this is a way to get a writer for th specific file
		Scanner scan = new Scanner(System.in);
		boolean duplicate, valid;
		String currentLine1;
		String f1_Nama = "";//I initialize the variablesto avoid errors
        String f2_Alamat = "";
        String f3_MobilePhone = "";
		String f4_Username = "";
		String f5_Password = "";
        double f6_Deposit = -1;
        String str;
        
        System.out.print("Masukkan nama\t\t: ");
        f1_Nama = scan.nextLine();

        System.out.print("Masukkan alamat rumah\t: ");
        f2_Alamat = scan.nextLine();

        do {
			duplicate = false;
            System.out.print("Masukkan nomor ponsel\t: ");
            f3_MobilePhone = scan.next();
			while ((currentLine1 = reader1.readLine()) != null) { //check for duplicate
				String[] words1 = currentLine1.split(",");
				if (words1[2].equals(f3_MobilePhone)) {
					duplicate = true;
					System.out.println("Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		} while (duplicate == true);

        do {
			duplicate = false;
            System.out.print("Atur username\t\t: ");
            f4_Username = scan.next();
			while ((currentLine1 = reader1.readLine()) != null) { //check for duplicate
				String[] words1 = currentLine1.split(",");
				if (words1[3].equals(f4_Username)) {
					duplicate = true;
					System.out.println("Username must be unique.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		} while (duplicate == true);

        System.out.print("Atur password (minimal 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
        f5_Password = scan.next(); scan.nextLine();
        while (!f5_Password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
            System.out.println("Password tidak memenuhi kriteria.\nAtur ulang :");
            f5_Password = scan.next();
        }

        System.out.print("Deposit awal\t: ");
        while (!scan.hasNextDouble()) {
            System.out.println("Nominal deposit tidak valid!\nMasukkan ulang\t:");
            scan.nextLine();
        }
        f6_Deposit = scan.nextDouble();

        if (f1_Nama == "" || f2_Alamat == "" || f3_MobilePhone == "" || f4_Username == "" || f5_Password == "" || f6_Deposit == -1) {//i check that all variables have a valid attribute assigned
			System.out.println("You gave false inputs, adding new account wasn't successful: ");
		}
		else { //if everything is correct i build a string
			str = f1_Nama + "," + f2_Alamat + "," + f3_MobilePhone + "," + f4_Username + "," + f5_Password + "," + String.valueOf(f6_Deposit);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));//with these code I add a line at the bottom of the file
			out.println(str);
			out.close();
		}
		//input.close();
		//writer1.close();
		writer.close(); 
        reader1.close(); 
        
        /* nominal = scan.nextDouble(); scan.nextLine();
        customer = new Customer(namaPengguna, password, name, alamatRumah, notelp, nominal, new Date());
        bank.customerMap.put(namaPengguna, customer); */
    }
}