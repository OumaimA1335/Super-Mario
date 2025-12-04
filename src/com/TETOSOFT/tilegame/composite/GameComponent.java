package com.TETOSOFT.tilegame.composite;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface GameComponent {

    void update(long deltaTime);

    void render(Graphics2D g);

    default void add(GameComponent component) {
        throw new UnsupportedOperationException("add() not supported on leaf.");
    }

    default void remove(GameComponent component) {
        throw new UnsupportedOperationException("remove() not supported on leaf.");
    }

    Rectangle getBounds();
}
