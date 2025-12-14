package com.TETOSOFT.tilegame.state;

import com.TETOSOFT.tilegame.GameEngine;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import com.TETOSOFT.tilegame.GameEngine;
import com.TETOSOFT.tilegame.logger.GameLogger;
import java.awt.event.KeyEvent;

public class PauseState implements GameState{
     private GameEngine engine;

    public PauseState(GameEngine engine) {
          GameLogger.stateEnter(PauseState.class.getSimpleName());
        this.engine = engine;
    }

    @Override
    public void update(long elapsedTime) {
        // Si joueur appuie sur P encore une fois, revenir au jeu
        if (engine.isKeyPressed(KeyEvent.VK_P)) {
            engine.setCurrentState(new PlayingState(engine)); // retourne à l'état normal
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
    // 2. Dessiner le HUD (interface)
        drawHUD(g);
    }
    private void drawHUD(Graphics2D g) {
        // Dessinez d'abord un fond pour le HUD
        g.setColor(new Color(0, 0, 0, 128)); // Fond semi-transparent
        g.fillRect(0, 0, 800, 40);

        // Puis le texte
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));

        g.drawString("Press ESC to Menu", 10, 20);

        g.setColor(Color.GREEN);
        g.drawString("Coins: " + engine.getCollectedStars(), 200, 20);

        g.setColor(Color.YELLOW);
        g.drawString("Lives: " + engine.getNumLives(), 350, 20);

        if (engine.getMapLoader() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Map: " + engine.getMapLoader().currentMap, 500, 20);
        }

        g.setColor(Color.CYAN);
        if(engine.getCurrentState().toString()=="PlayingState")
        {
           g.drawString("PLAYING STATE", 650, 20);
        }else {
           g.drawString("PAUSE STATE", 650, 20);
        }
    
        // Afficher l'état d'invincibilité
        if (engine.isPlayerInvincible()) {
            g.setColor(Color.RED);
            g.drawString("Decorator : INVINCIBLE", 600.0f, 40.0f);
        }
         if (engine.getPlayerMaxSpeed()!=0) {
            g.setColor(Color.RED);
            g.drawString("Decorator : Speed = "+engine.getPlayerMaxSpeed(), 600.0f, 60.0f);
        }
    }
    @Override
public void handleInput() {
    // Pas d'entrée spécifique, le update() gère la touche P
}
    
}
