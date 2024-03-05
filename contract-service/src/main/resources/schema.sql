CREATE TABLE IF NOT EXISTS contract(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    num_contract text,
    total_value float,
    customer uuid,
    dat_create timestamp not null,
    dat_update timestamp
);
