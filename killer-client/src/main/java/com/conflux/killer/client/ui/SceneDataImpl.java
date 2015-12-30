package com.conflux.killer.client.ui;

public class SceneDataImpl implements SceneData {

    private int[][] map;

    @Override
    public void loadMap() {
        map = new int[100][100];
        map[10][10] = 1;
        map[20][20] = 1;
    }

    @Override
    public int[][] getView() {
        return map;
    }
}
