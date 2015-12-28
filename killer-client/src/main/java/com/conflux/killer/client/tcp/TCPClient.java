package com.conflux.killer.client.tcp;

public interface TCPClient {
    boolean connectionServer(String ip,int port);
    void sendMessage(String message);
}
