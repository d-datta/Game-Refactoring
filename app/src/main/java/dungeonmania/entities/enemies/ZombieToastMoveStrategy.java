package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import java.util.List;
import java.util.stream.Collectors;

public class ZombieToastMoveStrategy implements MoveStrategy {
    @Override
    public void move(Enemy enemy, Game game) {
        ZombieToast zombietoast = (ZombieToast) enemy;
        Position nextPos;
        GameMap map = game.getMap();
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            Position plrDiff = Position.calculatePositionBetween(
                    map.getPlayer().getPosition(),
                    zombietoast.getPosition()
            );

            Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(zombietoast.getPosition(), Direction.RIGHT)
                    : Position.translateBy(zombietoast.getPosition(), Direction.LEFT);
            Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(zombietoast.getPosition(), Direction.UP)
                    : Position.translateBy(zombietoast.getPosition(), Direction.DOWN);
            Position offset = zombietoast.getPosition();
            if (plrDiff.getY() == 0 && map.canMoveTo(zombietoast, moveX))
                offset = moveX;
            else if (plrDiff.getX() == 0 && map.canMoveTo(zombietoast, moveY))
                offset = moveY;
            else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
                if (map.canMoveTo(zombietoast, moveX))
                    offset = moveX;
                else if (map.canMoveTo(zombietoast, moveY))
                    offset = moveY;
                else
                    offset = zombietoast.getPosition();
            } else {
                if (map.canMoveTo(zombietoast, moveY))
                    offset = moveY;
                else if (map.canMoveTo(zombietoast, moveX))
                    offset = moveX;
                else
                    offset = zombietoast.getPosition();
            }
            nextPos = offset;
        } else {
            List<Position> pos = zombietoast.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(zombietoast, p)).collect(Collectors.toList());
            if (pos.isEmpty()) {
                nextPos = zombietoast.getPosition();
            } else {
                nextPos = pos.get(zombietoast.getRandGen().nextInt(pos.size()));
            }
        }
        game.getMap().moveTo(zombietoast, nextPos);
    }
}
