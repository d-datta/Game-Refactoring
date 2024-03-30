package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.movable.OverlapAction;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Key extends Entity implements InventoryItem, OverlapAction, Collectable {
    private int number;

    public Key(Position position, int number) {
        super(position);
        this.number = number;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            onPickup((Player) entity, map);
        }
    }

    @Override
    public void onPickup(Player player, GameMap map) {
        if (player.pickUp(this)) {
            map.destroyEntity(this);
        }
    }

    public int getnumber() {
        return number;
    }

}
