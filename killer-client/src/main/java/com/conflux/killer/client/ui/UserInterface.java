package com.conflux.killer.client.ui;

import com.conflux.killer.client.tcp.TCPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame {

    public UserInterface( KeyListener keyListener, Runnable runnable, final TCPClient client ) throws HeadlessException {
        this.setSize( 660, 660 );
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.addKeyListener( keyListener );

        JLabel label = new JLabel( "The Killer" );
        label.setFont( new Font( "Arial", Font.BOLD, 50 ) );
        label.setBounds( 200, 50, 300, 60 );

        final JTextField textField = new JTextField();
        textField.setBounds( 180, 300, 300, 60 );
        textField.setText( "127.0.0.1" );

        JButton button = new JButton( "CONNECT" );
        button.setFont( new Font( "Arial", Font.BOLD, 25 ) );
        button.setBounds( 180, 400, 300, 60 );
        button.addActionListener( (e)-> {
                client.connectionServer( textField.getText(), 8765 );
                new Thread( runnable ).start();
        });

        JPanel panel = new JPanel();
        panel.setLayout( null );
        panel.add( label );
        panel.add( textField );
        panel.add( button );

        this.setContentPane( panel );

        this.setVisible( true );
    }
}
