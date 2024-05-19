-- Inserts for Clientes table
INSERT INTO Clientes (nome, sobrenome, contato, email, cep, pais, preferenciacontato) VALUES
('Nome1', 'Sobrenome1', '1234567890', 'email1@email.com', '12345', 'BRASIL', 'EMAIL'),
('Nome2', 'Sobrenome2', '1234567891', 'email2@email.com', '12346', 'BRASIL', 'TELEFONE'),
('Nome3', 'Sobrenome3', '1234567892', 'email3@email.com', '12347', 'BRASIL', 'WHATSAPP'),
('Nome4', 'Sobrenome4', '1234567893', 'email4@email.com', '12348', 'BRASIL', 'EMAIL'),
('Nome5', 'Sobrenome5', '1234567894', 'email5@email.com', '12349', 'BRASIL', 'WHATSAPP'),
('Nome6', 'Sobrenome6', '1234567895', 'email6@email.com', '12350', 'BRASIL', 'TELEFONE'),
('Nome7', 'Sobrenome7', '1234567896', 'email7@email.com', '12351', 'BRASIL', 'WHATSAPP'),
('Nome8', 'Sobrenome8', '1234567897', 'email8@email.com', '12352', 'BRASIL', 'EMAIL'),
('Nome9', 'Sobrenome9', '1234567898', 'email9@email.com', '12353', 'BRASIL', 'TELEFONE'),
('Nome10', 'Sobrenome10', '1234567899', 'email10@email.com', '12354', 'BRASIL', 'WHATSAPP');

-- Inserts for Car table
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page)
VALUES ('Nome do Carro', 'Motor do Carro', 'Cilindro do Carro', 'Aceleração do Carro', 'Potência do Carro', 'Torque do Carro', 'Velocidade Máxima do Carro', 4, 5, 'Valor da Válvula do Carro', 50000.00, 'https://i.pinimg.com/originals/76/ea/9b/76ea9b37388c46c67b718a5ec6b4ad33.gif', 'Descrição do Carro', 'Bmw');
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES
('Tesla Model S', 'Motor1', 'Cil1', 'Acel1', 'HP1', 'Torque1', 'VelMax1', 4, 5, 'Val1', 80000.00, 'https://i.pinimg.com/originals/1a/4c/5b/1a4c5bb4855f4d06caa78471692c5f9a.gif', 'Title1', 'TeslaModelS'),
('Tesla Model X', 'Motor2', 'Cil2', 'Acel2', 'HP2', 'Torque2', 'VelMax2', 4, 5, 'Val2', 85000.00, 'https://i.pinimg.com/originals/4f/12/e3/4f12e32fe10f4aeb22003a950c43e166.gif', 'Title2', 'TeslaModelX'),
('Porsche Taycan Turbo S', 'Motor3', 'Cil3', 'Acel3', 'HP3', 'Torque3', 'VelMax3', 2, 2, 'Val3', 150000.00, 'https://i.pinimg.com/originals/72/61/dc/7261dc7c0a0923d7fbcbe288ed5ff403.gif', 'Title3', 'Porsche'),
('Bmw I8', 'Motor4', 'Cil4', 'Acel4', 'HP4', 'Torque4', 'VelMax4', 2, 2, 'Val4', 140000.00, 'https://i.pinimg.com/originals/0a/d4/65/0ad465088cee13d5dab25c06955223b9.gif', 'Title4', 'Bmw'),
('BYD Yuan', 'Motor5', 'Cil5', 'Acel5', 'HP5', 'Torque5', 'VelMax5', 4, 5, 'Val5', 35000.00, 'https://i.pinimg.com/originals/e1/80/36/e18036de3669defc2f0f6a05533d5e6b.gif', 'Title5', 'BYDYuan'),
('BYD Tan', 'Motor6', 'Cil6', 'Acel6', 'HP6', 'Torque6', 'VelMax6', 4, 5, 'Val6', 40000.00, 'https://i.pinimg.com/originals/78/0a/ff/780aff211e0e33d9349c35f614a2ea44.gif', 'Title6', 'BYDTan');


-- Inserts for USR table
INSERT INTO USR (username, password, email, tipo, urlavatar) VALUES ('User 1', '$2a$10$82J2fDP6kak99vcroDzAFOmxrjHQxVo/LhhVmcbPycbWWRW7Y43K2', 'user1@example.com', 'ADMIN', 'https://www.gravatar.com/avatar/0f8ededc78c2d265f41bb2ef64cbb6f1ea551bc15b2b72ee552f5fc2084e9671');
INSERT INTO USR (username, password, email, tipo, id_cliente, urlavatar) VALUES ('User 2', '$2a$10$z0p6uXfjFrmkEv5O4aMdAOY1PFJcZNzQjmcMMux.QPV/AemxwUdkC', 'user2@example.com', 'CLIENTE', 1,'https://www.gravatar.com/avatar/0f8ededc78c2d265f41bb2ef64cbb6f1ea551bc15b2b72ee552f5fc2084e9671');

-- Inserts for Vendas table
INSERT INTO Vendas (id_carro, id_cliente, id_user, datavenda, precovenda) VALUES
(1, 1, 1, '2024-01-01', 80000.00),
(2, 2, 1, '2024-01-02', 85000.00),
(3, 3, 1, '2024-02-03', 150000.00),
(4, 4, 1, '2024-02-04', 140000.00),
(5, 5, 1, '2024-03-05', 35000.00),
(6, 1, 1, '2024-03-06', 40000.00),
(1, 2, 1, '2024-04-07', 80000.00),
(2, 3, 1, '2024-04-08', 85000.00),
(3, 4, 1, '2024-05-09', 150000.00),
(1, 1, 1, '2024-05-01', 80000.00),
(2, 2, 1, '2024-06-02', 85000.00),
(3, 3, 1, '2024-06-03', 150000.00),
(4, 4, 1, '2024-07-04', 140000.00),
(5, 5, 1, '2024-07-05', 35000.00),
(6, 1, 1, '2024-08-06', 40000.00),
(1, 2, 1, '2024-08-07', 80000.00),
(2, 3, 1, '2024-09-08', 85000.00),
(3, 4, 1, '2024-10-09', 100000.00),
(4, 5, 1, '2024-10-10', 10000.00),
(3, 4, 1, '2024-11-09', 15000.00),
(4, 5, 1, '2024-11-10', 14000.00),
(3, 4, 1, '2024-12-09', 150000.00),
(4, 5, 1, '2024-12-10', 1000.00);

-- Inserts for Reserva table
INSERT INTO Reserva (id_car, id_client, tipoReserva, statusReserva, dataReserva) VALUES
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 2, 'RESERVA', 'ESPERA', '2024-05-05');