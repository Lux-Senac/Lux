## Sistema Gerenciador de Concessionária em Java

### Descrição

Este projeto é um sistema de gerenciamento de concessionária de veículos desenvolvido em Java, utilizando Spring Boot e JPA. Ele permite gerenciar carros, clientes, vendas, avaliações e usuários do sistema.

### Funcionalidades

* **Gerenciamento de Carros:** Cadastro, edição e exclusão de carros, com informações como nome, motor, cilindradas, aceleração, potência, torque, velocidade máxima, número de portas, número de assentos, valor e imagem.
* **Gerenciamento de Clientes:** Cadastro, edição e exclusão de clientes, com informações como nome, contato e endereço.
* **Gerenciamento de Vendas:** Registro de vendas, associando um carro a um cliente, com data da venda e preço.
* **Gerenciamento de Avaliações:** Clientes podem avaliar os carros, atribuindo uma nota e um feedback.
* **Gerenciamento de Usuários:** Criação de usuários com diferentes níveis de acesso (administrador, cliente e funcionário).
* **Login e Logout:** Usuários podem fazer login e logout no sistema para acessar as funcionalidades de acordo com seu nível de acesso.

### Tecnologias utilizadas

* **Java:** Linguagem de programação utilizada no desenvolvimento do sistema.
* **Spring Boot:** Framework que facilita o desenvolvimento de aplicações Java, com recursos como injeção de dependências e configuração automática.
* **JPA:** (Java Persistence API) API que permite mapear objetos Java para tabelas de banco de dados relacionais.
* **Lombok:** Biblioteca que simplifica o código Java, gerando automaticamente getters, setters, construtores e outros métodos.
* **MySQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados do sistema.

### Estrutura do projeto

O projeto está organizado em pacotes, que agrupam as classes por funcionalidade:

* **br.com.lux:** Pacote raiz do projeto.
* **br.com.lux.config:** Configurações do sistema, como interceptadores para controle de acesso.
* **br.com.lux.controller:** Controladores que lidam com as requisições web.
* **br.com.lux.domain:** Classes que representam as entidades do sistema (carros, clientes, vendas, avaliações e usuários).
* **br.com.lux.repository:** Repositórios que permitem acessar os dados das entidades no banco de dados.
* **br.com.lux.services:** Serviços que implementam a lógica de negócio do sistema.

### Como executar o projeto

1. Certifique-se de ter o Java e o Maven instalados em sua máquina.
2. Clone o repositório do projeto.
3. Execute o comando `mvn spring-boot:run` para iniciar o servidor da aplicação.
4. Acesse a aplicação no navegador através do endereço `http://localhost:8080/`.

### Observações

* Este projeto é um exemplo e pode ser adaptado para atender as necessidades específicas de cada concessionária.
* A segurança do sistema deve ser reforçada com medidas como criptografia de senhas e controle de acesso mais granular.
* A interface do usuário pode ser aprimorada com frameworks como Thymeleaf ou React.

### Contribuições

Contribuições para o projeto são bem-vindas. Por favor, crie um fork do repositório e envie um pull request com suas alterações.
