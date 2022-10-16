#Builder
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn verify --fail-never
COPY . .
RUN mvn package -DskipTests

#app
FROM openjdk:18-jdk-slim
EXPOSE 8080
CMD ["java","-jar","/usr/local/lib/wishlist.jar"]
COPY --from=build /app/target/wishlist-0.0.1-SNAPSHOT.jar /usr/local/lib/wishlist.jar
