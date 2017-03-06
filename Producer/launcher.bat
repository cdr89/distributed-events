xcopy ..\lib .\lib\
docker build -t producer .
docker run -it --rm -p 8282:8080 producer
