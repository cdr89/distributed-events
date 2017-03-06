xcopy ..\lib .\lib\
docker build -t consumer .
docker run -it --rm -p 8181:8080 consumer
