CREATE TABLE person (
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    username              VARCHAR(50)  NULL,
    password              VARCHAR(255) NULL,
    role                  VARCHAR(20)  NULL,
    address               VARCHAR(50)  NULL,
    is_account_non_locked TINYINT(1)   NULL
);

CREATE TABLE promo_code (
    id        INTEGER PRIMARY KEY  AUTO_INCREMENT,
    name      VARCHAR(100) NULL,
    discount  INTEGER  NULL,
    person_id INTEGER  NULL,
    CONSTRAINT FOREIGN KEY (person_id) REFERENCES person (id)
);


CREATE TABLE orders (
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    person_id INTEGER  NULL,
    price     INTEGER  NULL,
    date      DATE NULL,
    CONSTRAINT FOREIGN KEY(person_id) REFERENCES person (id)
);


CREATE TABLE order_item (
    price      INTEGER NULL,
    quantity   INTEGER NULL,
    order_id   INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT FOREIGN KEY (product_id) REFERENCES product (id)
);


CREATE TABLE product (
    DTYPE          VARCHAR(31) NOT NULL,
    id             INTEGER PRIMARY KEY  AUTO_INCREMENT,
    quantity       INTEGER  NULL,
    color_id       INTEGER  NULL,
    dress_model_id INTEGER  NULL,
    dress_size_id  INTEGER  NULL,
    shoes_model_id INTEGER  NULL,
    shoes_size_id  INTEGER  NULL,
    bag_model_id   INTEGER  NULL,
    bag_size_id    INTEGER  NULL,
    price          INTEGER  NULL,
    name           VARCHAR(50) NULL,
    CONSTRAINT FOREIGN KEY (dress_model_id) REFERENCES dress_model (id),
    CONSTRAINT FOREIGN KEY (shoes_model_id) REFERENCES shoes_model (id),
    CONSTRAINT FOREIGN KEY (bag_model_id) REFERENCES bag_model (id),
    CONSTRAINT FOREIGN KEY (dress_size_id) REFERENCES dress_size (id),
    CONSTRAINT FOREIGN KEY (shoes_size_id) REFERENCES shoes_size (id),
    CONSTRAINT FOREIGN KEY (bag_size_id) REFERENCES bag_size (id),
    CONSTRAINT FOREIGN KEY (color_id) REFERENCES color (id)
);


CREATE TABLE dress_model(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    model VARCHAR(50) NULL
);


CREATE TABLE dress_size(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    size INTEGER
);


CREATE TABLE bag_model(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    model VARCHAR(50) NULL
);


CREATE TABLE bag_size(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    size INTEGER NULL
);


CREATE TABLE shoes_model(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    model VARCHAR(50) NULL
);


CREATE TABLE shoes_size(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    size INTEGER NULL
);


CREATE TABLE color(
    id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    color VARCHAR(50) NULL
);

