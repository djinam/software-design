package raf.graffito.dsw.core.messageGenerator;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private MessageType messageType;
    private LocalDateTime timestamp;

    public Message(String message, MessageType messageType, LocalDateTime timestamp) {
        this.message = message;
        this.messageType = messageType;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }
}
