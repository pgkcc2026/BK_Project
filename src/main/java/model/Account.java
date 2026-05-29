package model;

//Accountクラス
//→ 口座情報を管理するModelクラス（JavaBeans）
public class Account {
	
 // 口座ID（accountsテーブルの主キー）
 private int accountId;

 // ユーザーID
 // usersテーブルと紐づく外部キー
 private int userId;

 // 口座番号
 private String accountNumber;

 // 口座残高
 private int balance;

 // accountId取得
 public int getAccountId() {
     return accountId;
 }

 // accountId設定
 public void setAccountId(int accountId) {
     this.accountId = accountId;
 }

 // userId取得
 public int getUserId() {
     return userId;
 }

 // userId設定
 public void setUserId(int userId) {
     this.userId = userId;
 }

 // accountNumber取得
 public String getAccountNumber() {
     return accountNumber;
 }

 // accountNumber設定
 public void setAccountNumber(String accountNumber) {
     this.accountNumber = accountNumber;
 }

 // balance取得
 public int getBalance() {
     return balance;
 }

 // balance設定
 public void setBalance(int balance) {
     this.balance = balance;
	    }
	

}
