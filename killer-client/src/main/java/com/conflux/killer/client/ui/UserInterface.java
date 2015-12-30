package com.conflux.killer.client.ui;

import com.conflux.killer.client.tcp.TCPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame {

    public UserInterface( KeyListener keyListener, Runnable consumeThread, final TCPClient client ) throws HeadlessException {
        this.setSize( 660, 660 );
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.addKeyListener( keyListener );

        JLabel label = new JLabel( "The Killer" );
        label.setFont( new Font( "Arial", Font.BOLD, 50 ) );
        label.setBounds( 200, 50, 300, 60 );

        final JTextField textField = new JTextField();
        textField.setBounds( 180, 250, 300, 60 );
        textField.setText( "127.0.0.1" );

        JButton button = new JButton( "CONNECT" );
        button.setFont( new Font( "Arial", Font.BOLD, 25 ) );
        button.setBounds( 180, 400, 300, 60 );
        button.addActionListener( (e)-> {
                client.connectionServer( textField.getText(), 8765 );
                new Thread( consumeThread ).start();
        });

        String helpMessage = "這是一個關於猜疑與生存的遊戲，生存為玩家的第一要務。為了生存，" +
                "玩家必須先下手為強先殺掉其他對手，因為玩家永遠不知道會不會被背叛、偷襲。\n" +
                "你可以使用的按鍵有:上,下,左,右,Q,W,E";

        JButton helpBtn = new JButton( "HELP" );
        helpBtn.setFont( new Font( "Arial", Font.BOLD, 25 ) );
        helpBtn.setBounds( 180, 320, 300, 60 );
        helpBtn.addActionListener( (e)-> JOptionPane.showMessageDialog( this, helpMessage ));

        JPanel panel = new JPanel();
        panel.setLayout( null );
        panel.add( label );
        panel.add( helpBtn );
        panel.add( textField );
        panel.add( button );

        this.setContentPane( panel );

        this.setVisible( true );
    }
}
