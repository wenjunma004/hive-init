# Hive Init

This is a hive init implement by Quarkus .


## Requirements

To compile and run this demo you will need:

- JDK 1.8+
- GraalVM

### Configuring GraalVM and JDK 1.8+

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 1.8+ `java` command is on the path.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image-guide)
for help setting up your environment.

## Building Hive Init

Launch the Maven build on the checked out :

> ./mvnw install

### build native


> ./mvnw package -Pnative 

### run it directly 

>./target/hive-init-main-1.0-SNAPSHOT-runner

### build native with container


> ./mvnw package -Pnative -Dquarkus.native.container-build=true


### build docker image


> docker build -f src/main/docker/Dockerfile.native -t wenjmaksarc.azurecr.io/hive-init:latest  .

