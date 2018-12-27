# xy-inc

## Introdução
API comprometida a entregar para GPSs as necessidades mais comuns de geolocalização.    

## Tecnologias envolvidas
* [PostgreSQL](https://www.postgresql.org/) - Banco que armazenará os POIs ou Pontos de Interesse;
* [Docker](https://www.docker.com/) - Alternativa leve às máquinas virtuais. Facilitou a instalação, execução e configuração do PostgreSQL envolvendo-o em um contêiner;
* [Docker Compose](https://docs.docker.com/compose/) - Auxilia o Docker, evitando que múltiplos contêineres sejam gerenciados separadamente. A administração é simplificada mesmo com apenas um contêiner;
* [Eclipse Ogygen](https://www.eclipse.org/oxygen/) - IDE utilizada no projeto;
* [Gradle](https://gradle.org/) - Gerenciador famoso de dependências e builds. Possibilitou todos os passos envolvidos no deploy da aplicação com um único comando;
* [Postman](https://www.getpostman.com/) - Cliente HTTP utilizado para os testes de caixa preta nos serviços;
* [Spark Framework](http://sparkjava.com/) - Framework RESTful para rápido desenvolvimento. Permite testes de API produdivos, pois traz o Jetty embutido, Servlet Container bastante leve;
* [JUnit](https://junit.org/junit5/) - Ferramenta usada nos testes automatizados de API;
* [Hibernate](http://hibernate.org/) - Framework de persistência ORM;
* [Weld](http://weld.cdi-spec.org/) - Implementação de referência da tecnologia CDI, injeção de dependências tanto em Java EE quando Java SE;
* [Bean Validation](http://beanvalidation.org/) - Validação de beans por meio de simples anotações e com suporte à internacionalização;
* [REST Assured](http://rest-assured.io/) - Cliente para testar APIs em Java. Seu diferencial é uma DSL que descreve a requisição e a resposta de um endpoint de forma expressiva.

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
