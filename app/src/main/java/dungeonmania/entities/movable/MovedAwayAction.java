package dungeonmania.entities.movable;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;

public interface MovedAwayAction {
    void onMovedAway(GameMap map, Entity entity);
}
