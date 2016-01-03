package com.conflux.killer.server.cdc;

import com.conflux.killer.core.map.SceneData;
import com.conflux.killer.core.map.SceneDataImpl;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;
import com.conflux.killer.server.message.MessageSender;

import java.awt.*;
import java.util.*;

import static com.conflux.killer.server.Main.*;

public class DataCenterImpl implements DataCenter {

    private MessageSender sender;
    private Queue< Point > initialPoints;
    private Map< Integer, Point > characters;
    private SceneData sceneData;
    private boolean started = false;

    public DataCenterImpl( MessageSender sender ) {
        this.sender = sender;
        this.characters = new HashMap<>();
        this.initialPoints = new LinkedList<>();
        this.initialPoints.addAll( Arrays.asList(
                new Point( 1, 1 ), new Point( 1, 100 ), new Point( 100, 1 ), new Point( 100, 100 )
        ) );
        this.sceneData = new SceneDataImpl();
        this.sceneData.loadMap();
    }

    @Override
    public void addCharacter( int clientId ) {
        characters.put( clientId, null );
        sender.sendNewClientId( clientId );
        if ( characters.size() == 4 ) {
            started = true;
            for ( Map.Entry<Integer, Point> pair: characters.entrySet()) {
                pair.setValue(initialPoints.poll());
            }
            sender.gameStartWithAllCharacters( characters );
        } else {
            sender.currentPlayerNumbers( characters.size() );
        }
    }

    @Override
    public void updatePosition( int clientId, Direction direction ) {
        Point p = characters.get( clientId );
        int newX = p.x + direction.getX();
        int newY = p.y + direction.getY();

        int[][] map = this.sceneData.getView();

        if ((newX - 1) < 0 || (newX - 1) >= map.length ||
            (newY - 1) < 0 || (newY - 1)  >= map[newX - 1].length ||
            map[newX - 1][newY - 1] == 1) {
            return;
        }

        if ( newX > 0 && newX <= 100 && newY > 0 && newY <= 100 ) {
            p.x = newX;
            p.y = newY;

            sender.updatePosition( clientId, p );
        }
    }

    @Override
    public void fireAttack( int clientId, Skill skill, Direction direction ) {

        Point p = characters.get( clientId );
        Point pp = new Point( p.x, p.y );

        switch ( skill ) {
            case Q:
                pp.x += direction.getX();
                pp.y += direction.getY();
                break;
            case W:
                pp.x += direction.getX() * 2;
                pp.y += direction.getY() * 2;
                break;
            case E:
                pp.x += direction.getX() * 3;
                pp.y += direction.getY() * 3;
                break;
        }

        sender.fireAttack( skill, direction, pp );

        for ( Map.Entry< Integer, Point > pair : characters.entrySet() ) {
            if ( pair.getValue().equals( pp ) ) {
                removeCharacter( pair.getKey() );
                break;
            }
        }
    }

    @Override
    public void removeCharacter( int clientId ) {
        characters.remove( clientId );
        if (!started) {
            sender.currentPlayerNumbers( characters.size() );
        }
        sender.removeCharacter( clientId );
        if (characters.size() == 1 && started) {
            isGaming = false;
            started = false;
        }
    }
}
