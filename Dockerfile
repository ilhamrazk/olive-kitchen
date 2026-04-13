# ================================
# Stage 1: Build
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml dulu (cache dependencies lebih efisien)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code lalu build
COPY src ./src
RUN mvn clean package -DskipTests -B

# ================================
# Stage 2: Runtime
# ================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy JAR dari stage build
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render menggunakan PORT env variable)
EXPOSE 8080

# Jalankan aplikasi
# Spring Boot akan membaca DATABASE_URL, DB_USERNAME, DB_PASSWORD dari environment variable
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT:-8080}", "app.jar"]
