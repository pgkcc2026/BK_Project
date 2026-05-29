package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.MoneyTransfer;

public class MoneyTransferDAO {

public boolean insertTransfer(MoneyTransfer moneyTransfer) {

    String sql = "INSERT INTO money_transfer "
               + "(from_account_id, to_account_id, amount) "
               + "VALUES (?, ?, ?)";

    try (
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
    ) {
        ps.setInt(1, moneyTransfer.getFromAccountId());
        ps.setInt(2, moneyTransfer.getToAccountId());
        ps.setInt(3, moneyTransfer.getAmount());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}