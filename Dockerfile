#JAR
FROM openjdk:8-jdk
WORKDIR /
ADD target/us-currency-transaction.jar us-currency-transaction.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","us-currency-transaction.jar"]