package com.conflux.killer.server.tcp.connection;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import com.conflux.killer.core.message.MessageQueue;

public class ConnectionManager {
    public Map< Integer, Connection > connections;
    private ConnectionReception connectionReception;
    private MessageQueue messageQueue;

    public ConnectionManager( MessageQueue messageQueue ) {
        connections = new Hashtable< Integer, Connection >();
        this.messageQueue = messageQueue;
    }

    public void listen() throws IOException {
        connectionReception = new ConnectionReception( this );
        connectionReception.start();
    }

    protected void addNewConnection( Connection connection ) {
        connections.put( connection.getSequenceID(), connection );
        connection.start();
        messageQueue.addMessage( "3", connection.getSequenceID() );
    }

    protected void removeConnection( Connection connection ) {
        connections.remove( connection.getSequenceID() );
        connection.interrupt();
        messageQueue.addMessage( "4", connection.getSequenceID() );
    }

    protected void receiveMessage( String message, int clinetID ) {
        messageQueue.addMessage( message, clinetID );
    }

    public void sendMessage( String message, int receiverId ) {
        Connection connection = connections.get( receiverId );
        connection.sentMessage( message );
    }

    public void sendMessage( String message ) {
        for ( Connection connection : connections.values() ) {
            connection.sentMessage( message );
        }
    }

    public void removeAll() {
        try {
            for ( Connection connection : connections.values() ) {
                connection.interrupt();
            }
            connections = null;
            connectionReception.interrupt();
        } catch (Exception e) {

        }
    }
}
