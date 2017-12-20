package maze.drawer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.GameEngine;
import game.GameManager;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import maze.generateAlgorithm.MazeGenerator;
import monsters.Monster;
import objects.CollidableGameObject;
import mine.AbstractMineFactory;
import mine.HealthMine;
import mine.MineEasyFactory;
import objects.CollidableGameObject;
import objects.GameObject;
import pickup.AbstractPickupFactory;
import pickup.PickupEasyFactory;
import wall.DWall;
import wall.NDWall;

public class MazeDrawer {
    private int size;
    private Pane root;
    private double percentageOfDestructableWalls;
    private double percentageOfMines;
    private char[][] maze;
    private Image wall = null;
    private Image destructableWall = null;
    private Image mine = null;
    private Image bigMineRight = null;
    private Image bigMineLeft = null;
    private Image bigMineUp = null;
    private Image bigMineDown = null;
    private Image gift = null;
    private Image ground = null;
    GameEngine gameEngine = GameEngine.getInstanceOf();
    GameManager gameManager = new GameManager(gameEngine, gameEngine.getPlayer());


    public MazeDrawer(Pane root, int size, double percentageOfDestructableWalls, double percentageOfMines) {
        super();
        this.size = size;
        this.root = root;
        this.percentageOfDestructableWalls = percentageOfDestructableWalls;
        this.percentageOfMines = percentageOfMines;
        maze = new char[size][size];
    }

    private boolean positionIsValid(char[][] maze, int randomXPos, int randomYPos) {
        Integer realMazeLength = maze.length;
        if (realMazeLength % 2 == 0) {
            realMazeLength--;
        }
        if (randomXPos < realMazeLength - 1
                && randomYPos < realMazeLength - 1
                && randomXPos != 0
                && randomYPos != 0
                && (randomXPos > 4
                || randomYPos > 4)) {
            return true;
        }
        return false;
    }

    private void initializeDrawables() {
        try {
            ground = new Image(new FileInputStream("ground.jpg"));
            wall = new Image(new FileInputStream("wall.jpg"));
            destructableWall = new Image(new FileInputStream("D_Wall.jpg"));
            mine = new Image(new FileInputStream("mine.png"));
            bigMineRight = new Image(new FileInputStream("bigMineRight.png"));
            bigMineLeft = new Image(new FileInputStream("bigMineLeft.png"));
            bigMineUp = new Image(new FileInputStream("bigMineUp.png"));
            bigMineDown = new Image(new FileInputStream("bigMineDown.png"));
            gift = new Image(new FileInputStream("gift.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateMaze() {
        MazeGenerator mazeGenerator = new MazeGenerator((size - 1) / 2, (size - 1) / 2);
        String printedMaze = mazeGenerator.getMaze();
        int index = 0;
        int row = 0;
        int column = 0;
        try {
            while (true) {
                if (!(printedMaze.charAt(index) == '\n')) {
                    maze[row][column] = printedMaze.charAt(index++);
                    column++;
                } else {
                    column = 0;
                    index++;
                    row++;
                }
            }
        } catch (Exception e) {
            System.out.println("MazeDrawer class: generateMazeFunction => maze converted successfully");
        }
    }

    private void displayDrawables() {
        int cellSize = 70;
        new Monster(GameEngine.getInstanceOf(), 5 + (maze.length - 3) * cellSize, 5 + (maze.length - 3) * cellSize);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
            	if (maze[i][j] != '1' && maze[i][j] != '2') {
	            	ImageView ground = new ImageView();
	            	ground.setX(5 + j * cellSize);
	            	ground.setY(5 + i * cellSize);
	            	ground.setFitWidth(70);
	            	ground.setFitHeight(70);
	            	ground.setPreserveRatio(true);
	            	ground.setImage(this.ground);
	                gameEngine.getPane().getChildren().add(ground);
	            	ground.toBack();
	            }
                if (maze[i][j] == '1') {
                    CollidableGameObject wall = new NDWall(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
                    wall.draw(this.wall);
                } else if (maze[i][j] == '2') {
                	CollidableGameObject wall = new DWall(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
                	wall.draw(this.destructableWall);
                } else if (maze[i][j] == '3') {
                	AbstractMineFactory mineFactory = new MineEasyFactory(this.gameManager);
                	CollidableGameObject mine = (CollidableGameObject) mineFactory.createMine(5 + j * cellSize, 5 + i * cellSize);
                	mine.draw(this.mine);
                } else if (maze[i][j] == '4') {
                	AbstractMineFactory mineFactory = new MineEasyFactory(this.gameManager);
                	CollidableGameObject mine = (CollidableGameObject) mineFactory.createMine(5 + j * cellSize, 5 + i * cellSize);
                	mine.draw(this.mine);   
                } else if (maze[i][j] == '5') {
//                	CollidableGameObject wall = new NDWall(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
//                	wall.draw(this.bigMineRight);
                } else if (maze[i][j] == '6') {
                	AbstractMineFactory mineFactory = new MineEasyFactory(this.gameManager);
                	CollidableGameObject mine = (CollidableGameObject) mineFactory.createMine(5 + j * cellSize, 5 + i * cellSize);
                	mine.draw(this.mine);
	            } else if (maze[i][j] == '7') {
//	            	CollidableGameObject wall = new NDWall(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
//                	wall.draw(this.bigMineDown);
	            } else if (maze[i][j] == '8') {
	            	AbstractPickupFactory mineFactory = new PickupEasyFactory(this.gameManager);
                	CollidableGameObject gift = (CollidableGameObject) mineFactory.createPickup(5 + j * cellSize, 5 + i * cellSize);
                	gift.draw(this.gift);
	            } else if (maze[i][j] == '9') {
	            	new Monster(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
	            }
//                if (i == 2 && j == 1 || i == 1 && j == 2) {
//                	CollidableGameObject wall = new DWall(gameEngine, 5 + j * cellSize, 5 + i * cellSize);
//                	wall.draw(this.destructableWall);
//                }
            }
        }
    }

    private void spreadDestructibleWalls() {
        int numOfDestructableWallsInFreePath = 0;
        for (int i = 0; i < maze.length * maze.length * percentageOfDestructableWalls; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (maze[randomYPos][randomXPos] != '2' && this.positionIsValid(maze, randomXPos, randomYPos)) {
                maze[randomYPos][randomXPos] = '2';
                if (maze[randomYPos][randomXPos] == ' ') {
                    numOfDestructableWallsInFreePath++;
                }
            } else {
                i--;
            }
        }
    }

    private void spreadMines() {
        for (int i = 0; i < maze.length * maze.length * percentageOfMines; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (maze[randomYPos][randomXPos] == ' ' && this.positionIsValid(maze, randomXPos, randomYPos)) {
                maze[randomYPos][randomXPos] = '3';
            } else {
                i--;
            }
        }
    }

    private void spreadGifts() {
        for (int i = 0; i < maze.length * maze.length * percentageOfMines; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (maze[randomYPos][randomXPos] == ' '
                    && this.positionIsValid(maze, randomXPos, randomYPos)) {
                maze[randomYPos][randomXPos] = '8';
            } else {
                i--;
            }
        }
    }
    
    private void spreadMonsters() {
        for (int i = 0; i < maze.length * maze.length * percentageOfMines * 6; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (maze[randomYPos][randomXPos] == ' '
                    && this.positionIsValid(maze, randomXPos, randomYPos)) {
                maze[randomYPos][randomXPos] = '9';
            } else {
                i--;
            }
        }
    }

    private boolean notHorizontalMine(int randomYPos, int randomXPos) {
        if (maze[randomYPos][randomXPos] == ' ' && this.positionIsValid(maze, randomXPos, randomYPos)) {
            return true;
        } else {
            return false;
        }
    }

    private void spreadHorizontalBigMines() {
        for (int i = 0; i < maze.length * maze.length * percentageOfMines; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (this.notHorizontalMine(randomYPos, randomXPos) && this.notHorizontalMine(randomYPos, randomXPos + 1)) {
                maze[randomYPos][randomXPos] = '4';
                maze[randomYPos][randomXPos + 1] = '5';
            } else {
                i--;
            }
        }
    }

    private boolean notVerticalMine(int randomYPos, int randomXPos) {
        if (maze[randomYPos][randomXPos] == ' '
                && this.positionIsValid(maze, randomXPos, randomYPos)) {
            return true;
        } else {
            return false;
        }
    }

    private void spreadVerticalBigMines() {
        for (int i = 0; i < maze.length * maze.length * percentageOfMines; i++) {
            int randomXPos = (int) (Math.random() * maze.length);
            int randomYPos = (int) (Math.random() * maze.length);
            if (this.notVerticalMine(randomYPos, randomXPos) && this.notVerticalMine(randomYPos + 1, randomXPos)) {
                maze[randomYPos][randomXPos] = '6';
                maze[randomYPos + 1][randomXPos] = '7';
            } else {
                i--;
            }
        }
    }

    public void constructMaze() {
        this.initializeDrawables();
        this.generateMaze();
        this.spreadDestructibleWalls();
        this.spreadMines();
        this.spreadHorizontalBigMines();
        this.spreadVerticalBigMines();
        this.spreadGifts();
        this.spreadMonsters();
    }

    public void displayMaze() {
        this.displayDrawables();
    }
}
