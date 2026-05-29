package service;

import java.util.List;

import dao.transactionDAO;
import model.transaction;

public class transactionService {
	private transactionDAO transactionDao = new transactionDAO();

    public List<transaction> getTransactionList(int accountId) {
        return transactionDao.findByAccountId(accountId);
    }
}