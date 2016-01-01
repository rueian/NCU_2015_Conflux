package com.conflux.killer.client.dom;

import com.conflux.killer.core.message.Direction;
import com.conflux.killer.core.message.Skill;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rueian on 2016/1/1.
 */
public class Images {

    public static Map<Integer, Map<Direction, Image[]>> characters;

    public static Map<Skill, Image[]> attacks;

    public static void load() {
        characters = new HashMap<>();
        attacks = new HashMap<>();

        for(int i = 0; i < 4; i ++) {
            characters.put(i, new HashMap<>());
            characters.get(i).put(Direction.DOWN, new Image[4]);
            characters.get(i).put(Direction.UP, new Image[4]);
            characters.get(i).put(Direction.LEFT, new Image[4]);
            characters.get(i).put(Direction.RIGHT, new Image[4]);

            readGif(characters.get(i).get(Direction.DOWN), "characters/" + i + "/down.gif");
            readGif(characters.get(i).get(Direction.UP), "characters/" + i + "/up.gif");
            readGif(characters.get(i).get(Direction.LEFT), "characters/" + i + "/left.gif");
            readGif(characters.get(i).get(Direction.RIGHT), "characters/" + i + "/right.gif");
        }

        attacks.put(Skill.E, new Image[15]);
        attacks.put(Skill.Q, new Image[15]);
        attacks.put(Skill.W, new Image[15]);

        readGif(attacks.get(Skill.E), "attacks/0/fire1.gif");
        readGif(attacks.get(Skill.Q), "attacks/1/fire2.gif");
        readGif(attacks.get(Skill.W), "attacks/2/fire3.gif");
    }

    static void readGif(Image[] target, String path) {
        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();

        try {
            File f = new File(Images.class.getResource("/" + path).getFile());
            ImageInputStream ciis = ImageIO.createImageInputStream(f);
            reader.setInput(ciis, false);
            for (int i = 0, count = reader.getNumImages(true); i < count; i++)
            {
                target[i] = reader.read(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
