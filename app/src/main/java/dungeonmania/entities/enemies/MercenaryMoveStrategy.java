package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MercenaryMoveStrategy implements MoveStrategy {

    @Override
    public void move(Enemy enemy, Game game) {
        Mercenary mercenary=(Mercenary) enemy;
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        if (mercenary.isAllied()) {
            nextPos = mercenary.isAdjacentToPlayer ? player.getPreviousDistinctPosition()
                    : map.dijkstraPathFind(mercenary.getPosition(), player.getPosition(), mercenary);
            if (!mercenary.isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), nextPos))
                mercenary.isAdjacentToPlayer = true;
        } else if (map.getPlayer().getEffectivePotion() instanceof InvisibilityPotion) {
            // Move randomly
            Random randGen = new Random();
            List<Position> pos = mercenary.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(mercenary, p)).collect(Collectors.toList());
            if (pos.size() == 0) {
                nextPos = mercenary.getPosition();
                map.moveTo(mercenary, nextPos);
            } else {
                nextPos = pos.get(randGen.nextInt(pos.size()));
                map.moveTo(mercenary, nextPos);
            }
        } else if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(),
                    mercenary.getPosition());

            Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(mercenary.getPosition(), Direction.RIGHT)
                    : Position.translateBy(mercenary.getPosition(), Direction.LEFT);
            Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(mercenary.getPosition(), Direction.UP)
                    : Position.translateBy(mercenary.getPosition(), Direction.DOWN);
            Position offset = mercenary.getPosition();
            if (plrDiff.getY() == 0 && map.canMoveTo(mercenary, moveX))
                offset = moveX;
            else if (plrDiff.getX() == 0 && map.canMoveTo(mercenary, moveY))
                offset = moveY;
            else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
                if (map.canMoveTo(mercenary, moveX))
                    offset = moveX;
                else if (map.canMoveTo(mercenary, moveY))
                    offset = moveY;
                else
                    offset = mercenary.getPosition();
            } else {
                if (map.canMoveTo(mercenary, moveY))
                    offset = moveY;
                else if (map.canMoveTo(mercenary, moveX))
                    offset = moveX;
                else
                    offset = mercenary.getPosition();
            }
            nextPos = offset;
        } else {
            // Follow hostile
            nextPos = map.dijkstraPathFind(mercenary.getPosition(), player.getPosition(), mercenary);
        }
        map.moveTo(mercenary, nextPos);
    }
}
