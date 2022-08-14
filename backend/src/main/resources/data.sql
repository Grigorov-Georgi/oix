INSERT INTO users (id, email, first_name, last_name, password)
VALUES  (1, 'gosho@gmail.com', 'Georgi', 'Grigorov', '0adc9784fff95d8f5a46e235e079935199b4f92df5797641ab5092807d621a05bc68f65ec333cc9c'),
        (2, 'gosho1@gmail.com', 'Asen', 'Asenov', '7ad700fc491b1a4e434c670e1964019197b5e11ff0553504e2efcdbc5fb640d704ba06b7ebab7efb');


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
        (2, '2Very good condition', 101, '2018-11-02', null, 'Offer2', 'https://www.energicamotor.com/wp-content/uploads/2021/12/Ego-2.png', null, 2, 2, 2),
        (3, '3Very good condition', 102, '2018-11-03', null, 'Offer3', 'https://hsr-autos.co.uk/wp-content/uploads/2020/06/Motorcycle.jpg', null, 3, 3, 2),
        (4, '4Very good condition', 103, '2018-11-04', null, 'Offer4', 'https://3a5uhq3iy81v1tv1443318gq-wpengine.netdna-ssl.com/wp-content/uploads/sites/91/2020/07/V7-III-stone-rosso-main.png', null, 4, 4, 2),
        (5, '5Very good condition', 104, '2018-11-05', null, 'Offer5', 'https://images.unsplash.com/photo-1559289431-9f12ee08f8b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8M3x8fGVufDB8fHx8&w=1000&q=80', null, 5, 5, 1),
        (6, '6Very good condition', 105, '2018-11-01', null, 'Offer6', 'https://cdn1.polaris.com/globalassets/indian/2021/model/vehicle-cards/9b3af134-indian-ftr-1200.png?v=8c1cbc75&format=webp', null, 1, 1, 1),
        (7, '7Very good condition', 106, '2018-11-02', null, 'Offer7', 'https://www.ccarprice.com/products/Mercedes_Benz_E-Class_63_AMG.jpg', null, 2, 2, 1),
        (8, '8Very good condition', 107, '2018-11-03', null, 'Offer8', 'https://s1.cdn.autoevolution.com/images/news/2021-mercedes-amg-e-63-facelift-this-is-pretty-much-it-141136-7.jpg', null, 3, 3, 1),
        (9, '9Very good condition', 108, '2018-11-04', null, 'Offer9', null, null, 4, 4, 1),
        (10, '10Very good condition', 109, '2018-11-05', null, 'Offer10', 'https://cdn.motor1.com/images/mgl/wllErV/s3/mercedes-amg-e-63-s-4matic-final-edition.jpg', null, 5, 5, 1);

INSERT INTO users_user_roles(user_entity_id, user_roles_id)
VALUES  (1, 1),
        (1, 2),
        (1, 3),
        (2, 3);

INSERT INTO comments(id, description, offer_id, sender_id)
VALUES  (1, "CENA1?", 1, 1),
        (2, "CENA2?", 1, 1),
        (3, "CENA3?", 1, 1);


