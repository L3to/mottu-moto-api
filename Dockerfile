FROM maven:3.9.5-eclipse-temurin-17
WORKDIR /app
RUN adduser -s /bin/bash -D lemo
USER lemo
COPY .. /app
RUN mvn clean package -DskipTests
EXPOSE 8080 1521 22/tcp 80 5000 443/tcp 3000
CMD ["java", "-jar", "target/mottu-moto-api-0.0.1-SNAPSHOT.jar"]