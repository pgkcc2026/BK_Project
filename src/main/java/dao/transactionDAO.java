package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.transaction;

public class transactionDAO {

    public boolean insertTransaction(transaction transaction) {

        String sql = "INSERT INTO transactions "
                   + "(account_id, transaction_type, amount, balance_after, related_account_id) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, transaction.getAccountId());
            ps.setString(2, transaction.getTransactionType());
            ps.setInt(3, transaction.getAmount());
            ps.setInt(4, transaction.getBalanceAfter());

            if (transaction.getRelatedAccountId() == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, transaction.getRelatedAccountId());
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<transaction> findByAccountId(int accountId) {

        List<transaction> transactionList = new ArrayList<>();

        String sql = "SELECT transaction_id, account_id, transaction_type, amount, balance_after, related_account_id, transaction_date "
                   + "FROM transactions "
                   + "WHERE account_id = ? "
                   + "ORDER BY transaction_date DESC, transaction_id DESC";

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transaction transaction = new transaction();

                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountId(rs.getInt("account_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getInt("amount"));
                transaction.setBalanceAfter(rs.getInt("balance_after"));
                transaction.setRelatedAccountId(rs.getInt("related_account_id"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

                transactionList.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionList;
    }
}