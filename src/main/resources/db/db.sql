-- Users
INSERT INTO "user" (username, password, role) VALUES
                                                    ('guest', 'guest', 'GUEST'),
                                                   ('admin', 'admin', 'ADMIN'), -- Password: admin
                                                   ('user', 'user', 'USER');  -- Password: user

-- Categories
INSERT INTO category (name, description) VALUES
                                             ('Card Games', 'Various card games.'),
                                             ('Board Games', 'Various board games.'),
                                             ('Video Games', 'Various video games.'),
                                             ('Miscellaneous', 'Miscellaneous.');

-- Items
INSERT INTO item (name, description, price, picture, category_id) VALUES
                                                                  ('MTG Pack', 'Pack of 15 Magic The Gathering cards', 5.00, 'https://example.com/poker.jpg', 1),
                                                                  ('Catan', 'Classic board game.', 20.00, 'https://example.com/monopoly.jpg', 2),
                                                                  ('The Witcher 3', 'Popular RPG video game.', 60.00, 'https://example.com/fifa23.jpg', 3),
                                                                  ('Dice', 'Pack of DND dice', 10.00, 'https://example.com/puzzle.jpg', 4);

-- Orders
INSERT INTO "order" (user_id, order_date, type) VALUES
                                                                 (2, '2024-06-25 14:30:00', 'cash'),
                                                                 (2, '2024-06-26 11:00:00', 'paypal');

-- Order Items
INSERT INTO order_item (order_id, item_id, quantity) VALUES
                                                         (1, 1, 2), -- 2 MTG Packs
                                                         (1, 2, 1), -- 1 Catan
                                                         (2, 3, 1); -- 1 The Witcher 3
