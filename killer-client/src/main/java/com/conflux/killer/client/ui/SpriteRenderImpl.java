package com.conflux.killer.client.ui;

import com.conflux.killer.client.dom.Attack;
import com.conflux.killer.client.dom.Character;
import com.conflux.killer.client.dom.ObjectCenter;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rueian on 2016/1/1.
 */
public class SpriteRenderImpl implements SpriteRender {

    private ObjectCenter objectCenter;

    public SpriteRenderImpl(ObjectCenter objectCenter) {
        this.objectCenter = objectCenter;
    }

    @Override
    public void renderSprite(Graphics g) {
        Character m = objectCenter.getMe();
        for (Map.Entry<Integer, Character> pair: objectCenter.getAllCharacters().entrySet()) {
            Character c = pair.getValue();
            Sprite s = c.sprite;
            int xx = c.position.x - m.position.x + 5;
            int yy = c.position.y - m.position.y + 5;
            s.setPosition(new Point(xx * 60, yy * 60));
            s.update();
            s.draw(g);
        }

        try {
            for (Iterator<Attack> iterator = objectCenter.getAllAttacks().iterator(); iterator.hasNext();) {
                Attack a = iterator.next();
                a.updateCounter();
                Sprite s = a.sprite;
                int xx = a.position.x - m.position.x + 5;
                int yy = a.position.y - m.position.y + 5;
                s.setPosition(new Point(xx * 60, yy * 60));
                s.update();
                s.draw(g);
                if (a.counter > 12) {
                    iterator.remove();
                }
            }
        } catch (Exception e) {

        }

    }
}
