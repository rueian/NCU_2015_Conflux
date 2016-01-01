package com.conflux.killer.client;

import com.conflux.killer.client.dom.Images;
import com.conflux.killer.client.dom.ObjectCenter;
import com.conflux.killer.client.dom.ObjectCenterImpl;
import com.conflux.killer.client.message.MessageReceiverImpl;
import com.conflux.killer.client.message.MessageSender;
import com.conflux.killer.client.message.MessageSenderImpl;
import com.conflux.killer.client.tcp.TCPClient;
import com.conflux.killer.client.tcp.TCPClientImpl;
import com.conflux.killer.client.tcp.connection.Connector;
import com.conflux.killer.client.ui.GameKeyListener;
import com.conflux.killer.client.ui.UserInterface;
import com.conflux.killer.core.message.MessageConsumerThread;
import com.conflux.killer.core.message.MessageQueue;
import com.conflux.killer.core.message.MessageQueueImpl;
import com.conflux.killer.core.message.MessageReceiver;

import java.awt.event.KeyListener;

public class Main {

    public static void main( String[] args ) {
        Images.load();
        UserInterface userInterface = new UserInterface();
    }
}
