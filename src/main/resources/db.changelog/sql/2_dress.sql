CREATE TABLE dress(
                      id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name varchar(50),
                      quantity INTEGER,
                      dress_model_id INTEGER,
                      CONSTRAINT FOREIGN KEY(dress_model_id) REFERENCES dress_model(id)
);

CREATE TABLE dress_model(
                            id INTEGER PRIMARY KEY  AUTO_INCREMENT,
                            model varchar(50),
                            price INTEGER
);
ALTER TABLE dress add CONSTRAINT FOREIGN KEY (dress_model_id)
    REFERENCES dress_model(id) ON DELETE SET NULL;

CREATE TABLE person(
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       username varchar(50),
                       password varchar(50)
);

INSERT INTO person(username, password) values ('Natali', 'Natali');
INSERT INTO person(username, password) values ('Nat', 'Nat');
INSERT INTO person(username, password) values ('Oleg', 'Oleg');