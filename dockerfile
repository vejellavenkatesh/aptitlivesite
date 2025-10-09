
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR .

# Copy the JAR file into the container
COPY *.jar app.jar

# Expose the port your app runs on (adjust if needed)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
