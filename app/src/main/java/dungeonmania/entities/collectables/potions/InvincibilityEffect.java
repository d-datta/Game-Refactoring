package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;

public class InvincibilityEffect implements PotionEffect {
    @Override
    public BattleStatistics applyEffect(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, true, true));
    }
}
