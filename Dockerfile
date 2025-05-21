FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /app

ENV MAVEN_CONFIG=/app/.m2

COPY . .

RUN mvn clean package -DskipTests

RUN adduser -s /bin/sh -D sll && \
    chown -R sll:sll /app

USER sll

EXPOSE 8080

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
