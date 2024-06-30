FROM openjdk:21-jdk-slim
WORKDIR /app
COPY ./target/GameWebShop-0.0.1-SNAPSHOT.war /app/ROOT.war
EXPOSE 8080
CMD ["java", "-jar", "ROOT.war"]