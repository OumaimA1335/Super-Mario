package com.TETOSOFT.tilegame.state;

import com.TETOSOFT.tilegame.GameEngine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class PlayingState implements GameState {

    private GameEngine game;

    public PlayingState(GameEngine game) {
        this.game = game;
        System.out.println("STATE: PlayingState activé");
    }

    @Override
    public void update(long elapsedTime) {
        // Appelle la logique de jeu originale de GameEngine
        game.updateGameLogic(elapsedTime);
    }

    @Override
    public void draw(Graphics2D g) {
      //  System.out.println("PlayingState.draw() - Drawing game");

        // 1. Dessiner l'arrière-plan et la carte
        if (game.getDrawer() != null && game.getMap() != null) {
            game.getDrawer().draw(g, game.getMap(),
                    game.getScreen().getWidth(), game.getScreen().getHeight());
        } else {
            // Fallback en cas d'erreur
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.RED);
            g.drawString("ERROR: Game resources not loaded", 300, 300);
            return;
        }

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
        g.drawString("Coins: " + game.getCollectedStars(), 200, 20);

        g.setColor(Color.YELLOW);
        g.drawString("Lives: " + game.getNumLives(), 350, 20);

        if (game.getMapLoader() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Map: " + game.getMapLoader().currentMap, 500, 20);
        }

        g.setColor(Color.CYAN);
        g.drawString("PLAYING STATE", 650, 20);
    }

    @Override
    public void handleInput() {
        // La gestion d'input reste dans GameEngine
    }
}
