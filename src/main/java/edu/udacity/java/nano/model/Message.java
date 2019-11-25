package edu.udacity.java.nano.model;

/**
 * WebSocket message model
 */
public class Message {
    private String content;
    private String type;
    private String sender;
    private Integer currentSessions;

    public Message() {
    }

    public Message(String type, String sender, String content, Integer currentSessions) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.currentSessions = currentSessions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getCurrentSessions() {
        return currentSessions;
    }

    public void setCurrentSessions(Integer currentSessions) {
        this.currentSessions = currentSessions;
    }
}
