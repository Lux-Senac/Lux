CREATE TABLE Clientes (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          contato VARCHAR(255) NOT NULL,
                          endereco VARCHAR(255) NOT NULL
);

CREATE TABLE Car (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     motor VARCHAR(255),
                     cil VARCHAR(255),
                     acel VARCHAR(255),
                     hp VARCHAR(255),
                     torque VARCHAR(255),
                     velmax VARCHAR(255),
                     doors INT,
                     seats INT,
                     val VARCHAR(255),
                     price DECIMAL(10, 2),
                     image VARCHAR(255),
                     title VARCHAR(255),
                     page VARCHAR(255)
);

CREATE TABLE USR (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    tipoDeUser ENUM('Admin', 'cliente', 'funci') NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

CREATE TABLE Vendas (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        id_carro INT,
                        id_cliente INT,
                        data_venda DATE NOT NULL,
                        preco_venda DECIMAL(10, 2),
                        FOREIGN KEY (id_carro) REFERENCES Car(id),
                        FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

CREATE TABLE Avaliacoes (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            id_cliente INT,
                            id_carro INT,
                            avaliacao INT NOT NULL,
                            feedback TEXT,
                            FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
                            FOREIGN KEY (id_carro) REFERENCES Car(id)
);
