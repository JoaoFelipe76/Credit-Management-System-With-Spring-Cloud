# Sistema de Microsserviços em Spring Boot

## Sobre
Este projeto consiste em um sistema de microsserviços desenvolvido com **Spring Boot**. Ele inclui microsserviços para gerenciamento de dados de clientes, avaliação de crédito e emissão de cartões. O sistema utiliza **tokens JWT** e **Keycloak** para garantir acesso seguro, implementando tanto padrões de comunicação síncrona quanto assíncrona com **OpenFeign** e **RabbitMQ**. Para a contêinerização, o **Docker** é utilizado.

## Funcionalidades
- **Microsserviço de Clientes**: Gerencia os dados de clientes, como cadastro, atualização e consulta.
- **Microsserviço de Avaliação de Crédito**: Realiza avaliações de crédito com base nos dados dos clientes.
- **Microsserviço de Emissão de Cartões**: Emite cartões após a aprovação da avaliação de crédito.
- **Segurança**: Implementação de autenticação e autorização utilizando **JWT tokens** e **Keycloak**.
- **Comunicação Síncrona**: Utilização de **OpenFeign** para realizar chamadas HTTP entre microsserviços de maneira simplificada.
- **Comunicação Assíncrona**: Utilização de **RabbitMQ** para o envio e recebimento de mensagens entre os microsserviços de forma assíncrona.
- **Contêinerização**: Aplicações empacotadas e executadas em contêineres Docker.

## Tecnologias Utilizadas
- **Spring Boot**: Framework para desenvolvimento de microsserviços.
- **OpenFeign**: Cliente HTTP para comunicação entre microsserviços.
- **RabbitMQ**: Sistema de mensageria para comunicação assíncrona.
- **Keycloak**: Sistema de gerenciamento de identidade e acessos.
- **JWT (JSON Web Tokens)**: Tokens para autenticação e autorização segura.
- **Docker**: Contêinerização para padronização e portabilidade do ambiente de desenvolvimento e produção.

## Arquitetura dos Microsserviços
1. **Clientes**: Responsável por gerenciar os dados de clientes.
2. **Avaliação de Crédito**: Avalia o perfil de crédito dos clientes e retorna uma recomendação.
3. **Cartões**: Emite cartões de crédito para clientes aprovados.

## Comunicação entre Microsserviços
- **Síncrona**: Implementada com **OpenFeign**, permitindo chamadas diretas HTTP entre os microsserviços.
- **Assíncrona**: Implementada com **RabbitMQ**, permitindo o envio e recebimento de mensagens de forma desacoplada e sem bloqueios.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/JoaoFelipe76/microservices-spring-boot.git
