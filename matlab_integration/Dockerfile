FROM ubuntu:latest

RUN apt update
RUN apt install -y openjdk-17-jre maven

COPY matlab_common/ /opt/matlab_common/ 
COPY matlab_client/ /opt/matlab_client/ 
COPY matlab_server/ /opt/matlab_server/ 
COPY matlab_code/ /opt/matlab_code/ 
COPY pom.xml /opt/pom.xml
WORKDIR /opt/
RUN mvn package

EXPOSE 4242

ENTRYPOINT java -jar matlab_server/target/matlab_server-1.0-SNAPSHOT.jar
