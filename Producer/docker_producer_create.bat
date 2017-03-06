docker-machine rm producer
docker-machine create --driver virtualbox producer
docker-machine env --shell cmd producer
@FOR /f "tokens=*" %i IN ('docker-machine env --shell cmd producer') DO @%i

docker-machine ip producer
pause