package com.conflux.killer.client.dom;

import com.conflux.killer.client.ui.Sprite;
import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import java.awt.*;

public class Attack {
    public Skill type;
    public Point position;
    public Direction direction;
    public Sprite sprite;

    public Attack( Skill type, Point position, Direction direction ) {
        this.type = type;
        this.position = position;
        this.direction = direction;
        this.sprite = new Sprite(null, Images.attacks.get(type), 0, 1, 5, new Point(0, 0), new Point(0, 0), 1, Sprite.BA_DIE);
    }
}

