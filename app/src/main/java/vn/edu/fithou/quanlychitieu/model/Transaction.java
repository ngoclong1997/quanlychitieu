package vn.edu.fithou.quanlychitieu.model;

import java.util.Date;

public class Transaction {

    private double moneyAmount;

    private TransactionGroup group;

    private String note;

    private Date date;

    //Vi thuong hay the ATM
    private int walletType;

    public Transaction(double moneyAmount, TransactionGroup group, String note, Date date, int type) {
        this.moneyAmount = moneyAmount;
        this.group = group;
        this.note = note;
        this.date = date;
        this.walletType = type;
    }

    public Transaction() {
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public TransactionGroup getGroup() {
        return group;
    }

    public void setGroup(TransactionGroup group) {
        this.group = group;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWalletType() {
        return walletType;
    }

    public void setWalletType(int type) {
        this.walletType = type;
    }
}
