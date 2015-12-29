package com.conflux.killer.server;

import com.conflux.killer.core.message.MessageConsumerThread;
import com.conflux.killer.core.message.MessageQueue;
import com.conflux.killer.core.message.MessageQueueImpl;
import com.conflux.killer.core.message.MessageReceiver;
import com.conflux.killer.server.cdc.DataCenter;
import com.conflux.killer.server.cdc.DataCenterImpl;
import com.conflux.killer.server.message.*;
import com.conflux.killer.server.tcp.TCPServerImpl;
import com.conflux.killer.server.tcp.connection.ConnectionManager;

public class Main {
    public static void main( String[] args ) {
        MessageQueue messageQueue = new MessageQueueImpl();
        ConnectionManager connectionManager = new ConnectionManager( messageQueue );
        TCPServerImpl server = new TCPServerImpl( connectionManager );
        server.initTCPServer();

        MessageSender messageSender = new MessageSenderImpl( server );
        DataCenter dataCenter = new DataCenterImpl( messageSender );
        MessageReceiver messageReceiver = new MessageReceiverImpl( dataCenter );
        MessageConsumerThread thread = new MessageConsumerThread( messageQueue, messageReceiver );
        new Thread( thread ).start();
    }
}
