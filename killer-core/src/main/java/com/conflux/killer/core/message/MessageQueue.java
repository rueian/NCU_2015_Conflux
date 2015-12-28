package com.conflux.killer.core.message;

public interface MessageQueue {
    void addMessage( String message, int senderId );

    Message getMessage();
}
