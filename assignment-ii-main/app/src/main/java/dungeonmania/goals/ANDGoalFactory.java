package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

public class ANDGoalFactory implements AbstractGoalFactory{
    @Override
    public Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals = jsonGoal.getJSONArray("subgoals");
        Goal goal1 = GoalFactory.createGoal(subgoals.getJSONObject(0),config);
        Goal goal2 = GoalFactory.createGoal(subgoals.getJSONObject((1)),config);
        return new AndGoal(goal1, goal2);

    }
}
