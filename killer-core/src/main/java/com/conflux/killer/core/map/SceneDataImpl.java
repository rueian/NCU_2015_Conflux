package com.conflux.killer.core.map;


import java.io.*;

public class SceneDataImpl implements SceneData {

    private int[][] map;

    @Override
    public void loadMap() {
        map = new int[100][100];
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getClass().getResource("/map.txt").getPath()));
            for (int i = 0 ; i < 100 ;i++) {
                String line = br.readLine();
                for (int j = 0 ; j < 100 ;j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int[][] getView() {
        return map;
    }
}
