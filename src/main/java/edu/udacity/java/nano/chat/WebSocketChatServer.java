package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import edu.udacity.java.nano.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message) {
        message.setCurrentSessions(onlineSessions.size());

        for(Session s : onlineSessions.values()){
            try {
                s.getBasicRemote().sendText(JSON.toJSONString(message));
                System.out.println(String.format("Sending message '%s' to session #%s by [%s]", message.getContent(), s.getId(), message.getSender()));
            }
            catch (IOException ex) {
                System.out.println(String.format("Error while sending message to session %s. Exception is: %s", s.getId(), ex.toString()));
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        String sessionId = session.getId();
        onlineSessions.put(sessionId, session);
        Message message = new Message("ENTER", username, "Connected!", onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, Message message) {
        message.setType("CHAT");
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        String sessionId = session.getId();
        onlineSessions.remove(sessionId);
        Message message = new Message("LEAVE", username, "Disconnected!", onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}