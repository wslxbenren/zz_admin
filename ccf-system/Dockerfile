FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER dadovicn@icloud.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /ccf-system

WORKDIR /ccf-system
1
ADD ./target/ccf-system-2.1.jar ./

CMD sleep 5;java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Djava.security.egd=file:/dev/./urandom -jar ccf-system-2.1.jar
