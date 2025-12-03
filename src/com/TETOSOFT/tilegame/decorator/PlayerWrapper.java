package com.TETOSOFT.tilegame.decorator;

import com.TETOSOFT.tilegame.sprites.Player;

public class PlayerWrapper implements PlayerComponent {
    private Player player;
    
    public PlayerWrapper(Player player) {
        this.player = player;
    }
    
    @Override
    public void jump(boolean forceJump) {
        player.jump(forceJump);
    }
    
    @Override
    public float getMaxSpeed() {
        return player.getMaxSpeed();
    }
    
    @Override
    public void setY(float y) {
        player.setY(y);
    }
    
    @Override
    public void collideHorizontal() {
        player.collideHorizontal();
    }
    
    @Override
    public void collideVertical() {
        player.collideVertical();
    }
    
    @Override
    public boolean isAlive() {
        return player.isAlive();
    }
    
    @Override
    public void setVelocityX(float v) {
        player.setVelocityX(v);
    }
    
    @Override
    public void update(long elapsedTime) {
        player.update(elapsedTime);
    }
    
    // Déleguer les autres méthodes nécessaires
    public float getX() { return player.getX(); }
    public float getY() { return player.getY(); }
    public float getVelocityX() { return player.getVelocityX(); }
    public float getVelocityY() { return player.getVelocityY(); }
    
    // Getter pour le Player original
    public Player getPlayer() {
        return player;
    }
}