INSERT INTO users (id, email, first_name, last_name, profile_picture_url, password)
VALUES  (1, 'gosho@mail.com', 'Gosho', 'Goshov', null, 12345),
        (2, 'misho@mail.com', 'Misho', 'Mishov', null, 12345);

INSERT INTO locations(id, city)
VALUES  (1, 'Sofia'),
        (2, 'Varna'),
        (3, 'Ruse'),
        (4, 'Burgas'),
        (5, 'Pernik'),
        (6, 'Plovdiv'),
        (7, 'Sozopol');

INSERT INTO user_roles(id, user_role)
VALUES      (1, 'Admin'),
            (2, 'Moderator'),
            (3, 'User');

INSERT INTO categories(id, category)
VALUES  (1, 'CAR'),
        (2, 'TOY'),
        (3, 'HOME'),
        (4, 'TECHNOLOGY'),
        (5, 'CLOTHES'),
        (6, 'OTHER');

INSERT INTO offers (id, description, price, release_date, sold_date, title, buyer_id, seller_id, location_id)
VALUES  (1, 'Very good condition', 100, '2018-11-01', null, 'Stol na dva kraka', 1, null, 1),
        (2, 'Old but gold', 2.5, '2018-11-01', '2019-11-01', 'Masa bez stolove', 1, 2, 2);

INSERT INTO users_user_roles(user_entity_id, user_roles_id)
VALUES  (1, 1),
        (1, 2),
        (1, 3),
        (2, 3);

INSERT INTO offers_categories(offer_entity_id, categories_id)
VALUES      (1, 1),
            (1, 2),
            (1, 3),
            (2, 4);

INSERT INTO comments(id, description, offer_id, sender_id)
VALUES  (1, "CENA?", 1, 2),
        (2, "Vzimam vednaga", 2, 2),
        (3, "Nqkoi saka li go?", 1, 1);

