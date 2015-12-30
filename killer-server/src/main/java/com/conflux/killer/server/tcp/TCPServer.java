package com.conflux.killer.server.tcp;

public interface TCPServer {
    void initTCPServer();

    void sendMessage( String message, int receiverId );

    void sendMessage( String message );

    void removeAll();
}
