package com.conflux.killer.client.message;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

public interface MessageSender {
    void fireAttack( Skill skill, Direction direction );

    void updatePosition( Direction direction );
}
