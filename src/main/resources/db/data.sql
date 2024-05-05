-- Inserts for Clientes table
INSERT INTO Clientes (nome, sobreNome, contato, email, cep, pais)
VALUES ('Nome Exemplo', 'Sobrenome Exemplo', 123456789, 'exemplo@email.com', 123456, 'Brasil');


-- Inserts for Car table
INSERT INTO Car (name, motor, cil, acel, hp, torque, velmax, doors, seats, val, price, image, title, page)
VALUES ('Nome do Carro', 'Motor do Carro', 'Cilindro do Carro', 'Aceleração do Carro', 'Potência do Carro', 'Torque do Carro', 'Velocidade Máxima do Carro', 4, 5, 'Valor da Válvula do Carro', 50000.00, 'URL da Imagem do Carro', 'Descrição do Carro', 'Bmw');

-- Inserts for USR table
INSERT INTO USR (username, password, email, tipo) VALUES ('User 1', 'Password 1', 'user1@example.com', 'Admin');
INSERT INTO USR (username, password, email, tipo, id_cliente) VALUES ('User 2', 'Password 2', 'user2@example.com', 'cliente', 1);

-- Inserts for Vendas table
INSERT INTO Vendas (id_carro, id_cliente, data_venda, preco_venda) VALUES (1, 1, '2024-04-20', 10000.00);

-- Inserts for Avaliacoes table
INSERT INTO Avaliacoes (id_cliente, id_carro, avaliacao, feedback) VALUES (1, 1, 5, 'Great car!');

-- Inserts for Reserva table
INSERT INTO Reserva (id_car, id_client, tipoReserva, statusReserva, dataReserva)
VALUES (1, 1, 'TEST_DRIVE', 'ESPERA', '2024-05-05');