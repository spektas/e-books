FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 80
ADD /target/*.jar app.jar
WORKDIR /app
CMD "java" "-jar" "app.jar"

