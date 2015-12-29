package com.conflux.killer.core.message;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueueImpl implements MessageQueue {

    private Queue< Message > queue;

    public MessageQueueImpl() {
        queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void addMessage( String message, int senderId ) {
        queue.add( new Message( senderId, message ) );
    }

    @Override
    public Message getMessage() {
        return queue.poll();
    }

}
