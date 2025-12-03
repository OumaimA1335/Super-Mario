README.md — Super Mario Java Platformer
Super Mario – Java Platformer

Un jeu de plateforme inspiré de Super Mario, développé en Java, utilisant Swing/Graphics2D pour le rendu, un moteur de jeu simple, et plusieurs patrons de conception (dont Composite, Decorator, Adapter).

Ce projet fait partie d’un travail académique visant à appliquer des patterns avancés dans un environnement de jeu 2D.

1. Fonctionnalités

-Déplacement du joueur (gauche, droite, saut).

-Animation des sprites (joueur, ennemis, objets, tuiles).

-Détection des collisions (carte / ennemis / objets).

-Gestion d’une carte en tuiles (tilemap).

-Système modulaire permettant d’ajouter facilement de nouveaux ennemis ou objets.

-Patron Composite pour la gestion des entités du jeu.

-Pattern Decorator (ex: InvincibleDecorator).

-Adapter pour intégrer n’importe quel Sprite dans le Composite.

2. Technologies utilisées
Domaine	                       Technologie
Langage	                         Java 17
Librairie graphique	             Java2D (Graphics2D, BufferedImage)
Gestion du rendu	             Game loop custom
IDE recommandé	                 NetBeans
Design patterns	                 Composite, State, Decorator,Factory

3. Installation
Prérequis

Java 17+

IntelliJ IDEA ou VSCode ou NetBeans

GitHub installé


4. Exécution du jeu
A. Via IntelliJ IDEA

Ouvrez IntelliJ

Cliquez sur File > Open

Sélectionnez le dossier Super-Mario

Attendez l'import des dépendances

Exécutez la classe :

com.TETOSOFT.test.Game


ou selon votre projet :

com.TETOSOFT.tilegame.GameManager


(Le nom exact dépend de votre version.)

Le jeu se lancera dans une fenêtre Java Swing.

B. Compilation et exécution manuelle
javac -cp src -d out $(find src -name "*.java")
java -cp out com.TETOSOFT.test.Game

5. Structure du projet
Super-Mario/
│
├── src/com/TETOSOFT/
│   ├── graphics/           → gestion des images
│   ├── input/              → gestion clavier
│   ├── tilegame/           → moteur du jeu
│   │   ├── composite/      → Implémentation du patron Composite
│   │   ├── decorator/      → Décorateurs du player
│   │   ├── sprite/         → classes des sprites
│   │   ├── creatures/      → classes des ennemis
│   │   └── map/            → chargement de la tilemap
│   └── test/Game.java      → classe principale
│
└── resources/
    ├── maps/               → niveaux
    └── images/             → sprites

6. Patron Composite implémenté
But

Simplifier la gestion des objets du jeu :

le joueur

les ennemis

les pièces

les objets

tout autre sprite

-> Tous sont ajoutés dans un GameComposite.

Composants:

-GameComponent (interface)

-GameComposite (composite)

-LeafAdapter (adaptateur pour sprites existants)

Cycle de rendu:
rootComposite.update(deltaTime);
rootComposite.render(graphics);


-> Chaque enfant est automatiquement mis à jour et dessiné.

7. Ajouter un nouvel ennemi (exemple)
Enemy e = new Enemy(x, y);
GameComponent wrapped = new LeafAdapter(e);
gameComposite.add(wrapped);


Pas besoin de modifier le moteur du jeu.


9. Contributions

Les contributions sont les bienvenues.
Créez une branche :

git checkout -b feature-nouvelle-fonction

Puis ouvrez un Pull Request.


