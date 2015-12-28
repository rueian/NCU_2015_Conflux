package com.conflux.killer.server.cdc;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

public interface DataCenter {
    void addCharacter( int clientId );

    void updatePosition( int clientId, Direction direction );

    void fireAttack( int clientId, Skill skill, Direction direction );

    void removeCharacter( int clientId );
}
