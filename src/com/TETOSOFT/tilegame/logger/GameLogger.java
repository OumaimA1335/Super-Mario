/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TETOSOFT.tilegame.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ASUS
 */
/**
 * Utilitaire de logging pour le jeu Super Miro
 */
public class GameLogger {
    
    private static final Logger logger = LogManager.getLogger("PATTERN_LOGGER");
    
    private GameLogger() {
        // Classe utilitaire
    }
    
    // ===== LOGGING SIMPLIFIE =====
       public static void compositeCreated(String name, int size) {
        logger.info("[COMPOSITE] {} créé avec {} éléments", name, size);
    }

    public static void compositeElementAdded(String compositeName, String elementType, int newSize) {
        logger.debug("[COMPOSITE] {}: ajout de {} (total: {})", compositeName, elementType, newSize);
    }

    public static void compositeRendered(String name, int elements) {
        logger.trace("[COMPOSITE] {} rendu avec {} éléments", name, elements);
    }

    // Pattern State
    public static void stateTransition(String from, String to) {
        logger.info("[STATE] Transition: {} -> {}", from, to);
    }

    public static void stateEnter(String stateName) {
        logger.debug("[STATE] Entrée dans l'état: {}", stateName);
    }

    public static void stateExit(String stateName) {
        logger.debug("[STATE] Sortie de l'état: {}", stateName);
    }

    // Pattern Decorator
    public static void decoratorApplied(String decorator, String target) {
        logger.info("[DECORATOR] {} appliqué à {}", decorator, target);
    }

    public static void decoratorRemoved(String decorator, String target) {
        logger.info("[DECORATOR] {} retiré de {}", decorator, target);
    }

    // Pattern Factory (si vous en avez une)
    public static void factoryCreated(String factoryName) {
        logger.info("[FACTORY] {} créée", factoryName);
    }

    public static void factoryProductCreated(String factory, String product) {
        logger.debug("[FACTORY] {} a créé un {}", factory, product);
    }

    public static void gameStarted() {
        logger.info("=== Jeu démarré ===");
       
    }
    
    public static void gameEnded(int score) {
        logger.info("=== Jeu terminé ===");
       logger.info("Final score: " + score);
    }
    
    public static void stateChanged(String from, String to) {
        logger.info("Game: " + from + " -> " + to);
    }
    
    public static void playerStateChanged(String from, String to) {
        logger.info("Player: " + from + " -> " + to);
    }
    
    
    
    public static void powerUpCollected(String type, int total) {
       logger.info("PowerUp collecté: " + type + " (Total: " + total + ")");
    }
    
    public static void playerJump() {
      logger.debug("Player jump action");
    }
    
    public static void playerMove(String direction) {
        logger.trace("Player moving " + direction);
    }
    
    public static void collisionDetected(String entity1, String entity2) {
       logger.debug("Collision entre " + entity1 + " et " + entity2);
    }
    
    public static void mapLoaded(String mapName) {
        logger.info("Map chargée: " + mapName);
    }
    
    public static void warning(String message) {
        logger.warn("ATTENTION: " + message);
    }
    
    public static void error(String message, Throwable throwable) {
        logger.error("ERREUR: " + message, throwable);
    }
    
    public static void lifeChanged(int oldLives, int newLives) {
        logger.info("Vies: " + oldLives + " -> " + newLives);
    }
    
    public static void starsCollected(int stars) {
        logger.info("Étoiles collectées: " + stars);
    }
}