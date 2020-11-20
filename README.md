This repo demonstrates a simple Spring Boot app to send and receive messages in Kafka using spring-kafka. 

Pre-requisite:
1) JDK 11
2) Eclipse / IntelliJ IDE 
3) Maven (if not part of IDE already)
4) Install Apache Kafka v2.12 (or latest)

Steps to create Kafka Topics: Please note, Following Kafka topics are not required to be created manually, before running application (steps are for windows os).
1) Go to kafka  installation location (C:\kafka_2.12\) and create empty folder - data. And under that new empty folder below 2 folders. 
```
kafka
zookeeper
```
 
2) Create "zookeeper.properties" file with below content in kafka installation location (C:\kafka_2.12\config). 
```
# the directory where the snapshot is stored.
dataDir=C:/kafka_2.12/data/zookeeper
# the port at which the clients will connect
clientPort=2181
# disable the per-ip limit on the number of connections since this is a non-production config
maxClientCnxns=0
# Disable the adminserver by default to avoid port conflicts.
# Set the port to something non-conflicting if choosing to enable this
admin.enableServer=false
# admin.serverPort=8080
```

3) Change below lines from "server.properties" file in kafka installation location (C:\kafka_2.12\config). 
```
# A comma separated list of directories under which to store log files
log.dirs=C:/kafka_2.12/data/kafka
```

4) Start Zookeeper in one command line: 
```
C:\kafka_2.12\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

5) Start Kafka in another command line: 
```
C:\kafka_2.12\bin\windows\kafka-server-start.bat config\server.properties
```

6) Create below 4 topics

```
C:\kafka_2.12\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic dpinc
C:\kafka_2.12\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic partitioned
C:\kafka_2.12\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic filtered
C:\kafka_2.12\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic greeting
```

When the application runs successfully, following output is logged on to console (along with spring logs):

Message received from the 'dpinc' topic by the basic listeners with groups foo and bar
```
Received Message in group 'foo': Hello, World!
Received Message in group 'bar': Hello, World!
```

Message received from the 'dpinc' topic, with the partition info
```
Received Message: Hello, World! from partition: 0
```

Message received from the 'partitioned' topic, only from specific partitions
```
Received Message: Hello To Partioned Topic! from partition: 0
Received Message: Hello To Partioned Topic! from partition: 3
```
Message received from the 'filtered' topic after filtering
```
Received Message in filtered listener: Hello dpinc!
```
Message (Serialized Java Object) received from the 'greeting' topic
```
Received greeting message: Greetings, World!!
```
