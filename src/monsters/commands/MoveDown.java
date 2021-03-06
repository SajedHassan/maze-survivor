package monsters.commands;

import game.GameEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monsters.Monster;
import objects.ClonedObject;

/**
 * Created by khaledabdelfattah on 12/19/17.
 */
public class MoveDown implements MoveCommand {
    @Override
    public boolean execute(Monster monster) {
        int speed = monster.getSpeed();
        ImageView clone = ClonedObject.getClone();
        clone.setX(monster.getX() + 10);
        clone.setY(monster.getY() + 10 + speed);
        if (!monster.isMonsterCloneCollided(clone)) {
            monster.setY(monster.getY() + speed);
            monster.setAngle(90.0);
            return true;
        }
        return false;
    }
}
