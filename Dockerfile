# 1st stage, build the app
FROM maven:3.5.4-jdk-9 as build

WORKDIR /helidon

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom
ADD pom.xml .
RUN mvn package -DskipTests

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

# 2nd stage, build the runtime image
FROM openjdk:8-jre-slim
#FROM oracle/graalvm-ce
WORKDIR /helidon

# Copy the binary built in the 1st stage
COPY --from=build /helidon/target/warp-speed-planet-search.jar ./
COPY --from=build /helidon/target/libs ./libs

CMD ["java", "-Xmx6144m", "-jar", "warp-speed-planet-search.jar"]

EXPOSE 8080
