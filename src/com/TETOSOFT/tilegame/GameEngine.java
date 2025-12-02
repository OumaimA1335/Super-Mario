package com.TETOSOFT.tilegame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import com.TETOSOFT.graphics.*;
import com.TETOSOFT.input.*;
import com.TETOSOFT.test.GameCore;
import com.TETOSOFT.tilegame.sprites.*;
import com.TETOSOFT.tilegame.state.GameOverState;
import com.TETOSOFT.tilegame.state.GameState;
import com.TETOSOFT.tilegame.state.MenuStateSimple;

/**
 * GameManager manages all parts of the game.
 */
public class GameEngine extends GameCore {

    public static void main(String[] args) {
        new GameEngine().run();
    }

    public static final float GRAVITY = 0.002f;
    private GameState currentState;

    private Point pointCache = new Point();
    private TileMap map;
    private MapLoader mapLoader;
    private InputManager inputManager;
    private TileMapDrawer drawer;

    private GameAction moveLeft;
    private GameAction moveRight;
    private GameAction jump;
    private GameAction exit;
    private GameAction enterAction;

    private GameAction pauseAction;
    private int collectedStars = 0;
    private int numLives = 3;

    public void init() {
        super.init();

        // set up input manager
        initInput();

        // start resource manager
        mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());

        // load resources
        drawer = new TileMapDrawer();
        drawer.setBackground(mapLoader.loadImage("background.jpg"));

        // load first map
        map = mapLoader.loadNextMap();
        currentState = new MenuStateSimple(this);
    }

    /**
     * Closes any resurces used by the GameManager.
     */
    public void stop() {
        super.stop();

    }

    private void initInput() {
        moveLeft = new GameAction("moveLeft");
        moveRight = new GameAction("moveRight");
        jump = new GameAction("jump", GameAction.DETECT_INITAL_PRESS_ONLY);
        exit = new GameAction("exit", GameAction.DETECT_INITAL_PRESS_ONLY);
        enterAction = new GameAction("enter", GameAction.DETECT_INITAL_PRESS_ONLY);
        inputManager = new InputManager(screen.getFullScreenWindow());
        inputManager.setCursor(InputManager.INVISIBLE_CURSOR);

        inputManager.mapToKey(moveLeft, KeyEvent.VK_LEFT);
        inputManager.mapToKey(moveRight, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(jump, KeyEvent.VK_SPACE);
        inputManager.mapToKey(enterAction, KeyEvent.VK_ENTER);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }

    private void checkInput(long elapsedTime) {
        if (exit.isPressed()) {
    setCurrentState(new MenuStateSimple(this));
        }

        Player player = (Player) map.getPlayer();
        if (player.isAlive()) {
            float velocityX = 0;
            if (moveLeft.isPressed()) {
                velocityX -= player.getMaxSpeed();
            }
            if (moveRight.isPressed()) {
                velocityX += player.getMaxSpeed();
            }
            if (jump.isPressed()) {
                player.jump(false);
            }
            player.setVelocityX(velocityX);
        }

        // Pas besoin de gérer enterAction ici car c'est géré par le GameState
    }

    public void draw(Graphics2D g) {

        // Délégue le dessin à l'état courant
        if (currentState != null) {
            currentState.draw(g);
        } else {
            // Dessin par défaut (pour compatibilité)
            drawer.draw(g, map, screen.getWidth(), screen.getHeight());
            g.setColor(Color.WHITE);
            g.drawString("Press ESC for EXIT.", 10.0f, 20.0f);
            g.setColor(Color.GREEN);
            g.drawString("Coins: " + collectedStars, 300.0f, 20.0f);
            g.setColor(Color.YELLOW);
            g.drawString("Lives: " + (numLives), 500.0f, 20.0f);
            g.setColor(Color.WHITE);
            g.drawString("Home: " + mapLoader.currentMap, 700.0f, 20.0f);
        }

    }

    /**
     * Gets the current map.
     */
    public TileMap getMap() {
        return map;
    }

    /**
     * Gets the tile that a Sprites collides with. Only the Sprite's X or Y
     * should be changed, not both. Returns null if no collision is detected.
     */
    public Point getTileCollision(Sprite sprite, float newX, float newY) {
        float fromX = Math.min(sprite.getX(), newX);
        float fromY = Math.min(sprite.getY(), newY);
        float toX = Math.max(sprite.getX(), newX);
        float toY = Math.max(sprite.getY(), newY);

        // get the tile locations
        int fromTileX = TileMapDrawer.pixelsToTiles(fromX);
        int fromTileY = TileMapDrawer.pixelsToTiles(fromY);
        int toTileX = TileMapDrawer.pixelsToTiles(
                toX + sprite.getWidth() - 1);
        int toTileY = TileMapDrawer.pixelsToTiles(
                toY + sprite.getHeight() - 1);

        // check each tile for a collision
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {
                if (x < 0 || x >= map.getWidth()
                        || map.getTile(x, y) != null) {
                    // collision found, return the tile
                    pointCache.setLocation(x, y);
                    return pointCache;
                }
            }
        }

        // no collision found
        return null;
    }

    /**
     * Checks if two Sprites collide with one another. Returns false if the two
     * Sprites are the same. Returns false if one of the Sprites is a Creature
     * that is not alive.
     */
    public boolean isCollision(Sprite s1, Sprite s2) {
        // if the Sprites are the same, return false
        if (s1 == s2) {
            return false;
        }

        // if one of the Sprites is a dead Creature, return false
        if (s1 instanceof Creature && !((Creature) s1).isAlive()) {
            return false;
        }
        if (s2 instanceof Creature && !((Creature) s2).isAlive()) {
            return false;
        }

        // get the pixel location of the Sprites
        int s1x = Math.round(s1.getX());
        int s1y = Math.round(s1.getY());
        int s2x = Math.round(s2.getX());
        int s2y = Math.round(s2.getY());

        // check if the two sprites' boundaries intersect
        return (s1x < s2x + s2.getWidth()
                && s2x < s1x + s1.getWidth()
                && s1y < s2y + s2.getHeight()
                && s2y < s1y + s1.getHeight());
    }

    /**
     * Gets the Sprite that collides with the specified Sprite, or null if no
     * Sprite collides with the specified Sprite.
     */
    public Sprite getSpriteCollision(Sprite sprite) {

        // run through the list of Sprites
        Iterator i = map.getSprites();
        while (i.hasNext()) {
            Sprite otherSprite = (Sprite) i.next();
            if (isCollision(sprite, otherSprite)) {
                // collision found, return the Sprite
                return otherSprite;
            }
        }

        // no collision found
        return null;
    }

    public void update(long elapsedTime) {
        // Délégue la mise à jour à l'état courant
        if (currentState != null) {
            currentState.update(elapsedTime);
        } else {
            // Logique de jeu originale (maintenue pour compatibilité)
            updateGameLogic(elapsedTime);
        }
    }

    /**
     * Updates Animation, position, and velocity of all Sprites in the current
     * map.
     */
    public void updateGameLogic(long elapsedTime) {
        Creature player = (Creature) map.getPlayer();

        // player is dead! start map over
        if (player.getState() == Creature.STATE_DEAD) {
            map = mapLoader.reloadMap();
            return;
        }

        // get keyboard/mouse input
        checkInput(elapsedTime);

        // update player
        updateCreature(player, elapsedTime);
        player.update(elapsedTime);

        // update other sprites
        Iterator i = map.getSprites();
        while (i.hasNext()) {
            Sprite sprite = (Sprite) i.next();
            if (sprite instanceof Creature) {
                Creature creature = (Creature) sprite;
                if (creature.getState() == Creature.STATE_DEAD) {
                    i.remove();
                } else {
                    updateCreature(creature, elapsedTime);
                }
            }
            // normal update
            sprite.update(elapsedTime);
        }
    }

    /**
     * Updates the creature, applying gravity for creatures that aren't flying,
     * and checks collisions.
     */
    private void updateCreature(Creature creature,
            long elapsedTime) {

        // apply gravity
        if (!creature.isFlying()) {
            creature.setVelocityY(creature.getVelocityY()
                    + GRAVITY * elapsedTime);
        }

        // change x
        float dx = creature.getVelocityX();
        float oldX = creature.getX();
        float newX = oldX + dx * elapsedTime;
        Point tile
                = getTileCollision(creature, newX, creature.getY());
        if (tile == null) {
            creature.setX(newX);
        } else {
            // line up with the tile boundary
            if (dx > 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x)
                        - creature.getWidth());
            } else if (dx < 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x + 1));
            }
            creature.collideHorizontal();
        }
        if (creature instanceof Player) {
            checkPlayerCollision((Player) creature, false);
        }

        // change y
        float dy = creature.getVelocityY();
        float oldY = creature.getY();
        float newY = oldY + dy * elapsedTime;
        tile = getTileCollision(creature, creature.getX(), newY);
        if (tile == null) {
            creature.setY(newY);
        } else {
            // line up with the tile boundary
            if (dy > 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y)
                        - creature.getHeight());
            } else if (dy < 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y + 1));
            }
            creature.collideVertical();
        }
        if (creature instanceof Player) {
            boolean canKill = (oldY < creature.getY());
            checkPlayerCollision((Player) creature, canKill);
        }

    }

    /**
     * Checks for Player collision with other Sprites. If canKill is true,
     * collisions with Creatures will kill them.
     */
    public void checkPlayerCollision(Player player,
            boolean canKill) {
        if (!player.isAlive()) {
            return;
        }

        // check for player collision with other sprites
        Sprite collisionSprite = getSpriteCollision(player);
        if (collisionSprite instanceof PowerUp) {
            acquirePowerUp((PowerUp) collisionSprite);
        } else if (collisionSprite instanceof Creature) {
            Creature badguy = (Creature) collisionSprite;
            if (canKill) {
                // kill the badguy and make player bounce
                badguy.setState(Creature.STATE_DYING);
                player.setY(badguy.getY() - player.getHeight());
                player.jump(true);
            } else {
                // player dies!
                player.setState(Creature.STATE_DYING);
                numLives--;
                if (numLives == 0) {
                    int finalScore = collectedStars;
                    String reason = "VOUS AVEZ PERDU TOUTES VOS VIES!";
                    setCurrentState(new GameOverState(this, reason, finalScore));
                    return;
                }
            }
        }
    }

    /**
     * Gives the player the speicifed power up and removes it from the map.
     */
    public void acquirePowerUp(PowerUp powerUp) {
        // remove it from the map
        map.removeSprite(powerUp);

        if (powerUp instanceof PowerUp.Star) {
            // do something here, like give the player points
            collectedStars++;
            if (collectedStars == 100) {
                numLives++;
                collectedStars = 0;
            }

        } else if (powerUp instanceof PowerUp.Music) {
            // change the music

        } else if (powerUp instanceof PowerUp.Goal) {
            // advance to next map      

            map = mapLoader.loadNextMap();

        }
    }

    public boolean isKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                if (enterAction != null) {
                    boolean pressed = enterAction.isPressed();
                    // Réinitialise l'action pour éviter les pressions multiples
                    if (pressed) {
                        enterAction.reset();
                    }
                    return pressed;
                }
                return false;
            case KeyEvent.VK_ESCAPE:
                return exit != null && exit.isPressed();
            case KeyEvent.VK_LEFT:
                return moveLeft != null && moveLeft.isPressed();
            case KeyEvent.VK_RIGHT:
                return moveRight != null && moveRight.isPressed();
            case KeyEvent.VK_SPACE:
                return jump != null && jump.isPressed();
            case KeyEvent.VK_P:
                return pauseAction != null && pauseAction.isPressed();
            default:
                return false;
        }
    }

    public boolean isEnterPressed() {

        return enterAction != null && enterAction.isPressed();
    }

    public boolean isEscapePressed() {
        return exit != null && exit.isPressed();
    }

    public void setCurrentState(GameState newState) {
        if (currentState != null) {
            System.out.println("STATE CHANGE: " + currentState.getClass().getSimpleName()
                    + " -> " + newState.getClass().getSimpleName());
        }
        currentState = newState;
    }

    public TileMapDrawer getDrawer() {
        return drawer;
    }

    public ScreenManager getScreen() {
        return screen;
    }

    public MapLoader getMapLoader() {
        return mapLoader;
    }

    public int getCollectedStars() {
        return collectedStars;
    }

    public int getNumLives() {
        return numLives;
    }

    public void resetGameStats() {
        collectedStars = 0;
        numLives = 3; // ou le nombre initial de vies
        System.out.println("Statistiques réinitialisées");
    }

// Getter pour map (à ajouter si pas déjà présent)
    public void setMap(TileMap newMap) {
        this.map = newMap;
    }

    public void restartGame() {
        // Réinitialiser les statistiques
        resetGameStats();
        mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());

        // load resources
        drawer = new TileMapDrawer();
        drawer.setBackground(mapLoader.loadImage("background.jpg"));

        // load first map
        map = mapLoader.loadNextMap();
        // Réinitialiser la carte en utilisant mapLoader
        if (map != null) {

            System.out.println("Carte rechargée: " + map);
        } else {
            System.out.println("ERREUR: mapLoader est null");
        }
    }

}
