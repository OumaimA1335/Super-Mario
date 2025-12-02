package com.TETOSOFT.tilegame.state;

import com.TETOSOFT.tilegame.GameEngine;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class MenuStateSimple implements GameState {

    private GameEngine game;
    private long startTime;
    private boolean showPressEnter = true;
    private long lastBlinkTime = 0;

    public MenuStateSimple(GameEngine game) {
        this.game = game;
        this.startTime = System.currentTimeMillis();
        this.lastBlinkTime = startTime;
        System.out.println("STATE: MenuStateSimple activé - En attente d'entrée utilisateur");
    }

    @Override
    public void update(long elapsedTime) {
        long currentTime = System.currentTimeMillis();

        // Animation clignotante
        if (currentTime - lastBlinkTime > 500) {
            showPressEnter = !showPressEnter;
            lastBlinkTime = currentTime;
        }
        // Utilisez les méthodes de GameEngine
        if (game.isEnterPressed()) {
            System.out.println("ENTREE pressé - démarrage du jeu");
            game.setCurrentState(new PlayingState(game));
            return;
        }

        if (game.isEscapePressed()) {
            System.out.println("ECHAP pressé - retour au menu");
            game.stop();
            return;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Dessiner un écran de menu COMPLET avec les deux instructions
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        // Titre principal
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("SUPER MARIO", 250, 200);

        // Sous-titre
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("STATE PATTERN - DÉMONSTRATION", 180, 250);

        // PREMIÈRE IMAGE/TEXTE : "APPUYER SUR ENTREE POUR JOUER"
        if (showPressEnter) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("APPUYER SUR ENTREE POUR JOUER", 180, 350);
        }

        // DEUXIÈME IMAGE/TEXTE : "APPUYER SUR ECHAP POUR QUITTER" 
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("APPUYER SUR ECHAP POUR QUITTER", 190, 450);

        // Instructions de contrôle (optionnel)
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Contrôles: FLÈCHES pour se déplacer, ESPACE pour sauter", 160, 500);
    }

    @Override
    public void handleInput() {
        // Cette méthode peut être utilisée si vous avez une gestion d'input séparée
    }
}
