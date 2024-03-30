package dungeonmania.entities.collectables;


import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;

public interface Collectable {
    public void onPickup(Player player, GameMap map);
}
