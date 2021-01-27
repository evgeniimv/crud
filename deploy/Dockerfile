FROM centos:7

RUN yum install -y \
        git \
        wget \
        unzip

ARG JAVA_SOURCE="https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u282-b08/OpenJDK8U-jdk_ppc64le_linux_hotspot_8u282b08.tar.gz"

WORKDIR /usr/lib

RUN wget -P /usr/lib/ $JAVA_SOURCE && \
    tar -zxf OpenJDK8U-jdk_ppc64le_linux_hotspot_8u282b08.tar.gz && \
    rm -f OpenJDK8U-jdk_ppc64le_linux_hotspot_8u282b08.tar.gz

ENV JAVA_HOME="/usr/lib/jdk8u282-b08/"

ENV PATH="$JAVA_HOME/bin:${PATH}"

COPY get-jar-crud-app.sh init

RUN chmod +x init


RUN ["/init", "master" "https://github.com/evgeniimv/crud.git"]

RUN mkdir config
COPY application.properties config/

CMD ["java", "-jar", "CRUD-Application-1.0.jar"]

EXPOSE 8080