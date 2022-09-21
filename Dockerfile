# Use the official maven/Java 8 image to create a build artifact.
FROM maven:3.8.6-jdk-11 as builder

#Variables
ARG SONAR_LOGIN
ARG SONAR_HOST
ARG SONAR_PROJ


# Copy local code to the container image.
WORKDIR /app

RUN mkdir ./m2
COPY settings.xml ./m2
COPY pom.xml .
COPY src ./src

RUN mvn clean install -q -DskipTests -s ./m2/settings.xml

RUN mvn package -ntp -q -s ./m2/settings.xml

RUN mvn sonar:sonar  -Dsonar.projectKey=$SONAR_PROJ  -Dsonar.host.url=$SONAR_HOST  -Dsonar.login=$SONAR_LOGIN

FROM adoptopenjdk/openjdk11:alpine-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/demouser*.jar /demoUser.jar

# Run the web service on container startup.
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/demoUser.jar"]
