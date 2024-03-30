package dungeonmania.entities.buildables;


import dungeonmania.battles.BattleStatistics;

public class Shield extends Buildable {

    private final double defence;

    public Shield(int durability, double defence) {
        super(null, new BaseStats(durability, defence));
        this.defence = defence;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, defence, 1, 1));
    }


}
