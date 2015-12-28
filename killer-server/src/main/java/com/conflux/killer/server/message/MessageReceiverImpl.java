package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Message;
import com.conflux.killer.server.cdc.DataCenter;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

public class MessageReceiverImpl implements MessageReceiver {

    private DataCenter dataCenter;

    public MessageReceiverImpl( DataCenter dataCenter ) {
        this.dataCenter = dataCenter;
    }

    @Override
    public void receiveMessage( Message message ) {
        String[] commands = message.content.split( "," );
        if (commands[0].equals( "1" )) {
            dataCenter.fireAttack( message.senderId, Skill.valueOf( commands[ 1 ] ), Direction.valueOf( commands[ 2 ] ) );
        } else if (commands[0].equals( "2" )) {
            dataCenter.updatePosition( message.senderId, Direction.valueOf( commands[1] ) );
        } else if (commands[0].equals( "3" )) {
            dataCenter.addCharacter( message.senderId );
        } else if (commands[0].equals( "4" )) {
            dataCenter.removeCharacter( message.senderId );
        }

    }


}
