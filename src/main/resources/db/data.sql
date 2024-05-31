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
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page) VALUES
('Tesla Model S', 'Dois Motores Elétricos','651 Km', '3,1s', '605 cv', '92,48 Kgfm', '250 Km/h', 4, 5, 'AWD', 5.299, 'https://i.pinimg.com/originals/e9/eb/b5/e9ebb543389a2e5122e6d883eac3bc6b.jpg', 'Comforto e dirigibilidade com tecnologia.', 'TeslaModelS'),
('Tesla Model X', 'Dois Motores Elétricos', '400 Km', '4s', '772 cv', '94,8 Kgfm', '250 Km/h', 4, 7, 'AWD', 7.350, 'https://images.alphacoders.com/121/thumb-1920-1210651.jpg', 'Seguranca e Potencia em um carro.', 'TeslaModelX'),
('Porsche Taycan Turbo S', 'Dois Motores Elétricos', '388 Km', '2,8s', '761 cv', '107,1 Kgfm', '260 Km/h', 4, 4, 'AWD', 12.000, 'https://images6.alphacoders.com/104/thumb-1920-1041796.jpg', 'Um toque de luxuosidade e desempenho', 'Porsche'),
('Bmw I8', 'Intercooled Turbo Gas/Electric I-3','600 km', '4,4s', '369 cv', '42 kgfm', '250 Km/h', 2, 4, 'AWD', 7.899, 'https://images7.alphacoders.com/549/thumb-1920-549793.jpg', 'Um toque de luxuosidade e desempenho', 'Bmw'),
('BYD Yuan', 'Elétrico', '294 Km', '7,3s', '204 cv', '31,0 Kgfm', '160 Km/h', 4, 5, 'FWD', 3.599, 'https://i.pinimg.com/736x/60/99/fb/6099fbf9e2b828de6105eec182034dab.jpg', 'Carro família e econômico', 'BYDYuan'),
('BYD Tan', 'Elétrico', '306 Km', '4,6s', '272 cv', '69,3 Kgfm', '186 Km/h', 4, 7, 'AWD', 6.200, 'https://www.cnnbrasil.com.br/wp-content/uploads/sites/12/2024/04/byd-tan-2024.webp', 'O SUV elétrico para a família toda', 'BYDTan');

-- Inserts for USR table
INSERT INTO USR (username, password, email, tipo, urlavatar) VALUES ('User 1', '$2a$10$82J2fDP6kak99vcroDzAFOmxrjHQxVo/LhhVmcbPycbWWRW7Y43K2', 'user1@example.com', 'ADMIN', 'https://i.pinimg.com/originals/72/61/dc/7261dc7c0a0923d7fbcbe288ed5ff403.gif');
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
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 1, 'TESTDRIVE', 'ESPERA', '2024-05-10'),
(5, 1, 'RESERVA', 'APROVADO', '2024-04-05'),
(2, 5, 'TESTDRIVE', 'ESPERA', '2024-03-15'),
(3, 3, 'RESERVA', 'ESPERA', '2024-02-07'),
(4, 2, 'TESTDRIVE', 'REJEITADO', '2024-01-05'),
(1, 2, 'RESERVA', 'ESPERA', '2024-05-05');