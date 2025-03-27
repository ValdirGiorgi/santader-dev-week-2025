DROP TABLE IF EXISTS tb_user_features;
DROP TABLE IF EXISTS tb_user_news;
DROP TABLE IF EXISTS tb_feature;
DROP TABLE IF EXISTS tb_news;
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_account;
DROP TABLE IF EXISTS tb_card;

CREATE TABLE tb_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255),
    agency VARCHAR(255),
    balance DOUBLE,
    limit DOUBLEt DOUBLE
);

CREATE TABLE tb_card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255),
    card_limit DOUBLE
);

CREATE TABLE tb_feature (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    icon VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE tb_news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    icon VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE tb_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    account_id BIGINT,
    card_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES tb_account(id),
    FOREIGN KEY (card_id) REFERENCES tb_card(id)
);

CREATE TABLE tb_user_features (
    user_id BIGINT,
    features_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (features_id) REFERENCES tb_feature(id)
);

CREATE TABLE tb_user_news (
    user_id BIGINT,
    news_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (news_id) REFERENCES tb_news(id)
);
