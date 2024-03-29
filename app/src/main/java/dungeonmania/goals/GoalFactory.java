package dungeonmania.goals;

import org.json.JSONObject;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        String goalType = jsonGoal.getString("goal");
        AbstractGoalFactory factory = getFactory(goalType);
        if (factory != null) {
            return factory.createGoal(jsonGoal, config);
        }
        return null;
    }

    static AbstractGoalFactory getFactory(String goalType) {
        return switch (goalType) {
            case "exit" -> new ExitGoalFactory();
            case "boulders" -> new BouldersGoalFactory();
            case "treasure" -> new TreasureGoalFactory();
            case "AND" -> new ANDGoalFactory();
            case "OR" -> new ORGoalFactory();
            // Add cases for other goal types as needed
            default -> null;
        };
    }
}
