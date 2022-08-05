# Matlab Server for DIME integration

## Dependencies

- A matlab installation
- Java 8 for DIME
- Java 17 for the Matlab Server
- Maven

## Build and run

First, set your java installation to use java 8 with 

```
update-alternatives --config java
```

>Use [the official doc for platform specic instructions](https://fr.mathworks.com/help/matlab/matlab_external/setup-environment.html),
>in particular find your `$MATLABROOT` and how to setup `java.library.path` on your OS

On linux when you have your `$MATLABROOT`, install the mathworks provided jar with

```sh
mvn install:install-file \
	-Dfile=$MATLABROOT/extern/engines/java/jar/engine.jar \
	-DgroupId=com.mathworks.engine \
	-DartifactId=engine \
	-Dversion=1.0 \
	-Dpackaging=jar
```

To compile run

```sh
JAVA_HOME=/usr/lib/jvm/java-18-openjdk-amd64/ mvn compile
```

To build the jar files run

```sh
JAVA_HOME=/usr/lib/jvm/java-18-openjdk-amd64/ mvn package
```

To run the server :

```sh
/usr/lib/jvm/java-18-openjdk-amd64/bin/java \
	-Djava.library.path=$MATLABROOT/R2022a/bin/glnxa64 \
	-cp /opt/matlab/R2022a/extern/engines/java/jar/engine.jar \
	-jar matlab_server/target/matlab_server-1.0-SNAPSHOT.jar
```

To run the client (can run on Java 11):

```
java -jar matlab_client/target/matlab_client-1.0-SNAPSHOT.jar
```

## Including matlab code

Add your matlab code to the `matlab_code` directory if you want to call them in
the java code.

## Docker container

TODO: its a pain because of licensing, just run the server on your machine :-)
(see above).

### Get your matlab license

[download the license for the server here](https://fr.mathworks.com/licensecenter/)


Use the dummy `info.scce.dime.matlab.server.MatlabServer` class because matlab 
is a pain with docker.

Build the container
```
docker build -t matlab_container
```

Get the IP of the container with 
```
docker network inspect bridge
```
and put it in `info.scce.dime.matlab.client.App`

Launch the server with
```
docker run --rm --hostname matab_server --name matlab_server -p 4242:4242 matlab_container:latest
```

Launch the client as usual to make request
