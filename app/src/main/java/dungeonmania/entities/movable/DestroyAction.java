package dungeonmania.entities.movable;

import dungeonmania.map.GameMap;

public interface DestroyAction {
    void onDestroy(GameMap gameMap);
}
