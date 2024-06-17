# Registro financeiro

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

## Descrição

O projeto mantém o registro simples de renda e gastos pessoais do usuário e mostra algumas métricas relevantes. A api rest foi desenvolvida com Java e Spring, é consumida por um front em Angular. Os dados são salvos em um banco sqlite. Desenvolvi uma api simples para poder integrar com o front construído enquanto estudo Angular.

## Instruções de instalação

### Pré-requisitos

- Java 17
- Maven 3.6.3

### Instalar

- Clonar o repositório
    
    ```bash
    git clone https://github.com/JorderGomes/registro-financeiro-back.git
    ```
    
- Instalar as dependências com Maven
- Abra o terminal na pasta do projeto e execute este comando:
    
    ```bash
    ./mvnw spring-boot:run
    ```
    
- A API estará acessível em http://localhost:8080/
- A documentação estará disponível em: http://localhost:8080/swagger-ui/index.html#/

## Endpoints

```bash
POST /transactions                                       - Registrar uma nova transação
GET  /transactions/{transactionId}                       - Recuperar uma transação
GET  /metrics/percentual-income-costs?year_month=yyyy-MM - Recuperar a porcentagem de gastos e renda
GET  /metrics/month-balance?month=MM                     - Recuperar a os somatórios de gasto, renda e geral por mês
GET  /metrics/expensive-costs?year_month=yyyy-MM         - Recuperar os gastos mais caros do mês
GET  /metrics/costs-by-tag                               - Recuperar somatório dos gastos por tag
GET  /metrics/costs-by-month                             - Recuperar somatório dos gastos por mês
GET  /alive                                              - Verificar se a api está rodando
```
