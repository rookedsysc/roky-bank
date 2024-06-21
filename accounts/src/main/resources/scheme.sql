create schema roky_bank;

CREATE SEQUENCE customer_id_seq AS BIGINT;

CREATE TABLE IF NOT EXISTS roky_bank.customer (
  customer_id BIGINT PRIMARY KEY DEFAULT nextval('customer_id_seq'),
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(20) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS roky_bank.account (
  customer_id BIGINT NOT NULL,
  account_number BIGINT NOT NULL PRIMARY KEY, -- Application에서 생성
  account_type VARCHAR(100) NOT NULL,
  branch_address VARCHAR(200) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(20) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(20) DEFAULT NULL
);
