package dungeonmania.entities.enemies;

import dungeonmania.Game;

public interface MoveStrategy {
    void move(Enemy enemy, Game game);
}
