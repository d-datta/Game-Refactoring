package dungeonmania.goals;

import dungeonmania.Game;

public class OrGoal implements Goal {
    private final Goal goal1;
    private final Goal goal2;
    public OrGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return goal1.achieved(game) || goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";

        if (this.achieved(game))
            return "";
        else
            return "(" + goal1.toString(game) + " OR " + goal2.toString(game) + ")";
    }
}
