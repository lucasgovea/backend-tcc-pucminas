FROM openjdk:11
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/spring-app.jar
WORDIR /app
ENTRYPOINT java -jar spring-app.jar