package com.conflux.killer.client.message;

public interface MessageQueue {
    void addMessage(String message);
    String getMessage();
}
