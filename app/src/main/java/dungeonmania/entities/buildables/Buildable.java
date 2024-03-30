package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public abstract class Buildable extends Entity implements InventoryItem, BattleItem {
    private final BaseStats stats;

    public Buildable(Position position, BaseStats stats) {
        super(position);
        this.stats = stats;
    }

    public void use(Game game) {
        int durability = stats.getDurability();
        stats.setDurability(durability - 1);
        if (stats.getDurability() <= 0) {
            game.getPlayer().remove(this);
        }
    }

    public int getDurability() {
        return stats.getDurability();
    }
}
