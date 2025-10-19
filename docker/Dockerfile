FROM eclipse-temurin:17-jdk-alpine
COPY ./target/ /jars/

ARG PROJECT_JAR_NAME
ENV JAR_NAME=$PROJECT_JAR_NAME
RUN echo "PROJECT_JAR_NAME ${PROJECT_JAR_NAME}"
RUN echo "JAR_NAME ${JAR_NAME}"
CMD [ "java","-jar","/jars/${JAR_NAME}" ]