FROM gradle:4.10.2-jdk8-alpine
ADD . /app
ADD --chown=gradle . /app
WORKDIR /app
CMD ["./gradlew", "run"]


