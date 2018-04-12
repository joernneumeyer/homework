FROM maven:latest
LABEL maintainer="Loek Ehren <l.ehren@student.fontys.nl>"

ENV HOST=0.0.0.0
ENV PORT=8080

VOLUME /root/.m2

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean install

EXPOSE 8080
# Cache maven dependencies
VOLUME /root/.m2

ENTRYPOINT [ "mvn" ]

CMD [ "exec:java"]
