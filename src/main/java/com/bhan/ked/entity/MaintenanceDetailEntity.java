package com.bhan.ked.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenance_detail")
public class MaintenanceDetailEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;


    @Column(name = "paid_year")
    private int paidYear;


    @Column(name = "paid_month")
    private int paidMonth;


    @Column(name = "amount")
    private long amount;


    @Column(name = "status")
    private String status;

    @Column(name = "receipt")
    private byte[] receipt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaidYear() {
        return paidYear;
    }

    public void setPaidYear(int paidYear) {
        this.paidYear = paidYear;
    }

    public int getPaidMonth() {
        return paidMonth;
    }

    public void setPaidMonth(int paidMonth) {
        this.paidMonth = paidMonth;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }
}
