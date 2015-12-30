package com.conflux.killer.server.tcp.connection;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection extends Thread {
    private ConnectionManager connectionManager;
    private int sequenceID;
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;

    public Connection( Socket socket, int sequenceID, ConnectionManager connectionManager ) {
        this.socket = socket;
        this.sequenceID = sequenceID;
        this.connectionManager = connectionManager;
        try {
            ois = new DataInputStream( socket.getInputStream() );
            oos = new DataOutputStream( socket.getOutputStream() );
        } catch ( IOException e ) {
            e.printStackTrace();
            offline();
        }
    }

    @Override
    public void run() {
        try {
            while ( true ) {
                connectionManager.receiveMessage( ois.readUTF(), sequenceID );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            offline();
        }
    }

    public synchronized void sentMessage( String message ) {
        try {
            oos.writeUTF( message );
            oos.flush();
        } catch ( IOException e ) {
            offline();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    private void offline() {
        connectionManager.removeConnection( this );
    }

    public int getSequenceID() {
        return sequenceID;
    }
}
