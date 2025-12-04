package com.TETOSOFT.tilegame.composite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.TETOSOFT.graphics.Sprite;
import com.TETOSOFT.tilegame.sprites.Creature;
import com.TETOSOFT.tilegame.sprites.Player;
import com.TETOSOFT.tilegame.sprites.PowerUp;

public class LeafAdapter implements GameComponent {

    
    private Sprite sprite;
     public LeafAdapter(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(long deltaTime) {
        try {
            sprite.getClass().getMethod("update", long.class).invoke(sprite, deltaTime);
        } catch (Exception e) {
            // Si update n'existe pas, pas de comportement
        }
    }

    @Override
public void render(Graphics2D g) {
    // 1. Dessine le sprite
    try {
        sprite.getClass().getMethod("draw", Graphics2D.class).invoke(sprite, g);
    } catch (Exception e) {
        try {
            sprite.getClass().getMethod("render", Graphics2D.class).invoke(sprite, g);
        } catch (Exception ignored) {}
    }
    
    // 2. DÉMONSTRATION : Contour selon le type de sprite
    Rectangle bounds = getBounds();
    
    if (sprite instanceof Player) {
        g.setColor(Color.GREEN);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.drawString("Player via Composite", bounds.x, bounds.y - 10);
    } else if (sprite instanceof Creature) {
        g.setColor(Color.RED);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    } else if (sprite instanceof PowerUp) {
        g.setColor(Color.YELLOW);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}

    @Override
    public Rectangle getBounds() {
        try {
            return (Rectangle) sprite.getClass().getMethod("getBounds").invoke(sprite);
        } catch (Exception e) {
            return new Rectangle(0, 0, 0, 0);
        }
    }

     public Sprite getSprite() {
        return sprite;
    }

    
    
    
}
