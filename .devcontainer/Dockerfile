# Base image with Java 21
FROM mcr.microsoft.com/devcontainers/java:21

# Install Gradle
RUN apt-get update && apt-get install -y gradle

# Verify installations
RUN java -version && gradle -v
