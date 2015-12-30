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

    public static boolean isGaming = false;

    public static void main( String[] args ) {

        Thread game = null;

        while (true) {
            MessageQueue messageQueue = new MessageQueueImpl();
            ConnectionManager connectionManager = new ConnectionManager( messageQueue );
            TCPServerImpl server = new TCPServerImpl( connectionManager );
            server.initTCPServer();

            MessageSender messageSender = new MessageSenderImpl( server );
            DataCenter dataCenter = new DataCenterImpl( messageSender );
            MessageReceiver messageReceiver = new MessageReceiverImpl( dataCenter );
            MessageConsumerThread thread = new MessageConsumerThread( messageQueue, messageReceiver );
            game = new Thread( thread );
            game.start();

            isGaming = true;

            while (isGaming) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            server.removeAll();
            game.interrupt();
        }
    }
}
