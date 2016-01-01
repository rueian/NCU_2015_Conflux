package com.conflux.killer.client.ui;

import com.conflux.killer.client.dom.ObjectCenter;
import com.conflux.killer.client.dom.ObjectCenterImpl;
import com.conflux.killer.client.message.MessageReceiverImpl;
import com.conflux.killer.client.message.MessageSender;
import com.conflux.killer.client.message.MessageSenderImpl;
import com.conflux.killer.client.tcp.TCPClient;
import com.conflux.killer.client.tcp.TCPClientImpl;
import com.conflux.killer.client.tcp.connection.Connector;
import com.conflux.killer.core.message.MessageConsumerThread;
import com.conflux.killer.core.message.MessageQueue;
import com.conflux.killer.core.message.MessageQueueImpl;
import com.conflux.killer.core.message.MessageReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame implements GameControlable {

    private MessageReceiver messageReceiver;
    private KeyListener keyListener;
    private MessageSender messageSender;
    private ObjectCenter objectCenter;
    private RenderThreadImpl renderThread;

    public UserInterface() throws HeadlessException {

        this.setSize( 660, 660 );
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        showUI();

        this.setVisible( true );
    }

    @Override
    public void showUI() {
        JLabel label = new JLabel( "The Killer" );
        label.setFont( new Font( "Arial", Font.BOLD, 50 ) );
        label.setBounds( 200, 50, 300, 60 );

        final JTextField textField = new JTextField();
        textField.setBounds( 180, 250, 300, 60 );
        textField.setText( "127.0.0.1" );

        JButton connectButton = new JButton( "CONNECT" );
        connectButton.setFont(new Font("Arial", Font.BOLD, 25));
        connectButton.setBounds(180, 400, 300, 60);
        connectButton.addActionListener((e) -> {

            MessageQueue messageQueue = new MessageQueueImpl();
            Connector connector = new Connector(messageQueue);
            TCPClient client = new TCPClientImpl(connector);

            objectCenter = new ObjectCenterImpl(this);

            messageSender = new MessageSenderImpl(client);

            messageReceiver = new MessageReceiverImpl(objectCenter);

            Runnable consumerThread = new MessageConsumerThread(messageQueue, messageReceiver);

            if (keyListener != null) {
                this.removeKeyListener(keyListener);
            }

            keyListener = new GameKeyListener(messageSender, objectCenter);
            this.addKeyListener(keyListener);

            client.connectionServer(textField.getText(), 8765);
            new Thread(consumerThread).start();
            connectButton.setEnabled(false);
        });

        String helpMessage = "這是一個關於猜疑與生存的遊戲，生存為玩家的第一要務。為了生存，" +
                "玩家必須先下手為強先殺掉其他對手，因為玩家永遠不知道會不會被背叛、偷襲。\n" +
                "你可以使用的按鍵有:上,下,左,右,Q,W,E";

        JButton helpBtn = new JButton( "HELP" );
        helpBtn.setFont( new Font( "Arial", Font.BOLD, 25 ) );
        helpBtn.setBounds( 180, 320, 300, 60 );
        helpBtn.addActionListener( (e)-> JOptionPane.showMessageDialog( this, helpMessage ));

        JPanel panel = new JPanel();
        panel.setSize(660, 660);
        panel.setLayout(null);
        panel.add(label);
        panel.add(helpBtn);
        panel.add(textField);
        panel.add(connectButton);

        this.setContentPane(panel);
    }

    @Override
    public void startGame() {
        JPanel drawPanel = new JPanel();
        drawPanel.setSize(660, 660);
        this.setContentPane(drawPanel);
        this.requestFocus();
        SceneDataImpl sceneData = new SceneDataImpl();
        sceneData.loadMap();
        renderThread = new RenderThreadImpl(
                new SceneRenderImpl(sceneData, objectCenter),
                new SpriteRenderImpl(objectCenter),
                drawPanel.getGraphics()
        );
        renderThread.startRenderThread();
    }

    @Override
    public void endGame() {
        renderThread.stopRenderThread();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JPanel drawPanel = new JPanel();
        drawPanel.setSize(660, 660);
        JLabel label = new JLabel( "你死了" );
        label.setFont( new Font( "Arial", Font.BOLD, 20 ) );
        label.setBounds( 200, 50, 300, 60 );
        JButton backBtn = new JButton( "返回" );
        backBtn.setFont(new Font("Arial", Font.BOLD, 25));
        backBtn.setBounds(180, 400, 300, 60);
        backBtn.addActionListener((e) -> {
            this.showUI();
        });
        drawPanel.add(label);
        drawPanel.add(backBtn);
        this.setContentPane(drawPanel);
    }

    @Override
    public void updateCharacterNum( int num ) {
        JPanel drawPanel = new JPanel();
        drawPanel.setSize(660, 660);
        JLabel label = new JLabel( "已經有 " + num + " 位玩家加入" );
        label.setFont( new Font( "Arial", Font.BOLD, 20 ) );
        label.setBounds( 200, 50, 300, 60 );
        drawPanel.add(label);
        this.setContentPane(drawPanel);
    }

    @Override
    public void winGame() {
        renderThread.stopRenderThread();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JPanel drawPanel = new JPanel();
        drawPanel.setSize(660, 660);
        JLabel label = new JLabel( "恭喜你活到了最後" );
        label.setFont( new Font( "Arial", Font.BOLD, 20 ) );
        label.setBounds( 200, 50, 300, 60 );
        JButton backBtn = new JButton( "返回" );
        backBtn.setFont(new Font("Arial", Font.BOLD, 25));
        backBtn.setBounds(180, 400, 300, 60);
        backBtn.addActionListener((e) -> {
            this.showUI();
        });
        drawPanel.add(label);
        drawPanel.add(backBtn);
        this.setContentPane(drawPanel);
    }
}
