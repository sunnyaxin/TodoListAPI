# the first stage of our build will use a maven 3.6.1 parent image
FROM gradle:4.7.0-jdk8-alpine as builder

# copy the pom and src code to the container
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# package our application code
RUN gradle build --no-daemon

# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:8-jre-slim
EXPOSE 8080

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
WORKDIR /app
# set the startup command to execute the jar
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]