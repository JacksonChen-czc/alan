FROM java:8
VOLUME /tmp/springboot
ADD alan-admin-1.0.0-SNAPSHOT.jar springboot-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-docker.jar"]
