package com.conflux.killer.client.message;

import com.conflux.killer.client.tcp.TCPClient;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import static com.conflux.killer.core.message.CMCode.ATTACK;
import static com.conflux.killer.core.message.CMCode.MOVE;

public class MessageSenderImpl implements MessageSender {

    private TCPClient client;

    public MessageSenderImpl( TCPClient client ) {
        this.client = client;
    }

    @Override
    public void fireAttack( Skill skill, Direction direction ) {
        String message = String.format( "%s,%s,%s", ATTACK.ordinal(), skill, direction );
        client.sendMessage( message );
    }

    @Override
    public void updatePosition( Direction direction ) {
        String message = String.format( "%s,%s", MOVE.ordinal(), direction );
        client.sendMessage( message );
    }
}
