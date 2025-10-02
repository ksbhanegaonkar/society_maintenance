package com.bhan.ked.model;

import java.time.LocalDate;
import java.util.Date;

public class BankTransaction {
    private LocalDate date;
    private String particulars;
    private String chequeNo;
    private String debit;
    private String credit;
    private String balance;

    public BankTransaction(LocalDate date, String particulars, String chequeNo, String debit, String credit, String balance) {
        this.date = date;
        this.particulars = particulars;
        this.chequeNo = chequeNo;
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }

    public BankTransaction() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return date + " | " + particulars + " | Debit=" + debit + " | Credit=" + credit + " | Bal=" + balance;
    }
}
