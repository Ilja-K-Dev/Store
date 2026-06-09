# BUILD
FROM gradle:8.7-jdk21 AS build

RUN apt-get update \
 && apt-get install -y wget \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test

# RUNTIME
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8083

ENTRYPOINT [ "java", "-jar", "app.jar" ]
