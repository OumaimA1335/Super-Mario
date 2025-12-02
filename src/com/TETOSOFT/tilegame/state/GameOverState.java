package com.TETOSOFT.tilegame.state;

import com.TETOSOFT.tilegame.GameEngine;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class GameOverState implements GameState {
    private GameEngine game;
    private long startTime;
    private boolean showRestartMessage = true;
    private long lastBlinkTime = 0;
    private final int finalScore;
    private final String reason;
    
    public GameOverState(GameEngine game, String reason, int finalScore) {
        this.game = game;
        this.reason = reason;
        this.finalScore = finalScore;
        this.startTime = System.currentTimeMillis();
        this.lastBlinkTime = startTime;
        System.out.println("STATE: GameOverState activé - " + reason);
    }
    
    @Override
    public void update(long elapsedTime) {
        long currentTime = System.currentTimeMillis();
        
        // Animation clignotante du message "APPUYER SUR ENTREE"
        if (currentTime - lastBlinkTime > 500) {
            showRestartMessage = !showRestartMessage;
            lastBlinkTime = currentTime;
        }
        
        // Vérification des entrées
        if (game.isEnterPressed()) {
            System.out.println("ENTREE pressé - redémarrage du jeu");
            restartGame();
            return;
        }
        
        if (game.isEscapePressed()) {
            System.out.println("ECHAP pressé - arrêt du jeu");
            game.stop();
            return;
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        // Fond noir
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        
        // Titre GAME OVER
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        String gameOverText = "GAME OVER";
        int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);
        g.drawString(gameOverText, (800 - gameOverWidth) / 2, 150);
        
        // Raison de l'échec
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        int reasonWidth = g.getFontMetrics().stringWidth(reason);
        g.drawString(reason, (800 - reasonWidth) / 2, 220);
        
        // Score final
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        String scoreText = "SCORE FINAL: " + finalScore;
        int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
        g.drawString(scoreText, (800 - scoreWidth) / 2, 300);
        
        // Message de redémarrage (clignotant)
        if (showRestartMessage) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            String restartText = "APPUYER SUR ENTREE POUR RECOMMENCER";
            int restartWidth = g.getFontMetrics().stringWidth(restartText);
            g.drawString(restartText, (800 - restartWidth) / 2, 400);
        }
        
        // Message pour quitter
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        String quitText = "APPUYER SUR ECHAP POUR QUITTER";
        int quitWidth = g.getFontMetrics().stringWidth(quitText);
        g.drawString(quitText, (800 - quitWidth) / 2, 450);
    }
    
    @Override
    public void handleInput() {
        // Gestion d'input séparée si nécessaire
    }
    
  private void restartGame() {
    System.out.println("Redémarrage du jeu depuis GameOver");
    
    // Utiliser la même méthode que MenuStateSimple
    game.restartGame();
    game.setCurrentState(new PlayingState(game));
}
     
}