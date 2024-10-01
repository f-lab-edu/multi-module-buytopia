# Step 1: Base image
FROM openjdk:17-jdk-alpine

RUN apk --no-cache add curl

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file
COPY build/libs/eureka-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Step 4: Expose the port
EXPOSE 8761

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]