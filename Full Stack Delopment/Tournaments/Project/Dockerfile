FROM azul/zulu-openjdk-alpine:17
ARG JAR_FILE=build/libs/IndividualTrack-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]