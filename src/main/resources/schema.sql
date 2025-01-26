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
CREATE INDEX idx_owner ON transactions(owner);
CREATE INDEX idx_amount ON transactions(amount);
CREATE INDEX idx_type ON transactions(type);
CREATE INDEX idx_category ON transactions(category);
CREATE INDEX idx_remark ON transactions(remark);
