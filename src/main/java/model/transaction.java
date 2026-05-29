package model;

import java.sql.Timestamp;

public class transaction {

    private int transactionId;
    private int accountId;
    private String transactionType;
    private int amount;
    private int balanceAfter;
    private Timestamp transactionDate;
    private int relatedAccountId;
    
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(int balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
    public int getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(int relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }
    
}