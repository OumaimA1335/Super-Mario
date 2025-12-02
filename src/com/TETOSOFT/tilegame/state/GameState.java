// Fichier: src/com/TETOSOFT/tilegame/GameState.java
package com.TETOSOFT.tilegame.state;

import java.awt.Graphics2D;

public interface GameState {
    void update(long elapsedTime);
    void draw(Graphics2D g);
    void handleInput();
}