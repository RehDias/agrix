FROM eclipse-temurin:17-jdk-jammy as build-image
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw* ./
COPY pom.xml ./
COPY ./src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package

FROM eclipse-temurin:17-jre-jammy
COPY --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
