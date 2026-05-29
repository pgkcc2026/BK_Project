package dao;

// DB接続用クラスをインポート
import java.sql.Connection;
// SQL実行用クラスをインポート
import java.sql.PreparedStatement;
// SELECT結果取得用クラスをインポート
import java.sql.ResultSet;
// Accountモデルクラスをインポート
import model.Account;
// accountsテーブル操作を行うDAOクラス
public class AccountDAO {
	
    // 口座登録処理
    // Accountオブジェクトの情報をDBへ登録
    public boolean insertAccount(Account account) {

        // accountsテーブルへINSERTするSQL
        String sql = "INSERT INTO accounts(user_id, account_number, balance) VALUES (?, ?, ?)";

        try (
            // DB接続取得
            Connection conn = DBUtil.getConnection();

            // SQL準備
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            // 1番目の ? に user_id を設定
            ps.setInt(1, account.getUserId());

            // 2番目の ? に account_number を設定
            ps.setString(2, account.getAccountNumber());

            // 3番目の ? に balance を設定
            ps.setInt(3, account.getBalance());

            // SQL実行
            // executeUpdate() > 0 → 1件以上登録成功
            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            // エラー内容表示
            e.printStackTrace();
        }

        // 登録失敗
        return false;
    }

    // userIdから口座情報を取得する処理
    public Account findByUserId(int userId) {

        // user_idで口座検索SQL
        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (
            // DB接続取得
            Connection conn = DBUtil.getConnection();

            // SQL準備
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            // ? に userId設定
            ps.setInt(1, userId);

            // SELECT実行
            ResultSet rs = ps.executeQuery();

            // データが存在する場合
            if (rs.next()) {

                // Accountオブジェクト生成
                Account account = new Account();

                // account_id取得
                account.setAccountId(rs.getInt("account_id"));

                // user_id取得
                account.setUserId(rs.getInt("user_id"));

                // account_number取得
                account.setAccountNumber(rs.getString("account_number"));

                // balance取得
                account.setBalance(rs.getInt("balance"));

                // 取得した口座情報返却
                return account;
            }

        } catch (Exception e) {

            // エラー内容表示
            e.printStackTrace();
        }

        // データが存在しない場合
        return null;
    }
    
    public Account findByAccountId(int accountId) {

        String sql = "SELECT * FROM accounts WHERE account_id = ?";

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setUserId(rs.getInt("user_id"));
                account.setAccountNumber(rs.getString("account_number"));
                account.setBalance(rs.getInt("balance"));
                return account;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(int accountId, int balance) {

        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, balance);
            ps.setInt(2, accountId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    

}