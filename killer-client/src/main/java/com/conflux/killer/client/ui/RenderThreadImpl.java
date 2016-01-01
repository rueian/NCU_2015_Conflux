package com.conflux.killer.client.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderThreadImpl implements RenderThread {

    private SceneRender sceneRender;
    private SpriteRender spriteRender;
    private Graphics g;
    private Thread thread;

    public RenderThreadImpl(SceneRender sceneRender, SpriteRender spriteRender, Graphics g) {
        this.sceneRender = sceneRender;
        this.spriteRender = spriteRender;
        this.g = g;
    }


    @Override
    public void startRenderThread() {
        thread = new Thread(() -> {
            while (true) {

                Image offScreen = new BufferedImage(660, 660, BufferedImage.TYPE_3BYTE_BGR);
                Graphics offGraphics = offScreen.getGraphics();
                sceneRender.renderScene(offGraphics);
                spriteRender.renderSprite(offGraphics);
                g.drawImage(offScreen, 0, 0, null);

                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    @Override
    public void stopRenderThread() {
        thread.interrupt();
    }
}
