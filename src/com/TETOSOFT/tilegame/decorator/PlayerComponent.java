/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TETOSOFT.tilegame.decorator;

/**
 *
 * @author ASUS
 */
public interface PlayerComponent {

    void jump(boolean forceJump);

    float getMaxSpeed();

    void setY(float y);

    void collideHorizontal();

    void collideVertical();

    boolean isAlive();

    void setVelocityX(float v);

    void update(long elapsedTime);

    float getX();

    float getY();

    float getVelocityX();

    float getVelocityY();
}
