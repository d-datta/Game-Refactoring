package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;

public interface PotionEffect {
    BattleStatistics applyEffect(BattleStatistics origin);
}
