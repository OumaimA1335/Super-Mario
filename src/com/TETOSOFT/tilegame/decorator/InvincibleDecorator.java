package com.TETOSOFT.tilegame.decorator;

import com.TETOSOFT.tilegame.sprites.Creature;

public class InvincibleDecorator extends PlayerDecorator {
    private static final long POWERUP_DURATION = 8000; // 8 secondes
    private long powerUpEndTime;
    private boolean isActive = true;
    private int originalState;
    
    public InvincibleDecorator(PlayerComponent decoratedPlayer) {
        super(decoratedPlayer);
        this.powerUpEndTime = System.currentTimeMillis() + POWERUP_DURATION;
        
        // Sauvegarder l'état original si c'est une Creature
        if (decoratedPlayer instanceof Creature) {
            originalState = ((Creature)decoratedPlayer).getState();
        }
        
        System.out.println("Invincibilité activée pour " + (POWERUP_DURATION/1000) + " secondes!");
    }
    
    @Override
    public boolean isAlive() {
        // Quand invincible, le joueur ne peut pas mourir
        if (isActive) {
            return true; // Toujours vivant pendant l'invincibilité
        }
        return decoratedPlayer.isAlive();
    }
    
    @Override
    public void update(long elapsedTime) {
        // Vérifier l'expiration du power-up
        if (isActive && System.currentTimeMillis() > powerUpEndTime) {
            isActive = false;
            System.out.println("Invincibilité expirée!");
            
            // Restaurer l'état original si nécessaire
            if (decoratedPlayer instanceof Creature) {
                ((Creature)decoratedPlayer).setState(originalState);
            }
        }
        
        // Effet visuel pendant l'invincibilité (clignotement)
        if (isActive && (System.currentTimeMillis() / 200) % 2 == 0) {
            // Ici, vous pourriez changer l'apparence du joueur
            // Par exemple, changer la transparence ou la couleur
        }
        
        decoratedPlayer.update(elapsedTime);
    }
    
    // Méthodes spécifiques à l'invincibilité
    public boolean isInvincible() {
        return isActive;
    }
    
    public void takeDamage() {
        // Pendant l'invincibilité, ignorer les dégâts
        if (!isActive) {
            // Si le décoré a une méthode takeDamage, l'appeler
            // Sinon, gérer autrement
        }
    }
    
    public long getRemainingTime() {
        return Math.max(0, powerUpEndTime - System.currentTimeMillis());
    }

   
}