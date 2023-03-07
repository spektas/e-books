mvn clean package -DskipTests
docker build -t ebooks:v1 .
docker run -d -p 80:80 --name=ebooks ebooks:v1


