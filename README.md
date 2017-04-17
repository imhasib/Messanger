## Messenger

### Prerequisites:
* java
* Maven

### How to run?
* At first clone or download this repository.
* This is a maven project.
* Then import this as a maven project in any IDE and run the bellow commands. This commands are also executable from the project directory.

### To run the server
    mvn exec:java -Dexec.mainClass="com.hasib.Main" -Dexec.args="server"

### To run the client
    mvn exec:java -Dexec.mainClass="com.hasib.Main" -Dexec.args="client"
    
* Two or more client apps should be run. Then send a message one client to another client.
* After running server and clients. Click the "Start" button. Then click the "sign in" buttons of the client app. 