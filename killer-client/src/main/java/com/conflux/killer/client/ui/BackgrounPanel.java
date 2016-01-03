package com.conflux.killer.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rueian on 2016/1/3.
 */
public class BackgrounPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Images.welcome, 0, 0, null);
    }
}
