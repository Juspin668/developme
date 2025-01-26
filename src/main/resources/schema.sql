-- schema.sql
CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner VARCHAR(50),
    createTime BIGINT,
    fromAccount VARCHAR(50),
    toAccount VARCHAR(50),
    amount BIGINT,
    type VARCHAR(50),
    category VARCHAR(50),
    remark VARCHAR(255),
    updateTime BIGINT
);
