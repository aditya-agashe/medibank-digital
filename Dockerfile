FROM java:8-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ENV TZ=Australia/Melbourne

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /usr/app/app.jar

WORKDIR /usr/app

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar","--unsplash-access-key=${UNSPLASH_ACCESS_KEY}"]
