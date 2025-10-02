package com.bhan.ked.model;

import java.time.LocalDate;

public class ReconciledRecord {
    private String flat;
    private String name;
    private double amount;
    private LocalDate bankTransactionDate;
    private LocalDate noBrokerTransactionDate;
    private String latePayment;
    private String settlementIdNoBroker;
    private String bankStatementTransactionId;

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getBankTransactionDate() {
        return bankTransactionDate;
    }

    public void setBankTransactionDate(LocalDate bankTransactionDate) {
        this.bankTransactionDate = bankTransactionDate;
    }

    public LocalDate getNoBrokerTransactionDate() {
        return noBrokerTransactionDate;
    }

    public void setNoBrokerTransactionDate(LocalDate noBrokerTransactionDate) {
        this.noBrokerTransactionDate = noBrokerTransactionDate;
    }

    public String getLatePayment() {
        return latePayment;
    }

    public void setLatePayment(String latePayment) {
        this.latePayment = latePayment;
    }

    public String getSettlementIdNoBroker() {
        return settlementIdNoBroker;
    }

    public void setSettlementIdNoBroker(String settlementIdNoBroker) {
        this.settlementIdNoBroker = settlementIdNoBroker;
    }

    public String getBankStatementTransactionId() {
        return bankStatementTransactionId;
    }

    public void setBankStatementTransactionId(String bankStatementTransactionId) {
        this.bankStatementTransactionId = bankStatementTransactionId;
    }
}
