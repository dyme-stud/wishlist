#Builder
FROM maven:3.8.6-openjdk-18-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#app
FROM openjdk:18-jdk-slim
EXPOSE 8080
CMD ["java","-jar","/usr/local/lib/wishlist.jar"]
COPY --from=build /app/target/wishlist-0.0.1-SNAPSHOT.jar /usr/local/lib/wishlist.jar
