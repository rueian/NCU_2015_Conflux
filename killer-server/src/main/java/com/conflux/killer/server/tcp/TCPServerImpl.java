package com.conflux.killer.server.tcp;

import java.io.IOException;

import com.conflux.killer.server.tcp.connection.ConnectionManager;

public class TCPServerImpl implements TCPServer{
	private ConnectionManager connectionManager;
	
	public TCPServerImpl(ConnectionManager connectionManager) {
		this.connectionManager=connectionManager;
	}
	
	public void initTCPServer(){
		try {
			connectionManager.listen();
		} catch (IOException e) {
			System.out.println("server shut down");
		}
	}

    @Override
    public void sendMessage(String message,int receiverId) {
        connectionManager.sendMessage(message, receiverId);
    }

    @Override
    public void sendMessage(String message) {
        connectionManager.sendMessage(message);
    }
}
