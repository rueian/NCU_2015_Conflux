package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;
import com.conflux.killer.server.tcp.TCPServer;

import java.awt.*;
import java.util.Map;

public class MessageSenderImpl implements MessageSender {

    private TCPServer server;

    public MessageSenderImpl( TCPServer server ) {
        this.server = server;
    }

    @Override
    public void fireAttack( Skill skill, Direction direction, Point point ) {
        String msg = String.format( "1,%s,%s,%d,%d", skill.name(), direction.name(), point.x, point.y );
        server.sendMessage( msg );
    }

    @Override
    public void updatePosition( int clientId, Point point ) {
        String msg = String.format( "2,%d,%d,%d", clientId, point.x, point.y );
        server.sendMessage( msg );
    }

    @Override
    public void removeCharacter( int clientId ) {
        String msg = String.format( "3,%d", clientId );
        server.sendMessage( msg );
    }

    @Override
    public void currentPlayerNumbers( int numbers ) {
        String msg = String.format( "4,%d", numbers );
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
        String msg = String.format( "5,%s", builder.toString() );
        server.sendMessage( msg );
    }

    @Override
    public void sendNewClientId( int clientId ) {
        server.sendMessage( "6," + clientId );
    }
}
