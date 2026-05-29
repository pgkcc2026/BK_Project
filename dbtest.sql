DROP DATABASE IF EXISTS db01;
CREATE DATABASE db01;
/*ｄｂを起動 */
USE db01;
/*ユーザーテーブル */
CREATE TABLE users (
    userID VARCHAR(20) PRIMARY KEY,
    userPassword VARCHAR(20),
    userName VARCHAR(20),
    userEmail VARCHAR(50)
);

/*会員登録 */
INSERT INTO users VALUES ('kim', '1234', 'kimC', 'kim@test.com');
/*ユーザー検索 */
SELECT * FROM users;
/*ユーザー検索　ID、Pｗ*/
SELECT * FROM users WHERE userID='kim' AND userPassword='1234';

DROP USER IF EXISTS 'test'@'localhost';
CREATE USER 'test'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON db01.* TO 'test'@'localhost';
FLUSH PRIVILEGES;


CREATE TABLE home (
    homeID INT AUTO_INCREMENT,
    homeTitle VARCHAR(100) NOT NULL,
    userID VARCHAR(20) NOT NULL,
    homeDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    homeContent TEXT,
    homebbsAvailable INT DEFAULT 1,
    PRIMARY KEY (homeID),
    FOREIGN KEY (userID) REFERENCES users(userID)
);

DROP TABLE IF EXISTS home;
SELECT * FROM home;
