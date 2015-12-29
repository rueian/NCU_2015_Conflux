package com.conflux.killer.client.dom;

import com.conflux.killer.core.message.Direction;

import java.awt.*;

public class Character {

    public int clientId;
    public Point position;
    public Direction direction;

    public Character( int clientId, Point position ) {
        this.clientId = clientId;
        this.direction = Direction.DOWN;
        this.position = position;
    }

    public void setPosition( Point position ) {
        int diffX = position.x - this.position.x;
        int diffY = position.y - this.position.y;
        if (diffX > 0) {
            direction = Direction.RIGHT;
        } else if ( diffX < 0) {
            direction = Direction.LEFT;
        } else if ( diffY > 0) {
            direction = Direction.DOWN;
        } else if (diffY < 0) {
            direction = Direction.UP;
        }
        this.position = position;
    }

}
