FROM maven:3.3-jdk-8
COPY . /app
WORKDIR /app
EXPOSE 8100
CMD mvn spring-boot:run