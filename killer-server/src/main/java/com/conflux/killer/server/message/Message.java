package com.conflux.killer.server.message;

public class Message {
    public int senderId;
    public String content;

    public Message( int senderId, String content ) {
        this.senderId = senderId;
        this.content = content;
    }
}
