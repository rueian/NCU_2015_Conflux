package com.conflux.killer.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame {

    public UserInterface( KeyListener keyListener ) throws HeadlessException {
        this.setSize( 400, 400 );
        this.addKeyListener( keyListener );
        this.setVisible( true );
    }
}
