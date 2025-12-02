package com.TETOSOFT.tilegame.factory;

import com.TETOSOFT.tilegame.sprites.Creature;
import com.TETOSOFT.tilegame.sprites.Fly;
import com.TETOSOFT.tilegame.sprites.Grub;
import com.TETOSOFT.graphics.Animation;

public class EnemyFactory {

    public static Creature createEnemy(String type, Animation left, Animation right,
                                       Animation deadLeft, Animation deadRight) {
        switch (type.toLowerCase()) {
            case "fly":
                System.out.println("EnemyFactory created: fly");
                return new Fly(left, right, deadLeft, deadRight);
            case "grub":
                System.out.println("EnemyFactory created: grub");
                return new Grub(left, right, deadLeft, deadRight);
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
