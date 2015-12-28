package com.conflux.killer.server.tcp.connection;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import com.conflux.killer.core.message.MessageQueue;

public class ConnectionManager{
	private Map<Integer,Connection> connections;
	private ConnectionReception connectionReception;
	private MessageQueue messageQueue;
	public ConnectionManager(MessageQueue messageQueue) {
		connections= new Hashtable<Integer, Connection>();
		this.messageQueue=messageQueue;
	}
	
	public void listen() throws IOException{
		connectionReception= new ConnectionReception(this);
		connectionReception.start();
	}
	protected void addNewConnection(Connection connection){
		connections.put(connection.getSequenceID(), connection);
		connection.start();
	}
	protected void removeConnection(Connection connection){
		connections.remove(connection.getSequenceID());
		connection.interrupt();
	}
	protected void receiveMessage(String message,int clinetID){
	    messageQueue.addMessage(message, clinetID);
	}
	public void sendMessage(String message,int receiverId) {
		Connection connection = connections.get(receiverId);
		connection.sentMessage(message);
	}
}
