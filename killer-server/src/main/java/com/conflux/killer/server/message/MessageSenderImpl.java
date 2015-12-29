package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.SMCode;
import com.conflux.killer.core.message.Skill;
import com.conflux.killer.server.tcp.TCPServer;

import java.awt.*;
import java.util.Map;

import static com.conflux.killer.core.message.SMCode.*;

public class MessageSenderImpl implements MessageSender {

    private TCPServer server;

    public MessageSenderImpl( TCPServer server ) {
        this.server = server;
    }

    @Override
    public void fireAttack( Skill skill, Direction direction, Point point ) {
        String msg = String.format( "%s,%s,%s,%d,%d", ATTACK.ordinal(), skill.name(), direction.name(), point.x, point.y );
        server.sendMessage( msg );
    }

    @Override
    public void updatePosition( int clientId, Point point ) {
        String msg = String.format( "%s,%d,%d,%d", MOVE.ordinal(), clientId, point.x, point.y );
        server.sendMessage( msg );
    }

    @Override
    public void removeCharacter( int clientId ) {
        String msg = String.format( "%s,%d", DEAD.ordinal(), clientId );
        server.sendMessage( msg );
    }

    @Override
    public void currentPlayerNumbers( int numbers ) {
        String msg = String.format( "%s,%d", PLAYER_NUMBER.ordinal(), numbers );
        server.sendMessage( msg );
    }

    @Override
    public void gameStartWithAllCharacters( Map< Integer, Point > positions ) {
        StringBuilder builder = new StringBuilder( 128 );
        for ( Map.Entry< Integer, Point > pair : positions.entrySet() ) {
            builder.append( pair.getKey() )
                    .append( ":" )
                    .append( pair.getValue().x )
                    .append( ":" )
                    .append( pair.getValue().y )
                    .append( "," );
        }
        String msg = String.format( "%s,%s", START.ordinal(), builder.toString() );
        server.sendMessage( msg );
    }

    @Override
    public void sendNewClientId( int clientId ) {
        server.sendMessage( PLAYER_ID.ordinal() + "," + clientId );
    }
}
