CONFIG_APP=src/main/resources/application.properties
numInstanciasApp=$([[ ! -z $1 ]] && echo $1 || echo 1)

# Redireciona URL e porta de conexao com banco no host para o respectivo conteiner.
sed -i 's/localhost/contabil_bd/g' $CONFIG_APP
sed -i 's/5433/5432/g' $CONFIG_APP

# Limpa, testa e atualiza build.
./mvnw clean install

# Dockeriza aplicacao e banco de dados.
sudo docker-compose up --scale contabil_app=$numInstanciasApp