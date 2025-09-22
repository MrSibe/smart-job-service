# 后端Dockerfile - Spring Boot 3.5.5
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# 设置工作目录
WORKDIR /app

# 复制pom.xml和源代码
COPY pom.xml .
COPY src ./src

# 构建应用
RUN mvn clean package -DskipTests

# 生产阶段
FROM eclipse-temurin:21-jre-alpine

# 设置工作目录
WORKDIR /app

# 复制构建好的jar文件
COPY --from=builder /app/target/smart-job-service-*.jar app.jar

# 创建非root用户
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# 暴露端口
EXPOSE 8000

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/app.jar"]