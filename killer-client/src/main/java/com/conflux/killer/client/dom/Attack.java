package com.conflux.killer.client.dom;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.*;

public class Attack {
    public Skill type;
    public Point position;
    public Direction direction;

    public Attack( Skill type, Point position, Direction direction ) {
        this.type = type;
        this.position = position;
        this.direction = direction;
    }
}

