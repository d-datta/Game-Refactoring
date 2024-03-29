package dungeonmania.goals;

import org.json.JSONObject;

public class BouldersGoalFactory implements AbstractGoalFactory{
    @Override
    public Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        return new BoulderGoal();
    }
}
