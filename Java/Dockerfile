FROM alpine:3.10

RUN apk update && apk add --no-cache openjdk11 junit

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

ENV JUNIT_HOME=/usr/share/java
ENV PATH="$JUNIT_HOME/bin:${PATH}"

ENV CLASSPATH=$CLASSPATH:$JUNIT_HOME/junit.jar
ENV PATH="$CLASSPATH/bin:${PATH}"


WORKDIR /app
