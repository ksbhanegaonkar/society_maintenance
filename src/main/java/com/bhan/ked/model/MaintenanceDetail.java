package com.bhan.ked.model;

public class MaintenanceDetail {
    private int period;
    private int monthSelected;
    private long amount;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getMonthSelected() {
        return monthSelected;
    }

    public void setMonthSelected(int monthSelected) {
        this.monthSelected = monthSelected;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
