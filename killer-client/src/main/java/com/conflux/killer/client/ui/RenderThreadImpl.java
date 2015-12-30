package com.conflux.killer.client.ui;

public class RenderThreadImpl implements RenderThread {

    private SceneRender sceneRender;

    public RenderThreadImpl(SceneRender sceneRender) {
        this.sceneRender = sceneRender;
    }


    @Override
    public void startRenderThread() {
        new Thread(sceneRender::renderScene);
    }
}
