package BankApp;

import java.util.*;
import java.text.*;

interface Rekening {
    final double rate = 0.04, limit = 10000, limit1 = 200;
	void deposit(double n, Date d);
	void withdraw(double n, Date d);
}

class Customer implements Rekening {
    String namaPengguna, password, name, alamatRumah, notelp;
	double saldo;
	ArrayList<String> transaksi;
	Customer(String namaPengguna, String password, String name, String alamatRumah, String notelp, double saldo, Date date) {
		this.namaPengguna = namaPengguna;
		this.password = password;
		this.name = name;
		this.alamatRumah = alamatRumah;
		this.notelp = notelp;
		this.saldo = saldo;
		transaksi  =  new ArrayList<String>(5);
		addTransaction(String.format("Initial deposit - " + NumberFormat.getCurrencyInstance().format(saldo)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	void update(Date date) {
		if (saldo >= 10000) {
			saldo += rate*saldo;
		} else {
			saldo -= (int)(saldo/100.0);
		}
		addTransaction(String.format("Account updated. Balance - " + NumberFormat.getCurrencyInstance().format(saldo)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	@Override
	public void deposit(double nominal, Date date) {
		saldo += nominal;
		addTransaction(String.format(NumberFormat.getCurrencyInstance().format(nominal)+" credited to your account. Balance - " +NumberFormat.getCurrencyInstance().format(saldo)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	@Override
	public void withdraw(double nominal, Date date) {
		if (nominal > (saldo-200)) {
			System.out.println("Insufficient balance.");
			return;
		}
		saldo -= nominal;
		addTransaction(String.format(NumberFormat.getCurrencyInstance().format(nominal)+" debited from your account. Balance - " +NumberFormat.getCurrencyInstance().format(saldo)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	private void addTransaction(String message) {
			transaksi.add(0,message);
			if (transaksi.size() > 5) {
				transaksi.remove(5);
				transaksi.trimToSize();
			}
	}
}