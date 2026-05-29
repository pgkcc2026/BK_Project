package service;

import dao.transactionDAO;
import model.transaction;


//DAOクラスをインポート
//→ DB(accountsテーブル)操作を行うために使用
import dao.AccountDAO;

//Accountモデルクラスをインポート
//→ 口座情報を保持するオブジェクト
import model.Account;

import dao.MoneyTransferDAO;

import model.MoneyTransfer;

//口座関連の業務処理を行うServiceクラス
public class AccountService {

 private transactionDAO transactionDao = new transactionDAO();	
 // AccountDAOのインスタンス生成
 // → DBアクセス用
 private AccountDAO dao = new AccountDAO();

 private MoneyTransferDAO moneyTransferDao = new MoneyTransferDAO();

 
 // 口座開設処理メソッド
 // userId : ユーザーID
 // accountNumber : 口座番号
 public boolean createAccount(int userId, String accountNumber) {

     // 既に口座を持っているか確認
     // userIdでaccountsテーブル検索
     Account existingAccount = dao.findByUserId(userId);

     // 既に口座が存在する場合
     // → 新規口座作成不可
     if (existingAccount != null) {
         return false;
     }

     // 新しい口座情報を保存するための
     // Accountオブジェクト生成
     Account account = new Account();

     // ユーザーID設定
     account.setUserId(userId);

     // 口座番号設定
     account.setAccountNumber(accountNumber);

     // 初期残高を0円に設定
     account.setBalance(0);

     // DB(accountsテーブル)へ口座情報登録
     // 成功:true / 失敗:false
     return dao.insertAccount(account);
 }
 
 
 public boolean deposit(int userId, int amount) {

	    if (amount <= 0) {
	        return false;
	    }

	    Account account = dao.findByUserId(userId);

	    if (account == null) {
	        return false;
	    }

	    int newBalance = account.getBalance() + amount;

	    boolean updateResult = dao.updateBalance(account.getAccountId(), newBalance);

	    if (updateResult) {
	        transaction transaction = new transaction();
	        transaction.setAccountId(account.getAccountId());
	        transaction.setTransactionType("入金");
	        transaction.setAmount(amount);
	        transaction.setBalanceAfter(newBalance);

	        transactionDao.insertTransaction(transaction);
	    }

	    return updateResult;
	}
 
 public Account getAccountByUserId(int userId) {
	    return dao.findByUserId(userId);
	}
 
 
 public boolean withdraw(int userId, int amount) {

	    if (amount <= 0) {
	        return false;
	    }

	    Account account = dao.findByUserId(userId);

	    if (account == null) {
	        return false;
	    }

	    if (account.getBalance() < amount) {
	        return false;
	    }

	    int newBalance = account.getBalance() - amount;

	    boolean updateResult = dao.updateBalance(account.getAccountId(), newBalance);

	    if (updateResult) {
	        transaction transaction = new transaction();
	        transaction.setAccountId(account.getAccountId());
	        transaction.setTransactionType("出金");
	        transaction.setAmount(amount);
	        transaction.setBalanceAfter(newBalance);

	        transactionDao.insertTransaction(transaction);
	    }

	    return updateResult;
	}

 
 public boolean transfer(int fromUserId, int toAccountId, int amount) {

	    if (amount <= 0) {
	        return false;
	    }

	    Account fromAccount = dao.findByUserId(fromUserId);
	    Account toAccount = dao.findByAccountId(toAccountId);

	    if (fromAccount == null || toAccount == null) {
	        return false;
	    }

	    if (fromAccount.getAccountId() == toAccount.getAccountId()) {
	        return false;
	    }

	    if (fromAccount.getBalance() < amount) {
	        return false;
	    }

	    int fromNewBalance = fromAccount.getBalance() - amount;
	    int toNewBalance = toAccount.getBalance() + amount;

	    boolean fromUpdateResult =
	            dao.updateBalance(fromAccount.getAccountId(), fromNewBalance);

	    boolean toUpdateResult =
	            dao.updateBalance(toAccount.getAccountId(), toNewBalance);

	    if (fromUpdateResult && toUpdateResult) {

	        MoneyTransfer moneyTransfer = new MoneyTransfer();
	        moneyTransfer.setFromAccountId(fromAccount.getAccountId());
	        moneyTransfer.setToAccountId(toAccount.getAccountId());
	        moneyTransfer.setAmount(amount);
	       
	        moneyTransferDao.insertTransfer(moneyTransfer);
	        
	        // transactions テーブル：送金元側の履歴
	        transaction sendTransaction = new transaction();
	        sendTransaction.setAccountId(fromAccount.getAccountId());
	        sendTransaction.setTransactionType("送金");
	        sendTransaction.setAmount(amount);
	        sendTransaction.setBalanceAfter(fromNewBalance);
	        sendTransaction.setRelatedAccountId(toAccount.getAccountId());
	        transactionDao.insertTransaction(sendTransaction);

	        // transactions テーブル：送金先側の履歴
	        transaction receiveTransaction = new transaction();
	        receiveTransaction.setAccountId(toAccount.getAccountId());
	        receiveTransaction.setTransactionType("受取");
	        receiveTransaction.setAmount(amount);
	        receiveTransaction.setBalanceAfter(toNewBalance);
	        receiveTransaction.setRelatedAccountId(fromAccount.getAccountId());
	        transactionDao.insertTransaction(receiveTransaction);
	        
	        return true;

	    }

	    return false;
	}
 
 
 
}