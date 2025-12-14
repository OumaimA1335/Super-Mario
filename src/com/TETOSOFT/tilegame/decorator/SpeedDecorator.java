package com.TETOSOFT.tilegame.decorator;

import com.TETOSOFT.tilegame.logger.GameLogger;

public class SpeedDecorator extends PlayerDecorator {
    private static final float SPEED_MULTIPLIER = 3.5f;
    private static final long DURATION = 10000; // 10 secondes
    private long powerUpEndTime;
    private boolean isActive = true;
    
    public SpeedDecorator(PlayerComponent decoratedPlayer) {
        super(decoratedPlayer);
        this.powerUpEndTime = System.currentTimeMillis() + DURATION;
        System.out.println("Speed boost activé pour " + (DURATION/1000) + " secondes!");
    }
    
    @Override
    public float getMaxSpeed() {
        if (isActive && System.currentTimeMillis() > powerUpEndTime) {
            isActive = false;
            System.out.println("Speed boost expiré!");
        }
        
        return isActive ? 
               decoratedPlayer.getMaxSpeed() * SPEED_MULTIPLIER :
               decoratedPlayer.getMaxSpeed();
    }
    
    @Override
    public void update(long elapsedTime) {
        if (isActive && System.currentTimeMillis() > powerUpEndTime) {
            isActive = false;
            GameLogger.decoratorRemoved("Speed", "Player");
            System.out.println("Speed boost expiré!");
        }
        decoratedPlayer.update(elapsedTime);
    }
    
    public boolean isSpeedBoostActive() {
        return isActive;
    }
}