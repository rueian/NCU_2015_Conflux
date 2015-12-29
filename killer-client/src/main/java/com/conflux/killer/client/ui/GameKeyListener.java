package com.conflux.killer.client.ui;

import com.conflux.killer.client.dom.ObjectCenter;
import com.conflux.killer.client.message.MessageSender;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class GameKeyListener extends KeyAdapter {

    private MessageSender messageSender;
    private ObjectCenter objectCenter;

    public GameKeyListener( MessageSender messageSender, ObjectCenter objectCenter ) {
        this.messageSender = messageSender;
        this.objectCenter = objectCenter;
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        switch ( e.getKeyCode() ){
            case VK_UP:
                messageSender.updatePosition( Direction.UP );
                break;
            case VK_DOWN:
                messageSender.updatePosition( Direction.DOWN );
                break;
            case VK_LEFT:
                messageSender.updatePosition( Direction.LEFT );
                break;
            case VK_RIGHT:
                messageSender.updatePosition( Direction.RIGHT );
                break;
            case VK_Q:
                messageSender.fireAttack( Skill.Q, objectCenter.getMe().direction );
                break;
            case VK_W:
                messageSender.fireAttack( Skill.W, objectCenter.getMe().direction );
                break;
            case VK_E:
                messageSender.fireAttack( Skill.E, objectCenter.getMe().direction );
                break;
        }
    }

}
