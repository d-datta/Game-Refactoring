package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public class Bow extends Buildable {

    public Bow(int durability) {
        super(null, new BaseStats(durability, 0));

    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }
}
