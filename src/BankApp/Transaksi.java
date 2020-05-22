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

public class Transaksi extends Bank {
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	
	public static void cekSaldo(String param_f0_Username) throws IOException, FileNotFoundException {
		File file = new File(System.getProperty("user.dir")+"/saldo.txt");// we get the file
		BufferedReader reader = new BufferedReader(new FileReader(file)); // we get reader for the file

		String currentLine;
		boolean first = false;
		String[] fields = new String[0];

		while((currentLine = reader.readLine()) != null) { //for each line in txt file
			if (!first) { // if it is the first line the line is the fields and we save them into an array
				fields = currentLine.split(";");
				first = true;
			} else { //for the rest lines we print the information
				String[] infoSaldo = currentLine.split(";");
				if (infoSaldo[0].equals(param_f0_Username)) {
					double saldo = Double.parseDouble(infoSaldo[1]);
					System.out.println("Saldo\t: " + currencyFormat.format(saldo));
				}
			}
		}
	}
	
	public static void deleteField(String line) throws IOException, FileNotFoundException {
		File file1 = new File(System.getProperty("user.dir")+"/saldo.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));	
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		File file2 = new File(System.getProperty("user.dir")+"/saldoTemp.txt");//i create a temporary file to save the changes
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));

		while ((currentLine = reader1.readLine()) != null) {
			if( !first) {
				fields = currentLine.split(";");
				writer.write(currentLine + "\n");
				first = true;
			} else if (!currentLine.equals(line)){//if the current line in the reader is not the one we want to delete we write it to the temp file	
				writer.write(currentLine + "\n");
			}
		}
		reader1.close();
		writer.close();
		file1.delete(); // we delete the original file
		file2.renameTo(file1); // we rename the temporary file to the original file's name
	}

	public static void setorTunai(String param_f0_Username, double paramNominal, Date paramDate) throws IOException, FileNotFoundException {
		File file1 = new File(System.getProperty("user.dir")+"/saldo.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file1));
		File file2 = new File(System.getProperty("user.dir")+"/transactionLog.txt");
		// BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		// OutputStreamWriter writer = new OutputStreamWriter(
		// 	new FileOutputStream(System.getProperty("user.dir")+"/saldo.txt", true), "UTF-8");
		// BufferedWriter bufferedWriter = new BufferedWriter(writer); // this is a way to get a writer for the specific file

		String currentLine, writeToUpdateDeposit, writeToTransactionLog;
		boolean first = false;
		String[] fields = new String[0];

		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(";");
				first = true;
			} else {
				String[] info = currentLine.split(";");
				if (info[0].equals(param_f0_Username)) {
					double saldo = Double.parseDouble(info[1]);
					saldo += paramNominal;
					deleteField(currentLine);

					writeToUpdateDeposit = param_f0_Username + ";" + saldo;
					PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file1, true))); // with these code I add a line at the bottom of the file
					out1.println(writeToUpdateDeposit);

					// paramNominal = Double.parseDouble(info[1]);
					// System.out.println("Saldo\t: " + currencyFormat.format(paramNominal));
					writeToTransactionLog = param_f0_Username + ";" + currencyFormat.format(paramNominal) + " credited to your account. Balance - " + currencyFormat.format(saldo) + ";" + new Date();
					PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(file2, true))); // with these code I add a line at the bottom of the file
					out2.println(writeToTransactionLog);

					out1.close();
					out2.close();
					break;
				}
			}
		}
		// writer.close();
		// reader1.close();
		// reader2.close();
		reader.close();
	}

	public static void tarikTunai(String param_f0_Username, double paramNominal, Date paramDate) throws IOException, FileNotFoundException {
		File file1 = new File(System.getProperty("user.dir")+"/saldo.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file1));
		File file2 = new File(System.getProperty("user.dir")+"/transactionLog.txt");

		String currentLine, writeToUpdateDeposit, writeToTransactionLog;
		boolean first = false;
		String[] fields = new String[0];

		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(";");
				first = true;
			} else {
				String[] info = currentLine.split(";");
				if (info[0].equals(param_f0_Username)) {
					double saldo = Double.parseDouble(info[1]);
					if (paramNominal > (saldo-20000)) {
						System.out.println("Insufficient balance.");
						return;
					}
					saldo -= paramNominal;
					deleteField(currentLine);

					writeToUpdateDeposit = param_f0_Username + ";" + saldo;
					PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file1, true))); // with these code I add a line at the bottom of the file
					out1.println(writeToUpdateDeposit);

					writeToTransactionLog = param_f0_Username + ";" + currencyFormat.format(paramNominal) + " debited from your account. Balance - " + currencyFormat.format(saldo) + ";" + new Date();
					PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(file2, true))); // with these code I add a line at the bottom of the file
					out2.println(writeToTransactionLog);

					out1.close();
					out2.close();
					break;
				}
			}
		}
		// writer.close();
		reader.close();
		// reader2.close();
	}

	public static void transactionLog(String param_f0_Username) throws IOException, FileNotFoundException {
		File file = new File(System.getProperty("user.dir")+"/transactionLog.txt");// we get the file
		BufferedReader reader = new BufferedReader(new FileReader(file)); // we get reader for the file
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];

		while((currentLine = reader.readLine()) != null) { //for each line in txt file
			if (!first) { // if it is the first line the line is the fields and we save them into an array
				fields = currentLine.split(";");
				first = true;
			} else { // for the rest lines we print the information
				String[] info = currentLine.split(";");
				if (info[0].equals(param_f0_Username)) {
					System.out.println(info[1] + " on " + info[2]);
				}
			}
		}
	}
}
