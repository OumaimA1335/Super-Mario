package com.TETOSOFT.tilegame.composite;

public class Level extends GameComposite {
    private String levelName;
    public Level(String name) { this.levelName = name; }

    public String getName() { return levelName; }

    // méthodes utilitaires: spawnEnemy, clearEnemies, findCollisions...
}
