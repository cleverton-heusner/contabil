numInstanciasApp=$([[ ! -z $1 ]] && echo $1 || echo 1)

# Redireciona URL de conexao com banco do host para o respectivo conteiner.
sed -i 's/localhost/contabil_bd/g' src/main/resources/application.properties

# Limpa e atualiza build.
./mvnw clean && ./mvnw install

# Dockeriza aplicacao e banco de dados.
sudo docker-compose up --scale contabil_app=$numInstanciasApp