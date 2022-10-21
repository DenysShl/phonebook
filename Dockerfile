FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} phonebooks.jar
ENTRYPOINT ["java","-jar","/phonebooks.jar"]
EXPOSE 5005