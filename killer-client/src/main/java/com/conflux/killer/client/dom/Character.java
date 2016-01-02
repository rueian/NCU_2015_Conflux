package com.conflux.killer.client.dom;

import com.conflux.killer.client.ui.Images;
import com.conflux.killer.client.ui.Sprite;
import com.conflux.killer.core.message.Direction;

import java.awt.*;

public class Character {

    public int clientId;
    public Point position;
    public Direction direction;
    public Sprite sprite;

    public Character( int clientId, Point position ) {
        this.clientId = clientId;
        this.direction = Direction.DOWN;
        this.position = position;

        this.sprite = new Sprite(null, Images.characters.get(clientId % 4).get(Direction.DOWN), 0, 1, 5, new Point(0, 0), new Point(0, 0), 1, Sprite.BA_DIE);
    }

    public void setPosition( Point position ) {
        int diffX = position.x - this.position.x;
        int diffY = position.y - this.position.y;
        if (diffX > 0) {
            direction = Direction.RIGHT;
            this.sprite.setImage(Images.characters.get(clientId % 4).get(Direction.RIGHT));
        } else if ( diffX < 0) {
            direction = Direction.LEFT;
            this.sprite.setImage(Images.characters.get(clientId % 4).get(Direction.LEFT));
        } else if ( diffY > 0) {
            direction = Direction.DOWN;
            this.sprite.setImage(Images.characters.get(clientId % 4).get(Direction.DOWN));
        } else if (diffY < 0) {
            direction = Direction.UP;
            this.sprite.setImage(Images.characters.get(clientId % 4).get(Direction.UP));
        }
        this.position = position;
    }

}
