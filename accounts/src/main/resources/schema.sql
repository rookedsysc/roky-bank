create schema if not exists roky_bank;

CREATE TABLE IF NOT EXISTS postgresql.customer
(
    customer_id   BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    created_by    VARCHAR(20)  NOT NULL,
    updated_at    TIMESTAMP   DEFAULT NULL,
    updated_by    VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS roky_bank.account
(
    customer_id    BIGINT       NOT NULL,
    account_number BIGSERIAL PRIMARY KEY, -- Application에서 생성
    account_type   VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    created_by     VARCHAR(20)  NOT NULL,
    updated_at     TIMESTAMP   DEFAULT NULL,
    updated_by     VARCHAR(20) DEFAULT NULL
);
