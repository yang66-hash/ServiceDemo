FROM openjdk:8-jre-alpine
ENV APP_FILE SeatService-1.0-SNAPSHOT.jar
ENV APP_HOME /usr/apps
EXPOSE 8081
COPY $APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]