FROM maven:3.5.4-jdk-9 as build
WORKDIR /helidon
ADD pom.xml .
RUN mvn package -DskipTests
ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

FROM openjdk:8-jre-slim
WORKDIR /helidon
COPY --from=build /helidon/target/warp-speed-planet-search.jar ./
COPY --from=build /helidon/target/libs ./libs
CMD ["java", "-Xmx6144m", "-jar", "warp-speed-planet-search.jar"]
EXPOSE 8080