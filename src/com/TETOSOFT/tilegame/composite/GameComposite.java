package com.TETOSOFT.tilegame.composite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameComposite implements GameComponent {

    protected final List<GameComponent> children = new ArrayList<>();

    @Override
    public void update(long deltaTime) {
    // TEST: Affiche périodiquement le nombre d'éléments
    long currentTime = System.currentTimeMillis();
    if (currentTime % 3000 < 16) { // Toutes les 3 secondes
        System.out.println("[COMPOSITE] ✓ Update de " + children.size() + " éléments");
    }
    
    Iterator<GameComponent> it = new ArrayList<>(children).iterator();
    while (it.hasNext()) {
        it.next().update(deltaTime);
    } 
}

    @Override
    public void render(Graphics2D g) {
    // TEST: Compte les rendus
    System.out.println("[COMPOSITE] ✓ Render de " + children.size() + " éléments");
    
    for (GameComponent c : children) {
        c.render(g);
    }
}

    @Override
    public void add(GameComponent component) {
        children.add(component);
        // logging optionnel
        // System.out.println("[Composite] Added " + component.getClass().getSimpleName());
    }

    @Override
    public void remove(GameComponent component) {
        children.remove(component);
        // System.out.println("[Composite] Removed " + component.getClass().getSimpleName());
    }

    @Override
    public Rectangle getBounds() {
        return null; // on peut calculer une bounding box globale si nécessaire
    }

    public List<GameComponent> getChildren() {
        return children;
    }

    public int size() {
    return children.size();
}


    
}
