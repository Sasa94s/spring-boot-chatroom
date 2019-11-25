# Chat Room
Chat Room Application implementation using WebSocket.

## Background
WebSocket is a communication protocol that makes it possible to establish a two-way communication channel between a
server and a client.

## Instruction
### Implement the message model
Message model is the message payload that will be encoded/decoded before being exchanged between the client and the server. Implement the Message
class in chat module. Basic Actions are:
1. ENTER
2. CHAT
3. LEAVE

### Run the application with command
mvn build; mvn spring-boot:run

### Getting Started
- Go to http://localhost:8080/
- Enter any user name, or you can enter as a Guest without entering a user name.
- Start chatting with your peers.