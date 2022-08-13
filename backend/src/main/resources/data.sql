INSERT INTO users (id, email, first_name, last_name, password)
VALUES  (1, 'gosho@gmail.com', 'Georgi', 'Grigorov', '0adc9784fff95d8f5a46e235e079935199b4f92df5797641ab5092807d621a05bc68f65ec333cc9c');


INSERT INTO locations(id, city)
VALUES  (1, 'Sofia'),
        (2, 'Varna'),
        (3, 'Ruse'),
        (4, 'Burgas'),
        (5, 'Pernik'),
        (6, 'Plovdiv'),
        (7, 'Sozopol');

INSERT INTO user_roles(id, user_role)
VALUES      (1, 'ADMIN'),
            (2, 'MODERATOR'),
            (3, 'USER');

INSERT INTO categories(id, name)
VALUES  (1, 'CAR'),
        (2, 'TOY'),
        (3, 'HOME'),
        (4, 'TECHNOLOGY'),
        (5, 'CLOTHES'),
        (6, 'OTHER');

INSERT INTO offers (id, description, price, release_date, sold_date, title, url_picture, buyer_id, category_id, location_id, seller_id)
VALUES  (1, '1Very good condition', 100, '2018-11-01', null, 'Offer1', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 1, 1, 1),
        (2, '2Very good condition', 101, '2018-11-02', null, 'Offer2', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 2, 2, 1),
        (3, '3Very good condition', 102, '2018-11-03', null, 'Offer3', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 3, 3, 1),
        (4, '4Very good condition', 103, '2018-11-04', null, 'Offer4', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 4, 4, 1),
        (5, '5Very good condition', 104, '2018-11-05', null, 'Offer5', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 5, 5, 1),
        (6, '6Very good condition', 105, '2018-11-01', null, 'Offer6', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 1, 1, 1),
        (7, '7Very good condition', 106, '2018-11-02', null, 'Offer7', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 2, 2, 1),
        (8, '8Very good condition', 107, '2018-11-03', null, 'Offer8', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 3, 3, 1),
        (9, '9Very good condition', 108, '2018-11-04', null, 'Offer9', null, null, 4, 4, 1),
        (10, '10Very good condition', 109, '2018-11-05', null, 'Offer10', 'https://www.collinsdictionary.com/images/full/table_588358070_1000.jpg', null, 5, 5, 1);

INSERT INTO users_user_roles(user_entity_id, user_roles_id)
VALUES  (1, 1),
        (1, 2),
        (1, 3);

INSERT INTO comments(id, description, offer_id, sender_id)
VALUES  (1, "CENA1?", 1, 1),
        (2, "CENA2?", 1, 1),
        (3, "CENA3?", 1, 1);


