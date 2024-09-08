FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/rent-company-server-sprint8-8.0.0.jar ./rent-company.jar
EXPOSE 8080
ENV MONGO_URL=mongodb+srv://denissytnyk:JnURuSgOQRnM8pEB@cluster0.7fvf8cb.mongodb.net/accounting?retryWrites=true&w=majority&appName=Cluster0
CMD ["java", "-jar", "/app/rent-company.jar"]