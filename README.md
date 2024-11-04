# Capital Gains Tax Calculator

## Descrição do Projeto

Este projeto é uma aplicação CLI (Command Line Interface) que calcula o imposto de ganho de capital em operações de compra e venda de ativos financeiros. A aplicação foi desenvolvida em Java 21, utilizando Spring Boot, Gradle, Lombok, JUnit5 e está conteinerizada com Docker.


## Funcionalidades

- **Cálculo de Imposto**: Calcula o imposto de 20% sobre o lucro de vendas, descontando prejuízos acumulados.
- **Isenção para Operações Abaixo de R$ 20.000,00**: Vendas com valor total igual ou inferior a R$ 20.000,00 estão isentas de imposto.
- **Acúmulo de Prejuízos**: Prejuízos acumulados são descontados em vendas futuras antes da aplicação do imposto.
- **CLI**: Interface de linha de comando que aceita múltiplas listas de operações.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Gradle**
- **Lombok**
- **JUnit5** para testes unitários
- **Docker** para conteinerização

## Pré-requisitos

- **Java 21** ou superior
- **Docker** (opcional, para executar a aplicação conteinerizada)

## Estrutura do Projeto

- **src/main/java**: Código-fonte da aplicação.
- **src/test/java**: Testes unitários e testes de mutação (com PIT).
- **Dockerfile**: Arquivo para criação do contêiner Docker da aplicação.

## Configuração

### 1. Clone o Repositório
