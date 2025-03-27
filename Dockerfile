FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY gradle.properties* ./
COPY settings.gradle .
COPY build.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=0 /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
