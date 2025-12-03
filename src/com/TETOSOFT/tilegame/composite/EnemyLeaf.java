package com.TETOSOFT.tilegame.composite;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EnemyLeaf implements GameComponent {
    private int x, y, w, h;
    private boolean alive = true;

    public EnemyLeaf(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.w = w; this.h = h;
    }

    @Override
    public void update(long deltaTime) {
        // logique de déplacement / IA
        // si meurt:
        // alive = false;
    }

    @Override
    public void render(Graphics2D g) {
        // dessiner sprite ou rectangle de debug
        g.drawRect(x, y, w, h);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}
