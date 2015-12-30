package com.conflux.killer.client.ui;

public interface GameControlable {

    void showUI();
    void startGame();
    void winGame();
    void endGame();
    void updateCharacterNum( int num );
}
