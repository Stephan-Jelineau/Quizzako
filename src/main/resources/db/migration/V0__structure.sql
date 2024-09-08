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
    open_date DATETIME NOT NULL,
    close_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE quiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id INT,
    category_id INT,
    creation_date DATETIME NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    quiz_id INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

CREATE TABLE answer_map (
    id INT AUTO_INCREMENT PRIMARY KEY,
    answer_key VARCHAR(255) NOT NULL,
    answer_value VARCHAR(255) NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

CREATE TABLE cohort(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id INT,
    creation_date DATETIME NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE cohort_user (
    cohort_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (cohort_id, user_id),
    FOREIGN KEY (cohort_id) REFERENCES cohort(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE score (
    id INT NOT NULL AUTO_INCREMENT,
    quiz_id INT NOT NULL,
    user_id INT NOT NULL,
    score VARCHAR(50) NOT NULL,
    submission_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES quiz (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT unique_quiz_user UNIQUE (quiz_id, user_id)
);

CREATE TABLE cohort_quiz (
    cohort_id INT NOT NULL,
    quiz_id INT NOT NULL,
    PRIMARY KEY (cohort_id, quiz_id),
    FOREIGN KEY (cohort_id) REFERENCES cohort(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE ON UPDATE CASCADE
);