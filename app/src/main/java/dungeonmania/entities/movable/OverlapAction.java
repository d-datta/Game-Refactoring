package dungeonmania.entities.movable;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;

public interface OverlapAction {
    void onOverlap(GameMap map, Entity entity);
}
