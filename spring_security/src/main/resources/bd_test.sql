
CREATE TABLE IF NOT EXISTS users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    user_login TEXT UNIQUE NOT NULL,
    user_password TEXT NOT NULL,
    user_role TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS products (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    product_name TEXT UNIQUE NOT NULL,
    user_id TEXT NOT NULL,

    CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);