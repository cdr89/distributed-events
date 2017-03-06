docker-machine rm consumer
docker-machine create --driver virtualbox consumer
docker-machine env --shell cmd consumer
@FOR /f "tokens=*" %i IN ('docker-machine env --shell cmd consumer') DO @%i

docker-machine ip consumer
pause