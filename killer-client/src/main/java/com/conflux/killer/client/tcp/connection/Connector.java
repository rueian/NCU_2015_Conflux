package com.conflux.killer.client.tcp.connection;

import java.io.*;
import java.net.Socket;
import com.conflux.killer.core.message.MessageQueue;

public class Connector extends Thread{
	private Socket client;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	private MessageQueue messageQueue;
	
	public Connector(MessageQueue messageQueue) {
	    this.messageQueue=messageQueue;
	}

	public void start(String serverIP,int serverPort) {
        try {
        	client = new Socket(serverIP,serverPort);
            outputStream = new DataOutputStream(client.getOutputStream());
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) { e.printStackTrace();offline(); }
		super.start();
	}
	
	public void run(){
        try {
            receiveMessage();
        } catch (IOException e) { offline(); }
    }
	
	private void receiveMessage() throws IOException{
		while(true){
        	String input= inputStream.readUTF();
        	messageQueue.addMessage(input,0);
		}
	}
	
	public synchronized void sendMessage(String message){
		try {
			outputStream.writeUTF(message);
			outputStream.flush();
		} catch (IOException e) { offline(); }
	}
	private void offline(){
		System.out.println("Having trouble connecting to server");
	}
}
