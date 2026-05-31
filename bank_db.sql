-- データベースを作成
CREATE DATABASE bank_db;
-- 使用するデータベースを選択
USE bank_db;
　
-- ユーザー情報を管理するテーブルを作成する
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY, -- ユーザーID（自動採番・主キー）
    name VARCHAR(50) NOT NULL,				 -- ユーザー名
    email VARCHAR(100) NOT NULL UNIQUE,			-- メールアドレス（重複不可）
    password VARCHAR(100) NOT NULL				 -- パスワード
);
SELECT * FROM users;
-- 現在のusersテーブルには userID / userPassword というカラムは存在しないため、
-- 実際には email / password など、作成したカラム名に合わせる
SELECT * FROM users WHERE userID='' AND userPassword='kim1234';

-- 口座情報を管理するテーブルを作成する
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,	-- 口座ID（自動採番・主キー）
    user_id INT NOT NULL,						-- usersテーブルのユーザーID
    account_number VARCHAR(20) NOT NULL UNIQUE,	-- 口座番号（重複不可）
    balance INT NOT NULL DEFAULT 0,				-- 残高（初期値0）
    FOREIGN KEY (user_id) REFERENCES users(user_id)	-- usersテーブルとの外部キー
);

-- accountsテーブルの全データを確認する
SELECT * FROM accounts;

-- 取引履歴テーブルを作成する（以下の内容をtransactionsに変更）
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY, -- 取引ID（自動採番・主キー）
    account_id INT NOT NULL, 						-- 対象口座ID
    type VARCHAR(20) NOT NULL,						-- 取引種別（入金・出金など）
    amount INT NOT NULL,							-- 取引金額
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 取引日時
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) -- accountsテーブルとの外部キー
);

-- 取引履歴テーブルを作成する
-- 入金・出金後の残高も保存できるように balance_after を追加している
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY, -- 取引ID（自動採番・主キー）
    account_id INT NOT NULL,                       -- 対象口座ID
    transaction_type VARCHAR(20) NOT NULL,         -- 取引種別（入金・出金など）
    amount INT NOT NULL,                           -- 取引金額
    balance_after INT NOT NULL,                    -- 取引後の残高
    related_account_id INT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 取引日時
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) -- accountsテーブルとの外部キー
);

DROP TABLE transactions;
SELECT * FROM transactions;

-- 送金履歴を管理するテーブルを作成する
CREATE TABLE IF NOT EXISTS money_transfer (
    transfer_id INT AUTO_INCREMENT PRIMARY KEY, -- 送金ID（自動採番・主キー）
    from_account_id INT NOT NULL,               -- 送金元口座ID
    to_account_id INT NOT NULL,                 -- 送金先口座ID
    amount INT NOT NULL,                        -- 送金金額
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 送金日時
    FOREIGN KEY (from_account_id) REFERENCES accounts(account_id),	 -- 送金元口座IDはaccountsテーブルのaccount_idを参照する
    FOREIGN KEY (to_account_id) REFERENCES accounts(account_id)		-- 送金先口座IDもaccountsテーブルのaccount_idを参照する
);


SELECT * FROM money_transfer;

-- 本SQLでは、銀行システム用のデータベースを作成し、ユーザー情報、口座情報、取引履歴、送金履歴を管理するための各テーブルを作成しています。
-- usersテーブルでは利用者情報を管理し、accountsテーブルではユーザーごとの口座番号と残高を管理します。
-- transactionsテーブルでは入金・出金などの取引履歴を保存し、money_transferテーブルでは送金元口座・送金先口座・金額・送金日時を保存します。
-- また、各テーブルは外部キーを利用して関連付けを行い、データの整合性を保つ構成にしています。

