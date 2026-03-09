# Multi-Client Chat Application (Java)

## Project Overview

The **Multi-Client Chat Application** is a Java-based networking project that enables multiple users to communicate with each other through a centralized chat server. The application uses **Java Socket Programming and Multithreading** to allow multiple clients to connect to the server simultaneously and exchange messages in real time.

This project demonstrates fundamental concepts of **client-server architecture, network communication, and concurrent programming in Java**.

---

## Features

* Multi-client chat system
* Real-time message broadcasting
* Username-based communication
* Private messaging between users
* Join and leave notifications
* Chat message logging
* Multithreaded server to handle multiple users

---

## Technologies Used

* **Java**
* **Java Socket Programming**
* **Multithreading**
* **File Handling**
* **Client-Server Architecture**

---

## Project Structure

```
MultiClientChatApp/
│
├── ChatServer.java
├── ChatClient.java
├── chat_log.txt
└── README.md
```

---

## How It Works

1. The **Chat Server** listens on a specific port for incoming client connections.
2. When a client connects, the server creates a **new thread** to handle that client.
3. Each client sends messages to the server.
4. The server **broadcasts the message to all connected clients**.
5. Users can also send **private messages** using the `@username` command.
6. All chat activity is stored in a **log file**.

---

## How to Run the Project

### Step 1: Compile the Files

Open a terminal in the project folder and run:

```
javac ChatServer.java
javac ChatClient.java
```

### Step 2: Start the Server

```
java ChatServer
```

### Step 3: Start the Clients

Open multiple terminals and run:

```
java ChatClient
```

Now multiple users can connect and start chatting.

---

## Example Chat Commands

Send message to everyone:

```
Hello everyone
```

Send private message:

```
@username Hello
```

Exit chat:

```
/exit
```

---

## Learning Outcomes

Through this project, the following concepts were implemented and understood:

* Java Socket Programming
* Multithreaded server design
* Client-server communication
* Network programming fundamentals
* Real-time messaging systems
* File logging mechanisms

---

## Future Improvements

Possible enhancements for this project include:

* GUI chat application using **Java Swing**
* Online user list
* File transfer between users
* Message encryption
* Chat room support
* Server admin controls

---

## Author

**Sourabh Kulkarni**
