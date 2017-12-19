package objects;

import java.util.List;

import game.GameEngine;
import javafx.scene.image.ImageView;

/**
 * Represents a game object that may collide with other objects.
 *
 * @author H
 */
public abstract class CollidableGameObject extends GameObject {

	/**
	 * Representation of the javafx shape used for handling collisions.
	 */
	protected ImageView graphics;

	public CollidableGameObject(GameEngine gameEngine) {
		super(gameEngine);
	}

	public CollidableGameObject(GameEngine gameEngine, double x, double y) {
		super(gameEngine, x, y);
	}

	/**
	 * Checks whether this game object collides with another game object.
	 *
	 * @param other
	 * @return
	 */
	public boolean collidesWith(final GameObject other) {
		return this.getImageView().getBoundsInParent().intersects(other.getImageView().getBoundsInParent());
	}
	
	public boolean isCollided(final ImageView objectColne) {
		List<GameObject> gameObjects = gameEngine.getGameObjects();
		for (GameObject gameObject : gameObjects) {
			if (this != gameObject && this.collides(objectColne, gameObject)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collides(final ImageView objectColne, GameObject other) {
		return objectColne.getBoundsInLocal().intersects(other.getImageView().getBoundsInLocal());
	}

	/**
	 * @return the fxShape
	 */
	public final ImageView getGraphics() {
		return graphics;
	}


}
