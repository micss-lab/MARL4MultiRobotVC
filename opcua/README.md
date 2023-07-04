# opcua-example
Simple example of the OPC UA implementation using Eclipse Milo Library

#### OPC UA Client SDK
```xml
<dependency>
    <groupId>org.eclipse.milo</groupId>
    <artifactId>sdk-client</artifactId>
    <version>0.6.9</version>
</dependency>
```
#### OPC UA Server SDK
```xml
<dependency>
    <groupId>org.eclipse.milo</groupId>
    <artifactId>sdk-server</artifactId>
    <version>0.6.9</version>
</dependency>
```

## Running the example

#### Excecute the project

`$ mvn clean install`

#### Run the server

`$ mvn -f pom.xml exec:java`
or
`$ mvn -f pom.xml exec:java@ServerMain`

#### Run the client

`$ mvn -f pom.xml exec:java`
or
`$ mvn -f .\pom.xml exec:java@ClientMain`
