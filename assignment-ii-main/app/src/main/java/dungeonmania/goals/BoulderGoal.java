package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.Switch;

public class BoulderGoal implements Goal {

    @Override
    public boolean achieved(Game game) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(Switch::isActivated);
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";

        return ":boulders";
    }
}
