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
		BufferedWriter bufferedWriter1 = new BufferedWriter(writer1); // this is a way to get a writer for the specific file

		File file2 = new File(System.getProperty("user.dir")+"/saldo.txt");// we get the file
		BufferedReader reader2 = new BufferedReader(new FileReader(file2)); // we get reader for the file
		OutputStreamWriter writer2 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/saldo.txt", true), "UTF-8");
		BufferedWriter bufferedWriter2 = new BufferedWriter(writer2); // this is a way to get a writer for the specific file

		File file3 = new File(System.getProperty("user.dir")+"/transactionLog.txt");// we get the file
		BufferedReader reader3 = new BufferedReader(new FileReader(file3)); // we get reader for the file
		OutputStreamWriter writer3 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/transactionLog.txt", true), "UTF-8");
		BufferedWriter bufferedWriter3 = new BufferedWriter(writer3); // this is a way to get a writer for the specific file

		Scanner scan = new Scanner(System.in);
		boolean duplicate, valid;
		// I initialize the variables to avoid errors
		String currentLine1,
			f0_Nama = "",
        	f1_Alamat = "",
        	f2_MobilePhone = "",
			f3_Username = "",
			f4_Password = "", 
			writeToSaveUserInput,
			writeToAddDeposit, 
			writeToTransactionLog;
		double f5_Deposit = -1;
        
        System.out.print("Masukkan nama\t\t: ");
        f0_Nama = scan.nextLine();

        System.out.print("Masukkan alamat rumah\t: ");
        f1_Alamat = scan.nextLine();

        do {
			duplicate = false;
            System.out.print("Masukkan nomor ponsel\t: ");
            f2_MobilePhone = scan.next();
			while ((currentLine1 = reader1.readLine()) != null) { //check for duplicate
				String[] words1 = currentLine1.split(",");
				if (words1[2].equals(f2_MobilePhone)) {
					duplicate = true;
					System.out.println("Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		} while (duplicate == true);

        do {
			duplicate = false;
            System.out.print("Atur username\t\t: ");
            f3_Username = scan.next();
			while ((currentLine1 = reader1.readLine()) != null) { //check for duplicate
				String[] words1 = currentLine1.split(",");
				if (words1[3].equals(f3_Username)) {
					duplicate = true;
					System.out.println("Username must be unique.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		} while (duplicate == true);

        System.out.print("Atur password (minimal 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
        f4_Password = scan.next(); scan.nextLine();
        while (!f4_Password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
            System.out.println("Password tidak memenuhi kriteria.\nAtur ulang :");
            f4_Password = scan.next();
        }

        System.out.print("Deposit awal\t: ");
        while (!scan.hasNextDouble()) {
            System.out.println("Nominal deposit tidak valid!\nMasukkan ulang\t:");
            scan.nextLine();
        }
        f5_Deposit = scan.nextDouble();

        if (f0_Nama == "" || f1_Alamat == "" || f2_MobilePhone == "" || f3_Username == "" || f4_Password == "" || f5_Deposit == -1) {//i check that all variables have a valid attribute assigned
			System.out.println("You gave false inputs, adding new account wasn't successful: ");
		} else { // if everything is correct i build a string
			writeToSaveUserInput = f0_Nama + "," + f1_Alamat + "," + f2_MobilePhone + "," + f3_Username + "," + f4_Password;
			PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file1, true))); // with these code I add a line at the bottom of the file
			out1.println(writeToSaveUserInput);

			writeToAddDeposit = f3_Username + "," + f5_Deposit;
			PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(file2, true))); // with these code I add a line at the bottom of the file
			out2.println(writeToAddDeposit);

			writeToTransactionLog = f3_Username + "," + "Initial deposit - IDR " + f5_Deposit + "," + new Date();
			PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter(file3, true))); // with these code I add a line at the bottom of the file
			out3.println(writeToTransactionLog);

			out1.close();
			out2.close();
			out3.close();
        }
        
        //input.close();
		//writer1.close();
		writer1.close(); 
		reader1.close(); 
		writer2.close(); 
		reader2.close();
		writer3.close(); 
        reader3.close();
    }
}