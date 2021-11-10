FROM azul/zulu-openjdk-alpine:11-jre

ENV JAVA_OPTS=""

COPY build/libs/*.jar app.jar

EXPOSE 8092

ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -jar app.jar"]