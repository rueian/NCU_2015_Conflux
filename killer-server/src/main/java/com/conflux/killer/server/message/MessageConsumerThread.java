package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Message;
import com.conflux.killer.core.message.MessageQueue;

public class MessageConsumerThread implements Runnable {

    private MessageQueue queue;
    private MessageReceiver receiver;

    public MessageConsumerThread( MessageQueue queue, MessageReceiver receiver ) {
        this.queue = queue;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        while ( true ) {
            Message message = queue.getMessage();
            if ( message != null ) {
                receiver.receiveMessage( message );
            } else {
                try {
                    Thread.sleep( 10 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
