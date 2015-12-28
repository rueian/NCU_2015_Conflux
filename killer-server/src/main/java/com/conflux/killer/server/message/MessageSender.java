package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.*;
import java.util.Map;

public interface MessageSender {
    void fireAttack( Skill skill, Direction direction, Point point );

    void updatePosition( int clientId, Point point );

    void removeCharacter( int clientId );

    void currentPlayerNumbers( int numbers );

    void gameStartWithAllCharacters( Map< Integer, Point > positions );

    void sendNewClientId( int client );
}
