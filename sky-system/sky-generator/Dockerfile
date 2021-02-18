FROM java
MAINTAINER yanyu 1175533636@qq.com
WORKDIR /root
ADD /target/spring-docker.jar .
ENTRYPOINT ["java", "-jar", "*.jar"]
CMD ["java", "-version"]