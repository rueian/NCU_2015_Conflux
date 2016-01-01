package com.conflux.killer.client.dom;

import com.conflux.killer.client.ui.GameControlable;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectCenterImpl implements ObjectCenter {

    public int clientId = 0;

    public int currentPlayerNumbers = 0;

    public boolean isStarted = false;

    private Map< Integer, Character > characters;

    private List< Attack > attacks;

    private GameControlable gameStartable;

    public ObjectCenterImpl(GameControlable gameStartable) {
        characters = new HashMap<>();
        attacks = new ArrayList<>();
        this.gameStartable = gameStartable;
    }

    @Override
    public void setMyCharacterId( int clientId ) {
        if ( this.clientId == 0 ) {
            this.clientId = clientId;
        }
    }

    @Override
    public void updatePosition( int clientId, Point point ) {
        Character c = characters.get( clientId );
        if ( c != null ) {
            c.setPosition( point );
        }
    }

    @Override
    public void fireAttack( Skill skill, Direction direction, Point point ) {
        attacks.add( new Attack( skill, point, direction ) );
    }

    @Override
    public void removeCharacterId( int clientId ) {
        characters.remove( clientId );
        if (clientId == this.clientId) {
            gameStartable.endGame();
        } else if (characters.size() == 1 && characters.get(this.clientId) != null) {
            gameStartable.winGame();
        }
    }

    @Override
    public void gameStartAndInitAll( Map< Integer, Point > characters ) {
        for ( Map.Entry< Integer, Point > pair : characters.entrySet() ) {
            this.characters.put( pair.getKey(), new Character( pair.getKey(), pair.getValue() ) );
        }
        isStarted = true;
        gameStartable.startGame();
    }

    @Override
    public void forceEnd() {
        characters.remove( this.clientId );
    }

    @Override
    public void setCurrentPlayerNumbers( int numbers ) {
        this.currentPlayerNumbers = numbers;
        gameStartable.updateCharacterNum( numbers );
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public Character getMe() {
        return characters.get( clientId );
    }

    @Override
    public Map< Integer, Character > getAllCharacters() {
        return characters;
    }

    @Override
    public List< Attack > getAllAttacks() {
        return attacks;
    }
}
