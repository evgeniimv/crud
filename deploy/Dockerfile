
FROM centos:7

RUN yum install -y \
        git \
        wget \
        unzip

ARG JAVA_SOURCE ="https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u282-b08/OpenJDK8U-jdk_ppc64le_linux_hotspot_8u282b08.tar.gz"

WORKDIR /usr/lib

RUN wget -P /usr/lib/ $JAVA_SOURCE && \
    tar -zxf OpenJDK8U-jdk_x64_linux_hotspot_8u222b10.tar.gz && \
    rm -f OpenJDK8U-jdk_x64_linux_hotspot_8u222b10.tar.gz

ENV JAVA_HOME="/usr/lib/jdk8u222-b10/"

ENV PATH="$JAVA_HOME/bin:${PATH}"

COPY get_jar-file.sh /init

RUN chmod +x /init
WORKDIR /opt

RUN ["/init", "tes-dev", "https://github.com/SilinPavel/cloud-pipeline.git"]

RUN mkdir config
COPY application.properties config/

CMD ["java", "-jar", "tes-adapter-1.0-SNAPSHOT.jar"]

EXPOSE 8080