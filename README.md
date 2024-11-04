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

### Instale as Dependências

./gradlew build

### Executando a Aplicação
   

- **Para executar a aplicação CLI localmente**:

````./gradlew bootRun````

#### A aplicação iniciará e exibirá uma mensagem para inserir as operações de ganho de capital. Por exemplo:

```Insira as operações de ganho de capital (pressione Enter duas vezes para processar):

[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]

[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]

```

#### Via Docker
Para executar a aplicação em um contêiner Docker, execute:

````
docker build -t capitalgains .
docker run -it capitalgains
````

##### A aplicação estará disponível no contêiner da mesma forma que ao executá-la localmente.

##### Exemplo de Uso:
````
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]

[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]
````

#### Saída esperada:

````
[{"tax":0.0},{"tax":10000.0}]
[{"tax":0.0},{"tax":0.0}]

Insira o próximo conjunto de operações ou pressione Ctrl+C para sair:
````

#### Executando Testes
##### Testes Unitários
Para executar os testes unitários, use:

````
./gradlew test
````

### Estrutura do Código
- **CapitalGainsTaxCalculator**: Lógica de cálculo de imposto, incluindo cálculo de lucro, aplicação de isenção e acúmulo de prejuízos.
- **Operation e Tax**: Classes que representam uma operação de compra ou venda e o valor do imposto, respectivamente.
- **CLIApplication**: Interface de linha de comando que recebe as listas de operações em JSON e exibe o imposto calculado.
