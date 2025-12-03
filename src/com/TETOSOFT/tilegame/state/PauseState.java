package com.TETOSOFT.tilegame.state;

import com.TETOSOFT.tilegame.GameEngine;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import com.TETOSOFT.tilegame.GameEngine;
import java.awt.event.KeyEvent;

public class PauseState implements GameState{
     private GameEngine engine;

    public PauseState(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void update(long elapsedTime) {
        // Si joueur appuie sur P encore une fois, revenir au jeu
        if (engine.isKeyPressed(KeyEvent.VK_P)) {
            engine.setCurrentState(null); // retourne à l'état normal
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Dessiner le jeu derrière le texte de pause
        engine.getDrawer().draw(g, engine.getMap(), engine.getScreen().getWidth(), engine.getScreen().getHeight());
        
        // Dessiner l'overlay Pause
        g.setColor(new Color(0, 0, 0, 150)); // overlay semi-transparent
        g.fillRect(0, 0, engine.getScreen().getWidth(), engine.getScreen().getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("PAUSE", engine.getScreen().getWidth()/2 - 80, engine.getScreen().getHeight()/2);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Appuyez sur P pour reprendre", engine.getScreen().getWidth()/2 - 150, engine.getScreen().getHeight()/2 + 50);
    }
    @Override
public void handleInput() {
    // Pas d'entrée spécifique, le update() gère la touche P
}
    
}
