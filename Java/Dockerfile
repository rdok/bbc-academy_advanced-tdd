FROM alpine:3.10

RUN apk update && apk add --no-cache openjdk11 junit apache-ant

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

ENV JAVA_SHARED="/usr/share/java"

ENV JUNIT_HOME="${JAVA_SHARED}"
ENV PATH="$JUNIT_HOME/bin:${PATH}"

ENV CLASSPATH=$CLASSPATH:$JUNIT_HOME/junit.jar
ENV PATH="$CLASSPATH/bin:${PATH}"

WORKDIR /app
