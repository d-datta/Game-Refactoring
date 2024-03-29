package dungeonmania.goals;

import org.json.JSONObject;

public interface AbstractGoalFactory {
    Goal createGoal(JSONObject jsonGoal, JSONObject config);
}
