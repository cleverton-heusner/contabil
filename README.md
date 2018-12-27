# xy-inc

## Introdução
API comprometida a entregar para GPSs as necessidades mais comuns de geolocalização.    

## Principais tecnologias envolvidas
* [Spring Boot](http://spring.io/projects/spring-boot/) - Fácil configuração de uma aplicação baseada em Spring;
* [PostgreSQL](https://www.postgresql.org/) - Banco relacional que armazenará os lançamentos contábeis;
* [Docker](https://www.docker.com/) - Embutindo as dependências de uma aplicação em um contêiner, facilita a execução e o deploy da mesma em ambientes diversos;
* [Docker Compose](https://docs.docker.com/compose/) - Complementar ao Docker, permite que múltiplos contêineres sejam gerenciados ao mesmo tempo;
* [Spring Tool Suite](https://spring.io/tools/) - IDE sob medida para aplicações baseadas em Spring;
* [JPA](https://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html) - Especificação ORM, implementada neste projeto pelo Hibernate;
* [Bean Validation](https://beanvalidation.org/) - Validação de beans por meio de metadados com suporte à customização.
* [JUnit](https://junit.org/junit5/) - Ferramenta adotada para confecção de testes unitários e de integração;
* [Project Lombok](https://projectlombok.org/) - Redução da verbosidade do Java através da substituição de código repetitivo por metadados;
* [Mockito](https://site.mockito.org/) - Facilitador do uso de mocks em testes unitários;
* [Bean Validation](http://beanvalidation.org/) - Validação de beans por meio de simples anotações e com suporte à internacionalização;
* [Flyway](https://flywaydb.org/) - Versionamento do esquema de um banco de dados.

## Serviços
* Cadastro de POIs (Pontos de Interesse):
  + POST http://xy-inc.com/ponto
* Consulta de POIs cadastrados:
  + GET http://xy-inc.com/ponto
* Consulta POIs mais próximos de um PGS respeitando uma distância máxima. Os parâmetros x e y se referem à localização do GPS e d-max representa a distância máxima.
  + GET http://xy-inc.com/ponto/mais-proximos?x=1&y=2&d-max

## Testando   
1. Baixe o repositório remoto do projeto para sua máquina com o comando:
   ```bash
   git clone https://github.com/cleverton-heusner/xy-inc.git
   ```
     
2. No Eclipse, importe o repositório baixado como um projeto Gradle;
3. Para rodar a aplicação, vá até a raíz do projeto e digite o comando:
   ```bash
   sudo gradle ligarGps
   ```

4. Neste momento, a aplicação deverá estar pronta para ouvir requisições na porta **4567**. Com um cliente HTTP de sua preferência, envie para o endereço http://localhost:4567/ponto uma requisição **POST** contendo o seguinte JSON:
   ```json
   {
     "nome": "Lanchonete",
     "x": 10,
     "y": 20
   }
   ```
Se tudo deu certo, você verá na interface do cliente HTTP um código e uma mensagem de sucesso. Para visualizar o JSON do ponto recém-cadastrado, envie para a mesma URL uma requisição **GET**.
   	
## Autor:
Cleverton Heusner 
