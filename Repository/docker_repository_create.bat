docker-machine rm repository
docker-machine create --driver virtualbox repository
docker-machine env --shell cmd repository
@FOR /f "tokens=*" %i IN ('docker-machine env --shell cmd repository') DO @%i

docker-machine ip repository
pause