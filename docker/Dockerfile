FROM eclipse-temurin:17-jdk-alpine
COPY ./target/ /jars/

ARG PROJECT_JAR_NAME
ENV JAR_NAME=$PROJECT_JAR_NAME

CMD [ "java","-jar","${JAR_NAME}" ]