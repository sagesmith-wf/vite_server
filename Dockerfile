FROM openjdk:8

RUN mkdir /app
RUN ./gradlew assemble
RUN mv ../build/libs/vite-server-*.jar vite-server.jar
ADD vite-server.jar /app
ADD entrypoint.sh /
RUN chmod +x entrypoint.sh

EXPOSE 9000

CMD ["/entrypoint.sh"]
