package dungeonmania.entities.buildables;

public class BaseStats {
    private int durability;
    private double defense;

    public BaseStats(int durability, double defense) {
        this.durability = durability;
        this.defense = defense;
    }

    public int getDurability() {
        return durability;
    }

    public double getDefense() {
        return defense;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
