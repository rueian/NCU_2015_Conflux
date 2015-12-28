package com.conflux.killer.server.message;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueueImpl implements MessageQueue {

    private Queue<Message> queue;

    public MessageQueueImpl() {
        queue = new ConcurrentLinkedQueue< Message >();
    }

    @Override
    public void addMessage( String message, int senderId ) {
        queue.add( new Message(senderId, message) );
    }

    @Override
    public Message getMessage() {
        return queue.poll();
    }

}
