FROM alpine:3.10

RUN apk update && apk add --no-cache openjdk11 apache-ant

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

WORKDIR /app