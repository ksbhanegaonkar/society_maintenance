package com.bhan.ked.model;

import java.time.LocalDate;

public class NoBrokerHoodStatementRow {
    private int serialNo;
    private String accountName;
    private String billHead;
    private String tower;
    private String flat;
    private String email;
    private String voucherNo;
    private String referenceNo;
    private String chequeNo;
    private String settlementId;
    private String settlementIdLast8;
    private LocalDate date;
    private String receipts;
    private String payments;
    private String voucherType;
    private String narration;
    private String bankName;

    public NoBrokerHoodStatementRow(int serialNo, String accountName, String billHead, String tower, String flat, String email, String voucherNo, String referenceNo, String chequeNo, String settlementId, String settlementIdLast8, LocalDate date, String receipts, String payments, String voucherType, String narration, String bankName) {
        this.serialNo = serialNo;
        this.accountName = accountName;
        this.billHead = billHead;
        this.tower = tower;
        this.flat = flat;
        this.email = email;
        this.voucherNo = voucherNo;
        this.referenceNo = referenceNo;
        this.chequeNo = chequeNo;
        this.settlementId = settlementId;
        this.settlementIdLast8 = settlementIdLast8;
        this.date = date;
        this.receipts = receipts;
        this.payments = payments;
        this.voucherType = voucherType;
        this.narration = narration;
        this.bankName = bankName;
    }

    public NoBrokerHoodStatementRow() {
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBillHead() {
        return billHead;
    }

    public void setBillHead(String billHead) {
        this.billHead = billHead;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getSettlementIdLast8() {
        return settlementIdLast8;
    }

    public void setSettlementIdLast8(String settlementIdLast8) {
        this.settlementIdLast8 = settlementIdLast8;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReceipts() {
        return receipts;
    }

    public void setReceipts(String receipts) {
        this.receipts = receipts;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return serialNo + " | " + accountName + " | " + date + " | " + receipts + " | " + narration;
    }
}

