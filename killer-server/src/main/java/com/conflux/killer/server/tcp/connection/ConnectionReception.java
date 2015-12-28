package com.conflux.killer.server.tcp.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReception extends Thread{
    private final int SERVER_PORT = 8765;
	private ConnectionManager connectionManager;
	private int connectionCount;
	private ServerSocket server;
	public ConnectionReception(ConnectionManager connectionManager) {
		this.connectionManager=connectionManager;
	}
	@Override
	public void run() {
	    try {
        this.server= new ServerSocket(SERVER_PORT);
		    while(true){
				Socket socket = server.accept();
				Connection newConnection = establishConnection(socket);
				connectionManager.addNewConnection(newConnection);
		    }
	    } catch (IOException e) {
            e.printStackTrace();
        }
	}
	private Connection establishConnection(Socket socket){
		return new Connection(socket, ++connectionCount,connectionManager);
	}

}
