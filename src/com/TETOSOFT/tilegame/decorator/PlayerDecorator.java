/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TETOSOFT.tilegame.decorator;

import com.TETOSOFT.tilegame.sprites.Player;

/**
 *
 * @author ASUS
 */
public abstract class PlayerDecorator implements PlayerComponent {
    public PlayerComponent decoratedPlayer;

    public PlayerDecorator(PlayerComponent decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
        this.decoratedPlayer = decoratedPlayer;
    }
    
    @Override
    public void jump(boolean forceJump) {
       decoratedPlayer.jump(forceJump);
    }

    @Override
    public float getMaxSpeed() {
        return decoratedPlayer.getMaxSpeed();
    }

    @Override
    public void setY(float y) {
      decoratedPlayer.setY(y);
    }

    @Override
    public void collideHorizontal() {
      decoratedPlayer.collideHorizontal();
    }

    @Override
    public void collideVertical() {
     decoratedPlayer.collideVertical();
    }
    @Override
    public boolean isAlive() {
   return   decoratedPlayer.isAlive();
    }

    @Override
    public void setVelocityX(float v) {
      decoratedPlayer.setVelocityX(v);
    }
    @Override 
    public void  update(long elapsedTime){
        decoratedPlayer.update(elapsedTime);
    }
     @Override
    public float getX() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float getY() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float getVelocityX() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float getVelocityY() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
