# Contábil

## Introdução
Microsserviço destinado à gerência de lançamentos contábeis.    

## Principais tecnologias envolvidas
* [Spring Boot](http://spring.io/projects/spring-boot/) - Fácil configuração de uma aplicação baseada em Spring;
* [PostgreSQL](https://www.postgresql.org/) - Banco relacional que armazenará os lançamentos contábeis;
* [Docker](https://www.docker.com/) - Embutindo as dependências de uma aplicação em um contêiner, facilita a execução e o deploy da mesma em ambientes diversos;
* [Docker Compose](https://docs.docker.com/compose/) - Complementar ao Docker, permite que múltiplos contêineres sejam gerenciados ao mesmo tempo;
* [Spring Tool Suite](https://spring.io/tools/) - IDE sob medida para aplicações baseadas em Spring;
* [JPA](https://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html) - Especificação ORM, implementada neste projeto pelo Hibernate;
* [Bean Validation](https://beanvalidation.org/) - Especificação para validação de beans de forma declarativa implementada pelo Hibernate Validator;
* [JUnit](https://junit.org/junit5/) - Ferramenta adotada para confecção de testes unitários e de integração;
* [Project Lombok](https://projectlombok.org/) - Redução da verbosidade do Java através da substituição de código repetitivo por metadados;
* [Mockito](https://site.mockito.org/) - Facilitador do uso de mocks em testes unitários;
* [Flyway](https://flywaydb.org/) - Versionamento do esquema de um banco de dados.

## Serviços
* Cadastro de POIs (Pontos de Interesse):
  + POST http://xy-inc.com/ponto
* Consulta de POIs cadastrados:
  + GET http://xy-inc.com/ponto
* Consulta POIs mais próximos de um PGS respeitando uma distância máxima. Os parâmetros x e y se referem à localização do GPS e d-max representa a distância máxima.
  + GET http://xy-inc.com/ponto/mais-proximos?x=1&y=2&d-max

## Instalando   
1. Baixe o repositório remoto do projeto para sua máquina com o comando:
   ```bash
   git clone https://github.com/cleverton-heusner/contabil.git  
   ```
     
2. No Spring Tool Suite, importe o repositório baixado como um projeto maven;
3. Para que a IDE reconheça a dependência Project Lombok presente no projeto, siga as instruções no link https://projectlombok.org/setup/eclipse;
4. Para instalar a aplicação, vá até a raíz do projeto e digite o comando:
   ```bash
   ./instalar 2
   ```
5. A aplicação deverá estar pronta para escutar requisições na porta **9999**. Para cadastrar um lançamento contábil, envie para a URL http://localhost:9999/lancamentos-contabeis uma requisição **POST** contendo o seguinte JSON:
   ```json
   {
     "contaContabil": "1234567",
		 "data": "2018-12-27",
		 "valor": 10.99     
   }
   ```
Para visualizar o lançamento contábil recém-cadastrado, envie para a mesma URL uma requisição **GET**.
   	
## Autor:
Cleverton Heusner
