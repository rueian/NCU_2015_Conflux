package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.*;
import java.util.Map;

public class MessageSenderImpl implements MessageSender {
    @Override
    public void fireAttack( Skill skill, Direction direction, Point point ) {

    }

    @Override
    public void updatePosition( int clientId, Point point ) {

    }

    @Override
    public void removeCharacter( int clientId ) {

    }

    @Override
    public void currentPlayerNumbers( int numbers ) {

    }

    @Override
    public void gameStartWithAllCharacters( Map< Integer, Point > positions ) {

    }

    @Override
    public void sendNewClientId( int client ) {

    }
}
