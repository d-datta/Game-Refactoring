package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

import java.util.List;

public class SpiderMoveStrategy implements MoveStrategy {

    @Override
    public void move(Enemy enemy, Game game) {
        Spider spider=(Spider) enemy;
        Position nextPos = spider.getMovementTrajectory().get(spider.getNextPositionElement());
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && !entities.isEmpty() && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            spider.setForward(!spider.isForward());
            spider.updateNextPosition();
            spider.updateNextPosition();
        }
        nextPos = spider.getMovementTrajectory().get(spider.getNextPositionElement());
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.isEmpty()
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(),spider))) {
            game.getMap().moveTo(spider, nextPos);
            spider.updateNextPosition();
        }
    }
}
