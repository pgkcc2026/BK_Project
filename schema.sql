CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_number VARCHAR(50) NOT NULL UNIQUE,
    balance INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_accounts_users
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount INT NOT NULL,
    balance_after INT NOT NULL,
    related_account_id INT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transactions_accounts
        FOREIGN KEY (account_id) REFERENCES accounts(account_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS money_transfer (
    transfer_id INT AUTO_INCREMENT PRIMARY KEY,
    from_account_id INT NOT NULL,
    to_account_id INT NOT NULL,
    amount INT NOT NULL,
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transfer_from
        FOREIGN KEY (from_account_id) REFERENCES accounts(account_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_transfer_to
        FOREIGN KEY (to_account_id) REFERENCES accounts(account_id)
        ON DELETE CASCADE
);