package com.conflux.killer.server.tcp.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReception extends Thread {
    private final int SERVER_PORT = 8765;
    private ConnectionManager connectionManager;
    private int connectionCount;
    private ServerSocket server;

    public ConnectionReception( ConnectionManager connectionManager ) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket( SERVER_PORT );
            for ( int i = 0; i < 4; i ++ ) {
                Socket socket = server.accept();
                Connection newConnection = establishConnection( socket );
                connectionManager.addNewConnection( newConnection );
            }
            this.server.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection establishConnection( Socket socket ) {
        return new Connection( socket, ++connectionCount, connectionManager );
    }

}
