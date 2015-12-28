package com.conflux.killer.client.tcp;

import com.conflux.killer.client.tcp.connection.Connector;

public class TCPClientImpl implements TCPClient{
	private Connector connector;
	
	public TCPClientImpl(Connector connector) {
        this.connector=connector;
    }

    @Override
    public boolean connectionServer(String Ip,int port) {
        connector.start(Ip, port);
        return true;
    }

    @Override
    public void sendMessage(String message) {
        connector.sendMessage(message);
    }
	
}
