package BankApp;

import java.text.*;
import java.net.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class BukaRekening extends Bank {
    public static void bukaRekening() throws IOException, FileNotFoundException {
	// MEMBACA FILE customer.txt
	File file1 = new File(System.getProperty("user.dir")+"/customer.txt");// MENGAMBIL FILE
	BufferedReader reader1 = new BufferedReader(new FileReader(file1)); // MEMBACA FILE
	OutputStreamWriter writer1 = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/customer.txt", true), "UTF-8");
	BufferedWriter bufferedWriter1 = new BufferedWriter(writer1); // GET A WRITER FOR A SPECIFIC FILE

	// MEMBACA FILE saldo.txt
	File file2 = new File(System.getProperty("user.dir")+"/saldo.txt");
	BufferedReader reader2 = new BufferedReader(new FileReader(file2));
	OutputStreamWriter writer2 = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/saldo.txt", true), "UTF-8");
	BufferedWriter bufferedWriter2 = new BufferedWriter(writer2); // GET A WRITER FOR A SPECIFIC FILE

	// MEMBACA FILE transactionLog.txt
	File file3 = new File(System.getProperty("user.dir")+"/transactionLog.txt");
	BufferedReader reader3 = new BufferedReader(new FileReader(file3));
	OutputStreamWriter writer3 = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/transactionLog.txt", true), "UTF-8");
	BufferedWriter bufferedWriter3 = new BufferedWriter(writer3); // GET A WRITER FOR A SPECIFIC FILE
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	Scanner scan = new Scanner(System.in);
	boolean duplicate;
	// INISIALISASI VARIABEL
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

	System.out.println("\n---- Daftar sebagai Nasabah ----");
	// INPUT FIELD_0 : NAMA
        System.out.print("- Nama Lengkap\t: ");
        f0_Nama = scan.nextLine();

	// INPUT FIELD_1 : ALAMAT
        System.out.print("- Alamat Rumah\t: ");
        f1_Alamat = scan.nextLine();

	// INPUT FIELD_2 : NOMOR PONSEL
        do {
		duplicate = false;
		System.out.print("- Nomor Ponsel\t: ");
		f2_MobilePhone = scan.next(); scan.nextLine();
		while ((currentLine1 = reader1.readLine()) != null) {
			String[] info = currentLine1.split(";"); // Mendapatkan field data dari file customer.txt
			if (info[2].equals(f2_MobilePhone)) { // Cek duplikasi username dari file customer.txt
				duplicate = true;
				System.out.println("  Nomor Ponsel telah digunakan!");
			}
		}
		reader1 = new BufferedReader(new FileReader(file1));
	} while (duplicate == true);

	// INPUT FIELD_3 : USERNAME
        do {
		duplicate = false;
    		System.out.print("- Username\t: ");
    		f3_Username = scan.next();
		while ((currentLine1 = reader1.readLine()) != null) {
			String[] info = currentLine1.split(";"); // Mendapatkan field data dari file customer.txt
			if (info[3].equals(f3_Username)) { // Cek kondisi jika array USERNAME pada file customer.txt == input dari user
				duplicate = true;
				System.out.println("  Username telah digunakan!");
			}
		}
		reader1 = new BufferedReader(new FileReader(file1));
	} while (duplicate == true);

	// INPUT FIELD_4 : PASSWORD
	System.out.print("- Kriteria Password\n"+"  a. Min 8 karakter\n"+"  b. Huruf besar dan kecil\n"+"  c. Karakter/simbol dan angka\n");
	System.out.print("  > Password\t: ");
        f4_Password = scan.next(); scan.nextLine();
        while (!f4_Password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) { // Cek kondisi input password
            System.out.print("  Password tidak memenuhi kriteria!\n  > Password\t:");
            f4_Password = scan.next();
        }

	// INPUT FIELD_5 : DEPOSIT AWAL
	System.out.print("- Deposit awal\t: ");
        while (!scan.hasNextDouble()) { // Cek kondisi jika input bukan bertipe double
            	System.out.print("  Nominal deposit tidak valid!\n  Deposit awal \t: ");
		scan.nextLine();
	}
	f5_Deposit = scan.nextDouble(); scan.nextLine();

	// CEK KONDISI JIKA ADA FIELD YANG KOSONG MAKA TIDAK VALID
	if (f0_Nama == "" || f1_Alamat == "" || f2_MobilePhone == "" || f3_Username == "" || f4_Password == "" || f5_Deposit == -1) {//i check that all variables have a valid attribute assigned
		System.out.println("Input tidak valid! Gagal melakukan pendaftaran.\nSilakan daftar ulang, terima kasih.");
	} else { // AKSI JIKA SEMUA FIELD TERISI
		// MENAMBAHKAN DATA PENGGUNA BARU KE FILE customer.txt
		writeToSaveUserInput = f0_Nama + ";" + f1_Alamat + ";" + f2_MobilePhone + ";" + f3_Username + ";" + f4_Password;
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file1, true))); // with these code I add a line at the bottom of the file
		out1.println(writeToSaveUserInput);

		// MEMPERBARUI SALDO KE FILE saldo.txt
		writeToAddDeposit = f3_Username + ";" + f5_Deposit;
		PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(file2, true))); // with these code I add a line at the bottom of the file
		out2.println(writeToAddDeposit);

		// MENAMBAHKAN LOG DEPOSIT KE FILE transactionLog.txt
		writeToTransactionLog = f3_Username + ";" + "Initial deposit - " + currencyFormat.format(f5_Deposit) + ";" + new Date();
		PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter(file3, true))); // with these code I add a line at the bottom of the file
		out3.println(writeToTransactionLog);

		// SUCCESS
		System.out.println("BERHASIL TERDAFTAR.");
		// Close
		out1.close();
		out2.close();
		out3.close();
	}
	writer1.close();
	writer2.close();
	reader1.close();
	writer3.close();
	reader2.close();
        reader3.close();
    }
}
