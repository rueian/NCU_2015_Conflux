package com.conflux.killer.client.dom;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public interface ObjectCenter {
    void setMyCharacterId( int clientId );

    void updatePosition( int clientId, Point point );

    void fireAttack( Skill skill, Direction direction, Point point );

    void removeCharacterId( int clientId );

    void gameStartAndInitAll( Map< Integer, Point > characters );

    void forceEnd();

    void setCurrentPlayerNumbers( int numbers );

    Character getMe();

    Map< Integer, Character > getAllCharacters();

    List< Attack > getAllAttacks();
}
