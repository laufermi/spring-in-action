FROM openjdk:11-jdk-slim

USER root

ENV LANG=en_US.UTF8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app

RUN chown -R app /app
COPY build/libs/demo-0.0.1.jar /app/app.jar
COPY run.sh /app

EXPOSE 8080

USER 1000

CMD ["./run.sh"]
