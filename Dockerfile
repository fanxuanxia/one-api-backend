FROM maven:3.8.1-openjdk-17

# copy local code to container image
WORKDIR /appDocker
COPY pom.xml .
COPY src ./src

# build a release artifact
RUN mvn clean package -DskipTests

# run the web service on container startup
CMD ["java","-jar","/appDocker/target/oneApiBackend-0.0.1-SNAPSHOT.jar"]
