package com.conflux.killer.client.ui;

public class RenderThreadImpl implements RenderThread {

    private SceneRender sceneRender;
    private Thread thread;

    public RenderThreadImpl(SceneRender sceneRender) {
        this.sceneRender = sceneRender;
    }


    @Override
    public void startRenderThread() {
        thread = new Thread(() -> {
            while (true) {
                sceneRender.renderScene();
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
