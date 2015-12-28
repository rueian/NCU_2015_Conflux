package com.conflux.killer.server.message;

public interface MessageQueue {
    void addMessage(String message,int senderId);
    String getMessage();
}
