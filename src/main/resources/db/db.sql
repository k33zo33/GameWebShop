-- Users
INSERT INTO "user" (username, password, role) VALUES
                                                    ('guest', 'guest', 'GUEST'),
                                                   ('admin', '$2a$12$zgx/ViXA6cqHULcZ.cT8p.oqOaHzC6/A4J1R6ZPes2nH.KGUCiW32', 'ADMIN'), -- Password: admin
                                                   ('user', '$2a$12$2rPaONNwo.mMzitrpNSSm.ncTTICHnZP1HJs5unFDzj68SCa6/VDa', 'USER');  -- Password: user

-- Categories
INSERT INTO category (name, description) VALUES
                                             ('Card Games', 'Various card games.'),
                                             ('Board Games', 'Various board games.'),
                                             ('Video Games', 'Various video games.'),
                                             ('Miscellaneous', 'Miscellaneous.');

-- Items
INSERT INTO item (name, description, price, picture, category_id) VALUES
                                                                  ('MTG Pack', 'Pack of 15 Magic The Gathering cards', 5.00, 'https://upload.wikimedia.org/wikipedia/en/3/34/Boosterpacks.png', 1),
                                                                  ('Catan', 'Classic board game.', 20.00, 'https://upload.wikimedia.org/wikipedia/en/a/a3/Catan-2015-boxart.jpg', 2),
                                                                  ('The Witcher 3', 'Popular RPG video game.', 60.00, 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg', 3),
                                                                  ('Dice', 'Pack of DND dice', 10.00, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/Dice_%28typical_role_playing_game_dice%29.jpg/200px-Dice_%28typical_role_playing_game_dice%29.jpg', 4),
                                                                  ('Pokemon pack', 'Pack of 15 Pokemon cards', 6.00, 'https://upload.wikimedia.org/wikipedia/en/3/3b/Pokemon_Trading_Card_Game_cardback.jpg', 1),
                                                                  ('Fifa 22', 'Football simulation game', 30.00, 'https://upload.wikimedia.org/wikipedia/en/6/6c/FIFA_22_Cover.jpg', 3);

-- Orders
INSERT INTO "order" (user_id, order_date, type) VALUES
                                                                 (2, '2024-06-25 14:30:00', 'cash'),
                                                                 (2, '2024-06-26 11:00:00', 'paypal');

-- Order Items
INSERT INTO order_item (order_id, item_id, quantity) VALUES
                                                         (1, 1, 2), -- 2 MTG Packs
                                                         (1, 2, 1), -- 1 Catan
                                                         (2, 3, 1); -- 1 The Witcher 3
