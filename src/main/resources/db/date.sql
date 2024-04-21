-- Inserts for Clientes table
INSERT INTO Clientes (nome, contato, endereco) VALUES ('Cliente 1', 'Contato 1', 'Endereco 1');
INSERT INTO Clientes (nome, contato, endereco) VALUES ('Cliente 2', 'Contato 2', 'Endereco 2');
INSERT INTO Clientes (nome, contato, endereco) VALUES ('Cliente 3', 'Contato 3', 'Endereco 3');
INSERT INTO Clientes (nome, contato, endereco) VALUES ('Cliente 4', 'Contato 4', 'Endereco 4');
INSERT INTO Clientes (nome, contato, endereco) VALUES ('Cliente 5', 'Contato 5', 'Endereco 5');

-- Inserts for Car table
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES ('Car 1', 'Motor 1', 'Cil 1', 'Acel 1', 'Hp 1', 'Torque 1', 'Velmax 1', 4, 5, 'Val 1', 10000.00, 'Image 1', 'Title 1', 'Page 1');
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES ('Car 2', 'Motor 2', 'Cil 2', 'Acel 2', 'Hp 2', 'Torque 2', 'Velmax 2', 4, 5, 'Val 2', 20000.00, 'Image 2', 'Title 2', 'Page 2');
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES ('Car 3', 'Motor 3', 'Cil 3', 'Acel 3', 'Hp 3', 'Torque 3', 'Velmax 3', 4, 5, 'Val 3', 30000.00, 'Image 3', 'Title 3', 'Page 3');
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES ('Car 4', 'Motor 4', 'Cil 4', 'Acel 4', 'Hp 4', 'Torque 4', 'Velmax 4', 4, 5, 'Val 4', 40000.00, 'Image 4', 'Title 4', 'Page 4');
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES ('Car 5', 'Motor 5', 'Cil 5', 'Acel 5', 'Hp 5', 'Torque 5', 'Velmax 5', 4, 5, 'Val 5', 50000.00, 'Image 5', 'Title 5', 'Page 5');

-- Inserts for USR table
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 1', 'Password 1', 'user1@example.com', 'Admin', 1);
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 2', 'Password 2', 'user2@example.com', 'cliente', 2);
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 3', 'Password 3', 'user3@example.com', 'funci', 3);
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 4', 'Password 4', 'user4@example.com', 'Admin', 4);
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 5', 'Password 5', 'user5@example.com', 'cliente', 5);

-- Inserts for Vendas table
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (1, 1, '2024-04-20', 10000.00);
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (2, 2, '2024-04-21', 20000.00);
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (3, 3, '2024-04-22', 30000.00);
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (4, 4, '2024-04-23', 40000.00);
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (5, 5, '2024-04-24', 50000.00);

-- Inserts for Avaliacoes table
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (1, 1, 5, 'Great car!');
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (2, 2, 4, 'Good car!');
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (3, 3, 3, 'Average car!');
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (4, 4, 2, 'Below average car!');
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (5, 5, 1, 'Bad car!');
