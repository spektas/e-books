
INSERT INTO user (id, username, password, algorithm)
VALUES ('1', 'sema', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT'); --12345

INSERT INTO authority (id, name, user)
VALUES ('1', 'READ', '1');
INSERT INTO authority (id, name, user)
VALUES ('2', 'WRITE', '1');
