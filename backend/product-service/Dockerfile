# BUILD
FROM gradle:8.7-jdk21 AS build

RUN apt-get update \
 && apt-get install -y wget \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY product-service/build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle gradle
COPY gradlew .

COPY product-service product-service
COPY shared-kernel shared-kernel

RUN chmod +x gradlew

RUN ./gradlew :product-service:build -x test

# RUNTIME
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/product-service/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "app.jar" ]
