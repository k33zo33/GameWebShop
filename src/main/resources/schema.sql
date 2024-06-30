CREATE TABLE IF NOT EXISTS "user" (
                                      id INT PRIMARY KEY AUTO_INCREMENT,
                                      username VARCHAR(255) NOT NULL,
                                      password VARCHAR(255) NOT NULL,
                                      role VARCHAR(50)
                                    );

CREATE TABLE IF NOT EXISTS category (
                                        id INT PRIMARY KEY  AUTO_INCREMENT,
                                        name VARCHAR(255) NOT NULL,
                                        description TEXT
                                    );

CREATE TABLE IF NOT EXISTS item (
                                    id INT  PRIMARY KEY AUTO_INCREMENT,
                                    name VARCHAR(255) NOT NULL,
                                    price DECIMAL(10,2) NOT NULL,
                                    category_id INT,
                                    description TEXT,
                                    picture VARCHAR(255),
                                    FOREIGN KEY (category_id) REFERENCES category(id)
                                );

CREATE TABLE IF NOT EXISTS "order" (
                                             id INT  PRIMARY KEY AUTO_INCREMENT,
                                             user_id INT,
                                             order_date TIMESTAMP,
                                             FOREIGN KEY (user_id) REFERENCES "user"(id),
                                             type VARCHAR(50)
                                    );

CREATE TABLE IF NOT EXISTS order_item (
                                                id INT  PRIMARY KEY AUTO_INCREMENT,
                                                order_id INT,
                                                item_id INT,
                                                quantity INT,
                                                FOREIGN KEY (order_id) REFERENCES "order" (id),
                                                FOREIGN KEY (item_id) REFERENCES item(id)
                                            );

CREATE TABLE IF NOT EXISTS user_log (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(255) NOT NULL,
    login_date TIMESTAMP,
    ip_address VARCHAR(50),
    event VARCHAR(50)
    );
