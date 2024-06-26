package dungeonmania.entities.collectables.potions;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Collectable;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.movable.OverlapAction;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Potion extends Entity implements InventoryItem, BattleItem, OverlapAction, Collectable {
    private int duration;
    private PotionEffect effect;

    public Potion(Position position, int duration, PotionEffect effect) {
        super(position);
        this.duration = duration;
        this.effect  = effect;
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

    @Override
    public void use(Game game) {
        return;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return effect.applyEffect(origin);
    }

    @Override
    public int getDurability() {
        return 1;
    }
}
