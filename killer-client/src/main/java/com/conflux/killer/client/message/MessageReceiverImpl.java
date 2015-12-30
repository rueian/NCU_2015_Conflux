package com.conflux.killer.client.message;

import com.conflux.killer.client.dom.ObjectCenter;
import com.conflux.killer.client.ui.GameControlable;
import com.conflux.killer.core.message.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.conflux.killer.core.message.SMCode.*;

public class MessageReceiverImpl implements MessageReceiver {

    private ObjectCenter objectCenter;

    public MessageReceiverImpl(ObjectCenter objectCenter) {
        this.objectCenter = objectCenter;
    }

    @Override
    public void receiveMessage( Message message ) {
        String[] commands = message.content.split( "," );

        int type = Integer.parseInt( commands[ 0 ] );

        if ( type == ATTACK.ordinal() ) {
            Skill skill = Skill.valueOf( commands[ 1 ] );
            Direction direction = Direction.valueOf( commands[ 2 ] );
            int x = Integer.parseInt( commands[ 3 ] );
            int y = Integer.parseInt( commands[ 4 ] );
            objectCenter.fireAttack( skill, direction, new Point( x, y ) );
            return;
        }

        if ( type == MOVE.ordinal() ) {
            int clientId = Integer.parseInt( commands[ 1 ] );
            int x = Integer.parseInt( commands[ 2 ] );
            int y = Integer.parseInt( commands[ 3 ] );
            objectCenter.updatePosition( clientId, new Point( x, y ) );
            return;
        }

        if ( type == DEAD.ordinal() ) {
            int clientId = Integer.parseInt( commands[ 1 ] );
            objectCenter.removeCharacterId( clientId );
            return;
        }

        if ( type == PLAYER_NUMBER.ordinal() ) {
            int numbers = Integer.parseInt( commands[ 1 ] );
            objectCenter.setCurrentPlayerNumbers( numbers );
            return;
        }

        if ( type == START.ordinal() ) {
            Map< Integer, Point > characters = new HashMap<>();
            for ( int i = 1; i < commands.length; i++ ) {
                String[] info = commands[ i ].split( ":" );
                int id = Integer.parseInt( info[ 0 ] );
                int x = Integer.parseInt( info[ 1 ] );
                int y = Integer.parseInt( info[ 2 ] );
                characters.put( id, new Point( x, y ) );
            }
            objectCenter.gameStartAndInitAll( characters );
            return;

        }

        if ( type == PLAYER_ID.ordinal() ) {
            int clientId = Integer.parseInt( commands[ 1 ] );
            objectCenter.setMyCharacterId( clientId );
            return;
        }

        if ( type == END.ordinal() ) {
            objectCenter.forceEnd();
        }
    }
}
