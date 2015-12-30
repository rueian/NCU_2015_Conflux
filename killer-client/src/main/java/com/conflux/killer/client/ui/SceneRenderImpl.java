package com.conflux.killer.client.ui;

import com.conflux.killer.client.dom.ObjectCenter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SceneRenderImpl implements SceneRender {
    private SceneData sceneData;
    private ObjectCenter objectCenter;
    private Graphics g;

    public SceneRenderImpl(SceneData sceneData, ObjectCenter objectCenter, Graphics g) {
        this.sceneData = sceneData;
        this.objectCenter = objectCenter;
        this.g = g;
    }

    @Override
    public void renderScene() {
        Image offScreen = new BufferedImage(660, 660, BufferedImage.TYPE_3BYTE_BGR);
        Graphics offGraphics = offScreen.getGraphics();

        Point p = objectCenter.getMe().position;

        int[][] map = sceneData.getView();

        int i = p.x;
        int j = p.y;

        int minI = i - 6;
        int minJ = j - 6;
        int maxI = i + 5;
        int maxJ = j + 5;


        for (j = minJ; j < maxJ; j ++) {
            for(i = minI; i < maxI; i ++) {
                if (i >= 0 && i < map.length && j >= 0 && j < map[i].length){
                    if (map[i][j] == 1) {
                        offGraphics.setColor(Color.BLACK);
                    } else {
                        offGraphics.setColor(Color.WHITE);
                    }
                    offGraphics.fillRect( i * 60, j * 60, 60, 60 );
                }
            }
        }

        g.drawImage(offScreen, 0, 0, null);
    }
}
