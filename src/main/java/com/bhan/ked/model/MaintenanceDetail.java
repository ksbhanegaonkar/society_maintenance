package com.bhan.ked.model;

import jakarta.persistence.Column;

public class MaintenanceDetail {
    private int userId;
    private int paidYear;
    private int paidMonth;
    private long amount;

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
}
