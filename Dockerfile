FROM openjdk:11
RUN mkdir app-spring
ARG JAR_FILE
WORKDIR /app-spring
ADD ./target/${JAR_FILE} /app/app-spring.jar
ENTRYPOINT java -jar app-spring.jar