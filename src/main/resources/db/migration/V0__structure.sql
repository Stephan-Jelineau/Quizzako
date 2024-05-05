CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(500) NOT NULL,
    role VARCHAR(15) NOT NULL
);

CREATE TABLE request_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    role_resquested VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
    open_date VARCHAR(50) NOT NULL,
    close_date VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);