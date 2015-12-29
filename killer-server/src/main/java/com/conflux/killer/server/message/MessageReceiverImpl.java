package com.conflux.killer.server.message;

import com.conflux.killer.core.message.*;
import com.conflux.killer.server.cdc.DataCenter;

import static com.conflux.killer.core.message.CMCode.*;

public class MessageReceiverImpl implements MessageReceiver {

    private DataCenter dataCenter;

    public MessageReceiverImpl( DataCenter dataCenter ) {
        this.dataCenter = dataCenter;
    }

    @Override
    public void receiveMessage( Message message ) {
        String[] commands = message.content.split( "," );
        int code = Integer.parseInt( commands[0] );
        if ( code == ATTACK.ordinal() ) {
            dataCenter.fireAttack( message.senderId, Skill.valueOf( commands[ 1 ] ), Direction.valueOf( commands[ 2 ] ) );
        } else if ( code == MOVE.ordinal() ) {
            dataCenter.updatePosition( message.senderId, Direction.valueOf( commands[ 1 ] ) );
        } else if ( code == ADD_PLAYER.ordinal() ) {
            dataCenter.addCharacter( message.senderId );
        } else if ( code == REMOVE_PLAYER.ordinal() ) {
            dataCenter.removeCharacter( message.senderId );
        }

    }


}
